package service;

import java.io.IOException;
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
    private static final int LIMIT_PAGES = 10;
    private static final int LIMIT_PAGESIZE = 25;
    private static final int TIME_THREAD_SLEEP = 3000;

    public void extract() throws InterruptedException {
        JsonReader reader = new JsonReaderImpl();
        JSONArray jsonArray;
        for (int i = 1; i < LIMIT_PAGES; i++) {
            String pageNumber = String.valueOf(i);
            try {
                jsonArray = reader.readJsonFromUrl("https://api.stackexchange.com/2.3/users?"
                        + "page="
                        + pageNumber
                        + "&pagesize="
                        + LIMIT_PAGESIZE
                        + "&order=desc&min=223&sort=reputation&site=stackoverflow"
                        + "&filter=!gkOQYbF*7bn)h4UFlp5fK-3pYYE48hwZWPg"
                        + "&key=eJdtWWka4oixta5gH0rW3Q((");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Parser<Item> parser = new ParserItemImpl();
            List<Item> itemList = parser.parse(jsonArray);
            Thread.sleep(TIME_THREAD_SLEEP);

            if (!itemList.isEmpty()) {
                String usersId = itemList.stream()
                        .map(Item::getUserId)
                        .map(String::valueOf)
                        .collect(Collectors.joining(";"));
                String path = "https://api.stackexchange.com/2.3/users/"
                        + usersId
                        + "/tags?page=1&pagesize=100&order=desc&sort=popular&site=stackoverflow"
                        + "&filter=!*MKI3RJrq_UbXSvj"
                        + "&key=eJdtWWka4oixta5gH0rW3Q((";
                JsonReader readerTag = new JsonReaderImpl();
                JSONArray jsonArrayTags;
                try {
                    jsonArrayTags = readerTag.readJsonFromUrl(path);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Parser<Tag> parserTag = new ParserUserTagImpl();
                List<Tag> tagList = parserTag.parse(jsonArrayTags);

                ReportBuilder report = new ReportBuilder();
                List<User> reportUsers = report.getUser(itemList, tagList);
                report.printResultToConsole(reportUsers);
            }
            Thread.sleep(TIME_THREAD_SLEEP);
        }
    }
}
