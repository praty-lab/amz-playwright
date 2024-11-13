package com.example.playwright;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.qameta.allure.*;

public class LoginTest extends BaseTest {

	@Test(priority = 1, description = "login validation")
	@Story("login with invalid credentials")
	@Severity(SeverityLevel.CRITICAL)
	public void loginWithInvalidCredentials() {
		System.out.println("Testing login with invalid credentials.");
		page.click("#nav-link-accountList");
		page.fill("input[type=email]", "invalid-email@test.com");
		page.click("#continue");
		page.waitForTimeout(2000);

		// Validation: Check error message
//        String errorMessage = page.locator("text=We cannot find an account with that email address")
//                                   .textContent();
//        Assert.assertTrue(errorMessage.contains("We cannot find an account with that email address"));
	}

	@Test(priority = 2, description = "login validation")
	@Story("login with empty credentials")
	@Severity(SeverityLevel.CRITICAL)
	public void loginWithEmptyFields() {
		System.out.println("Testing login with empty fields.");
		// page.click("#nav-link-accountList");
		page.click("#ap_email");
		page.fill("#ap_email", "");
		page.click("#continue");
		page.waitForTimeout(2000);
		// Validate error message for empty email field
		String emailErrorMessage = page
				.locator("(//div[contains(text(),'Enter your email or mobile phone number')])[1]").textContent();
		Assert.assertTrue(emailErrorMessage.contains("Enter your email or mobile phone number"),
				"Expected error for empty email not displayed.");
		System.out.println("Testing login with empty fields.");
		// Leave password empty
		page.fill("#ap_email", "8848391867");
		page.click("#continue");
		page.waitForTimeout(2000);
		page.click("#signInSubmit");
		System.out.println("Testing login with empty fields.");

	}

	// Proper login begins here

	public boolean isSignedInCheck() {
		// Check if already logged in by hovering over the account section
		page.hover("#nav-link-accountList");

		boolean isSignedIn = page.locator("text=Sign Out").isVisible();
		System.out.println("Is Signed In: " + isSignedIn);
		return isSignedIn;
	}

	@Test(priority = 3, description = "login validation")
	@Story("login with valid credentials")
	@Severity(SeverityLevel.CRITICAL)
	@Parameters("browserType")
	public void loginTest() {
		page.navigate("https://www.amazon.in/");
		System.out.println("Login activity begins");

		// Check if the user is already signed in
		if (isSignedInCheck()) {
			System.out.println("The person is already signed in. Logging out...");
			page.click("text=Sign Out");
			page.waitForURL("https://www.amazon.in/ap/signin");
			enterCredentials();
		} else {
			System.out.println("The person is not signed in, proceeding to login.");
			page.click("#nav-link-accountList");
			enterCredentials();
		}

		// Wait for a while to confirm login success
		page.waitForTimeout(3000);

		// Optionally, you can check again if signed in successfully 
		boolean isLoggedInCheck = loginCheck();
		Assert.assertFalse(isLoggedInCheck);

	}

	private void enterCredentials() {
		page.fill("input[type=email]", username);
		page.click("#continue");
		page.waitForTimeout(2000); // Shorter wait for UI response

		System.out.println("Entered email");

		page.fill("input[type=password]", password);
		page.click("#signInSubmit");
		page.waitForTimeout(2000); // Shorter wait for UI response

		System.out.println("Password entered successfully");
	}
}
