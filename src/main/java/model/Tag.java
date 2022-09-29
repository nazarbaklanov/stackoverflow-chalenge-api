package model;

public class Tag {
    private Long userId;
    private String name;

    public Tag() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Tag{"
                + "user_id=" + userId
                + ", name='" + name + '\''
                + '}';
    }
}
