package com.progress.tracker;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.progress.tracker.topicdao.Topic;
import com.progress.tracker.topicdao.TopicDaoImpl;
import com.progress.tracker.trackerdao.Tracker;
import com.progress.tracker.trackerdao.TrackerDaoImpl;
import com.progress.tracker.userdao.User;
import com.progress.tracker.userdao.UserDaoImpl;

public class Main {
    public static void main(String[] args) {
        UserDaoImpl userDao = new UserDaoImpl();
        TopicDaoImpl topicDao = new TopicDaoImpl();
        TrackerDaoImpl trackerDao = new TrackerDaoImpl();
        Scanner scanner = new Scanner(System.in);

        try {
            userDao.establishConnection();
            topicDao.establishConnection();
            trackerDao.establishConnection();

            Optional<User> loggedInUser = Optional.empty();
            while (loggedInUser.isEmpty()) {
                System.out.print("Enter username: ");
                String username = scanner.nextLine();

                System.out.print("Enter password: ");
                String password = scanner.nextLine();

                loggedInUser = userDao.loginUser(username, password);
                if (loggedInUser.isEmpty()) {
                    System.out.println("Login failed. Please try again.\n");
                }
            }

            User user = loggedInUser.get();
            System.out.println("\nLogin successful! Welcome, " + user.getName() + "\n");

            boolean running = true;
            while (running) {
                System.out.println("===== Main Menu =====");
                System.out.println("1. View All Topics");
                System.out.println("2. View My Tracker");
                System.out.println("3. Add new Tracker by topic ID");
                System.out.println("4. Update My Tracker");
                System.out.println("5. Delete Tracker by Tracker ID");
                System.out.println("6. Exit");

                String choice = "";
                while (true) {
                    System.out.print("Select an option (from 1 to 6): ");
                    choice = scanner.nextLine();
                    if (choice.matches("[1-6]")) break;
                    System.out.println("Invalid input. Please enter a number between 1 and 6.");
                }

                switch (choice) {
                    case "1":
                        List<Topic> topics = topicDao.getAllTopics();
                        System.out.println("\nAvailable Topics:");
                        for (Topic t : topics) {
                            System.out.println("ID: " + t.getTopicId() + ", Name: " + t.getName() +
                                    ", Type: " + t.getType() + ", Units: " + t.getTotalUnits());
                        }
                        System.out.println();
                        break;

                    case "2":
                        List<Tracker> userTrackers = trackerDao.getTrackersByUserId(user.getuserId());
                        System.out.println("\nYour Tracker:");
                        for (Tracker tr : userTrackers) {
                            System.out.println("Tracker ID: " + tr.getTrackerId() + ", Topic ID: " + tr.getTopicId() +
                                    ", Topic Name: " + topicDao.getTopicById(tr.getTopicId()).map(Topic::getName).orElse("Unknown") +
                                    ", Progress: " + tr.getProgress() + ", Status: " + tr.getStatus());
                        }
                        System.out.println();
                        break;

                    case "3":
                        int topicId = getIntInput(scanner, "Enter Topic ID to add to your tracker: ");
                        int progress = getIntInput(scanner, "Enter progress (0-100): ");
                        String progressStr = progress + "%";
                        System.out.print("Enter status (e.g. In Progress, Completed): ");
                        String status = scanner.nextLine();
                        Tracker newTracker = new Tracker(0, user.getuserId(), topicId, status, progressStr);
                        trackerDao.addTracker(newTracker);
                        System.out.println("Tracker entry added.\n");
                        break;

                    case "4":
                        int trackerId = getIntInput(scanner, "Enter Tracker ID to update: ");
                        int newProgress = getIntInput(scanner, "Enter new progress (0-100): ");
                        String newProgressStr = newProgress + "%";
                        System.out.print("Enter new status: ");
                        String newStatus = scanner.nextLine();
                        Tracker updatedTracker = new Tracker(trackerId, user.getuserId(), 0, newStatus, newProgressStr);
                        trackerDao.UpdateTracker(updatedTracker);
                        System.out.println("Tracker updated.\n");
                        break;

                    case "5":
                        int deleteId = getIntInput(scanner, "Enter Tracker ID to delete: ");
                        trackerDao.deleteTracker(deleteId);
                        System.out.println("Tracker deleted.\n");
                        break;

                    case "6":
                        running = false;
                        System.out.println("Goodbye!");
                        break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }

    // Helper method for integer input
    public static int getIntInput(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Please try again.");
            }
        }
    }
}
