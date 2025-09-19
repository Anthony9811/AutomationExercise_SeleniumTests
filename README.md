# 🌐 Automation Exercise E2E Test Suite

## Project Overview
This project is an End-to-End (E2E) test automation suite developed using Selenium WebDriver and Java, focused on validating the test cases from www.automationexercise.com.

It's built on the **Page Object Model (POM)** architecture to ensure high maintainability and code reusability, serving as a robust portfolio piece that demonstrates best practices in web automation.

# 🛠️ Technology Stack
* **Language**: Java 17+.
* **Test Framework**: TestNG.
* **Automation Tool**: Selenium WebDriver.
* **Architecture**: Page Object Model (POM).
* **Build Tool**: Maven.

## 📂 Repository Structure
* `src/main/java/pages`: Contains all Page Object classes. These abstract the UI by holding locators and action methods.
* `src/main/java/data`: Contains Data Objects (POJOs) (e.g., `UserInformation.java`). These models cleanly pass structured test data to the Page Objects.
* `src/test/java/tests`: Contains the TestNG test classes. These define the high-level test logic and assertions.
* `pom.xml`: Defines all project dependencies and build settings.
## ⚙️ Test Automation Concepts Demonstrated
The framework showcases stability and professional design through the following techniques:

### Design & Data Handling
* **Data-Driven Design**: Test data is separated into dedicated POJOs, making test methods concise and improving readability by passing a single object instead of a long parameter list (e.g., `signUpPage.setUserInformation(userData)`).

### Stability & Robustness
* **Explicit Waits**: `WebDriverWait` and `ExpectedConditions` are used throughout to prevent flaky tests by ensuring elements are ready for interaction (present, visible, or clickable).
* **Advanced Interaction**: `JavascriptExecutor` is utilized to handle complex UI scenarios, such as scrolling elements into the viewport to bypass common `ElementClickInterceptedException` issues.

### Flow Control
* **Setup Assertions**: The prerequisite check ("Verify home page is visible") is executed within a shared `@BeforeMethod` block. If this configuration fails, all associated test methods are automatically **skipped**, guaranteeing a known starting state and preventing irrelevant test failures.

## 🚀 Getting Started
### Prerequisites
* Java Development Kit (JDK) 17 or higher.
* Apache Maven.
* Google Chrome browser.
* An IDE like IntelliJ IDEA _(preferable)_, Eclipse or NetBeans.

### Setup Instructions
1. Clone the repository: `https://github.com/Anthony9811/AutomationExercise_SeleniumTests.git`.
2. Navigate to the project's directory.
3. Build the project open the command line on you IDE and enter the following command: `mvn clean install`.
4. Run the tests:

You have a few options for running the tests:

- **From the command line (Maven):**
  Use the following command to run all tests in the project:
  `mvn clean test`

- **Using the TestNG Suite (NOT AVAILABLE YET):**
  Open the project in your IDE _(preferably IntelliJ IDEA)_ and right-click on the `testng.xml` file. Select the "Run" option to execute the entire test suite.

## 📋 Implemented Test Cases
### ✅ Test Case 1: Register User
- **Goal**: Verify the full user lifecycle: successful account creation, confirmed login status, and subsequent account deletion with final confirmation.
- **Concepts**: Multi-page navigation, form filling (using data providers), handling radio buttons/dropdowns, text validation for successful login, and final confirmation of account deletion.

**Flow**:
1. **Account Creation**: Navigate to sign-up, fill out all user and address details (using the `UserInformation` data object).
2. **Login Confirmation**: Verify the successful logged-in status.
3. **Account Deletion**: Delete the newly created account.
4. **Confirmation**: Verify that the account deletion message is displayed.

