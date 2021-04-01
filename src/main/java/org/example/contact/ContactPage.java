package org.example.contact;

import org.example.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ContactPage extends BasePage {

    private static final String POPUP_COOKIE_XPATH = "//div[@id='popup-cookie']";
    private static final String COOKIE_CONFIRM_BUTTON_XPATH = "//button[contains(@class, 'cookie-confirm')]";
    private static final String FIRST_NAME_INPUT_XPATH = "//input[@id='firstName']";
    private static final String LAST_NAME_INPUT_XPATH = "//input[@id='lastName']";
    private static final String EMAIL_INPUT_XPATH = "//input[@id='emailAddress']";
    private static final String MESSAGE_CATEGORY_SELECT_XPATH = "(//div[contains(@class, 'selectable-container')])[1]/div[contains(@class, 'multiselect')]";
    private static final String QUESTION_TOPIC_SELECT_XPATH = "(//div[@data-toggle='dropdown'])[2]";
    private static final String MESSAGE_TEXTAREA_XPATH = "//div[@class='form-group']/div[contains(@class, 'col-sm-8')]/textarea[@name='question']";
    private static final String CHECKBOX_XPATH = "//input[@type='checkbox']";
    private static final String SUBMIT_BUTTON_XPATH = "//button[@id='helpFormSubmit']";
    private static final String MESSAGE_UL_CATEGORIES_XPATH = "(//ul[contains(@class, 'multiselect-container')])[1]";
    private static final String MESSAGE_CATEGORIES_XPATH = "//ul[contains(@class, 'multiselect-container')]/li/a/label";
    private static final String QUESTION_UL_TOPICS_XPATH = "(//ul[contains(@class, 'multiselect-container')])[2]";
    private static final String QUESTION_TOPICS_XPATH = "//ul[contains(@class, 'multiselect-container')]/li/a/label";
    private static final String SUBMIT_BUTTON_BLUE_XPATH = "//button[@id='helpFormSubmit' and contains(@class, 'button-blue')]";
    private static final String CATEGORY_FIELD_CONTENT_XPATH = "//div[contains(@class, 'selected-item')]/span";
    private static final String TOPIC_FIELD_CONTENT_XPATH = "(//div[contains(@class, 'selected-item')]/span)[2]";
    private static final String PHONE_NR_INPUT_XPATH = "//input[@id='phoneNumber']";



    public ContactPage(WebDriver driver, WebDriverWait wait, Actions actions) {
        super(driver, wait, actions);
    }

    public void clearForm() {
        findElement(FIRST_NAME_INPUT_XPATH).clear();
        findElement(LAST_NAME_INPUT_XPATH).clear();
        findElement(EMAIL_INPUT_XPATH).clear();
        findElement(PHONE_NR_INPUT_XPATH).clear();

        // TODO: wyczysc telefon

        clearSelect(CATEGORY_FIELD_CONTENT_XPATH, MESSAGE_CATEGORY_SELECT_XPATH, MESSAGE_UL_CATEGORIES_XPATH, MESSAGE_CATEGORIES_XPATH);
        clearSelect(TOPIC_FIELD_CONTENT_XPATH, QUESTION_TOPIC_SELECT_XPATH, QUESTION_UL_TOPICS_XPATH, QUESTION_TOPICS_XPATH);

        findElement(MESSAGE_TEXTAREA_XPATH).clear();
    }

    private void clearSelect(String currentValueXpath, String selectXpath, String selectPopupXpath, String selectValuesXpath) {
        WebElement currentValue = getDriver().findElement(By.xpath(currentValueXpath));
        if (!currentValue.getText().contains("wybierz")) {
            findElement(selectXpath).click();
            waitForElementVisibility(selectPopupXpath);
            List<WebElement> messageCategories = getDriver().findElements(By.xpath(selectValuesXpath));
            WebElement emptyValue = messageCategories.stream()
                    .filter(webElement -> webElement.getText().contains("wybierz"))
                    .findFirst().orElseThrow();
            emptyValue.click();
        }
    }


    public void waitForPopupCookie() {
        try {
            waitForElementVisibility(POPUP_COOKIE_XPATH);
        } catch (TimeoutException ignored) {

        }
    }

    public void confirmCookiePopup() {
        List<WebElement> buttons = getDriver().findElements(By.xpath(COOKIE_CONFIRM_BUTTON_XPATH));
        if (!buttons.isEmpty()) {
            buttons.get(0).click();
        }
    }

    public void completeForm(ContactInfo contactInfo) throws InterruptedException {
        sendKeys(findElement(FIRST_NAME_INPUT_XPATH), contactInfo.getFirstName());
        sendKeys(findElement(LAST_NAME_INPUT_XPATH), contactInfo.getLastName());
        sendKeys(findElement(EMAIL_INPUT_XPATH), contactInfo.getEmail());
        sendKeys(findElement(PHONE_NR_INPUT_XPATH), contactInfo.getPhoneNumber());

        findElement(MESSAGE_CATEGORY_SELECT_XPATH).click();
        waitForElementVisibility(MESSAGE_UL_CATEGORIES_XPATH);
        List<WebElement> messageCategories = getDriver().findElements(By.xpath(MESSAGE_CATEGORIES_XPATH));

        WebElement category = messageCategories.stream()
                .filter(w -> w.getText().contains(contactInfo.getMessageCategory()))
                .findFirst().orElseThrow();
        category.click();

        findElement(QUESTION_TOPIC_SELECT_XPATH).click();
        waitForElementVisibility(QUESTION_UL_TOPICS_XPATH);
        List<WebElement> questionTopics = getDriver().findElements(By.xpath(QUESTION_TOPICS_XPATH));

        WebElement topic = questionTopics.stream()
                .filter(webElement -> webElement.getText().contains(contactInfo.getQuestionTopic()))
                .findFirst().orElseThrow();
        topic.click();

        sendKeys(findElement(MESSAGE_TEXTAREA_XPATH), contactInfo.getMessage());
    }

    public void clickCheckbox() {
        findElement(CHECKBOX_XPATH).click();
    }

    public boolean isSubmitButtonBlue() {
        List<WebElement> buttonsBlue = getDriver().findElements(By.xpath(SUBMIT_BUTTON_BLUE_XPATH));
        return !buttonsBlue.isEmpty();
    }




}
