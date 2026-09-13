package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BasePage {

    private final By firstNameField = By.id("first-name");
    private final By lastNameField = By.id("last-name");
    private final By postalCodeField = By.id("postal-code");
    private final By continueButton = By.id("continue");
    private final By errorMessage = By.cssSelector("[data-test='error']");

    private final By finishButton = By.id("finish");
    private final By totalLabel = By.className("summary_total_label");

    private final By completeHeader = By.className("complete-header");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void fillInformation(String firstName, String lastName, String postalCode) {
        type(firstNameField, firstName);
        type(lastNameField, lastName);
        type(postalCodeField, postalCode);
        click(continueButton);
    }

    public String getStepOneError() {
        return getText(errorMessage);
    }

    public String getTotalLabel() {
        return getText(totalLabel);
    }

    public void finishOrder() {
        click(finishButton);
    }

    public String getCompleteMessage() {
        return getText(completeHeader);
    }
}
