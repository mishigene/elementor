package io.elementor.logic.pages.RestaurantPage;

import com.microsoft.playwright.Page;
import io.elementor.infra.browser.PageBase;

public class MainPage extends PageBase {
    private final String restaurantName = "//tr//td[(text()=\"0\")]";
    private final String createNewButton = "//div//button[(text()=\"Create new\")]";
    private final String createNewRestaurantIDlocator = "//div//input[@id='id']";
    private final String createNewRestaurantNamelocator = "//div//input[@id='name']";
    private final String createNewRestaurantAddresslocator = "//div//input[@id='address']";
    private final String createNewRestaurantScorelocator = "//div//input[@id='score']";
    private final String submitButtom = "//div//button[@class='btn btn-primary']";
    private final String okButtom = "//div//button[contains(text(),'OK')]";
    private final String deleteButton = "//td//button[contains(text(),'X')]";
    private final String deleteButtonForRestByID = "//tr[./td[contains(text(),'%s')]]//td//button";
    public MainPage(Page page) {  super(page);}
    public boolean searchForID(String term)
    {
        String responseID = page.textContent(restaurantName);
        if((responseID.compareTo(term))== 0)
        {
            return true;
        }
        return false;
    }
    public void clickOnCreateButton()
    {
        page.waitForSelector(createNewButton);
        page.click(createNewButton);
    }
    public void clickSubmit()
    {
        page.click(submitButtom);
    }
    public void clickOkButton()
    {
        page.click(okButtom);
    }


    public void fillID(String id) {
    page.waitForSelector(createNewRestaurantIDlocator);
    page.click(createNewRestaurantIDlocator);
    page.fill(createNewRestaurantIDlocator,id);
    }

    public void fillName(String name) {
        page.waitForSelector(createNewRestaurantNamelocator);
        page.click(createNewRestaurantNamelocator);
        page.fill(createNewRestaurantNamelocator,name);
    }
    public void fillAddress(String address) {
        page.waitForSelector(createNewRestaurantAddresslocator);
        page.click(createNewRestaurantAddresslocator);
        page.fill(createNewRestaurantAddresslocator,address);
    }
    public void fillScore(String score) {
        page.waitForSelector(createNewRestaurantScorelocator);
        page.click(createNewRestaurantIDlocator);
        page.fill(createNewRestaurantScorelocator,score);
    }

    public void clickOnDeleteButton() {
        page.waitForSelector(deleteButton);
        page.click(deleteButton);
        page.click(okButtom);
    }

    public void clickOnDeleteButtonofRestaurantByID(String id) {
        page.waitForSelector(String.format(deleteButtonForRestByID,id));
        page.click(String.format(deleteButtonForRestByID,id));
        page.click(okButtom);
    }
}
