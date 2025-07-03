package com.progress.tracker.trackerdao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TrackerDaoImpl implements TrackerDao
{
    private Connection connection = null;

    @Override
    public void establishConnection() throws ClassNotFoundException,SQLException
    {
        if (connection == null || connection.isClosed()) {
            connection = com.progress.tracker.connection.ConnectionManager.getConnection();
        }
    }

    @Override
    public void closeConnection() throws SQLException 
    {
        if(connection != null)
        {
            connection.close();;
        }
    }

    @Override
    public void addTracker(Tracker tracker) throws SQLException 
    {
        String sql = "INSERT INTO tracker (user_id, topic_id, status, progress) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) 
        {
            pstmt.setInt(1, tracker.getUserId());
            pstmt.setInt(2, tracker.getTopicId());
            pstmt.setString(3, tracker.getStatus());
            pstmt.setString(4, tracker.getProgress());

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted == 0) 
            {
                throw new SQLException("Tracker not inserted: " + tracker);
            }
        }
    }

    @Override
    public Optional<Tracker> getTrackerById(int id) throws SQLException 
    {
        String sql = "SELECT * FROM tracker WHERE tracker_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) 
        {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) 
            {
                Tracker tracker = extractTrackerFromResultSet(rs);
                return Optional.of(tracker);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Tracker> getAllTrackers() throws SQLException 
    {
        String sql = "SELECT * FROM tracker";
        List<Tracker> trackers = new ArrayList<>();

        try (PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) 
        {
                while (rs.next()) 
                {
                Tracker tracker = extractTrackerFromResultSet(rs);
                trackers.add(tracker);
                }
        }
        return trackers;
    }

    @Override
    public List<Tracker> getTrackersByUserId(int userId) throws SQLException 
    {
        String sql = "SELECT * FROM tracker WHERE user_id = ?";
        List<Tracker> trackers = new ArrayList<>();

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) 
        {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) 
            {
                Tracker tracker = extractTrackerFromResultSet(rs);
                trackers.add(tracker);
            }
        }
        return trackers;
    }

    @Override
    public List<Tracker> getTrackersByTopicId(int topicId) throws SQLException 
    {
        String sql = "SELECT * FROM tracker WHERE topic_id = ?";
        List<Tracker> trackers = new ArrayList<>();

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) 
        {
            pstmt.setInt(1, topicId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) 
            {
                Tracker tracker = extractTrackerFromResultSet(rs);
                trackers.add(tracker);
            }
        }
        return trackers;
    }

    @Override
    public boolean UpdateTracker(Tracker tracker) throws SQLException 
    {
        String sql = "UPDATE tracker SET status = ?, progress = ? WHERE tracker_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) 
        {
            pstmt.setString(1, tracker.getStatus());
            pstmt.setString(2, tracker.getProgress());
            pstmt.setInt(3, tracker.getTrackerId());

            int rowsUpdated = pstmt.executeUpdate();
            return rowsUpdated > 0;
        }
    }

    @Override
    public boolean deleteTracker(int id) throws SQLException 
    {
        String sql = "DELETE FROM tracker WHERE tracker_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) 
        {
            pstmt.setInt(1, id);
            int rowsDeleted = pstmt.executeUpdate();
            return rowsDeleted > 0;
        }
    }

    // Helper method to extract Tracker from ResultSet
    private Tracker extractTrackerFromResultSet(ResultSet rs) throws SQLException 
    {
        int trackerId = rs.getInt("tracker_id");
        int userId = rs.getInt("user_id");
        int topicId = rs.getInt("topic_id");
        String status = rs.getString("status");
        String progress = rs.getString("progress");

        return new Tracker(trackerId, userId, topicId, status, progress);
    }
}