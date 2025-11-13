# 🏥 Hospital Management System (HMS)

This project is a comprehensive console-based application designed to simulate and manage core operations within a hospital environment. Developed in Java, it utilizes an Object-Oriented Programming (OOP) design centered around distinct **Service** layers and **Entity** models, promoting separation of concerns and maintainable code.

---

## ✨ Key Features

The Hospital Management System provides essential management tools for various hospital components:

* **Patient Management:** Register, search, update, and remove different types of patients (`Patient`, `InPatient`, `OutPatient`, `EmergencyPatient`).
* **Staff Management:** Manage details for `Doctor`s (including `Surgeon` and `Consultant`) and `Nurse`s, including specializations, shifts, and departmental assignment.
* **Scheduling:** Schedule, view, and manage `Appointment`s, including options for rescheduling, canceling, and retrieving **upcoming appointments**.
* **Records Management:** Create, view, and retrieve `MedicalRecord`s linked to specific patients and doctors.
* **Department Management:** Define hospital `Department`s, assign staff, track bed occupancy, and calculate the **Occupancy Rate**.
* **Reporting:** Generate reports and statistics on departmental metrics, doctor performance, and emergency cases.

---

## 🏗️ Project Architecture

The project adheres to a Service Layer architecture to ensure a clear separation of concerns:

| Package | Responsibility | Key Classes |
| :--- | :--- | :--- |
| **`main`** | Handles User Interface (Console), Menu Navigation, and coordinating application flow. | `HospitalManagementApp` |
| **`Service`** | Contains all Business Logic and Data Manipulation (CRUD, searching, reporting). Implements interfaces for standardized operations. | `PatientService`, `DoctorService`, `AppointmentService` |
| **`Entity`** | Contains all Data Models. Defines the structure and domain behavior (e.g., calculating Occupancy Rate). | `Patient`, `Doctor`, `Department`, `Appointment` |
| **`Utils`** | Provides helper functionality for tasks like ID generation and validation. | `HelperUtils` |

---

## 🛠️ Technology Stack

* **Language:** Java
* **Design:** Object-Oriented Programming (OOP) principles, Service Layer Architecture, Interface Segregation.
* **Date/Time:** Java 8+ Time API (`LocalDate`, `LocalDateTime`).
* **Data Management:** In-memory `ArrayList` collections are used to simulate persistence.

---

## 🚀 Getting Started

### Prerequisites

* Java Development Kit (JDK) 17 or higher.
* Lombok plugin installed in your IDE (essential for recognizing generated methods).

### Running the Application

1.  **Clone the repository:**
    ```bash
    git clone https://github.com/Hjufaili/Hospital-Management-System
    cd Hospital-Management-System
    ```
2.  **Compile the Java files:**
    * *If using an IDE (e.g., IntelliJ, Eclipse), ensure your project is configured for Lombok annotation processing, then simply use the built-in Run command.*
    * *Manual compilation (from the root directory):*
        ```bash
        # Note: Lombok JAR would typically be required on the classpath for manual compilation
        # Since this is a console app, standard compilation should suffice if IDE handles Lombok
        javac -d out Service/*.java Entity/*.java Utils/*.java main/*.java
        ```
3.  **Run the application:**
    ```bash
    java -cp out main.HospitalManagementApp
    ```
