# Binary Search Tree DSA App (Spring Boot)

Enter numbers, build a Binary Search Tree, view previous trees, and (optionally) return a balanced BST.

## Requirements
- Java 17+
- Maven 3.9+

## Thymeleaf in this Project

This application uses **Thymeleaf** as its server-side template engine.  

### What is Thymeleaf?

Thymeleaf is a modern Java template engine for web applications. It allows you to create **dynamic HTML pages** by embedding variables and expressions directly into your HTML. Unlike rendering pure JSON responses, Thymeleaf lets the server generate fully-formed HTML views that can include dynamic content, loops, conditionals, and more.

### Why Thymeleaf is used here

In this project, Thymeleaf is used to:

- Render the **number input form** for users to enter a series of numbers (`/enter-numbers` route).
- Display the **visual representation of the Binary Search Tree** after processing (`/process-numbers` route).
- Show the **list of previously created trees** retrieved from the database (`/previous-trees` route).

Using Thymeleaf allows the server to directly inject dynamic data, such as the JSON representation of a BST, into the HTML. This enables interactive pages without needing a separate frontend framework like React or Angular.

## Quick Start
To start, please ensure that the application is running on a local port
```bash
mvn spring-boot:run
```
Open:
- Enter numbers: http://localhost:8080/enter-numbers
- Previous trees: http://localhost:8080/previous-trees
- H2 console: http://localhost:8080/h2-console (JDBC URL: `jdbc:h2:mem:bstdb`)

## Routes
- `GET /enter-numbers` – HTML form
- `POST /process-numbers` – builds BST, returns JSON
  - Optional param: `balanced=true` to return a balanced BST (bonus)
- `GET /previous-trees` – shows saved inputs + trees

## Example POST
```
numbers=2,1,4,3,5
```

Sample JSON return format:
```json
{"value":2,"left":{"value":1,"left":null,"right":null},"right":{"value":4,"left":{"value":3,"left":null,"right":null},"right":{"value":5,"left":null,"right":null}}}
```

## Tests
Run:
```bash
mvn test
```
Includes 3 unit tests in `BSTTests.java`:
- testing to see if the root was set after first insert
- left/right placement after inserts
- balanced tree from sorted array
