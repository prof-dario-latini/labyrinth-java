# Java Labyrinth Project

Educational project designed to help students learn Java programming concepts through examples and small challenges.

## 🎯 Learning Goals

- Understand basic Java syntax and structure
- Practice object-oriented programming (OOP) concepts
- Learn how to use enums, classes, and interfaces
- Work with loops, arrays, and collections
- Understand how to handle user input and simple logic

---

## 🚀 How to Run

1. **Clone the repository:**
   ```bash
   git clone https://github.com/prof-dario-latini/labyrinth-java.git
   ```
2. **Compile and run:**
   ```bash
   mvn clean install
   mvn exec:java -Dexec.mainClass="Main"
   ```
3. **Build**
   ```bash
   mvn compile 
   mvn package
   ```
4. **Open the project** in your preferred IDE (IntelliJ IDEA, VS Code, Eclipse, etc.) and start to edit what you want!

---

## 🧩 Files


| File              | Topic   | Description                          |
|-------------------|---------|--------------------------------------|
| `Labyrinth.java`  | Classes | Main class of the Labyrinth          |
| `Room.java`       | Classes | Room of the labyrinth                |
| `Position.java`   | Record  | Create and use custom data types     |
| `Persona.java`    | Classes | Generic class for player and npc     |
| `Direction.java`  | Enums   | Handle directions for moving objects |
| `TextUI.java`     | Classes | Text games through console           |
| `GraphicsUI.java` | Enums   | 2D game                              |
| `...`             | TODO    | TODO: Explain other files            |

---

## 📘 Suggested Activities

- Modify existing examples and observe how the program behaves.
- Add new classes (e.g., `Monsters`, `Trap`, `Ammo`) using OOP principles.
- Implement user interaction via `Scanner`.
- Extend the project with graphical or file input/output features.

---

## 🧑‍🏫 For Teachers

This repository can be used as:
- A base for Java programming labs
- A shared example for explaining OOP
- A template for student assignments

Feel free to fork and adapt it to your course.

---

## 📜 License

This project is released under the **MIT License**.  
See the [LICENSE](./LICENSE) file for details.

---

## ✉️ Author

**Prof. Dario Latini**  
Computer Science Teacher – IIS Tommaso Salvini - Polo Tecnologico, of Rome
📧 [School official site](https://www.iistommasosalvini.edu.it/)

## 🫱🏼‍🫲🏾 Credits

Thanks to Ben Johnson for the project:
[java_2d_game](https://github.com/learncodebygaming/java_2d_game)

I used [ChatGPT](https://chatgpt.com/) to make the pngs for the first version of the graphics
