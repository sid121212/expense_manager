# Expense Manager Application

This is a Spring Boot-based Expense Manager application that helps users manage group expenses by adding expenses, splitting them, and tracking what each user owes or is owed within a group. The application uses PostgreSQL as the database and is containerized using Docker.

## Features

- **Create Users and Groups**: Create users and assign them to different groups.
- **Add Expenses**: Add expenses to groups, specifying who paid and the reason for the expense.
- **Settle Up**: Keep track of settled expenses by individual users and generate a summary of debts.
- **Track Owes and Owed**: Retrieve a list of users who owe or are owed by a user within a group, with real-time amounts after adjustments.
  
## Technologies

- **Java 17**
- **Spring Boot**
- **Spring Data JPA**
- **PostgreSQL**
- **Docker**
- **Lombok**

## Prerequisites

- Java 17+
- Docker and Docker Compose
- PostgreSQL

## Setup

1. **Clone the repository**:

   ```bash
   git clone https://github.com/yourusername/expense-manager.git
   cd expense-manager
