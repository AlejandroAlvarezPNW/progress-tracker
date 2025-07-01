package main.java.com.progress.tracker.topicdao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import main.java.com.progress.tracker.connection.*;

public class TopicDaoImpl implements TopicDao {

    private Connection connection = null;

    @Override
    public void establishConnection() throws ClassNotFoundException, SQLException {
        if (connection == null) {
            connection = ConnectionManager.getConnection();
        }
    }

    @Override
    public void closeConnection() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    @Override
    public void addTopic(Topic topic) throws SQLException 
    {
        String sql = "INSERT INTO Topic (name, type, total_units) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) 
        {
            pstmt.setString(1, topic.getName());
            pstmt.setString(2, topic.getType());
            pstmt.setInt(3, topic.getTotalUnits());

            int rowsMade = pstmt.executeUpdate();
            if (rowsMade == 0) 
            {
                throw new TopicNotCreatedException(topic);
            }
        } 
        catch (SQLException e) 
        {
            throw new TopicNotCreatedException("SQL error while creating topic: " + topic, e);
        }
    }

    @Override
    public Optional<Topic> getTopicById(int id) throws SQLException {
        String sql = "SELECT * FROM Topic WHERE topic_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) 
        {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) 
            {
                int topicId = rs.getInt("topic_id");
                String name = rs.getString("name");
                String type = rs.getString("type");
                int totalUnits = rs.getInt("total_units");

                Topic topic = new Topic(topicId, name, type, totalUnits);
                return Optional.of(topic);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Topic> getAllTopics() throws SQLException 
    {
        List<Topic> topics = new ArrayList<>();
        String sql = "SELECT * FROM Topic";

        try (PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) 
        {
            while (rs.next()) 
            {
                int topicId = rs.getInt("topic_id");
                String name = rs.getString("name");
                String type = rs.getString("type");
                int totalUnits = rs.getInt("total_units");

                Topic newTopic = new Topic(topicId, name, type, totalUnits);
                topics.add(newTopic);
            }
        } 
        catch (SQLException e) 
        {
            System.out.println("Error fetching topics: " + e.getMessage());
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        return topics;
    }

    @Override
    public boolean deleteTopic(int id) 
    {
        String sql = "DELETE FROM Topic WHERE topic_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) 
        {
            pstmt.setInt(1, id);
            int rowsDeleted = pstmt.executeUpdate();
            return rowsDeleted > 0;
        }
        catch (SQLException e) 
        {
            System.err.println("Error deleting topic with ID " + id);
            e.printStackTrace();
        }
        return false;
    }
}
