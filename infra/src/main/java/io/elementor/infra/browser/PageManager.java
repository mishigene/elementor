package io.elementor.infra.browser;

import com.microsoft.playwright.Page;

import java.lang.reflect.InvocationTargetException;
import java.util.Stack;

public class PageManager {
    private Stack<PageBase> pages;

    public PageManager() {
        this.pages = new Stack<>();
    }

    public <T extends PageBase> T createPage(Class<T> clz, Page page){
        try {
            T newPage = clz.getConstructor(Page.class).newInstance(page);
            setCurrentPage(newPage);
            return newPage;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCurrentPage(PageBase pom){
        if(!(pom instanceof IPopUp)){
            pages.empty();
        }
        pages.push(pom);
    }

    public <T extends PageBase> T getCurrentPage(){
        PageBase peek = pages.peek();
        try{
            return (T)peek;
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

}
