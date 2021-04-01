package org.example.login;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.HomePage;
import org.example.LoginPage;
import org.example.base.BaseTest;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertTrue;

public class OpenLoginPageSteps  {

    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    private HomePage homePage;
    private LoginPage loginPage;



    @BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @Before
    public void setUp() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--lang=en-US");
        driver = new ChromeDriver(chromeOptions);

        init();

        driver.get("https://www.x-kom.pl/");
        homePage = new HomePage(driver, wait, actions);
    }

    public void init() {
        wait = new WebDriverWait(driver, 15);
        actions = new Actions(driver);
        driver.manage().window().maximize();
    }

    @After
    public void closeBrowser() {
        driver.close();
    }


    @Given("^We are on home page$")
    public void we_are_on_home_page() throws Throwable {
        System.out.println("On home page");
    }

    @When("^I click My Account button$")
    public void i_click_My_Account_button() throws Throwable {
        loginPage = homePage.clickMyAccountButton();
    }

    @Then("^Login page is opened$")
    public void login_page_is_opened() throws Throwable {
        assertTrue(loginPage.isLoginButtonVisible());
    }


}
