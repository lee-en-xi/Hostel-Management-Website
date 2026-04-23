# Food Ordering System (University Group Project)

A robust, desktop-based Food Ordering System built with **Java** and **Object-Oriented Programming (OOP)** principles. This project was collaboratively developed as a semester-long university assignment to simulate a real-world multi-user food delivery ecosystem.

## 👥 Team Collaboration
Developed by **Group 16**, consisting of Lee En Xi and university team members. Our focus was on creating a scalable architecture with a clear separation of concerns among different user roles.

## 🚀 Project Overview
The system manages the entire lifecycle of a food order—from vendor menu management and customer placement to runner delivery and administrative oversight. 

### Key Features by User Role:
* **Admin**: Manage system users, monitor overall transactions, and handle system configurations.
* **Vendor**: Manage food menus (Add/Edit/Delete items), track incoming orders, and view sales performance.
* **Customer**: Browse menus, place orders, track delivery status, and provide reviews.
* **Runner**: View available delivery tasks, update delivery status, and track earnings.

## 🛠️ Technical Stack
* **Language**: Java (JDK 20+)
* **GUI Framework**: Java Swing / NetBeans GUI Builder
* **Architecture**: Object-Oriented Programming (Encapsulation, Inheritance, Polymorphism)
* **Data Storage**: Text-based (.txt) / File I/O (Designed for lightweight data persistence)
* **Layout Manager**: AbsoluteLayout (NetBeans awtextra)

## 🏗️ OOP Implementation
This project leverages core OOP concepts to ensure code reusability and maintainability:
- **Encapsulation**: Used to protect user data and order details.
- **Inheritance**: Implemented a base `User` class with specific subclasses for `Admin`, `Vendor`, `Customer`, and `Runner`.
- **Polymorphism**: Used for handling different types of order status updates and user-specific actions.

## 📦 Installation & Setup
1. **Clone the repository**:
   ```bash
   git clone [https://github.com/lee-en-xi/Food-Ordering-System.git](https://github.com/lee-en-xi/Food-Ordering-System.git)
