package io.elementor.logic.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.MouseButton;
import io.elementor.infra.browser.PageBase;

public class YTSearchResults extends PageBase {

    private final String channelResult = "ytd-channel-renderer";
    private final String channelTitle = "ytd-channel-name >> yt-formatted-string";
    public YTSearchResults(Page page) {
        super(page);
    }

    public String getChannelResultTitle(){
        return page.textContent(chain(channelResult,channelTitle));
    }

    public void midClickOnResult(String title){
        page.click(String.format("text=%s",title),new Page.ClickOptions().setButton(MouseButton.MIDDLE));
    }
}
