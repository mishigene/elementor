package io.elementor.steps;

import com.microsoft.playwright.Page;
import io.elementor.infra.browser.PageBase;

public class InventoryPage extends PageBase {

    private final String FirstInventoryItemSelector = "//div[@class='inventory_item'][1]";
    private final String AddToCartFirstButtonId = "//input[@id='add-to-cart-sauce-labs-backpack']";
    private final String RemoveFromCartFirstButtonId = "//input[@id='remove-sauce-labs-backpack']";

    private final String UserNameElementId = "//input[@id='user-name']";
    private final String PasswordElementById = "//input[@id='password']";
    private final String LoginButtonElementById = "//input[@id='login-button']";
    private final String SortingDrowdownSelector = "//input[id='id attribute is not available for this element']";
    private final String CartSelector = "//a[@class='shopping_cart_link']";
    private final String CheckOutSelector = "//button[@id='checkout']";
    private final String SubmitCheckOutSelector = "//input[@id='continue']";
    private final String FinishCheckOutSelector = "//button[@id='finish']";
    private final String CheckOutFirstNameSelector = "//input[@id='first-name']";
    private final String CheckOutSecondNameSelector = "//input[@id='last-name']";
    private final String CheckOutZipCodeSelector = "//input[@id='postal-code']";


    public Input UserName;
    public Input Password;
    public Button LoginButton;

    public InventoryItem FirstInventoryItem;
    public InventoryItem SortingDropdown;

    public InventoryPage(Page page)
    {
        super(page);

        UserName = new Input(UserNameElementId, page);
        Password = new Input(PasswordElementById, page);
        LoginButton = new Button(LoginButtonElementById, page);
        FirstInventoryItem = new InventoryItem(FirstInventoryItemSelector, page);
        SortingDropdown = new InventoryItem(SortingDrowdownSelector, page);
    }

    public String getURLTitle()
    {
       return page.url();
    }

    public Boolean elementExist(String path){
        return page.isVisible(path);
    }

    public void OpenCart() {
        page.click(CartSelector);
    }

    public void GoToCheckOut(){
        page.click(CheckOutSelector);
    }

    public void FillCheckOutData(){
        page.fill(CheckOutFirstNameSelector, "Michael");
        page.fill(CheckOutSecondNameSelector, "Liamin");
        page.fill(CheckOutZipCodeSelector, "5244622");
    }

    public void SubmitCheckOut(){
        page.click(SubmitCheckOutSelector);
    }

    public void FinishCheckOut(){
        page.click(FinishCheckOutSelector);
    }
}