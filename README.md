
## Screenshots

![App Screenshot](https://raw.githubusercontent.com/subhajit51193/TestAppWithJWTLoginSystem/main/projectImage.jpg)


# JWT Login System

This is a basic application with entity named patients where patients can register themselves and signin with their credentials. And access basic CRUD endpoints.


## Features

- Signup and Signin with Spring Security and JWT
- Basic CRUD operations


## API Reference

 - POST : Register patients
 - GET : Signin using credentials
 - GET : Access own details
 - GET : Get one patient details
 - GET : Get all patients details
 - PUT : Update patient details
 - DELETE : Delete patient details


## Installation

Download and run with following configurations
Change DB details accordingly

```bash
server.port=8048
#db specific properties
spring.datasource.url=jdbc:mysql://${DB_HOST:localhost}:${DB_PORT:3306}/${DB_NAME:testApp}
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.username=${DB_USERNAME:root}
spring.datasource.password=${DB_PASSWORD:sql@subhajit51193}

#ORM s/w specific properties
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```
    
## Deployment

Not yet deployed




## Demo

In progress...

## Tech Stack

**Client:** Java,SpringBoot,MySQL

**Server:** Embedded


## Authors

- [@Subhajit Saha](https://github.com/subhajit51193)


## Feedback

If you have any feedback, please reach out to us at nnorth87@gmail.com


## 🔗 Links
[![portfolio](https://img.shields.io/badge/my_portfolio-000?style=for-the-badge&logo=ko-fi&logoColor=white)](https://subhajit51193.github.io/)
[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/subhajit-saha-103110185/)
