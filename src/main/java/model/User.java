package model;

import java.util.List;

public class User {
    private String name;
    private String location;
    private Long answerCount;
    private Long questionCount;
    private List<String> tags;
    private String linkToProfile;
    private String linkToAvatar;

    public User() {
    }

    public User(String name,
                String location,
                Long answerCount,
                Long questionCount,
                List<String> tags,
                String linkToProfile,
                String linkToAvatar) {
        this.name = name;
        this.location = location;
        this.answerCount = answerCount;
        this.questionCount = questionCount;
        this.tags = tags;
        this.linkToProfile = linkToProfile;
        this.linkToAvatar = linkToAvatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Long getAnswerCount() {
        return answerCount;
    }

    public void setAnswerCount(Long answerCount) {
        this.answerCount = answerCount;
    }

    public Long getQuestionCount() {
        return questionCount;
    }

    public void setQuestionCount(Long questionCount) {
        this.questionCount = questionCount;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getLinkToProfile() {
        return linkToProfile;
    }

    public void setLinkToProfile(String linkToProfile) {
        this.linkToProfile = linkToProfile;
    }

    public String getLinkToAvatar() {
        return linkToAvatar;
    }

    public void setLinkToAvatar(String linkToAvatar) {
        this.linkToAvatar = linkToAvatar;
    }

    @Override
    public String toString() {
        return "User{"
                + "name='" + name + '\''
                + ", location='" + location + '\''
                + ", answerCount=" + answerCount
                + ", questionCount=" + questionCount
                + ", tags=" + tags
                + ", linkToProfile='" + linkToProfile + '\''
                + ", linkToAvatar='" + linkToAvatar + '\''
                + '}';
    }
}
