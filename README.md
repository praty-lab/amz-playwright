Playwright Testing Project
This repository contains an automation testing framework built using Playwright, TestNG, and Allure for cross-browser testing. It incorporates advanced features such as timeouts, screenshots on test failure and success, parallel and cross-browser testing, and data-driven testing. The project also integrates Allure for generating visually appealing and detailed test reports.

Project Overview
Playwright: A modern framework for browser automation, supporting Chromium, Firefox, and WebKit.
TestNG: A flexible testing framework used for test configuration and execution.
Allure: A reporting tool that provides beautiful, comprehensive test reports.
AspectJ: Used for aspect-oriented programming (AOP) to manage cross-cutting concerns in the code.
Features
Timeouts: Test execution is controlled with proper timeout mechanisms to avoid long-running tests.
Allure Reports: Automatic generation of detailed test reports for easy analysis.
Screenshots on Failure/Success: Captures screenshots on both test failures and successful executions.
Parallel and Cross-Browser Testing: Supports running tests on multiple browsers (Chromium, Firefox, WebKit) in parallel.
Data-Driven Testing: Supports data-driven testing to run tests with different data sets.
Verification and Validation: Automated checks for expected outcomes to ensure correctness.
Prerequisites
Ensure the following tools are installed before running this project:

Java 17+
Maven
Node.js (for Playwright installation)
Playwright libraries (installed via Maven)
Setup and Installation
1. Clone the Repository
git clone https://github.com/your-username/playwright-testing.git
cd playwright-testing
2. Install Dependencies
Run the following Maven command to download and install all dependencies:

mvn clean install
3. Install Playwright Browsers
Playwright requires the browsers to be installed separately. Run the following command to install the necessary browsers:

npx playwright install
Running the Tests
To execute tests, use the following Maven command:

mvn test
This will execute all the tests defined in the src/test/java folder.

Running Allure Reports
After tests are executed, generate the Allure reports by running:

mvn allure:serve
This will launch a local server and open the test reports in your browser.

Example of Test Execution
mvn clean test -Dtest=TestLogin
This will run the TestLogin class.

Features in Detail
1. Timeouts
Timeouts are set using Playwright's built-in functionality to control wait times and prevent tests from running indefinitely. This ensures that your tests fail gracefully if a page or element takes too long to load. Here’s an example of setting a timeout in Playwright:

page.waitForSelector("selector", new Page.WaitForSelectorOptions().setTimeout(5000));
2. Allure Reports
Allure integration provides a detailed visual representation of test results. After the test execution, you can generate the report by running the following:

mvn allure:serve
The report will display information like:

Test status (passed, failed, skipped)
Execution time
Logs and screenshots
You can configure Allure in the pom.xml file by adding the allure-testng dependency.

3. Screenshots on Failure/Success
Screenshots are automatically taken on both test successes and failures. This helps in capturing the state of the application at the time of execution. You can customize this behavior by using a listener in TestNG to capture screenshots.

Example code to capture a screenshot on test failure:

if (ITestResult.FAILURE == result.getStatus()) {
    File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(screenshot, new File("path/to/screenshot"));
}
4. Verification and Validation
Verification is performed using assertions to validate the actual outcomes against expected results. Playwright allows you to interact with page elements and confirm their state, such as checking text, visibility, or attribute values.

Example:

assertEquals(page.textContent("selector"), "Expected Text");
5. Parallel and Cross-Browser Testing
This project supports parallel execution and cross-browser testing with Chromium, Firefox, and WebKit. You can configure the browsers to run in parallel by using TestNG’s @Test annotations and specifying the browser for each test.

TestNG allows running tests across multiple browsers:

@Test(dataProvider = "browsers")
public void testOnDifferentBrowsers(String browser) {
    // Logic to launch Playwright with the specified browser
}
6. Data-Driven Testing
This project supports data-driven testing using TestNG’s @DataProvider annotation. You can create a method to supply multiple sets of test data, enabling the same test to be run with different inputs.

Example:

@DataProvider(name = "testData")
public Object[][] dataProviderMethod() {
    return new Object[][] { { "username1", "password1" }, { "username2", "password2" } };
}

@Test(dataProvider = "testData")
public void testLogin(String username, String password) {
    // Test logic
}
Project Structure
src/main/java: Contains source code (if any).
src/test/java: Contains test classes.
src/test/resources: Contains configuration files.
pom.xml: Maven configuration file defining project dependencies and plugins.
Dependencies
The project uses the following dependencies:

Playwright: For browser automation and cross-browser testing.
TestNG: For test execution and management.
Allure: For generating detailed test reports.
AspectJ: For managing cross-cutting concerns.
Configuration
The pom.xml file contains the necessary configurations for:

Java version: 17
Playwright version: 1.47.0
TestNG version: 6.14.3
Allure version: 2.29.0
Contributing
Feel free to fork the repository and contribute by submitting a pull request. For significant changes, please open an issue to discuss before making any changes.

License
This project is licensed under the MIT License - see the LICENSE file for details.
