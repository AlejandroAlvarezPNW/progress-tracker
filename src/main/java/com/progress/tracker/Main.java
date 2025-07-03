package com.progress.tracker;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.progress.tracker.userdao.User;
import com.progress.tracker.userdao.UserDao;
import com.progress.tracker.userdao.UserDaoImpl;

/**
 * Hello world!
 *
 */
public class Main 
{
    public static void main(String[] args) 
    {
        UserDaoImpl userDao = new UserDaoImpl();
        try 
        {
            userDao.establishConnection(); // Establish connection first!
            List<User>users = userDao.getAllUsers();

            System.out.println("All Users:");
            for (User user : users)
            {
                System.out.println("ID: " + user.getuserId() + ", Name: " + user.getName() + ", Username: " + user.getuserName());
            }
        } 
        catch(Exception e) 
        {
            e.printStackTrace();
        }
}
}
