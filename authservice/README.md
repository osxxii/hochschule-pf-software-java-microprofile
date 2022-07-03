# Home Improvement - Auth-Service
This project ist part of the Home Improvement application and provides a backend service to authenticate and authorize users. This service provides a RESTful API to save users in a database. Furthermore, the API can be used to authenticate and authorize users. After a sucessfull authorization, the service returns a JSON Web Token (JWT) that can be used to use protected services.

## How to configure the project
To run the project, you need to install the following technologies:
- **Java** (version 8, OpenJDK recommended)
- **[Apache Maven](https://maven.apache.org/)**
- *Application Server* ([Payara Micro](https://www.payara.fish/products/payara-platform-community/) recommended)
- *Integrated Development Environment* (IDE, [Apache NetBeans]https://netbeans.apache.org/) recommended)

If you use Apache NetBeans as an IDE, you do not need to install Apache Maven separately. The application server will be included in our packaging process via Apache Maven, so you won't need to install it manually. Simply open the project in NetBeans.

## How to run the project
To run the project you need an application server. For this purpose, Payara Micro is recommended. To run the project with Payara Micro, there are (at least) two possibilities:
1. Using NetBeans Interface
2. Using the command line

### 1. Using NetBeans
NetBeans provides support to create and run Payara Micro maven web applications from the NetBeans interface. Payara Micro implements Jakarta EE Microprofile and is the microservices-ready version of Payara Server. Before you run the project, right click on the project properties and make sure that no server is selected in the run section. 
![NetBeans Project Properties](/images/netbeans_runconfig.png)

Also make sure, that in the Payara Micro section, the version "defined in pom.xml" is selected. If these settings are made properly, you can execute the project by clicking ```Run -> Build Project``` and then ```Run -> Run Project```.

### 2. Using the command line
If you work on command line, you need to have Apache Maven setup properly first. Before you are able to deploy the application, make sure to create a `.war` file by using the command `mvn package` in the root folder (this folder) of the project. The resulting package can then be deployed to the application server. 
Therefore you have to use the following command: 
```bash
java -jar <path-to-payara-micro-jar-file> --deploy <path-to-war-file-of-project>```
``` 
In the case that the this project and the payara micro jar file (version 5.2021.4 or later) are in the same folder you can deploy the application with the following command: 
```bash
java -jar payara-micro-5.2021.4.jar  --deploy homeimprovement-profileservice/target/profileservice.war
```

Make sure that the path and the payara micro jar file are addressed correctly.

## RESTful API
When the service is running, you can use the REST-Endpoints to test and use the functionality. A good and easy to use tool to access and test RESTful APIs is [Postman](https://www.postman.com/).

### Sign Up
http://localhost:8080/authservice/data/auth/signup with http method POST and the body:

```json
{
    "username": "",
    "email": "",
    "password": ""
}
```

After a successful registration of the user the endpoint returns an Account object with http status 200. Otherwise you will get http status 409 (Conflict).
### Login
http://localhost:8080/authservice/data/auth/login with http method POST and the body:

```json
{
    "email": "",
    "password": ""
}
``` 

After providing the correct credentials a jwt is returned.
