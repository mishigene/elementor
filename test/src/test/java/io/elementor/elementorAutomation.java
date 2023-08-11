package io.elementor;

import com.microsoft.playwright.Playwright;
import io.elementor.steps.InventoryPage;
import io.qameta.allure.Story;
import io.elementor.infra.browser.Browser;
import io.elementor.steps.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Example Tests")
public class elementorAutomation extends TestBase {

    private Browser browser;
    private Playwright playwright;
    private final String MainUrl = "https://www.saucedemo.com/";
    private final String InventoryUrl = "https://www.saucedemo.com/inventory.html";
    private final String CheckoutUrlSuccessful = "https://www.saucedemo.com/checkout-complete.html";
    private final String UserName = "standard_user";
    private final String Password = "secret_sauce";


    @BeforeEach
    public void before() {
        browser = new Browser();
        System.out.println("Test Started");
    }

    @AfterEach
    public void after()
    {
        // save screenshot function (like *.label)
        browser.close();
    }


    @Story("Open Sauce website")
    @Test
    public void OpenSauceWebsite() throws InterruptedException {
        LoginPage loginPage = browser.navigate(MainUrl, LoginPage.class);
        assert loginPage.getURLTitle().equals(MainUrl);
        browser.close();
    }

    @Test
    public void loginPagePositiveLogin() throws InterruptedException {
        LoginPage loginPage = browser.navigate(MainUrl, LoginPage.class);
        loginPage.UserName.Fill(UserName);
        loginPage.Password.Fill(Password);
        loginPage.LoginButton.Click();
        assert loginPage.getURLTitle().equals(InventoryUrl);
        browser.close();
    }

    @Test
    public void loginPageNegativeLogin() throws InterruptedException {
        LoginPage loginPage = browser.navigate(MainUrl, LoginPage.class);
        loginPage.UserName.Fill(UserName + "T");
        loginPage.Password.Fill(Password + "T");
        loginPage.LoginButton.Click();

        assert loginPage.getURLTitle().equals(MainUrl);
        assert loginPage.elementExist("//h3[@data-test='error']");

        browser.close();
    }

    @Test
    public void addAndRemoveItemFromCart() throws InterruptedException {
        LoginPage loginPage = browser.navigate(MainUrl, LoginPage.class);
        loginPage.UserName.Fill(UserName);
        loginPage.Password.Fill(Password);
        loginPage.LoginButton.Click();

        assert loginPage.getURLTitle().equals(InventoryUrl);

        InventoryPage inventoryPage = browser.navigate(InventoryUrl, InventoryPage.class);
        inventoryPage.FirstInventoryItem.AddToCartClick();
        assert inventoryPage.FirstInventoryItem.IsInCart();

        inventoryPage.FirstInventoryItem.RemoveFromCartClick();
        assert inventoryPage.FirstInventoryItem.IsNotInCart();
    }

    @Test
    public void sortItemsByAsc() throws InterruptedException {
        LoginPage loginPage = browser.navigate(MainUrl, LoginPage.class);
        loginPage.UserName.Fill(UserName);
        loginPage.Password.Fill(Password);
        loginPage.LoginButton.Click();

        assert loginPage.getURLTitle().equals(InventoryUrl);

        InventoryPage inventoryPage = browser.navigate(InventoryUrl, InventoryPage.class);
        inventoryPage.FirstInventoryItem.SortByASC();
        //Should be asc assertion function to validate that it did actually sorted the items by asc

        inventoryPage.FirstInventoryItem.SortByDESC();
        //Should be asc assertion function to validate that it did actually sorted the items by desc

    }

    @Test
    public void checkOutFlow() throws InterruptedException {
        LoginPage loginPage = browser.navigate(MainUrl, LoginPage.class);
        loginPage.UserName.Fill(UserName);
        loginPage.Password.Fill(Password);
        loginPage.LoginButton.Click();

        assert loginPage.getURLTitle().equals(InventoryUrl);

        InventoryPage inventoryPage = browser.navigate(InventoryUrl, InventoryPage.class);
        inventoryPage.FirstInventoryItem.AddToCartClick();
        inventoryPage.OpenCart();
        inventoryPage.GoToCheckOut();
        inventoryPage.FillCheckOutData();
        inventoryPage.SubmitCheckOut();
        inventoryPage.FinishCheckOut();

        assert inventoryPage.getURLTitle().equals(CheckoutUrlSuccessful);
    }
}