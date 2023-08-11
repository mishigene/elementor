package io.elementor.steps;

import com.microsoft.playwright.Page;

public class InventoryItem extends Element {
    private final String AddButtonSelector = "//button[@id='add-to-cart-sauce-labs-backpack']";
    private final String RemoveButtonSelector = "//button[@id='remove-sauce-labs-backpack']";
    private final String SortingDropdownSelector = "//select[@class='product_sort_container']";

    private Page _page;

    InventoryItem(String path, Page page) {
        super(path);
        _page = page;
    }

    public void AddToCartClick() {

        _page.click(AddButtonSelector);
    }
    public void SortByASC() {

        _page.selectOption(SortingDropdownSelector,"az");
    }
    public void SortByDESC() {

        _page.selectOption(SortingDropdownSelector,"za");
    }

    public void RemoveFromCartClick() {

        _page.click(RemoveButtonSelector);
    }

    public boolean IsInCart() {
        return _page.isVisible(RemoveButtonSelector);
    }

    public boolean IsNotInCart() {
        return _page.isVisible(AddButtonSelector);
    }
}
