-- MySQL dump of CRS database
-- Generated: 2025-07-03

DROP DATABASE IF EXISTS crs;
CREATE DATABASE crs;
USE crs;

-- Table structure for students
CREATE TABLE students (
    student_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    dob DATE,
    program VARCHAR(50),
    year INT,
    contact VARCHAR(50)
);

-- Table structure for courses
CREATE TABLE courses (
    course_id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100),
    credits INT,
    department VARCHAR(50),
    prerequisites VARCHAR(100),
    max_capacity INT
);

-- Table structure for enrollments
CREATE TABLE enrollments (
    enrollment_id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT,
    course_id INT,
    grade VARCHAR(2),
    semester VARCHAR(10),
    FOREIGN KEY (student_id) REFERENCES students(student_id) ON DELETE CASCADE,
    FOREIGN KEY (course_id) REFERENCES courses(course_id) ON DELETE CASCADE
);

-- Sample students
INSERT INTO students (name, dob, program, year, contact) VALUES
('Alice Johnson', '2001-02-15', 'CS', 2, 'alice@email.com'),
('Bob Smith', '2000-06-21', 'IT', 3, 'bob@email.com'),
('Carol Williams', '2002-01-12', 'CS', 1, 'carol@email.com'),
('David Brown', '1999-11-03', 'IT', 4, 'david@email.com'),
('Emma Davis', '2001-08-27', 'CS', 2, 'emma@email.com');

-- Sample courses
INSERT INTO courses (title, credits, department, prerequisites, max_capacity) VALUES
('Introduction to Programming', 3, 'CS', '', 50),
('Data Structures', 3, 'CS', 'Introduction to Programming', 40),
('Databases', 3, 'CS', 'Data Structures', 35),
('Operating Systems', 4, 'CS', 'Data Structures', 30),
('Computer Networks', 3, 'CS', 'Operating Systems', 30),
('Software Engineering', 3, 'CS', 'Data Structures', 40),
('Web Development', 3, 'IT', 'Introduction to Programming', 45),
('Mobile App Development', 3, 'IT', 'Web Development', 35),
('Artificial Intelligence', 4, 'CS', 'Data Structures', 25),
('Project Management', 2, 'IT', '', 50);

-- Sample enrollments
INSERT INTO enrollments (student_id, course_id, grade, semester) VALUES
(1, 1, 'A', 'Fall2024'),
(1, 2, 'B+', 'Spring2025'),
(2, 7, 'A-', 'Fall2024'),
(3, 1, 'B', 'Fall2024'),
(4, 10, 'C+', 'Fall2024');
