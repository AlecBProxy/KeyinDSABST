# Binary Search Tree DSA App (Spring Boot)
Enter numbers, build a Binary Search Tree, view previous trees, and (optionally) return a balanced BST.

## Requirements
- Java 17+
- Maven 3.9+
- MySQL Server (local installation)

## Setup

### Database Configuration
1. **Install MySQL** on your local machine if not already installed
2. **Create a database** named `bst_db`:
   ```sql
   CREATE DATABASE bst_db;
   ```
3. **Update application.properties** with your MySQL credentials:
   ```properties
   # In src/main/resources/application.properties
   spring.datasource.url=jdbc:mysql://localhost:3306/bst_db
   spring.datasource.username=YOUR_USERNAME_HERE
   spring.datasource.password=YOUR_PASSWORD_HERE
   spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
   spring.jpa.hibernate.ddl-auto=create-drop
   spring.jpa.show-sql=true
   ```
   **Replace `YOUR_USERNAME_HERE` and `YOUR_PASSWORD_HERE` with your actual MySQL credentials.**

### Running the Application
```bash
mvn spring-boot:run
```

The application will start on **http://localhost:8080**

## User Guide

### Main Features

#### 1. Creating Binary Search Trees
- Navigate to **http://localhost:8080/enter-numbers** (or just **http://localhost:8080**)
- Enter comma-separated numbers (e.g., `3, 1, 4, 5, 2`)
- **Choose your tree type**:
  - **Unchecked "Return balanced tree"**: Creates a standard BST by inserting numbers sequentially
  - **Checked "Return balanced tree"**: Creates an optimally balanced BST for better performance
- Click **"Submit Numbers"** to see the JSON representation immediately

#### 2. Viewing JSON Output
- After submitting numbers, the **JSON structure** of your tree appears below the form
- This shows the complete tree structure with nodes and their left/right children
- Each submission automatically saves to the database

#### 3. Visual Tree Representation
- Click **"View Tree"** to see a graphical representation of your BST
- **Important**: The visual always shows the **most recently created tree**
- Great for understanding the tree structure visually

#### 4. Tree History
- Visit **http://localhost:8080/previous-trees** to see all your past trees
- View a detailed table showing:
  - **Input Numbers**: Original numbers you entered
  - **Balanced?**: Whether the balanced option was used
  - **Tree JSON**: Complete tree structure
- Useful for comparing balanced vs. unbalanced trees with the same input

### Example Workflow
1. Enter numbers: `5, 2, 8, 1, 3, 7, 9`
2. Try **without** balance option → see sequential insertion result
3. Try **with** balance option → see optimally balanced result
4. Click **"View Tree"** → see visual representation
5. Visit **"Previous Trees"** → compare both versions side by side

## Thymeleaf in this Project
This application uses **Thymeleaf** as its server-side template engine.  

### What is Thymeleaf?
Thymeleaf is a modern Java template engine for web applications. It allows you to create **dynamic HTML pages** by embedding variables and expressions directly into your HTML. Unlike rendering pure JSON responses, Thymeleaf lets the server generate fully-formed HTML views that can include dynamic content, loops, conditionals, and more.

### Why Thymeleaf is used here
In this project, Thymeleaf is used to:
- Render the **number input form** for users to enter a series of numbers (`/enter-numbers` route).
- Display the **visual representation of the Binary Search Tree** after processing (`/visualize` route).
- Show the **list of previously created trees** retrieved from the database (`/previous-trees` route).

Using Thymeleaf allows the server to directly inject dynamic data, such as the JSON representation of a BST, into the HTML. This enables interactive pages without needing a separate frontend framework like React or Angular.

## API Routes
- `GET /` or `/enter-numbers` – Main input form
- `POST /process-numbers` – Builds BST, returns JSON
  - Required param: `numbers` (comma-separated values)
  - Optional param: `balanceTree=true` to return a balanced BST
- `GET /visualize` – Visual tree representation (shows latest tree)
- `GET /previous-trees` – Database history of all trees
- `GET /previous-trees/latest` – JSON of most recent tree

## Example Usage

### POST Request
```
POST /process-numbers
Content-Type: application/x-www-form-urlencoded

numbers=2,1,4,3,5&balanceTree=false
```

### Sample JSON Response
```json
{
  "value": 2,
  "left": {
    "value": 1
  },
  "right": {
    "value": 4,
    "left": {
      "value": 3
    },
    "right": {
      "value": 5
    }
  }
}
```

## Tests
Run unit tests:
```bash
mvn test
```

Includes 3 comprehensive tests in `BSTTests.java`:
- **Single node insertion**: Verifies root is set correctly
- **Multiple node placement**: Tests left/right BST property
- **Balanced tree construction**: Validates optimal balanced tree from sorted array

All tests should pass with **BUILD SUCCESS** status.

## Troubleshooting
- **Database connection issues**: Verify MySQL is running and credentials are correct in `application.properties`
- **Port conflicts**: If port 8080 is busy, add `server.port=8081` to `application.properties`
- **Test failures**: Ensure you're using the updated BST implementation with correct balanced tree algorithm
