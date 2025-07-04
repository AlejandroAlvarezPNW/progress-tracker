package com.progress.tracker.trackerdao;

public class Tracker 
{
    private int tracker_id;
    private int user_id;
    private int topic_id;
    private String status;
    private String progress;

    //Constructor
    public Tracker(int tracker_id, int user_id, int topic_id, String status, String progress)
    {
        this.tracker_id = tracker_id;
        this.user_id = user_id;
        this.topic_id = topic_id;
        this.status = status;
        this.progress = progress;
    }

    public Tracker(int user_id, int topic_id, String status, String progress)
    {
        this.user_id = user_id;
        this.topic_id = topic_id;
        this.status = status;
        this.progress = progress;
    }

    //Gettters and Setters
    public int getTrackerId()
    {
        return tracker_id;
    }

    public void setTrackerId(int tracker_id)
    {
        this.tracker_id = tracker_id;
    }

    public int getUserId()
    {
        return user_id;
    }

    public void setUserId(int user_id)
    {
        this.user_id = user_id;
    }

    public int getTopicId()
    {
        return topic_id;
    }

    public void setTopicId(int topic_id)
    {
        this.topic_id = topic_id;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getProgress()
    {
        return progress;
    }

    public void setProgress(String progress)
    {
        this.progress = progress;
    }

    // ToString Method.
    @Override
    public String toString()
    {
        return "Tracker [tracker_id=" + tracker_id + ", user_id=" + user_id + ", topic_id=" + topic_id
            + ", status=" + status + ", progress=" + progress + "]";
    }
}
