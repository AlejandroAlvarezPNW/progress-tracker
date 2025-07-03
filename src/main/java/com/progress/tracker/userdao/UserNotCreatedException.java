package com.progress.tracker.userdao;

public class UserNotCreatedException extends Exception 
{

	private static final long serialVersionUID = 1L;

	public UserNotCreatedException(String user) 
    {
		super("User with the following values could not be created: " + user);
	}
	
    public UserNotCreatedException(String message, Throwable cause) 
    {
        super(message, cause);
    }

    public UserNotCreatedException(User user) 
    {
        //TODO Auto-generated constructor stub
    }

}

