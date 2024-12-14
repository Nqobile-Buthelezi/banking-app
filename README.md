# Banking App Web Application

A simple banking web application built using **Java**, **Maven**, and **Javalin** as the web server. This app allows users to manage their accounts, view balances, transfer money, and more. It is designed to demonstrate modern web development principles and provide an interactive, user-friendly experience.

## Features

- **User Authentication**: Secure sign-up and login functionality.
- **Account Overview**: View account balance, transaction history, and personal details.
- **Fund Transfers**: Transfer money between accounts.
- **Transaction History**: View all past transactions and filter by date or type.
- **Admin Panel**: Admin users can manage accounts, view users' transactions, and more.
- **Responsive Design**: Optimized for both desktop and mobile devices.

## Technologies Used

- **Java** - Main programming language.
- **Maven** - Dependency management and build tool.
- **Javalin** - Lightweight web framework for building the backend.
- **Thymeleaf** - Server-side template engine for rendering HTML.
- **SQLite** - Local database for storing user and transaction data.
- **HTML, CSS, JavaScript** - Frontend development (with Thymeleaf for dynamic content).
  
## Installation

To run this application locally, follow the steps below:

### Prerequisites

- **Java** 17
- **Maven** 3.x or higher

### Steps

1. Clone the repository:

   ```bash
   https://github.com/Nqobile-Buthelezi/banking-app.git
   cd banking-app

2. Build the Application

    ```bash
    mvn clean install

3. Run

    ```bash
    mvn exec:java