package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.stream.Collectors;

public class InventoryPage extends BasePage {

    private final By pageTitle = By.className("title");
    private final By inventoryItems = By.className("inventory_item");
    private final By itemPrices = By.className("inventory_item_price");
    private final By itemNames = By.className("inventory_item_name");
    private final By sortDropdown = By.className("product_sort_container");
    private final By cartBadge = By.className("shopping_cart_badge");
    private final By cartLink = By.className("shopping_cart_link");

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public String getPageTitle() {
        return getText(pageTitle);
    }

    public int getInventoryItemCount() {
        return driver.findElements(inventoryItems).size();
    }

    public void addItemToCart(String itemIdSuffix) {
        click(By.id("add-to-cart-" + itemIdSuffix));
    }

    public void sortBy(String visibleText) {
        pause();
        WebElement dropdown = driver.findElement(sortDropdown);
        new Select(dropdown).selectByVisibleText(visibleText);
        pause();
    }

    public void scrollToBottomAndWait() {
        scrollToBottom();
        waitSeconds(3);
    }

    public void captureScreenshot(String fileName) {
        takeScreenshot(fileName);
    }

    public List<Double> getAllPricesInOrder() {
        return driver.findElements(itemPrices).stream()
                .map(el -> Double.parseDouble(el.getText().replace("$", "")))
                .collect(Collectors.toList());
    }

    public List<String> getAllProductNamesInOrder() {
        return driver.findElements(itemNames).stream()
                .map(el -> el.getText())
                .collect(Collectors.toList());
    }

    public String getCartBadgeCount() {
        return getText(cartBadge);
    }

    public boolean isCartBadgeDisplayed() {
        return isDisplayed(cartBadge);
    }

    public void goToCart() {
        click(cartLink);
    }
}
