package io.elementor.infra.browser;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

public class PageBase {
protected Page page;

    public PageBase(Page page) {
        this.page = page;
        page.waitForLoadState(LoadState.NETWORKIDLE, new Page.WaitForLoadStateOptions().setTimeout(20000));
    }

    protected String chain(String... locators){
        return String.join(" >> ", locators);
    }



}
