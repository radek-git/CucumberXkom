package org.example;

import org.example.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {

    private final String MY_ACCOUNT_BUTTON_XPATH = "(//a[contains(@class, 'fz2r3r-6')])[2]";

    public HomePage(WebDriver driver, WebDriverWait wait, Actions actions) {
        super(driver, wait, actions);
    }


    public LoginPage clickMyAccountButton() {
        WebElement button = getDriver().findElement(By.xpath(MY_ACCOUNT_BUTTON_XPATH));
        button.click();
        return new LoginPage(getDriver(), getWait(), getActions());
    }
}
