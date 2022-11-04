# Paymybuddy Prototype Web Application

Paymybuddy is a project interacts with a MySQL Database through Ja

# UML Diagram
![umldiagram](https://user-images.githubusercontent.com/62340191/199959415-3d4ea49c-abf3-46af-8db2-e250c6dcfd64.PNG)

# MPD Diagram
![mpddiagram](https://user-images.githubusercontent.com/62340191/199959462-b31e5df6-6509-4963-925e-3b492c4145de.PNG)

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
4) 
| Endpoint | Content |
|----------|---------|
|   / or /index       | Landing Page |
| /login        |  Login page       |
|  /register    |  Registration page    |
|  /home        |  Home page after Login (protected through authentification) |
|  /profile        |  Profile page for managing contacts and balance (protected through authentification)   |
|  /transfer        |  Transfer page for making transactions (protected through authentification) |
|  /logout        |  Log out    |
