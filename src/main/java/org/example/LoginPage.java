package org.example;

import org.example.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class LoginPage extends BasePage {

    private final String LOGIN_BUTTON_XPATH = "//button[@type='submit']";


    public LoginPage(WebDriver driver, WebDriverWait wait, Actions actions) {
        super(driver, wait, actions);
    }


    public boolean isLoginButtonVisible() {
        List<WebElement> buttons = getDriver().findElements(By.xpath(LOGIN_BUTTON_XPATH));
        return !buttons.isEmpty();
    }
}
