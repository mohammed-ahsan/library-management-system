# Library Management System

A comprehensive library management system built with Spring Boot.

## 📺 Video Showcase

Watch the demo of the Library Management System in action:

[![Library Management System Demo](https://img.youtube.com/vi/c3d8cPizgwc/0.jpg)](https://youtu.be/c3d8cPizgwc)

Click the image above or [watch on YouTube](https://youtu.be/c3d8cPizgwc)

## Features

- **Book Management**: Create, read, update, and delete books
- **Author Management**: Manage author information
- **User Management**: Handle library users
- **Circulation System**: Borrow and return books
- **Search Functionality**: Search for books in the library

## Tech Stack

- Java with Spring Boot
- PostgreSQL Database
- Docker & Docker Compose
- Gradle

## Getting Started

### Prerequisites

- Docker and Docker Compose
- Java 17 or higher
- Gradle

### Running the Application

1. Start the database:
```bash
docker-compose up -d
```

2. Run the application:
```bash
./gradlew bootRun
```

The application will be available at `http://localhost:8080`

## API Documentation

API endpoints can be tested using the Bruno collection located in the `bruno/sisimpur-library` directory.
