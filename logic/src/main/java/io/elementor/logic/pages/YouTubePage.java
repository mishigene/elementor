package io.elementor.logic.pages;


import com.microsoft.playwright.Page;
import io.elementor.infra.Utils;
import io.elementor.infra.browser.PageBase;

public class YouTubePage extends PageBase {

    private final String videoWrapper = "ytd-rich-item-renderer";
    private final String search = "//input[@id=\"search\"]";

    public YouTubePage(Page page) {
        super(page);

    }

    public void selectVideoByIndex(int index){
        page.click(String.format("%s >> nth=%s",videoWrapper, index));
    }

    public void selectVideoByTitle(String title){
        page.click(String.format("%s:has-text(\"%s\")",videoWrapper,title));
    }

    public void search(String term){
        page.click(search);
        Utils.sleep(100);
        page.fill(search, term);
        Utils.sleep(100);
        page.keyboard().press("Enter");
    }


}
