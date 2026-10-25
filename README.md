# Government Portal Selenium Tests

Selenium WebDriver automation tests for validating form submissions and field validation on a demo government service portal using Java, Maven, TestNG, and Jenkins.

## Project Overview

This project automates the testing of a local demo government service form. It verifies mandatory field validation, email format validation, mobile number validation, and successful form submission.

The project demonstrates browser automation and continuous integration using Selenium WebDriver, TestNG, Maven, GitHub, and Jenkins.

**Note:** This project uses a local demo HTML form and does not connect to a real government portal or store application data in a database.

## Technologies Used

- Java 21
- Selenium WebDriver 4.41.0
- TestNG 7.11.0
- Apache Maven 3.10.0
- Google Chrome
- Git and GitHub
- Jenkins
- HTML and JavaScript

## Project Structure

```text
government-portal-testing/
├── src/
│   └── test/
│       ├── java/
│       │   └── tests/
│       │       └── FormSubmissionTest.java
│       └── resources/
│           └── form.html
├── pom.xml
├── testng.xml
├── Jenkinsfile
└── .gitignore
```

## Automated Test Cases

| Test Case | Description |
|---|---|
| Empty Form Validation | Verifies that an empty form is invalid |
| Email Validation | Checks rejection of an invalid email address |
| Mobile Number Validation | Checks rejection of a mobile number with fewer than 10 digits |
| Successful Submission | Verifies valid form submission and the success message |

## Prerequisites

Install the following software before running the project:

- JDK 21
- Apache Maven
- Google Chrome
- Git (optional, for cloning the repository)

Selenium Manager automatically manages the required browser driver in supported environments.

## How to Run the Project

### 1. Clone the repository

```bash
git clone https://github.com/shravani078/government-portal-selenium-tests.git
cd government-portal-selenium-tests
```

### 2. Verify Java and Maven

```bash
java -version
mvn -version
```

Ensure that Java 21 is being used.

### 3. Execute the automated tests

```bash
mvn clean test
```

Maven compiles the test code, runs the TestNG test suite, and generates test reports.

## Test Results

The project is designed to execute four automated test cases covering form validation and submission.

A successful execution should report:

- Total tests: 4
- Failures: 0
- Errors: 0
- Skipped: 0
- Build status: SUCCESS

Actual results may vary if the environment or code changes.

## Continuous Integration with Jenkins

The project includes a `Jenkinsfile` for running the tests through a Jenkins pipeline.

The pipeline performs the following stages:

1. Checks out the source code from GitHub.
2. Checks Java and Maven availability.
3. Executes the Selenium tests using Maven.
4. Publishes JUnit-compatible test results and archives the Surefire reports.

## Test Reports

After execution, Maven generates test reports in:

```text
target/surefire-reports/
```

## Repository

GitHub: https://github.com/shravani078/government-portal-selenium-tests

## Author

Developed as an academic project to demonstrate web form automation testing and continuous integration.
