package service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import model.Item;
import model.Tag;
import model.User;
import org.json.JSONArray;
import service.impl.JsonReaderImpl;
import service.impl.ParserItemImpl;
import service.impl.ParserUserTagImpl;

public class Extracter {
    private static final int LIMIT_PAGES = 1000;
    private static final int LIMIT_PAGE_SIZE = 100;
    private static final int TIME_THREAD_SLEEP = 2000;
    private static final int MIN_REPUTATION_POINTS = 223;
    private static final String KEY = "eJdtWWka4oixta5gH0rW3Q((";
    private static final String USERS_FILTER = "!gkOQYbF*7bn)h4UFlp5fK-3pYYE48hwZWPg";
    private static final String TAGS_FILTER = "!*MKI3RJrq_UbXSvj";

    public void extract() throws InterruptedException {
        JsonReader reader = new JsonReaderImpl();
        JSONArray jsonArray;
        for (int i = 1; i < LIMIT_PAGES; i++) {
            try {
                jsonArray = reader.readJsonFromUrl("https://api.stackexchange.com/2.3/users?"
                        + "page=" + i
                        + "&pagesize=" + LIMIT_PAGE_SIZE
                        + "&order=desc&min=" + MIN_REPUTATION_POINTS
                        + "&sort=reputation&site=stackoverflow"
                        + "&filter=" + USERS_FILTER
                        + "&key=" + KEY);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Parser<Item> parser = new ParserItemImpl();
            List<Item> itemList = parser.parse(jsonArray);
            Thread.sleep(TIME_THREAD_SLEEP);

            if (!itemList.isEmpty()) {
                List<Tag> usersTagList = new ArrayList<>();
                String usersId = itemList.stream()
                        .map(Item::getUserId)
                        .map(String::valueOf)
                        .collect(Collectors.joining(";"));
                for (int j = 1; j < LIMIT_PAGES; j++) {
                    String path = "https://api.stackexchange.com/2.3/users/" + usersId
                            + "/tags?page=" + j
                            + "&pagesize=100&order=desc&sort=popular&site=stackoverflow"
                            + "&filter=" + TAGS_FILTER
                            + "&key=" + KEY;
                    JsonReader readerTag = new JsonReaderImpl();
                    JSONArray jsonArrayTags;
                    try {
                        jsonArrayTags = readerTag.readJsonFromUrl(path);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    if (jsonArrayTags.isEmpty()) {
                        break;
                    }
                    Parser<Tag> parserTag = new ParserUserTagImpl();
                    usersTagList.addAll(parserTag.parse(jsonArrayTags));
                    Thread.sleep(TIME_THREAD_SLEEP);
                }
                ReportBuilder report = new ReportBuilder();
                List<User> reportUsers = report.getUser(itemList, usersTagList);
                report.printResultToConsole(reportUsers);
            }
        }
    }
}
