# Progress Tracker

## Project Overview

Progress Tracker is a Java-based application designed to help users track their learning or project progress across various topics. The application allows users to log in, view available topics, add topics to their personal tracker, update progress, and manage their learning journey efficiently.

## Purpose
- Enable users to monitor their progress on different topics.
- Provide CRUD (Create, Read, Update, Delete) operations for users, topics, and progress tracking.
- Serve as a proof of concept for a scalable progress tracking system.

## Features
- **User Authentication:** Secure login for users.
- **Topic Management:** View all available topics.
- **Personal Tracker:** Add, update, and delete topics from a personal tracker.
- **Progress Tracking:** Record and update progress for each topic, formatted as a percentage.
- **Status Management:** Mark topics as In Progress, Completed, etc.

## Functionality
- Users can log in with a username and password.
- After login, users can:
  - View all topics
  - View their tracker
  - Add a topic to their tracker (with progress and status)
  - Update progress and status for a tracked topic
  - Delete a tracker by tracker id
- All progress values are stored and displayed as percentages (e.g., "75%").

## Technical Details
- **Language:** Java
- **Database:** JDBC (assumes a relational database with tables for users, topics, and trackers)
- **Structure:**
  - `userdao` for user management
  - `topicdao` for topic management
  - `trackerdao` for progress tracking
- **Entry Point:** `Main.java`

## How to Run
1. Ensure you have Java 11+ and a compatible database set up.
2. Configure your database connection in `ConnectionManager.java`.
3. Build the project using Maven:
   ```sh
   mvn clean package
   ```
4. Run the application:
   ```sh
   java -cp target/progress-tracker-1.0-SNAPSHOT.jar com.progress.tracker.Main
   ```

## Project Requirements Document (PRD)
- **Purpose:** Provide a proof of concept for a progress tracking system.
- **Features:** User login, topic management, personal tracker, progress and status updates.
- **Functionality:** As described above.
- **Outcome:** A working Java console application demonstrating the core features and database integration for progress tracking.

## Proof of Concept
This project demonstrates the core logic and user experience for a progress tracking system. It can be extended with a web interface, advanced analytics, or integration with other learning platforms in the future.

---

*For more details, see the source code and comments in each class.*
