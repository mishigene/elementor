package io.elementor.logic.pages;

import com.microsoft.playwright.Page;
import io.elementor.infra.browser.PageBase;

public class YTChannelPage extends PageBase {
    public YTChannelPage(Page page) {
        super(page);
    }

    public int getSubsCount(){
        return Integer.parseInt(page.textContent("id=subscriber-count").split(" ")[0]);
    }
}
