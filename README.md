# Paymybuddy Prototype Web Application

Paymybuddy is a project interacts with a MySQL Database through Ja

# UML Diagram
![Lau_Tsyyeung_4_uml_112022](https://user-images.githubusercontent.com/62340191/201066009-7080d7c9-0d14-4b10-9580-2ea3a6246e1e.PNG)

# MPD Diagram
![Lau_Tsyyeung_4_mpd_112022](https://user-images.githubusercontent.com/62340191/201065984-1137c501-feb1-448a-ba01-6853f813eaef.PNG)

# Requirements
- JDK 17.0.1
- Apache Maven 4.0.0

# Installation
1) Use git command to clone the project or download the project as a Zip file.
```bash
git clone https://github.com/tylau-dev/paymybuddy.git
```

2) Initialize the MySQL database by running the queries in the queries.sql.

# Usage
1) Run the following command line to run the project
```bash
mvn spring-boot:run
```

2) The Web Application is running on the port 8080. 

3) Following is the list of the Front-End interface

| Endpoint | Content |
|----------|---------|
|   / or /index       | Landing Page |
| /login        |  Login page       |
|  /register    |  Registration page    |
|  /home        |  Home page after Login (protected through authentification) |
|  /profile        |  Profile page for managing balance (protected through authentification)   |
|  /transfer        |  Transfer page for making transactions (protected through authentification) |
|  /contact       |  Contact page for adding contacts (protected through authentification)   |
|  /logout        |  Log out    |
