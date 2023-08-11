package io.elementor.logic.pages.ebayPages;


import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.SelectOption;
import io.elementor.infra.browser.PageBase;

public class ItemPage extends PageBase {
    private static final String ITEM_NAME = "Nike Airmax Vapormax MEN 7-13 NEW!";

    public ItemPage(Page page) {
        super(page);
    }

    public String getItemNameTitle(String productName) {
        String itemName = page.textContent(String.format(ITEM_NAME, productName));
        return itemName;
    }

    public boolean checkIfItemExist(String itemName) {
        return this.page.isVisible(String.format(ITEM_NAME, itemName));
    }

    public String getSellerStarsLbl() {
        return page.textContent("//div[@class = 'ux-seller-section__item--seller']//a//span[@class = 'ux-textspans ux-textspans--PSEUDOLINK']");
    }

    public void selectItem(int dropDownNumber, String item) {
        String choice = "//div[@class='vi-msku-cntr '][" + dropDownNumber + "]/div[2]/select";
        page.selectOption(choice, new SelectOption().setLabel(item));
    }

    public void clickOnBuyItNowButton(String signInOrCheckOutAsGuest) {
        this.page.waitForSelector("#binBtn_btn_1");
        page.click("#binBtn_btn_1");
        page.locator("text=" + signInOrCheckOutAsGuest).click();
        captchaWait();
    }


    public void clickOnAddToCartButton() {
        this.page.waitForSelector("//span[contains(text(),'Add to cart')]");
        page.click("//span[contains(text(),'Add to cart')]");
        captchaWait();
    }

    public void captchaWait() {
        boolean flag = false;
        while (flag == false) {
            page.bringToFront();
            if (page.url().contains("captcha")) {
                flag = false;
            } else flag = true;
        }
    }
}
