package com.progress.tracker.userdao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/* User Dao Interface */


public interface UserDao 
{
    // needed for later so we make sure that the connection manager gets called
    public void establishConnection() throws ClassNotFoundException, SQLException;
    
    // as well, this method will help with closing the connection
	public void closeConnection() throws SQLException ;

    // Add this method to the UserDao interface
    void addUser(User user) throws SQLException, UserNotCreatedException;

    //Made for using getallUsers() in Main.java
    void addUserOL(User user) throws SQLException, UserNotCreatedException;

    //Get User with Id
    Optional<User>getUserById(int id) throws SQLException;

    //Get all users
    List<User> getAllUsers() throws SQLException;

    //Remove any unwated topic
    public boolean deleteUser(int id) throws SQLException;
}
