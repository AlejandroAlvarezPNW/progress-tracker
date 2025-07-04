package com.progress.tracker.userdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.progress.tracker.connection.ConnectionManager;

/* User Dao Class */

public class UserDaoImpl implements UserDao
{
    private Connection connection = null;
    
    @Override
	public void establishConnection() throws ClassNotFoundException, SQLException 
	{
		
		if(connection == null) 
		{
			connection = ConnectionManager.getConnection();
		}
	}

    @Override
	public void closeConnection() throws SQLException 
	{
		connection.close();
	}

    // Core user operations
    @Override
    public void addUser(User user) throws SQLException, UserNotCreatedException
    {
        String sql = "INSERT INTO User (name, username, password) Values(?, ?, ?)";
        try(PreparedStatement pstmt = connection.prepareStatement(sql))
        {
            //pstmt.setInt(1, user.getuserId());
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getuserName());
            pstmt.setString(3, user.getPassword());

            int rowsMade = pstmt.executeUpdate();
            if(rowsMade == 0)
            {
                throw new UserNotCreatedException(user);
            }
        }
        catch(SQLException e)
        {
            throw new UserNotCreatedException("SQL error while creating user: " + user);
        }
    }

    // Core user operations
    @Override
    public void addUserOL(User user) throws SQLException, UserNotCreatedException
    {
        String sql = "INSERT INTO User (name, username, password) Values(?, ?, ?)";
        try(PreparedStatement pstmt = connection.prepareStatement(sql))
        {
            pstmt.setInt(1, user.getuserId());
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getuserName());
            pstmt.setString(3, user.getPassword());

            int rowsMade = pstmt.executeUpdate();
            if(rowsMade == 0)
            {
                throw new UserNotCreatedException(user);
            }
        }
        catch(SQLException e)
        {
            throw new UserNotCreatedException("SQL error while creating user: " + user);
        }
    }

    @Override
    public Optional<User> getUserById(int id) throws SQLException
    {
        String sql = "SELECT * FROM User WHERE user_id = ?";
        try(PreparedStatement pstmt = connection.prepareStatement(sql))
        {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if(rs.next())
            {
                int userId = rs.getInt("user_id");
                String name = rs.getString("name");
                String username = rs.getString("username");
                String password = rs.getString("password");

                User user = new User(id, name, username, password);
                return Optional.of(user);
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<User> getAllUsers() throws SQLException
    {
        List<User> users = new ArrayList<>();

        try
        {
            String sql = "SELECT * FROM User";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) 
            {
                int id = rs.getInt("user_id");
                String name = rs.getString("name");
                String userName = rs.getString("username");
                String password = rs.getString("password");

                User newUser = new User(id, name, userName, password);
                users.add(newUser);
            }
            
        }
        catch(SQLException e)
        {
            System.out.println("Error fetching users: " + e.getMessage());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public boolean deleteUser(int id)
    {
        String sql = "DELETE FROM User WHERE user_id = ?";
        try(PreparedStatement pstmt = connection.prepareStatement(sql))
        {
            pstmt.setInt(1, id);
            int rowsDeleted = pstmt.executeUpdate();
            return rowsDeleted > 0;
        }
        catch(SQLException e)
        {
            System.err.println("Error deleting user with ID " + id);
            e.printStackTrace();
        }

        return false;
    }

    //Login method
    public Optional<User> loginUser(String username, String password) throws SQLException 
    {
        String sql = "SELECT * FROM User WHERE username = ? AND password = ?";
    
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) 
        {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
        
            ResultSet rs = pstmt.executeQuery();
        
            if (rs.next()) 
            {
                int userId = rs.getInt("user_id");
                String name = rs.getString("name");
                String uname = rs.getString("username");
                String pass = rs.getString("password");

                User user = new User(userId, name, uname, pass);
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

}
