package io.elementor.infra.browser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.microsoft.playwright.*;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class Browser {
    private static final Logger logger = LoggerFactory.getLogger(Browser.class);
    private com.microsoft.playwright.Browser pwBrowser;
    private PageManager pageManager;
    private final Path videoPath = FileSystems.getDefault().getPath("vids");

    public Browser() {
        pageManager = new PageManager();
        BrowserType type = BrowserType.CHROME;
        switch (type) {
            case CHROME:
                Playwright playwright = Playwright.create();
                    com.microsoft.playwright.BrowserType chromium = playwright.chromium();
                    // Can be "msedge", "chrome-beta", "msedge-beta", "msedge-dev", etc.
                    pwBrowser = chromium.launch(new com.microsoft.playwright.BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));
                break;
            case EDGE:
                break;
            case FIREFOX:
                break;
            case SAFARI:
                break;
        }


    }

    private BrowserContext getContext(){
        if(pwBrowser.contexts().size()==0){
            BrowserContext context = pwBrowser.newContext(new com.microsoft.playwright.Browser.NewContextOptions().setRecordVideoDir(videoPath));
            context.setDefaultTimeout(50000);
        }
        return pwBrowser.contexts().get(0);

    }

    private Page getPage(BrowserContext context){
        return getPage(context, 0);
    }

    private Page getPage(BrowserContext context, int index){
        if(context.pages().size()==0){
            context.newPage();
        }
        return context.pages().get(index);
    }

    public <T extends PageBase> T navigate(String url, Class<T> clz) {
        Page page = getPage(getContext());
        page.navigate(url);
        return pageManager.createPage(clz, page);
    }

    public void resetContext(){
        pwBrowser.contexts().get(0).close();
    }

    public void close(){
        pwBrowser.close();
    }

    public <T extends PageBase> T getCurrentPage(){
        return pageManager.getCurrentPage();
    }

    public <T extends PageBase> T createPage(Class<T> clz) {
        return createPage(clz, 0);
    }
    public <T extends PageBase> T createPage(Class<T> clz, int index) {
        return createPage(clz, index, null);
    }

    public <T extends PageBase> T createPage(Class<T> clz, Page page) {
        return createPage(clz,0,page);
    }
    public <T extends PageBase> T createPage(Class<T> clz, int index, Page page) {
        if(page==null){
            page = getPage(getContext(),index);
        }

        return pageManager.createPage(clz, page);
    }


    public <T extends PageBase> T waitForPage( Class<T> clz, Runnable run) {
        Page page = getContext().waitForPage(run);
        page.bringToFront();
        return createPage(clz, page);
    }

}
