# Notifications
#### In this project we'll learn how to send notifications (Emails).

## Technologies:
* Java 11
* Spring Boot 2.7.5-SNAPSHOT
* Maven 3.8.6
* MySql
## Tools:
* Intellij
* Git
* MySql
* Postman
## Authentication:
* In this project we used Basic Authentication (username & password).
* Due to lack of source we are taking username and password from property file.
* In live we can validate these credentials from SSM Parameter Store/Pipeline.
## Requirements:
#### For building and running the application:

* JDK 11.0.16
* Apache Maven 3.8.6
* MySql

#### Running the application locally:

* Clone the Git repository or download zip file.
* Open command promt from the cloned project directory (if downloaded zip file, then unzip file into a directory).
* Run the below commands
```
mvn clean install
mvn package
```
There are several ways to run a Spring Boot application on your local machine. One way is to execute the main method in the com.app.notifications.NotificationsApplication class from your IDE.

Alternatively you can use the Spring Boot Maven command in command prompt/terminal like :
```
mvn spring-boot:run
```
This application runs on port 8181.

#### Run application in docker container:

1.Build image
```
docker build [OPTIONS] PATH | URL | -
```

2.Run application in container
```
docker run -d --name notificationsapplication -p 8181:8181 [image id]
```
## Swagger Documentation:
http://localhost:8181/swagger-ui/index.html#/
## Postman collection:
https://www.getpostman.com/collections/750b5bbd45f3e4710e1d
