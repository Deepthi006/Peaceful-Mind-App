# Peaceful-Mind-App
Peaceful Mind 🧘 - A Mental Well-being Desktop App
Peaceful Mind is a desktop application developed in Java Swing, designed to be a supportive tool for mental well-being. It provides a clean, user-friendly interface with a suite of features aimed at helping users track their emotions, find inspiration, and manage stress through interactive exercises.

This project also serves as a practical demonstration of several key computer science data structures and algorithms (DSA) to solve real-world problems efficiently.

📸 Application Preview
✨ Features & Implemented DSA
The application is organized into four distinct modules, each leveraging specific data structures and algorithms:

📊 Mood Tracker:

Functionality: Allows users to log their daily mood from a predefined list.

DSA Implemented:

Time-Series Analysis: A List stores mood entries over time.

Pattern Finding: A linear scan algorithm (O(n)) calculates the longest streak of a specific mood (e.g., "Happy").

Frequency Counting: A HashMap is used to efficiently find the most frequently logged mood (O(n)).

📜 Quotes:

Functionality: Enables users to search a collection of inspirational quotes for specific keywords.

DSA Implemented:

Inverted Index: A HashMap where keys are words and values are a List of quote indices. This allows for near-instantaneous search (O(1) average time complexity) once the index is built.

🧘 Stress Relief:

Functionality: Recommends an optimal set of stress-relief activities based on the user's available time.

DSA Implemented:

0/1 Knapsack Problem: Solved using dynamic programming. This algorithm finds the combination of activities that maximizes "effectiveness" (value) without exceeding the total "time required" (weight/capacity).

🤖 Chat:

Functionality: A simple, stateful chatbot that guides the user through a conversation.

DSA Implemented:

Graph (Adjacency List): A HashMap represents a directed graph where keys are conversation nodes and values are ConversationNode objects. This allows the chatbot to traverse from one state to another based on user input keywords.

🛠️ Technology
Core Language: Java

GUI Framework: Java Swing

Core Libraries: Standard Java SE library (no external dependencies).

🚀 How to Run the Project
To compile and run this application on your local machine, you will need the Java Development Kit (JDK) installed (version 8 or higher).

1. Open a Terminal or Command Prompt:
Navigate to the src directory where the MainFrame.java file is located.

2. Compile the Code:
Run the Java compiler:

javac MainFrame.java

3. Run the Application:
After a successful compilation, run the application using the java command:

java MainFrame

The "Peaceful Mind" application window should now appear on your screen.
