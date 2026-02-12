# SAM_AUTO - Sampark Automation Project

**SAM_AUTO** is a robust automation suite built to streamline and automate processes for **Sampark's AIS-140** and **Lite versions**. It utilizes modern testing frameworks to ensure the highest standards of reliability and efficiency for Sampark's service offerings.

---

## 🚀 Key Features

- **End-to-End UI Testing**: Comprehensive web automation using Selenium WebDriver.
- **API Testing**: Integration testing for RESTful services via REST Assured.
- **Page Object Model (POM)**: Maintainable and scalable design architecture.
- **Custom Reporting**: Rich HTML reports generated via ExtentReports.
- **Excel Data Driven**: Support for external test data management using Apache POI.
- **Robust Logging**: Detailed execution logs powered by Log4j2.
- **Email Integration**: Automated email reading and verification capabilities.

---

## 🛠️ Tech Stack

| Technology | Purpose |
| :--- | :--- |
| **Java 17** | Core programming language |
| **Selenium WebDriver** | Web browser automation |
| **TestNG** | Testing framework and execution control |
| **Maven** | Dependency management and build tool |
| **REST Assured** | API testing library |
| **ExtentReports** | Execution reporting and dashboards |
| **Apache POI** | Excel data processing |
| **Log4j2** | Application and test logging |

---

## 📁 Project Structure

The project follows a modular Page Object Model (POM) structure:

- `src/main/java/com/aepl/sam/base`: Contains `TestBase` for setup and teardown.
- `src/main/java/com/aepl/sam/pages`: Page classes representing web components.
- `src/main/java/com/aepl/sam/locators`: Centralized storage for element locators.
- `src/main/java/com/aepl/sam/actions`: Custom action wrappers (e.g., Mouse events).
- `src/main/java/com/aepl/sam/utils`: Helper classes (Excel, Config, WebDriverFactory, etc.).
- `src/test/java/com/aepl/sam/tests`: TestNG test suites.
- `src/main/resources`: Configuration files (`qa.config.properties`) and log settings.

---

## 🏁 Getting Started

### Prerequisites

- **Java JDK 17** or higher.
- **Apache Maven** installed and configured.
- A modern web browser (Chrome/Firefox/Edge).

### Installation

1. Clone the repository:
   ```bash
   git clone <repository-url>
   ```
2. Navigate to the project directory:
   ```bash
   cd SAM_AUTO
   ```
3. Install dependencies:
   ```bash
   mvn clean install
   ```

### Configuration

The framework uses property files located in `src/main/resources/`.
Update `qa.config.properties` with your environment-specific details:
- `browser`: The browser to use for execution.
- `username`/`password`: Credentials for application login.
- `baseUrl`: The entry point URL of the application.

---

## 🧪 Running Tests

### Via Maven
To run the entire suite defined in `testng.xml`:
```bash
mvn test
```

### Via TestNG XML
Right-click on `testng.xml` in your IDE and select **Run As > TestNG Suite**.

---

## 📊 Reporting & Logs

- **Reports**: After execution, ExtentReports are typically generated in the `test-output` or `reports` directory.
- **Logs**: Detailed logs can be found in the `logs/` directory, configured via `log4j2.xml`.

---

## 🤝 Internal Contributions

This is an internal project. Contributions are welcome from authorized Sampark/AEPL team members. 
- Please follow the [Internal Code of Conduct](CODE_OF_CONDUCT.md).
- Ensure all code follows the established Page Object Model patterns.
- Open a PR for any changes and tag the Project Lead for review.

---

> [!IMPORTANT]
> This repository is **Confidential** and for **Internal Use Only**. Unauthorized distribution of the code or documentation is strictly prohibited.
