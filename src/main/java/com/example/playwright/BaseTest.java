package com.example.playwright;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import com.microsoft.playwright.*;

import java.nio.file.Paths;
import java.util.Scanner;

public class BaseTest {
	protected static Page page;
	protected static Browser browser;
	protected static BrowserContext context;
	protected static Playwright playwright;
	protected String username;
	protected String password;
	private boolean isLoggedIn;

	@Parameters("browserType")
	@BeforeTest(alwaysRun = true)
	public void setup(String browserType) {

		// Setting up the scanner for getting values from user at run time
		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter Amazon username: ");
		username = scanner.nextLine();

		System.out.print("Enter Amazon password: ");
		password = scanner.nextLine();

		scanner.close(); // Close the scanner after use

		playwright = Playwright.create();
		browser = browserType.equalsIgnoreCase("chromium")
				? playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false))
				: playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));

		context = browser.newContext();
		page = context.newPage();

		// Global timeout settings
		page.setDefaultTimeout(5000);
		page.setDefaultNavigationTimeout(10000);

		page.navigate("https://www.amazon.in/");
	}

	public boolean loginCheck() {

		page.hover("#nav-link-accountList");
		isLoggedIn = page.locator("text=Sign in").isVisible();

		return isLoggedIn;

	}

	// Screenshot handling on test completion
	@AfterMethod(alwaysRun = true)
	public void takeScreenshotOnFailureOrSuccess(ITestResult result) {
		if (!result.isSuccess()) {
			// Take a screenshot for failed test cases
			page.screenshot(new Page.ScreenshotOptions()
					.setPath(Paths.get("screenshots", result.getName() + "_failure.png")).setFullPage(true));
		} else {
			// Take a screenshot for successful test cases
			page.screenshot(new Page.ScreenshotOptions()
					.setPath(Paths.get("screenshots", result.getName() + "_success.png")).setFullPage(true));
		}
	}

	@AfterTest(alwaysRun = true)
	public void tearDown() {
		if (page != null) {
			page.close();
		}
		if (context != null) {
			context.close(); // Close context after tests are done
		}
		if (browser != null) {
			browser.close(); // Close the browser
		}
		if (playwright != null) {
			playwright.close(); // Close Playwright
		}
	}
}
