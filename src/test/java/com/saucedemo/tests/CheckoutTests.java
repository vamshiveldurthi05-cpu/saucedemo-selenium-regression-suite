package com.saucedemo.tests;

import com.saucedemo.pages.CartPage;
import com.saucedemo.pages.CheckoutPage;
import com.saucedemo.pages.InventoryPage;
import com.saucedemo.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckoutTests extends BaseTest {

    private InventoryPage inventoryPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;

    @BeforeMethod
    public void loginAndAddItemBeforeEachTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        inventoryPage = new InventoryPage(driver);
        inventoryPage.addItemToCart("sauce-labs-backpack");
        inventoryPage.goToCart();

        cartPage = new CartPage(driver);
        cartPage.goToCheckout();

        checkoutPage = new CheckoutPage(driver);
    }

    @Test
    public void completeCheckoutShouldShowThankYouMessage() {
        checkoutPage.fillInformation("Vamshi", "Krishna", "500001");
        checkoutPage.finishOrder();

        Assert.assertEquals(checkoutPage.getCompleteMessage(), "Thank you for your order!",
                "Expected the order confirmation message");
    }

    @Test
    public void checkoutWithMissingFirstNameShouldShowError() {
        checkoutPage.fillInformation("", "Krishna", "500001");

        Assert.assertTrue(checkoutPage.getStepOneError().contains("First Name is required"),
                "Expected a first-name-required error message");
    }
}
