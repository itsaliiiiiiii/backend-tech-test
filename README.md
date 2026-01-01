# Recipe Management API

A robust Spring Boot backend application for managing recipes, categories, ingredients, and nutrition data. This application serves as the RESTful API for a modern recipe platform.

## 🚀 Features

-   **Recipe Management**: Create, read, update, and delete recipes.
-   **Category Organization**: Browse recipes by categories (e.g., Breakfast, Vegan, Asian).
-   **Rich Data Model**: Support for ingredients, step-by-step directions, and nutritional information.
-   **Data Seeding**: Automatically initializes the database with sample data (5 categories, 5 recipes) for quick testing.
-   **API Documentation**: Integrated Swagger/OpenAPI documentation.
-   **Dual Database Support**: seamless switching between H2 (in-memory, dev) and PostgreSQL (production).

## 🛠 Tech Stack

-   **Java 17**
-   **Spring Boot 3.3.0**
-   **Spring Data JPA** (Hibernate)
-   **Database**: H2 (Development), PostgreSQL (Production)
-   **Lombok**: For reducing boilerplate code.
-   **MapStruct**: For efficient Entity-DTO mapping.
-   **OpenAPI (Swagger)**: For API documentation.
-   **Spring Dotenv**: For managing environment variables.

## 📋 Prerequisites

-   Java Development Kit (JDK) 17 or higher
-   Maven 3.6+
-   (Optional) PostgreSQL for production database usage

## ⚙️ Configuration

The application uses a `.env` file for sensitive configuration (database credentials) or defaults to an in-memory H2 database if no configuration is provided.

### Environment Variables (.env)

Create a `.env` file in the root directory if you wish to use PostgreSQL:

```properties
DB_URL=jdbc:postgresql://<HOST>:<PORT>/<DB_NAME>
DB_USERNAME=<USERNAME>
DB_PASSWORD=<PASSWORD>
DB_DRIVER=org.postgresql.Driver
DB_DIALECT=org.hibernate.dialect.PostgreSQLDialect
```

*Note: If these variables are not set, the application defaults to H2.*

## 🏃‍♂️ Running the Application

1.  **Clone the repository**:
    ```bash
    git clone https://github.com/itsaliiiiiiii/backend-tech-test.git
    cd backend
    ```

2.  **Build the project**:
    ```bash
    ./mvnw clean install
    ```

3.  **Run the application**:
    ```bash
    ./mvnw spring-boot:run
    ```

The application will start on `http://localhost:8080`.

## 📚 API Documentation

Once the application is running, you can access the interactive Swagger UI documentation at:

👉 **[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)**

### Key Endpoints

| Method | Endpoint | Description |
| :--- | :--- | :--- |
| **GET** | `/api/recipes` | Get all recipes |
| **GET** | `/api/recipes/{id}` | Get recipe by ID |
| **POST** | `/api/recipes` | Create a new recipe |
| **PUT** | `/api/recipes/{id}` | Update a recipe |
| **DELETE** | `/api/recipes/{id}` | Delete a recipe |
| **GET** | `/api/categories` | Get all categories |

## 🧪 Testing

Run unit and integration tests using Maven:

```bash
./mvnw test
```

## 📂 Project Structure

```
com.example.recipes
├── config          # App configuration (DataSeeder, OpenAPI, WebConfig)
├── controller      # REST Controllers (API Endpoints)
├── dto             # Data Transfer Objects
├── entity          # JPA Entities (Database Model)
├── exception       # Global Exception Handling
├── mapper          # MapStruct Mappers
├── repository      # Data Access Layer
└── service         # Business Logic Layer
```

## 🤝 Contributing

1.  Fork the repository.
2.  Create a feature branch (`git checkout -b feature/amazing-feature`).
3.  Commit your changes (`git commit -m 'Add amazing feature'`).
4.  Push to the branch (`git push origin feature/amazing-feature`).
5.  Open a Pull Request.
