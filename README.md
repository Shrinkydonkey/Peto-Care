# Pet Adoption System - Java Web Application

## Project Structure

```
PetAdoptionSystem/
├── src/main/java/com/petadopt/
│   ├── model/                  # Entity classes (User, Pet, Shelter, AdoptionRequest)
│   ├── dao/                    # Data Access Objects (JDBC operations)
│   ├── servlet/                # Servlets (Controllers)
│   └── util/                   # Utilities (DatabaseConnection)
├── src/main/webapp/           # JSP pages and static files
│   ├── WEB-INF/
│   │   └── web.xml
│   ├── login.jsp
│   ├── register.jsp
│   ├── home.jsp
│   ├── pets.jsp
│   └── ...
└── sql/                       # Database schema
    └── schema.sql
```

## Setup Instructions

### 1. Database Setup

1. Install MySQL Server
2. Create database:
   ```sql
   CREATE DATABASE pet_adoption_db;
   ```
3. Run the schema:
   ```bash
   mysql -u root -p pet_adoption_db < sql/schema.sql
   ```

### 2. Update Database Configuration

Edit `src/main/java/com/petadopt/util/DatabaseConnection.java`:

```java
private static final String URL = "jdbc:mysql://localhost:3306/pet_adoption_db";
private static final String USERNAME = "your_username";
private static final String PASSWORD = "your_password";
```

### 3. Add Dependencies

Add MySQL Connector to your project:

**Maven** (pom.xml):
```xml
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.33</version>
</dependency>

<dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>javax.servlet-api</artifactId>
    <version>4.0.1</version>
    <scope>provided</scope>
</dependency>

<dependency>
    <groupId>javax.servlet.jsp</groupId>
    <artifactId>javax.servlet.jsp-api</artifactId>
    <version>2.3.3</version>
    <scope>provided</scope>
</dependency>

<dependency>
    <groupId>jstl</groupId>
    <artifactId>jstl</artifactId>
    <version>1.2</version>
</dependency>
```

### 4. Deploy Application

1. Build the project
2. Deploy WAR file to Tomcat server
3. Access: `http://localhost:8080/PetAdoptionSystem/`

## Test Accounts

After running schema.sql, these accounts are available:

| Role    | Email                    | Password    |
|---------|-------------------------|-------------|
| Admin   | admin@petadopt.com      | admin123    |
| Shelter | shelter@petadopt.com    | shelter123  |
| Adopter | adopter@petadopt.com    | adopter123  |

## Key Features Demonstrated

### 1. Core Java Concepts (10 marks)
- ✅ **Classes & Objects**: User, Pet, Shelter, AdoptionRequest models
- ✅ **Encapsulation**: Private fields with getters/setters
- ✅ **Collections**: ArrayList<Pet>, List<User>, etc.
- ✅ **Exception Handling**: try-catch blocks in all DAO methods

### 2. Database Integration (JDBC) (8 marks)
- ✅ **Connection Management**: DatabaseConnection utility
- ✅ **CRUD Operations**: Create, Read, Update, Delete in all DAOs
- ✅ **PreparedStatement**: SQL injection prevention
- ✅ **Transaction Management**: updateRequestStatus() with commit/rollback
- ✅ **ResultSet Processing**: Data extraction methods

### 3. Servlets & Web Integration (7 marks)
- ✅ **HTTP Methods**: doGet() and doPost() implementations
- ✅ **Request/Response**: Parameter handling, forwarding, redirecting
- ✅ **Session Management**: Login session tracking
- ✅ **Role-based Access**: Different dashboards for roles

### 4. Problem Understanding (8 marks)
- ✅ **Three Actors**: Adopter, Shelter Staff, Admin
- ✅ **Use Cases**: Browse pets, apply for adoption, manage requests
- ✅ **MVC Architecture**: Model (Java classes), View (JSP), Controller (Servlets)

## Architecture Diagram

```
Browser
   ↓
Login.jsp → LoginServlet → UserDAO → MySQL Database
   ↓                           ↓
Session Created         JDBC Connection
   ↓                           ↓
Dashboard.jsp           Query Execution
```

## API Endpoints

| Servlet Path | Method | Description |
|-------------|--------|-------------|
| /login      | POST   | User login |
| /register   | POST   | User registration |
| /logout     | GET    | User logout |
| /pets       | GET    | List/filter pets |
| /pets       | POST   | Add/update pet |
| /adoption   | POST   | Apply for adoption |
| /adoption   | POST   | Update request status |

## Transaction Example

When approving adoption request (AdoptionRequestDAO.updateRequestStatus()):

```java
conn.setAutoCommit(false);  // Start transaction

// 1. Update adoption request status to 'approved'
// 2. Update pet status to 'adopted'
// 3. Reject all other pending requests for same pet

conn.commit();  // Commit if all successful
// OR
conn.rollback();  // Rollback if any operation fails
```

## Important Notes

1. **Security**: Passwords are stored in plain text for demo. Use BCrypt/hashing in production.
2. **Validation**: Add input validation on both client and server side.
3. **Error Handling**: Implement proper error pages and logging.
4. **File Upload**: Add image upload functionality for pet photos.

## Next Steps for Review-2

- Add image upload for pets
- Implement email notifications
- Add search functionality
- Create reports/analytics
- Add more filters and sorting
- Implement password hashing
- Add pagination for large datasets
