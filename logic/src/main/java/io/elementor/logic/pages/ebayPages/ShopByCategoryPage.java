package io.elementor.logic.pages.ebayPages;

import com.microsoft.playwright.Page;
import io.elementor.infra.browser.PageBase;

public class ShopByCategoryPage extends PageBase {

    public ShopByCategoryPage(Page page){
        super(page);
    }

    public String getItemTitle(String itemTitle)
    {
         return page.textContent("text =" + itemTitle);
    }
}