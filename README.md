# Struts2 Embedded Tomcat Example
Example of using the Struts2 framework with an embedded Tomcat webserver for easy development and deployment.

## Technologies
- Java 23
- Struts 7.0.0
- Tomcat 11
- Gradle 8.11.1

## Structure
The project's web resources (such as web.xml, JSP files, and other static assets) are located in:
`src/main/resources/app`
This directory serves as the root of your web application, replacing the typical `src/main/webapp` structure in a standalone server environment.

## How to Build and Run the Project

### 1. Build the project
To build the project, open a terminal in the project root directory and execute:
```bash
./gradlew build
```
This command compiles the application and packages it as a JAR file. The JAR file can be found in `build/libs`.

### 2. Run the application
Execute the following command in your project root directory:
```bash
java -jar build/libs/EmbeddedTomcatStruts-0.0.1.jar
```
Once you see a message confirming the webserver has been started up, you can access the application at: http://localhost:8080/

You can stop the server by using the `CTRL + C` shortcut.

### 3. Run using an IDE
Alternatively, you can run the application directly from your IDE:

Open the project in your preferred IDE (e.g., IntelliJ IDEA, Eclipse).
Open the [Starter](src/main/java/dev/boriskole/example/Starter.java) class and run the main method in the class.