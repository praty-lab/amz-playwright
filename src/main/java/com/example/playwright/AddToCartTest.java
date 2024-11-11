package com.example.playwright;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class AddToCartTest extends BaseTest {
//changes 
	@Parameters("browserType")
    @Test(priority = 4, dependsOnMethods = "com.example.playwright.LoginTest.loginTest") // Ensure loginTest runs first
    public void searchAndAddToCartTest() {
		boolean isLoggedInCheck = loginCheck();
        Assert.assertFalse(isLoggedInCheck);
        System.out.println("Searching for Samsung S23 and adding to cart");

        // Search for the Samsung S23
        page.fill("#twotabsearchtextbox", "samsung s23");
        page.click("#nav-search-submit-button");
        page.waitForTimeout(3000);
        
        System.out.println("Navigated to results page");

        // Wait for the "Add to Cart" button to be visible and click it
        page.waitForSelector("#a-autoid-1-announce");
        System.out.println("Add to cart button clicked");
        page.click("#a-autoid-1");
        page.waitForTimeout(3000);

        // Navigate to the cart
        page.click("#nav-cart");
        System.out.println("Cart tab clicked");
        page.waitForTimeout(2000);

        // Proceed to checkout
        page.click("body.a-aui_72554-c.a-aui_a11y_2_750578-t2.a-aui_a11y_6_837773-t2.a-aui_a11y_sr_678508-t1.a-aui_amzn_img_959719-t4.a-aui_amzn_img_gate_959718-t1.a-aui_killswitch_csa_logger_372963-c.a-aui_pci_risk_banner_210084-c.a-aui_template_weblab_cache_333406-c.a-aui_tnr_v2_180836-c.a-meter-animate:nth-child(2) div.a-container.sc-background-dark:nth-child(27) div.a-section.a-spacing-none.sc-fifteen-hundred-desktop-max-width:nth-child(5) div.a-fixed-right-flipped-grid:nth-child(6) div.a-fixed-right-grid-inner div.a-fixed-right-grid-col.a-col-right:nth-child(1) div.a-cardui.sc-card-style.sc-java-remote-feature.celwidget.sc-card-spacing-top-none div.a-cardui-body.a-scroller-none div.a-box-group.sc-buy-box-group:nth-child(1) div.a-section.sc-buy-box-inner-box:nth-child(9) span.celwidget:nth-child(3) span.a-button.a-button-normal.a-button-span12.a-button-primary:nth-child(1) span.a-button-inner > input.a-button-input");

        System.out.println("Navigated to checkout page");
    }
}
