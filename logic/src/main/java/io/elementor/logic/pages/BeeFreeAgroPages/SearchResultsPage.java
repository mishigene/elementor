package io.elementor.logic.pages.BeeFreeAgroPages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.elementor.infra.browser.PageBase;

import java.util.List;

public class SearchResultsPage extends PageBase {

    private final String resultsTitleOnUpperBar = "//h1[@class = 'srp-controls__count-heading']/span[2]";
    private final String resaultOfSearchedElementsLst = "//ul/li[contains(@class , 's-item s-item__pl-on-bottom')]";
    private final String ebayLogo = "#gh-la";

    public SearchResultsPage(Page page) {
        super(page);
    }

    public String getResultsTitleOnUpperBar()
    {
        return  page.textContent(resultsTitleOnUpperBar);
    }

    public void clickElementByTitle(String title){
        if (title.contains("YoYo")){
                    Locator locator = page.locator(String.format("//span[contains(text(),'%s')]",title));
            locator.click();
        }
        else {
            Locator locator = page.locator(String.format("//span[contains(text(),'%s')]", title));
            locator.click();
        }

    }
    public String getElementTitle(String title){
        Locator locator = page.locator(String.format("text=%s >> nth=0",title));
        return locator.textContent();
    }

    public boolean isAllItemsInCorrectCondition(String condition){
        boolean isBrandNew = true;
        this.page.waitForSelector("//span[@class='SECONDARY_INFO']");
        Locator locator = page.locator("//span[@class='SECONDARY_INFO']");
        List<String> strings = locator.allInnerTexts();
        for (String str : strings) {
            if(!str.equals(condition)) isBrandNew = false;
        }
        return isBrandNew;
    }

    public void clickOnEbayLogo()
    {
        page.click(ebayLogo);
    }

    public void clickConditionBtn(String conditionOption)
    {
        this.page.waitForSelector("//span[text()='Condition']/parent::span/parent::button");
        page.click("//span[text()='Condition']/parent::span/parent::button");
        String locator = (String.format("//span[@class='filter-menu-button__menu']//div[@class='filter-menu-button__items']//span[text()='%s']", conditionOption));
        this.page.waitForSelector(locator);
        page.click(locator);
    }
}