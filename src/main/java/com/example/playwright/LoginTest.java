package com.example.playwright;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
	
	// NEGATIVE TEST CASES

    @Test(priority = 1)
    public void loginWithInvalidCredentials() {
        System.out.println("Testing login with invalid credentials.");
        page.click("#nav-link-accountList");
        page.fill("input[type=email]", "invalid-email@test.com");
        page.click("#continue");
        page.waitForTimeout(2000);
//        page.fill("input[type=password]", "wrongPassword");
//        page.click("#signInSubmit");
//        page.waitForTimeout(2000);

        // Validate error message
        String errorMessage = page.locator("text=We cannot find an account with that email address").textContent();
        Assert.assertTrue(errorMessage.contains("We cannot find an account with that email address"), "Expected error message not displayed.");
        System.out.println("Invalid login test passed.");
    }
    
    @Test(priority = 2)
    public void loginWithEmptyFields() {
        System.out.println("Testing login with empty fields.");
       // page.click("#nav-link-accountList");
        page.click("#ap_email");
        page.fill("#ap_email", "");
        page.click("#continue");
        page.waitForTimeout(2000);
//test
        // Validate error message for empty email field
        String emailErrorMessage = page.locator("text=Enter your email or mobile phone number").textContent();
        Assert.assertTrue(emailErrorMessage.contains("Enter your email or mobile phone number"), "Expected error for empty email not displayed.");

        // Leave password empty
        page.fill("#ap_email", "8848391867");
        page.click("#continue");
        page.waitForTimeout(2000);
        page.click("#signInSubmit");

        // Validate error message for empty password
        String passwordErrorMessage = page.locator("text=Enter your password").textContent();
        Assert.assertTrue(passwordErrorMessage.contains("Enter your password"), "Expected error for empty password not displayed.");
        System.out.println("Empty fields test passed.");
    }
    

    // BOUNDARY TEST CASES
//
//    @Test(priority = 3)
//    public void loginWithMaxLengthCredentials() {
//        System.out.println("Testing login with max length credentials.");
//        String longEmail = "a".repeat(255) + "@test.com"; // assuming 255 is the max length allowed
//        String longPassword = "a".repeat(100); // assuming 100 is the max length for password
//
//        page.click("#nav-link-accountList");
//        page.fill("input[type=email]", longEmail);
//        page.click("#continue");
//        page.waitForTimeout(2000);
//        page.fill("input[type=password]", longPassword);
//        page.click("#signInSubmit");
//
//        // Validate error message
//        String errorMessage = page.locator("span.a-list-item").textContent();
//        Assert.assertTrue(errorMessage.contains("We cannot find an account with that email address"), "Expected error for max length credentials not displayed.");
//        System.out.println("Max length credentials test passed.");
//    }
    
    //Proper login begins here

    public boolean isSignedInCheck() {
        // Check if already logged in by hovering over the account section
        page.hover("#nav-link-accountList");

        boolean isSignedIn = page.locator("text=Sign Out").isVisible();
        System.out.println("Is Signed In: " + isSignedIn);
        return isSignedIn;
    }

    @Parameters("browserType")
    @Test(priority = 3) // Runs first
    
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

        //Optionally, you can check again if signed in successfully
//        
        boolean isLoggedInCheck = loginCheck();
        Assert.assertFalse(isLoggedInCheck);
      
    }

    private void enterCredentials() {
        page.fill("input[type=email]", "8848391867");
        page.click("#continue");
        page.waitForTimeout(2000); // Shorter wait for UI response

        System.out.println("Entered email");

        page.fill("input[type=password]", "Praty@1991");
        page.click("#signInSubmit");
        page.waitForTimeout(2000); // Shorter wait for UI response

        System.out.println("Password entered successfully");
    }
    
 
}
