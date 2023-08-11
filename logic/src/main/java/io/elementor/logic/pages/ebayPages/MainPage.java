package io.elementor.logic.pages.ebayPages;

import com.microsoft.playwright.Page;
import io.elementor.infra.browser.PageBase;

public class MainPage extends PageBase {

    private final String search = "//input[@id=\"gh-ac\"]";
    private final String categoryTitle = "//li[contains(@class,'hl-cat-nav')]/a";


    public MainPage(Page page)
    {
        super(page);
    }

    public void search(String term)
    {
        page.click(search);
        page.fill(search, term);
        page.keyboard().press("Enter");
    }

    public void clickOnCategoryTitle(String categoryTitleName)
    {
        page.click(String.format(categoryTitle + "[text()='%s']",categoryTitleName));
    }

    public String getURLTitle()
    {
       return page.url();
    }
}