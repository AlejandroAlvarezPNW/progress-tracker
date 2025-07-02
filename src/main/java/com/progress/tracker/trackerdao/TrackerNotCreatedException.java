package main.java.com.progress.tracker.trackerdao;

import main.java.com.progress.tracker.models.Tracker;

public class TrackerNotCreatedException extends Exception 
{

    private static final long serialVersionUID = 1L;

    public TrackerNotCreatedException(Tracker tracker) 
    {
        super("Tracker with the following values could not be created: " + tracker);
    }

    public TrackerNotCreatedException(String message) 
    {
        super(message);
    }

    public TrackerNotCreatedException(String message, Throwable cause) 
    {
        super(message, cause);
    }
}
