# 📚 Course Registration System (CRS)

A simple Java-based **Course Registration System** built using **MVC architecture**, **JDBC**, and **MySQL**, for managing students, courses, and enrollments in an educational institution.

---

## ✨ Features
✅ Role-based login: Student, Admin, Faculty  
✅ Students:
- View available courses
- Register (enroll) for courses
✅ Admin:
- Manage students (Create, Read, Update, Delete)
- Manage courses (Create, Read, Update, Delete)
✅ Automatic prerequisite and capacity checks
✅ MySQL database with sample data
✅ Swing-based graphical interface

---

## 🏗️ Project Structure
src/
├── model/ # Data classes (Student, Course, Enrollment)
├── dao/ # Database access layer (CRUD for Students & Courses)
├── controller/ # Business logic & rules (prerequisites, capacity)
├── view/ # User interface (Swing)
├── util/ # DB connection helper
database/
├── crs_schema.sql # Database schema
├── sample_data.sql # Sample data for testing
README.md



---

## ⚙️ Technologies
- Java (JDK 8+)
- Swing (GUI)
- MySQL
- JDBC
- MVC architecture

---

## 🚀 Setup & Run

### 1️⃣ Clone the repository
```bash
git clone <your-repo-url>
cd <your-project-folder>



2️⃣ Set up the database
Start MySQL server

Log in to MySQL:


mysql -u root -p
Create & populate the database:


SOURCE database/crs_schema.sql;
SOURCE database/sample_data.sql;
3️⃣ Configure database credentials
In src/util/DBConnection.java, update:


private static final String USER = "your_mysql_username";
private static final String PASSWORD = "your_mysql_password";
4️⃣ Compile the project
From project root:


javac -cp . src/**/*.java
or if ** does not work:





javac -cp . src\model\*.java src\dao\*.java src\util\*.java src\controller\*.java src\view\*.java
5️⃣ Run the application


java -cp src view.LoginView

---


🔐 Login Credentials


Role: Student
Username: student
Password: 1234


Role: Admin
Username: admin
Password: admin


Role: Faculty
Username: faculty
Password: faculty


📋 Notes

Requires MySQL Connector/J (JDBC driver).

Download: MySQL Connector/J

Include the .jar in your classpath when compiling & running.



Example:

javac -cp ".;mysql-connector-java-8.x.x.jar" src/**/*.java
java -cp ".;src;mysql-connector-java-8.x.x.jar" view.LoginView
Database name: crs


---

🙌 Contributors
✍️ Janeesha Dewmini

