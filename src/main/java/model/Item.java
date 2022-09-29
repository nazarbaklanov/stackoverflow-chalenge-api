package model;

public class Item {
    private Long answerCount;
    private Long questionCount;
    private Long accountId;
    private Long reputation;
    private Long userId;
    private String location;
    private String profileImage;
    private String link;
    private String displayName;

    public Item() {
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

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getReputation() {
        return reputation;
    }

    public void setReputation(Long reputation) {
        this.reputation = reputation;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return "Item{"
                + "answer_count=" + answerCount
                + ", question_count=" + questionCount
                + ", account_id=" + accountId
                + ", reputation=" + reputation
                + ", user_id=" + userId
                + ", location='" + location + '\''
                + ", profile_image='" + profileImage + '\''
                + ", link='" + link + '\''
                + ", display_name='" + displayName + '\''
                + '}';
    }
}
