package com.saucedemo.tests;

import com.saucedemo.pages.InventoryPage;
import com.saucedemo.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {

    @Test
    public void validLoginShouldShowInventoryPage() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        InventoryPage inventoryPage = new InventoryPage(driver);
        Assert.assertEquals(inventoryPage.getPageTitle(), "Products",
                "Expected to land on the Products page after valid login");
    }

    @Test
    public void invalidPasswordShouldShowError() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "wrong_password");

        Assert.assertTrue(loginPage.isErrorDisplayed(), "Expected an error message to be displayed");
        Assert.assertTrue(loginPage.getErrorMessage().contains("do not match"),
                "Expected a username/password mismatch error");
    }

    @Test
    public void lockedOutUserShouldShowError() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("locked_out_user", "secret_sauce");

        Assert.assertTrue(loginPage.isErrorDisplayed(), "Expected an error message for locked out user");
        Assert.assertTrue(loginPage.getErrorMessage().contains("locked out"),
                "Expected a locked-out-user specific error message");
    }

    @Test
    public void emptyCredentialsShouldShowError() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("", "");

        Assert.assertTrue(loginPage.isErrorDisplayed(), "Expected an error for empty credentials");
        Assert.assertTrue(loginPage.getErrorMessage().contains("Username is required"),
                "Expected a username-required error message");
    }
}
