package io.elementor.logic.pages.BeeFreeAgroPages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.elementor.infra.browser.PageBase;

public class ShoppingCartPage extends PageBase {
    public ShoppingCartPage(Page page){
        super(page);
    }
    public int numberOfItems()
    {
        this.page.waitForSelector("//div[@class='cart-bucket-lineitem']");
        Locator locator = page.locator("//div[@class='cart-bucket-lineitem']");
        return locator.count();
    }

}
