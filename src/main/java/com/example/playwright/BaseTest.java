package com.example.playwright;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.microsoft.playwright.*;

public class BaseTest {
    protected static Page page;    // Use static to ensure the same instance is shared
    protected static Browser browser;
    protected static BrowserContext context;
    protected static Playwright playwright;
    private boolean isLoggedIn;

    @Parameters("browserType") // Accept the parameter from the XML file
    @BeforeTest(alwaysRun = true)
    public void setup(String browserType) {
        System.out.println("Setting up the test for browser: " + browserType);
        playwright = Playwright.create();

        switch (browserType.toLowerCase()) {
            case "chromium":
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
                break;
            case "firefox":
                browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
                break;
            default:
                throw new IllegalArgumentException("Invalid browser: " + browserType);
        }

        // Ensure context and page are created for the browser
        context = browser.newContext();
        page = context.newPage();
        page.navigate("https://www.amazon.in/");
        //loginCheck();
        
    }
    public boolean loginCheck() {
    	
        page.hover("#nav-link-accountList");
        isLoggedIn = page.locator("text=Sign in").isVisible();
        
		return isLoggedIn;
    	
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


//import org.testng.annotations.AfterSuite;
//import org.testng.annotations.BeforeSuite;
//import org.testng.annotations.Parameters;
//
//
//import com.microsoft.playwright.*;
//public class BaseTest {
//    protected static Page page;
//    protected static Browser browser;
//    protected static BrowserContext context;
//    protected static Playwright playwright;
//
//    @BeforeSuite(alwaysRun = true)
//    @Parameters("browserType")
//    public void setup(String browserType) {
//        playwright = Playwright.create();
//        switch (browserType.toLowerCase()) {
//            case "chromium":
//                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
//                break;
//            case "firefox":
//                browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
//                break;
//            case "webkit":
//                browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
//                break;
//            default:
//                throw new IllegalArgumentException("Invalid browser: " + browserType);
//        }
//
//        context = browser.newContext();
//        page = context.newPage();
//        page.navigate("https://www.amazon.in/"); // or your desired URL
//    }
//
//    @AfterSuite(alwaysRun = true)
//    public void tearDown() {
//        if (page != null) {
//            page.close();
//        }
//        if (browser != null) {
//            browser.close();
//        }
//        if (playwright != null) {
//            playwright.close();
//        }
//    }
//}
//
//
