package com.saucedemo.tests;

import com.saucedemo.pages.CartPage;
import com.saucedemo.pages.InventoryPage;
import com.saucedemo.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CartTests extends BaseTest {

    private InventoryPage inventoryPage;

    @BeforeMethod
    public void loginBeforeEachTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");
        inventoryPage = new InventoryPage(driver);
    }

    @Test
    public void addingThreeItemsShouldShowCartBadgeThree() {
        inventoryPage.addItemToCart("sauce-labs-backpack");
        inventoryPage.addItemToCart("sauce-labs-bike-light");
        inventoryPage.addItemToCart("sauce-labs-bolt-t-shirt");

        Assert.assertEquals(inventoryPage.getCartBadgeCount(), "3", "Cart badge should show 3 items");
    }

    @Test
    public void cartPageShouldListAddedItems() {
        inventoryPage.addItemToCart("sauce-labs-backpack");
        inventoryPage.addItemToCart("sauce-labs-bike-light");
        inventoryPage.goToCart();

        CartPage cartPage = new CartPage(driver);
        Assert.assertEquals(cartPage.getCartItemCount(), 2, "Cart page should list 2 items");
        Assert.assertTrue(cartPage.getItemNamesInCart().contains("Sauce Labs Backpack"),
                "Cart should contain the backpack");
    }

    @Test
    public void removingItemFromCartShouldUpdateCount() {
        inventoryPage.addItemToCart("sauce-labs-backpack");
        inventoryPage.addItemToCart("sauce-labs-bike-light");
        inventoryPage.goToCart();

        CartPage cartPage = new CartPage(driver);
        cartPage.removeItem("sauce-labs-backpack");

        Assert.assertEquals(cartPage.getCartItemCount(), 1, "Cart should show 1 item after removing one");
    }
}
