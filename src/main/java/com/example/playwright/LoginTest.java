package com.example.playwright;



import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
	

    @Test(priority = 1, description="Login with invalid credentials")
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

    @Test(priority = 2, description="Login with empty fields")
    public void loginWithEmptyFields() {
        page.click("#nav-link-accountList");

        // Wait for email input and fill with empty input
        page.click("#ap_email");
        page.fill("#ap_email", "");
        page.click("#continue");
        page.waitForTimeout(2000);

        // Validation: Check error message
        String emailErrorMessage = page.locator("text=Enter your email or mobile phone number")
                                         .textContent();
        Assert.assertTrue(emailErrorMessage.contains("Enter your email or mobile phone number"));
    }

    @Test(priority = 3, description="Login with valid credentials")
    public void loginTest() {
        page.click("#nav-link-accountList");

        // Wait for email input and enter valid credentials
        page.waitForSelector("input[type=email]");
        
        
        page.fill("input[type=email]", username);
        page.click("#continue");

        // Wait for password field
        page.waitForSelector("input[type=password]");
       
        page.fill("input[type=password]", password);
        page.click("#signInSubmit");
        

        // Wait and validate login success
        page.waitForTimeout(3000);
        Assert.assertTrue(page.locator("text=Sign Out").isVisible(), "User should be signed in.");
    }
}
