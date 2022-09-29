package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import model.Item;
import model.Tag;
import model.User;

public class ReportBuilder {
    private static final String NAME = "User name: ";
    private static final String LOCATION = "Location: ";
    private static final String ANSWERS = "Answer count: ";
    private static final String QUESTIONS = "Question count: ";
    private static final String TAGS = "Tags: ";
    private static final String LINK_TO_PROFILE = "Link to profile: ";
    private static final String LINK_TO_AVATAR = "Link to avatar: ";
    private static final String DELIMITER_FIELD = "; ";
    private static final String DELIMITER_TAGS = ", ";

    public ReportBuilder() {
    }

    public List<User> getUser(List<Item> items, List<Tag> tags) {
        List<User> userList = new ArrayList<>();
        for (Item item : items) {
            User user = new User();
            if (tags.stream().noneMatch(t -> t.getUserId().equals(item.getUserId()))) {
                continue;
            }
            user.setName(item.getDisplayName());
            user.setLocation(item.getLocation());
            user.setAnswerCount(item.getAnswerCount());
            user.setQuestionCount(item.getQuestionCount());
            user.setLinkToProfile(item.getLink());
            user.setLinkToAvatar(item.getProfileImage());
            List<String> tagList = new ArrayList<>();
            for (Tag tag : tags) {
                if (Objects.equals(tag.getUserId(), item.getUserId())) {
                    tagList.add(tag.getName());
                }
            }
            user.setTags(tagList);
            userList.add(user);
        }
        return userList;
    }

    public void printResultToConsole(List<User> users) {
        for (User user : users) {
            System.out.println(NAME + user.getName() + DELIMITER_FIELD
                    + LOCATION + user.getLocation() + DELIMITER_FIELD
                    + ANSWERS + user.getAnswerCount() + DELIMITER_FIELD
                    + QUESTIONS + user.getQuestionCount() + DELIMITER_FIELD
                    + TAGS + String.join(DELIMITER_TAGS, user.getTags()) + DELIMITER_FIELD
                    + LINK_TO_PROFILE + user.getLinkToProfile() + DELIMITER_FIELD
                    + LINK_TO_AVATAR + user.getLinkToAvatar());
        }
    }
}
