package com.progress.tracker.topicdao;

public class TopicNotCreatedException extends Exception 
{

    private static final long serialVersionUID = 1L;

    public TopicNotCreatedException(Topic topic) 
    {
        super("Topic with the following values could not be created: " + topic);
    }

    public TopicNotCreatedException(String message, Throwable cause) 
    {
        super(message, cause);
    }
}
