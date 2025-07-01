package main.java.com.progress.tracker.trackerdao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.sound.midi.Track;

import main.java.com.progress.tracker.*;

public interface TrackerDao 
{
    //Connect & Disconnect
    void establishConnection() throws ClassNotFoundException, SQLException;
    void closeConnection() throws SQLException;

    //Create
    void addTracker(Tracker tracker) throws SQLException;

    //Read
    Optional<Tracker> getTrackerById(int id) throws SQLException;
    List<Tracker> getAllTrackers() throws SQLException;
    List<Tracker> getTrackersByUserId(int userId) throws SQLException;
    List<Tracker> gTrackersByTopicId(int topicId) throws SQLException;

    //Update
    boolean UpdateTracker(Tracker tracker) throws SQLException;

    //Delete
    boolean deleteTracker(int id) throws SQLException;
    
}
