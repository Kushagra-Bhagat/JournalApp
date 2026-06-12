# 📓 Journal App

A Spring Boot-based journaling application that allows users to securely create, manage, and retrieve personal journal entries. Features JWT-based authentication, automated weekly reports, and motivational quote integration.

---

## 🚀 Features

- **JWT Authentication** — Secure login and registration with token-based auth
- **Role-Based Access Control** — Protected endpoints using Spring Security
- **Journal Management** — Full CRUD operations for personal journal entries
- **Weekly Summary Reports** — Automated cron job runs every Sunday to generate journal summaries
- **Motivational Quotes** — Integrated third-party Quotes API to serve randomized quotes on demand
- **Environment Profiles** — Separate `dev` and `prod` profiles for environment-specific configurations
- **Code Quality** — SonarQube integrated for static code analysis and reducing code smells

---

## 🛠️ Tech Stack

| Layer | Technology |
|-------|------------|
| Backend | Java, Spring Boot |
| Security | Spring Security, JWT |
| Database | MongoDB |
| ORM | Spring Data MongoDB |
| Scheduler | Spring Cron (`@Scheduled`) |
| Code Quality | SonarQube |
| Version Control | Git, GitHub |

---

## 📁 Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/journal/
│   │       ├── controller/       # REST API controllers
│   │       ├── service/          # Business logic
│   │       ├── repository/       # MongoDB repositories
│   │       ├── model/            # Entity/domain classes
│   │       ├── config/           # Security & app config
│   │       └── scheduler/        # Cron job for weekly reports
│   └── resources/
│       ├── application.properties
│       ├── application-dev.properties
│       └── application-prod.properties
```

---

## ⚙️ Getting Started

### Prerequisites
- Java 17+
- Maven
- MongoDB (local or Atlas)

### Steps

1. **Clone the repository**
   ```bash
   git clone https://github.com/Kushagra-Bhagat/journal-app.git
   cd journal-app
   ```

2. **Configure MongoDB**
   Update `application-dev.properties`:
   ```properties
   spring.data.mongodb.uri=mongodb://localhost:27017/journaldb
   ```

3. **Run the application**
   ```bash
   mvn spring-boot:run -Dspring-boot.run.profiles=dev
   ```

4. **Access the API**
   ```
   http://localhost:8080
   ```

---

## 🔐 API Endpoints

| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| POST | `/auth/register` | Register new user | No |
| POST | `/auth/login` | Login and get JWT token | No |
| GET | `/journal` | Get all journal entries | Yes |
| POST | `/journal` | Create a new entry | Yes |
| PUT | `/journal/{id}` | Update an entry | Yes |
| DELETE | `/journal/{id}` | Delete an entry | Yes |
| GET | `/quotes` | Get a random motivational quote | Yes |

---

## 📅 Cron Job

A scheduled task runs every Sunday at 8:00 AM to generate a weekly summary report of the user's journal entries.

```java
@Scheduled(cron = "0 0 8 * * SUN")
public void generateWeeklySummary() { ... }
```

---

## 🧪 Code Quality

SonarQube is configured for static analysis. To run:
```bash
mvn sonar:sonar
```

---

## 📬 Contact

**Kushagra Bhagat**
- GitHub: [@Kushagra-Bhagat](https://github.com/Kushagra-Bhagat)
- LinkedIn: [linkedin.com/in/Kushagra-Bhagat](https://linkedin.com/in/Kushagra-Bhagat)
