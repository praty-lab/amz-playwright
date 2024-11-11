package com.example.playwright;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.microsoft.playwright.options.SelectOption;

public class AddToCartTest extends BaseTest {

    @Test(priority = 1, description="Search and add product to cart")
    public void searchAndAddToCartTest() {
        // Wait for search dropdown to load
        page.waitForSelector("#searchDropdownBox");
        page.selectOption("#searchDropdownBox", new SelectOption().setLabel("Electronics"));

        // Wait for search bar and search for Samsung S23
        page.waitForSelector("#twotabsearchtextbox");
        page.fill("#twotabsearchtextbox", "samsung s23");
        page.click("#nav-search-submit-button");

        // Wait for product results and select first item
        page.waitForSelector(".s-main-slot .s-result-item");
        page.click(".s-main-slot .s-result-item"); 

        // Wait for "Add to Cart" and click
        page.waitForSelector("#add-to-cart-button");
        page.click("#add-to-cart-button");

        // Validation: Check if product was added to cart
        String cartCount = page.locator("#nav-cart-count").textContent();
        Assert.assertTrue(cartCount.matches("\\d+"), "Cart count should reflect the added item.");
    }
}
