package com.saucedemo.tests;

import com.saucedemo.pages.InventoryPage;
import com.saucedemo.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class InventoryTests extends BaseTest {

    private InventoryPage inventoryPage;

    @BeforeMethod
    public void loginBeforeEachTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");
        inventoryPage = new InventoryPage(driver);
    }

    @Test
    public void sixProductsShouldBeDisplayed() {
        Assert.assertEquals(inventoryPage.getInventoryItemCount(), 6,
                "Expected exactly 6 products on the inventory page");
    }

    @DataProvider(name = "priceSortOptions")
    public Object[][] priceSortOptions() {
        return new Object[][]{
                {"Price (low to high)", true},
                {"Price (high to low)", false}
        };
    }

    @Test(dataProvider = "priceSortOptions")
    public void sortingByPriceShouldOrderCorrectly(String dropdownOption, boolean ascending) {
        inventoryPage.sortBy(dropdownOption);
        inventoryPage.scrollToBottomAndWait();
        inventoryPage.captureScreenshot("price_sort_" + dropdownOption.replaceAll("[^a-zA-Z0-9]", "_"));

        List<Double> prices = inventoryPage.getAllPricesInOrder();
        List<Double> expected = prices.stream().sorted().collect(Collectors.toList());
        if (!ascending) {
            Collections.reverse(expected);
        }
        Assert.assertEquals(prices, expected, "Prices should be sorted correctly for: " + dropdownOption);
    }

    @DataProvider(name = "nameSortOptions")
    public Object[][] nameSortOptions() {
        return new Object[][]{
                {"Name (A to Z)", true},
                {"Name (Z to A)", false}
        };
    }

    @Test(dataProvider = "nameSortOptions")
    public void sortingByNameShouldOrderCorrectly(String dropdownOption, boolean ascending) {
        inventoryPage.sortBy(dropdownOption);
        inventoryPage.scrollToBottomAndWait();
        inventoryPage.captureScreenshot("name_sort_" + dropdownOption.replaceAll("[^a-zA-Z0-9]", "_"));

        List<String> names = inventoryPage.getAllProductNamesInOrder();
        List<String> expected = names.stream().sorted().collect(Collectors.toList());
        if (!ascending) {
            Collections.reverse(expected);
        }
        Assert.assertEquals(names, expected, "Names should be sorted correctly for: " + dropdownOption);
    }

    @Test
    public void addingItemShouldUpdateCartBadge() {
        inventoryPage.addItemToCart("sauce-labs-backpack");
        Assert.assertTrue(inventoryPage.isCartBadgeDisplayed(), "Cart badge should appear after adding an item");
        Assert.assertEquals(inventoryPage.getCartBadgeCount(), "1", "Cart badge should show 1 item");
    }
}
