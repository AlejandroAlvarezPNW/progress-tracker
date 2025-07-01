package main.java.com.progress.tracker.topicdao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/* Topic Dao Interface */

public interface TopicDao {

    // Ensures connection manager is called
    void establishConnection() throws ClassNotFoundException, SQLException;

    // Closes the connection
    void closeConnection() throws SQLException;

    // Add a new topic
    void addTopic(Topic topic) throws SQLException;

    // Get a topic by its ID
    Optional<Topic> getTopicById(int id) throws SQLException;

    // Get all topics
    List<Topic> getAllTopics() throws SQLException;

    // Delete a topic by ID
    boolean deleteTopic(int id) throws SQLException;
}