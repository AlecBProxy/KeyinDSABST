# Binary Search Tree DSA App (Spring Boot)

Enter numbers, build a Binary Search Tree, view previous trees, and (optionally) return a balanced BST.

## Requirements
- Java 17+
- Maven 3.9+

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
