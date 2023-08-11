package io.elementor.logic.pages.ebayPages;

import com.microsoft.playwright.Page;
import io.elementor.infra.browser.PageBase;

public class CheckOutPage extends PageBase {

    public CheckOutPage(Page page){
        super(page);
    }

    public String getSubTotalItemsLbl()
    {
        return page.textContent("//tr[@data-test-id = 'SUB_TOTAL']/td[1]/span");
    }
}
