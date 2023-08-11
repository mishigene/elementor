package io.elementor.steps;

import com.microsoft.playwright.Page;

public class Button extends Element {
    private Page _page;

    Button(String path, Page page) {
        super(path);
        _page = page;
    }

    public void Click() {
        _page.click(this.Path);
    }
}


