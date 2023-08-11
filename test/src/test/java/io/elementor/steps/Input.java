package io.elementor.steps;

import com.microsoft.playwright.Page;

public class Input extends Element {
    private Page _page;

    public Input(String path, Page page) {
        super(path);
        _page = page;
    }

    public void Fill(String text) {
        _page.fill(this.Path, text);
    }
}

