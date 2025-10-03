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

### ✅ Test Cases 2 & 4: User Login and Safe Logout
- **Goal**: Validate a user's ability to successfully log in with correct credentials and then securely log out. The test verifies the visibility of the "Logged in as _username_" element after login and asserts its invisibility after clicking the logout button.
- **Concepts**: Login form submission, successful assertion validation, graceful session termination (logout), and verifying element absence/invisibility.

**Flow**:
1. **Navigate & Login**: Navigate to the site, go to the Login page, and enter valid credentials.
2. **Login Confirmation**: Verify the session is active (user is logged in).
3. **Logout**: Click the 'Logout' button.
4. **Session Termination**: Verify the user is successfully logged out.

### ✅ Test Case 3: Negative Login Test
- **Goal**: Validate that the application correctly handles login attempts with incorrect email and password combinations. The test asserts that the specific error message, "**Your email or password is incorrect!**", is visible after the login button is clicked.
- **Concepts**: Negative testing, form submission validation, and accurate text assertion for error messages.

**Flow**:
1. **Navigate & Login**: Navigate to the site, go to the Login page.
2. **Input Invalid Data**: Enter a non-existent or incorrect email address and/or password.
3. **Attempt Login**: Click the 'Login' button.
4. **Error Verification**: Assert that the expected login error message is displayed.

### ✅ Test Case 5: Existing Email Registration
- **Goal**: Verify that a new user account cannot be created using an email address that is already registered in the system, and that an appropriate error message is displayed to the user.
- **Concepts**: Validate the negative user registration scenario by attempting to sign up with an email address that is already registered in the system. The test asserts that the correct error message "Email Address already exist!" is displayed.

**Flow**:
1. **Navigate & Signup**: Go to the home page, click on 'Signup / Login', and verify that 'New User Signup!' is visible.
2. **Input Existing Data**: Enter a name and an email address that is already registered with an existing account.
3. **Attempt Signup**: Click the 'Signup' button.
4. **Error Verification**: Assert that the expected error message is displayed on the page.

### ✅ Test Case 6: Contact Us Form Submission
- **Goal**: Validate the entire "Contact Us" form workflow, from filling out the form and uploading a file to verifying the success message and navigating back to the home page.
- **Concepts**: Form filling, file upload, interacting with JavaScript alerts, text validation for success messages, and navigating between pages.

**Flow**:
1. **Navigate**: Navigate to the 'Contact Us' page from the home page.
2. **Form Submission**: Fill out all required fields (name, email, subject, message) and attach a file.
3. **Confirmation**: Click 'Submit', confirm the alert, and verify the success message is displayed.
4. **Final Navigation**: Click the 'Home' button and assert that the user is correctly returned to the home page.

### ✅ Test Case 7: Verify Test Cases Page
- **Goal**: Validate that clicking the "Test Cases" button on the homepage correctly redirects the user to the dedicated test cases page.
- **Concepts**: Page navigation, URL validation, and verifying page url on the destination page to confirm successful navigation.

**Flow**:
1. **Navigate**: Navigate to the site's home page.
2. **Click**: Click on the 'Test Cases' button in the navigation bar.
3. **Verification**: Assert that the user has successfully landed on the Test Cases page.

### ✅ Test Case 8: Product Browsing and Detail Verification
- **Goal**: Verify that the 'Products' page loads correctly, displays a visible list of all items, and confirms that clicking any product successfully navigates to a details page showing all required product specifications.
- **Concepts**: Page navigation, element list handling, multi-field data validation, dynamic interaction with elements through the `JavascriptExecutor` class.

**Flow**:
1. **Navigate**: Navigate to the site and click the 'Products' button.
2. **Product List**: Verify that the "ALL PRODUCTS" page is visible and that the product list itself is displayed.
3. **Detail View**: Click the 'View Product' button for the first product.
4. **Verification**: Assert that the resulting product detail page displays all necessary information (product name, category, price, availability, condition, brand).

#### ⚠️Special Note on Element Interaction (Click Interception):
The standard Selenium `click()` method for the 'View Product' button often fails with an `ElementClickInterceptedException`. This is due to dynamic advertisements or overlays on the page obscuring the button's click area.

* **Solution**: This test case uses `JavascriptExecutor` to execute a direct click script on the 'View Product' button, bypassing the interfering ad layer and ensuring reliable execution.

### ✅ Test Case 9: Product Search and Verification
- **Goal**: Verify that the product search functionality works correctly by entering a keyword, confirming the search results page is displayed, and ensuring all visible products match the search criteria.
- **Concepts**: Search form submission, asserting text visibility, and validating a list of elements (the search results) to confirm their content matches the search criteria.

**Flow**:
1. **Navigate**: Navigate to the site and then click on the 'Products' button.
2. **Search Action**: Enter a product name or keyword into the search input field and click the search button.
3. **Verification**: Assert that the 'SEARCHED PRODUCTS' section appears and that every product displayed on the results page is relevant to the search term.

### ✅ Test Case 10 & 11: Home & Cart Page Subscription Validation
- **Goal**: Verify that a user can successfully subscribe to the newsletter from the home page footer and receive a confirmation message.
- **Concepts**: Scrolling the page (using `JavascriptExecutor` class), interacting with footer elements, and asserting the final success message.

**Flow**:
1. **Scroll**: Navigate to the site and scroll down to the bottom of the page (the footer area).
2. **Verify UI**: Assert that the 'SUBSCRIPTION' heading is visible.
3. **Subscribe**: Enter an email address into the subscription input field and click the submit button (arrow icon).
4. **Verification**: Assert that the success message is displayed, confirming the subscription was successful.

### ✅ Test Case 12: Add Products in Cart and Verify Details
- **Goal**: Verify the core e-commerce functionality of the site: successfully adding multiple products to the shopping cart, and then accurately validating the product names, prices, quantities, and total calculations within the cart view.
- **Concepts**: Concepts: Simulating mouse hover actions, handling multiple elements (`List<WebElement>`), and complex assertion of numeric and textual data.

**Flow**:
1. **Navigate & Add 1**: Navigate to the Products page, hover over the first product, and click 'Add to cart'. Click 'Continue Shopping'.
2. **Add 2**: Hover over the second product and click 'Add to cart'.
3. **View Cart**: Click the 'View Cart' button.
4. **Verification**: Assert that both products are present and that their associated details (price, quantity, total) are correct.

**Advanced Data Structure Implementation:**
To facilitate clean and type-safe verification, the test case uses two key Data Object structures:
1. `ExpectedProduct` (in `src/main/java/data`): A general-purpose POJO (_Plain Old Java Object_) created to store the expected price, quantity and total price of a product before it is added to the cart.
2. `ActualProduct` (nested `record` class within `CartPage`): A Java Record defined inside the `CartPage` class. This is used to model the actual data scraped from a single row of the cart table, making it easy to compare against the `ExpectedProduct` data structure.

### ✅ Test Case 13: Verify Product Quantity in Cart
- **Goal**: Verify that a user can successfully change the quantity of a product on the detail page before adding it to the cart, and that the cart accurately reflects the updated quantity.
- **Concepts**: Navigating to a product detail page, form input modification (changing quantity), and precise assertion of numeric values on the cart page.

**Flow**:
1. **Navigate**: Click 'View Product' for a product on the home page.
2. **Detail View**: Verify the product detail page is opened.
3. **Update Quantity**: Increase the product quantity (e.g., to 4).
4. **Add to Cart**: Click 'Add to cart' and then 'View Cart'.
5. **Verification**: Assert that the final quantity of the product displayed in the cart matches the updated value.

#### Key Architectural Enhancement: `ProductActions` Component
This test case introduces a new concept into the framework: the `ProductActions` component.

**Purpose**: This component centralizes reusable UI interaction logic that is specific to product elements (like 'View Product' or 'Add to Cart') but might be shared across multiple page objects (like `HomePage` and `ProductsPage`).

**Benefit**: This keeps the classes cleaner, adhering to the Single Responsibility Principle (SRP) by delegating product-specific actions to the new component.

### ✅ Test Case 14: Place Order: Register While Checkout (Full E2E Flow)
- **Goal**: Verify the longest and most critical user path: a user adds products to the cart, registers a new account during the checkout process, reviews the order, provides payment details, and successfully places the order, followed by account cleanup.
- **Concepts**: Complex multistep flow automation, session management, comprehensive validation of all checkout steps, and secure handling of payment inputs.

**Flow**:
1. **Preparation**: Add product(s) to the cart and click 'Proceed To Checkout'.
2. **Registration**: Register a new user account during the checkout process (similar to Test Case 1).
3. **Checkout**: Proceed through the checkout steps, review the address and order.
4. **Payment**: Enter payment details and confirm the order.
5. **Verification**: Assert that the order success message is displayed.
6. **Cleanup**: Delete the newly created account.

#### Key Architectural Enhancements:
* Payment details are managed using a new `PaymentData` Java Record and a dedicated Data Provider. This introduces a cleaner, more maintainable structure for handling payment test data, showcasing a modern, robust approach to data-driven testing.