-- =========================================
-- Drop existing tables (if they exist)
-- =========================================
DROP TABLE IF EXISTS tracker;
DROP TABLE IF EXISTS topic;
DROP TABLE IF EXISTS user;

-- =========================================
-- Create user table
-- =========================================
CREATE TABLE user (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL
);

-- =========================================
-- Create topic table
-- =========================================
CREATE TABLE topic (
    topic_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    type VARCHAR(50) NOT NULL,
    total_units INT NOT NULL
);

-- =========================================
-- Create tracker table
-- =========================================
CREATE TABLE tracker (
    tracker_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    topic_id INT,
    status VARCHAR(50) NOT NULL,
    progress VARCHAR(50) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user(user_id),
    FOREIGN KEY (topic_id) REFERENCES topic(topic_id)
);

-- =========================================
-- Insert sample users
-- =========================================
INSERT INTO user (name, username, password) VALUES
('Alejandro Alvarez', 'alejandro', 'pass123'),
('Maria Lopez', 'mariaL', 'secure456'),
('John Smith', 'jsmith', 'hello789'),
('Alice Johnson', 'alicej', 'pass123'),
('Bob Smith', 'bobsmith', 'letmein'),
('Carlos Rivera', 'crivera', 'qwerty'),
('Dana Liu', 'dliu', 'abc123'),
('Ethan Patel', 'epatel', 'securepw');

-- =========================================
-- Insert sample topics
-- =========================================
INSERT INTO topic (name, type, total_units) VALUES
-- Books
('Java Basics', 'Books', 10),
('Databases 101', 'Books', 8),
('Data Structures', 'Books', 12),
('1984 by George Orwell', 'Books', 24),
('To Kill a Mockingbird', 'Books', 31),
('The Great Gatsby', 'Books', 18),
('The Catcher in the Rye', 'Books', 26),
('Brave New World', 'Books', 20),
('The Hobbit', 'Books', 19),
('Fahrenheit 451', 'Books', 22),
('Moby Dick', 'Books', 135),
('Pride and Prejudice', 'Books', 61),
('The Alchemist', 'Books', 25),

-- TV Shows
('Breaking Bad', 'TV Show', 62),
('Stranger Things', 'TV Show', 34),
('The Office', 'TV Show', 201),
('Game of Thrones', 'TV Show', 73),
('Friends', 'TV Show', 236),
('The Mandalorian', 'TV Show', 24),
('The Witcher', 'TV Show', 16),
('Avatar: The Last Airbender', 'TV Show', 61),
('Better Call Saul', 'TV Show', 63),
('The Boys', 'TV Show', 24),

-- Music Albums
('Thriller - Michael Jackson', 'Music', 9),
('Back in Black - AC/DC', 'Music', 10),
('The Dark Side of the Moon - Pink Floyd', 'Music', 10),
('Rumours - Fleetwood Mac', 'Music', 11),
('Abbey Road - The Beatles', 'Music', 17),
('1989 - Taylor Swift', 'Music', 13),
('DAMN. - Kendrick Lamar', 'Music', 14),
('The Eminem Show - Eminem', 'Music', 20),
('Divide - Ed Sheeran', 'Music', 16),
('Future Nostalgia - Dua Lipa', 'Music', 11);

-- =========================================
-- Insert sample tracker entries
-- =========================================
INSERT INTO tracker (user_id, topic_id, status, progress) VALUES
-- Alejandro
(1, 1, 'In Progress', '30%'),
(1, 2, 'Not Started', '0%'),
-- Maria
(2, 1, 'Completed', '100%'),
-- John
(3, 3, 'In Progress', '50%'),

-- Alice (user_id = 4)
(4, 1, 'In Progress', '20%'),
(4, 11, 'Completed', '100%'),
(4, 21, 'In Progress', '50%'),

-- Bob (user_id = 5)
(5, 2, 'In Progress', '30%'),
(5, 12, 'Completed', '100%'),
(5, 22, 'Almost Done', '90%'),

-- Carlos (user_id = 6)
(6, 3, 'Just Started', '10%'),
(6, 13, 'In Progress', '60%'),
(6, 23, 'Almost Done', '80%'),

-- Dana (user_id = 7)
(7, 4, 'Completed', '100%'),
(7, 14, 'Completed', '100%'),
(7, 24, 'In Progress', '20%'),

-- Ethan (user_id = 8)
(8, 5, 'Not Started', '0%'),
(8, 15, 'Completed', '100%'),
(8, 25, 'In Progress', '35%');
