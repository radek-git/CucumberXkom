package org.example.contact;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.example.base.BaseTest;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class CompleteContactFormSteps extends BaseTest {

    private ContactPage contactPage;

    @Before
    @Override
    public void setUp() {
        super.setUp();

        driver.get("https://www.x-kom.pl/centrum-pomocy");

        contactPage = new ContactPage(driver, wait, actions);

    }

    @After
    @Override
    public void closeBrowser() {
        super.closeBrowser();
    }

    @Given("^Close Cookie Popup when appears$")
    public void close_Cookie_Popup_when_appears() throws Throwable {
        contactPage.waitForPopupCookie();
        contactPage.confirmCookiePopup();
    }

    @Given("^Form is empty$")
    public void form_is_empty() throws Throwable {
        contactPage.clearForm();
    }

    @When("^I fill form using data$")
    public void i_fill_form_using_data(List<List<String>> data) throws Throwable {
        contactPage.completeForm(new ContactInfo(
                data.get(1).get(0),
                data.get(1).get(1),
                data.get(1).get(2),
                data.get(1).get(3),
                data.get(1).get(4),
                data.get(1).get(5),
                data.get(1).get(6)
        ));
    }

    @When("^Click privacy checkbox$")
    public void click_privacy_checkbox() throws Throwable {
        contactPage.clickCheckbox();
    }

    @Then("^Submit button is blue$")
    public void submit_button_is_blue() throws Throwable {
        assertTrue(contactPage.isSubmitButtonBlue());
    }




}
