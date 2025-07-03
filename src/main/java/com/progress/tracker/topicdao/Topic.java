package com.progress.tracker.topicdao;

public class Topic {
    private int topicId;
    private String name;
    private String type;
    private int totalUnits;

    public Topic(int topicId, String name, String type, int totalUnits) {
        this.topicId = topicId;
        this.name = name;
        this.type = type;
        this.totalUnits = totalUnits;
    }

    // Getters and setters
    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getTotalUnits() {
        return totalUnits;
    }

    public void setTotalUnits(int totalUnits) {
        this.totalUnits = totalUnits;
    }

    @Override
    public String toString() {
        return "Topic [topicId=" + topicId + ", name=" + name + ", type=" + type
                + ", totalUnits=" + totalUnits + "]";
    }
}
