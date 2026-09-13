package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.stream.Collectors;

public class CartPage extends BasePage {

    private final By cartItems = By.className("cart_item");
    private final By itemNames = By.className("inventory_item_name");
    private final By checkoutButton = By.id("checkout");
    private final By continueShoppingButton = By.id("continue-shopping");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public int getCartItemCount() {
        return driver.findElements(cartItems).size();
    }

    public List<String> getItemNamesInCart() {
        return driver.findElements(itemNames).stream()
                .map(el -> el.getText())
                .collect(Collectors.toList());
    }

    public void removeItem(String itemIdSuffix) {
        click(By.id("remove-" + itemIdSuffix));
    }

    public void goToCheckout() {
        click(checkoutButton);
    }

    public void continueShopping() {
        click(continueShoppingButton);
    }
}
