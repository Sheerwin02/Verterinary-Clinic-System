# APU Veterinary Clinic System (AVCS)

The APU Veterinary Clinic System (AVCS) is a distributed enterprise application designed to manage the operations of a veterinary clinic efficiently. The system provides role-based dashboards and functionalities for managing staff, veterinarians, receptionists, customers, pets, appointments, rotas, and medical records.

## Features

### General

- **Role-Based Dashboards**: Separate dashboards for managing staff, veterinarians, and receptionists.
- **Secure Authentication**: Login and registration system with role-based access control.
- **Responsive Design**: User-friendly and mobile-adaptive interface.

### For Managing Staff

- Manage staff profiles, including adding and editing details.
- Approve or reject user registrations.
- Create and view weekly rotas for veterinarians.
- Generate daily and monthly appointment reports with chart visualization (using Chart.js).

### For Receptionists

- Manage appointments, including scheduling, updating, and canceling.
- Maintain customer and pet profiles.
- Assign veterinarians based on expertise and pet species.

### For Veterinarians

- View schedules and appointments.
- Record and manage diagnoses and prognoses for pets.
- Track follow-up requirements for pets under their care.

### Additional Features

- **Hybrid MVC Architecture**: Combines client-side and server-side MVC for enhanced performance and interactivity.
- **Report Export**: Charts can be exported as PNG images for offline use.
- **Entity Relationship Management**: Comprehensive database structure for managing clinic operations.

## System Architecture

The AVCS follows a multi-tier architecture:

1. **Presentation Layer**: JSP pages, HTML, CSS, and JavaScript with AJAX for dynamic updates.
2. **Business Logic Layer**: Java Servlets and Enterprise JavaBeans (EJB) for processing user actions and enforcing business rules.
3. **Database Layer**: Persistent storage with JPA managing entities such as users, appointments, pets, and more.

## Key Technologies

- **Frontend**: JSP, HTML, CSS, JavaScript (Chart.js for visualization).
- **Backend**: Java EE, Servlets, EJBs.
- **Database**: MySQL with JPA for ORM.
- **Frameworks**: Java Persistence API (JPA) for database interactions.

## Usage Guide

### Login

1. Open the AVCS application.
2. Enter your username and password on the login page.
3. Role-based dashboard will load upon successful authentication.

### Registration

1. Click "Register" on the login page.
2. Fill in the form with the required details.
3. If registering as a veterinarian, specify your area of expertise.
4. Await account approval by the managing staff.

### Managing Appointments

- Receptionists can schedule, edit, or cancel appointments.
- Veterinarians can mark appointments as complete and update diagnoses or prognoses.

### Generating Reports

- Managing staff can view daily and monthly reports in the dashboard.
- Use the export feature to download charts for offline review.

## File Structure

### Presentation Layer

- **JSP Files**: Templates for web pages.
- **AJAX**: For seamless server-side interactions without reloading the page.

### Business Logic Layer

- **Servlets**: Handle user requests and process business logic.
- **EJBs**: Encapsulate core functionalities such as authentication, rota management, and report generation.

### Database Layer

- **Entities**: Represent users, pets, appointments, and more.
- **APIs**: Provide custom queries for specific functionalities.

## Future Enhancements

- Add support for email notifications.
- Implement a mobile application for easier access.
- Introduce AI-based recommendations for veterinarian assignments.

---

This project was developed as part of the Enterprise Programming for Distributed Applications module at APU.
