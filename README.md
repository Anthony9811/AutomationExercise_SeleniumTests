# 🌐 Automation Exercise E2E Test Suite

## Project Overview
This project is an End-to-End (E2E) test automation suite developed using Selenium WebDriver and Java, focused on validating the test cases from www.automationexercise.com.

It's built on the **Page Object Model (POM)** architecture to ensure high maintainability and code reusability, serving as a robust portfolio piece that demonstrates best practices in web automation.

### ⚠️Special Note On Test Runs
The websites shows advertisements at the bottom with varying sizes, which results in inconsistencies with some tests that sometimes fail due to this in one run, but are successful when they run a second time, so it is likely that some of them don't pass due to the 'Element click intercepted' exception. Tried different ways to solve it, but sadly the ads still appear anyway. 

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

### ✅ Test Case 15: Place Order: Register Before Checkout (Full E2E Flow)
- **Goal**: Verify a critical e-commerce path where a user first creates an account, then adds products to the cart, and proceeds directly to checkout as a logged-in user to place the final order.
- **Concepts**: Complex multistep flow automation, maintaining a persistent logged-in session, and comprehensive validation of the checkout sequence.

**Flow**: The main difference from **Test Case 14** is the execution order of the registration steps relative to the checkout steps.

1. **Registration**: Register a new user account before adding any items to the cart.
2. **Preparation**: Add product(s) to the cart.
3. **Checkout**: Proceed directly to checkout as the logged-in user.
4. **Order Placement**: Review the address, enter payment details, and confirm the order.
5. **Verification**: Assert that the order success message is displayed.
6. **Cleanup**: Delete the newly created account.

### ✅ Test Case 16: Place Order: Login Before Checkout (Returning User Flow)
- **Goal**: Verify the full e-commerce path for a returning user who logs in with existing credentials, adds products to the cart, places an order, and then safely logs out to terminate the session.
- **Concepts**: Complex multistep flow automation, using pre-existing user data, comprehensive validation of all checkout steps, Java Record and Data Provider.

**Flow**:

1. **Login**: Log in with a valid, existing user account before interacting with products.
2. **Preparation**: Add product(s) to the cart.
3. **Checkout**: Proceed directly to checkout as the logged-in user.
4. **Order Placement**: Review the address (which should be pre-populated from the user's account), enter payment details, and confirm the order.
5. **Verification**: Assert that the order success message is displayed.
6. **Cleanup**: Logout to terminate the user session (replacing the account deletion step).

### ✅ Test Case 17: Remove Products From Cart
- **Goal**: Verify that a user can successfully remove a specific product from the shopping cart and that the cart's product count is correctly updated.
- **Concepts**: Adding items to the cart, clicking specific action buttons within a list, and crucial numeric assertion where the verification is based on confirming the product count in the cart has decreased by exactly one.

**Flow**:

1. **Preparation**: Add one or more products to the cart.
2. **Navigate**: Click the 'View Cart' button and verify the cart page is displayed.
3. **Removal Action**: Click the 'X' button corresponding to the product intended for removal.
4. **Verification**: Assert that the product count in the cart has decreased, confirming the item was successfully removed.

### ✅ Test Case 18: View Category Products (Hierarchical Navigation)
- **Goal**: Verify the functionality of the product category sidebar by confirming that all categories are visible and that clicking on a category and then a sub-category successfully loads the corresponding filtered product page.
- **Concepts**: Interacting with collapsible/nested sidebar menus, dynamic link clicking, and asserting that the resulting page content accurately reflects the chosen category.

**Flow**

1. **Verify UI**: Navigate to the home page and assert that the categories section is visible on the left sidebar.
2. **Primary Navigation**: Click on a main category (e.g., 'Women') to expand its sub-categories.
3. **Sub-category Navigation**: Click a specific sub-category link (e.g., 'Dress' under 'Women').
4. **Verification**: Verify that the correct category page is displayed and that a confirmation text (e.g., 'WOMEN - TOPS PRODUCTS') is visible.
5. ****Secondary Navigation****: Click on a sub-category link from a different main category (e.g., 'Men').
6. **Final Verification**: Assert that the user is correctly navigated to the second category's page.

### ✅ Test Case 19: View & Cart Brand Products
- **Goal**: Verify the functionality of the product Brands sidebar by confirming that all brand names are visible and that clicking any brand successfully navigates to a filtered page displaying only that brand's products. The test verifies navigation for multiple brands.
- **Concepts**: Dynamic element clicking, handling product list filtering, and asserting page content (e.g., brand name) reflects the selected filter.

**Flow**:

1. **Navigate**: Navigate to the site, click on the 'Products' button.
2. **Verify UI**: Assert that the 'Brands' section is visible on the left sidebar.
3. **Primary Navigation**: Click on any brand name.
4. **Verification 1**: Verify that the user is navigated to the correct brand page and that the displayed product list is filtered by that specific brand.
5. **Secondary Navigation**: Click on another brand link from the sidebar.
6. **Verification 2**: Verify that the user is successfully navigated to the second brand page and that the products are correctly displayed/filtered.

### ✅ Test Case 20: Search Products and Verify Cart After Login
- **Goal**: Verify that items added to the cart while the user is not logged in (anonymous session) are successfully retained, linked, and visible in the cart once the user completes the login process.
- **Concepts**: Product search and filtering, adding items to cart, performing an authentication (login) mid-test, navigating back to the cart, and asserting the visibility and integrity of the previously added products. This is a crucial test for the site's user experience.

**Flow:**

1. **Preparation (Anonymous)**: Navigate to the Products page, search for an item, and add the product(s) to the cart.
2. **Verify Anonymous Cart**: Verify that the product(s) are visible in the cart while still logged out.
3. **Login**: Click 'Signup / Login' and submit valid login details.
4. **Verification (Logged In)**: Navigate back to the Cart page.
5. **Critical Assertion**: Verify that the same product(s) are still visible in the cart after the login, confirming successful session and cart persistence.

#### Note: the test can be inconsistent due to ads

### ✅ Test Case 21: Add Review on Product
- **Goal**: Verify the product review functionality by navigating to a product's detail page, submitting a complete review, and confirming the successful submission message.
- **Concepts**: Element interaction, form filling, navigation to a detail page, and asserting a transient success message.

**Flow**:

1. **Navigate**: Click the 'Products' button and verify navigation to the 'ALL PRODUCTS' page.
2. **Product Detail**: Click 'View Product' for any item.
3. **Review Input**: Verify that the 'Write Your Review' section is visible.
4. **Submit**: Enter the required details (name, email, and review message) and click 'Submit'.
5. **Verification**: Assert that the success message is displayed.

### ✅ Test Case 22: Add to Cart from Recommended Items
- **Goal**: Verify that the 'RECOMMENDED ITEMS' carousel is visible on the home page and that products can be successfully added to the cart directly from this section.
- **Concepts**: Scrolling the page (using JavascriptExecutor), verifying dynamically loaded/positioned elements, and basic cart addition/verification flow.

**Flow**:

1. **Scroll & Verify UI**: Navigate to the home page and scroll to the bottom. Verify the 'RECOMMENDED ITEMS' section is visible.
2. **Add to Cart**: Click the 'Add To Cart' button on a recommended product.
3. **View Cart**: Click the 'View Cart' button.
4. **Verification**: Assert that the recommended product is correctly displayed in the cart page.

Note: This test ensures a critical cross-selling feature is functional across different page sections. However, due to the presence of ads when running the tests it can cause some unexpected and sometimes unavoidable failures.

### ✅ Test Case 23: Verify Address Details in Checkout Page
- **Goal**: Verify that the delivery and billing address details displayed on the checkout page are identical to the address information provided by the user during the initial account registration.
- **Concepts**: Complex E2E flow, cross-page data comparison, extracting and asserting large blocks of text (address details), and robust teardown (account deletion) to maintain a clean state.

**Flow**:

1. **Registration**: Register a new user account, filling in all address details (delivery/billing address).
2. **Preparation**: Add product(s) to the cart.
3. **Checkout**: Click 'Cart' and then 'Proceed To Checkout' as the logged-in user.
4. **Verification**: On the checkout page, extract the text of both the displayed delivery and billing addresses.
5. **Data Integrity Check**: Assert that these displayed addresses match the data stored in the initial UserInformation data object.
6. **Cleanup**: Delete the newly created account.

### ✅ Test Case 24: Download Invoice After Purchase Order (Full E2E + Download)
- **Goal**: Validate the final step of the customer journey: the ability to receive and download a legal document (the invoice) after a successful purchase. The test covers registration, placing an order, verifying the success message, clicking the "Download Invoice" button, and verifying the file is successfully saved to the disk.
- **Concepts**: Complex E2E flow, handling file downloads in Selenium, checking file system integrity, and robust teardown (account deletion).

**Flow**:

1. **Registration & Login**: Register a new user account during the checkout process (similar to Test Case 14).
2. **Order Placement**: Add product(s) to the cart, proceed through checkout, review details, and submit payment information.
3. **Invoice Download**: After confirming the success message, click the 'Download Invoice' button.
4. **Verification**: Assert that the success message is displayed, and then verify that the invoice file was successfully downloaded to the local file system.
5. **Cleanup**: Delete the newly created account.

### ✅ Test Case 25: Verify Scroll Up Using 'Arrow' Button and Scroll Down
- **Goal**: Verify that the scroll functionality works correctly in both directions: programmatically scrolling down to reveal the footer, and then using the fixed 'Arrow' button to instantly scroll back up to the top of the page.
- **Concepts**: Simulating scroll-down to the bottom of the page (using JavascriptExecutor), interacting with fixed/floating action buttons (the scroll-up arrow), and verifying element visibility based on page position.

**Flow**:

1. **Scroll Down**: Navigate to the home page and programmatically scroll the page down to the bottom.
2. **Verify Footer**: Assert that the 'SUBSCRIPTION' section in the footer is visible.
3. **Scroll Up Action**: Click the 'Arrow' button (fixed at the bottom right) to move upward.
4. **Verification**: Assert that the page has scrolled completely up and that key text from the top of the page (e.g., 'Full-Fledged practice website for Automation Engineers') is now visible on the screen.

### ✅ Test Case 26: Verify Scroll Up without 'Arrow' button and Scroll Down
- **Goal**: Verify the page's standard scrolling functionality by programmatically scrolling down to the footer and then using a separate JavaScript command to scroll all the way back up to the page top. This validates basic page navigation independent of the fixed UI elements.
- **Concepts**: Advanced use of JavascriptExecutor to programmatically control page position, specifically demonstrating the ability to scroll to the absolute bottom and then to the absolute top (scrollTo(0, 0)).

**Flow**:

1. **Scroll Down**: Navigate to the home page and use a command to scroll the page down to the bottom.
2. **Verify Footer**: Assert that the 'SUBSCRIPTION' section in the footer is visible.
3. **Scroll Up Action**: Use a different JavaScript command to scroll the page up to the top.
4. **Verification**: Assert that the page has scrolled completely up and that the key introductory text ('Full-Fledged practice website for Automation Engineers') is visible on the screen.