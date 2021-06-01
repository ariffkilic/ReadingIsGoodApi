# Reading Is Good Api
  
### Technologies Used
  - Java 11
  - Maven
  - Docker
  - Spring boot
  - Mockito
  - Junit5 
  - H2 database

# Steps to run

#### Run app 
    - Execute "mvn clean install" command on <project path
    - Execute java -jar .\reading-app-0.0.1-SNAPSHOT.jar

#### Run app on Docker
    - docker build -t reading-app .
    - docker run -d -p 8080:8080 reading-app
    
#### Application UP on IDE
    - There is no any configuration needed.
    - Execute ReadingAppApplication.java
    
### Some information
    - Project has 4 rest controller and 8 endpoints.
    - H2 is a runtime database running JVM. When application closes, datas are erased.
    - Project has 4 tables. Book, Customer, Order and Order_Book tables.
    - When application is starting, some datas are inserted. If you wanna see, you can look import.sql file.
    - Some customers and books are inserted database, you can search them via Rest Services.
    - If you wanna search orders and statistics, firstly you must insert some data via Order services.
    - Swagger UI : http://localhost:8080/swagger-ui.html (if you up on ide, you will see with this url.)
