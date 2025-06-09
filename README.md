# 🎓 Java Swing Quiz App 🎮

![Java](https://img.shields.io/badge/Java-Swing-blue.svg)
![MySQL](https://img.shields.io/badge/Database-MySQL-orange.svg)
![GitHub stars](https://img.shields.io/github/stars/yourusername/quiz-app?style=social)
![License](https://img.shields.io/github/license/yourusername/quiz-app)
![Platform](https://img.shields.io/badge/Platform-Desktop-lightgrey)

> 🚀 A sleek, interactive, and image-rich quiz application built using **Java Swing** and **MySQL**. Choose from exciting categories, challenge yourself across 3 levels, and celebrate your victories in style! 🎉

---

## 🌟 Features

✨ **User-Friendly Experience**
- 👤 User Registration & Login
- 🎨 Beautiful UI built with Java Swing
- 🧩 Quiz Categories: Science, Mathematics, History, and more
- 🧠 3 Levels: Level 1️⃣, 2️⃣, 3️⃣ (10 questions each)

🔥 **Gameplay Mechanics**
- 🖼️ Each question is paired with a unique image
- 💡 Lifelines: `Skip` ➖ | `50/50` 🌓 | `Hint` 💬
- 🧮 Scoreboard with global competitor rankings
- 🎆 Win Celebration Animation
- ⏱️ Timed questions (optional)

📊 **Backend**
- 💾 MySQL Database for users and scores
- 🔒 Secure user data handling
- 📈 Leaderboard system

---

## 🖼️ Demo

> Add a screen recording or image GIF of the application in action!

![Quiz App Demo](https://media.giphy.com/media/v1.Y2lkPTc5MGI3NjExY2VhZGIzZTRjNGU5OGU1MjAwNjI2MjJhNmNjMDIxMTM1YWI3OGIzOCZjdD1n/gU2HtNfxr5a9y/giphy.gif)

---

## 🛠️ Tech Stack

| Tech            | Description                      |
|-----------------|----------------------------------|
| 🧑‍💻 Java Swing   | GUI Development Framework        |
| 🗄️ MySQL         | Database Backend                |
| 🧠 JDBC          | Java Database Connectivity       |
| 🎨 AWT           | Lightweight UI components        |
| 🔐 Secure Login | Authentication system             |

---

## 🔧 Setup Instructions

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/quiz-app.git
   cd quiz-app
   ```

2. **Set up the MySQL Database**
   ```sql
   CREATE DATABASE quizz;
   USE quizz;

   CREATE TABLE users (
     id INT AUTO_INCREMENT PRIMARY KEY,
     first_name VARCHAR(100) NOT NULL,
     email VARCHAR(100) NOT NULL UNIQUE,
     password VARCHAR(255) NOT NULL
   );

   CREATE TABLE scores (
     id INT AUTO_INCREMENT PRIMARY KEY,
     user_id INT,
     category VARCHAR(100),
     level INT,
     score INT,
     timestamp DATETIME DEFAULT CURRENT_TIMESTAMP,
     FOREIGN KEY (user_id) REFERENCES users(id)
   );
   ```

3. **Update JDBC Credentials in Java Code**
   ```java
   String url = "jdbc:mysql://localhost:3306/quizz";
   String user = "root";
   String dbPassword = "your_mysql_password";
   ```

4. **Run the application**
    - Compile and run the Java application from your IDE or terminal.

---

## 🧑‍🚀 Contributors

| Name           | Role             |
|----------------|------------------|
| 🧑‍💻 Your Name    | Developer & Designer |
| 🎨 Your Friend   | UI/UX & Animations  |
| 🧠 Your Classmate| Questions & Content |

---

## 📈 Future Enhancements

- 🌐 Online multiplayer mode
- 📱 Mobile version with JavaFX or Android
- 🗣️ Text-to-speech for accessibility
- 🧑‍🏫 Admin panel to add questions dynamically

---

## 📸 Screenshots

| Login | Category Selection | Question Interface |
|-------|---------------------|--------------------|
| ![login](screenshots/login.png) | ![category](screenshots/category.png) | ![quiz](screenshots/quiz.png) |

---

## 📜 License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

---

## 🙌 Support

If you like this project, please consider ⭐ starring the repository and sharing it!  
You can also [buy me a coffee](https://www.buymeacoffee.com/yourusername) ☕.

---

> “Learning never exhausts the mind.” — Leonardo da Vinci
