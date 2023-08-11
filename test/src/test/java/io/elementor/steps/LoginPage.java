package io.elementor.steps;

import com.microsoft.playwright.Page;
import io.elementor.infra.browser.PageBase;

public class LoginPage extends PageBase {

    private final String UserNameElementId = "//input[@id='user-name']";
    private final String PasswordElementById = "//input[@id='password']";
    private final String LoginButtonElementById = "//input[@id='login-button']";

    public Input UserName;
    public Input Password;
    public Button LoginButton;

    public LoginPage(Page page)
    {
        super(page);

        UserName = new Input(UserNameElementId, page);
        Password = new Input(PasswordElementById, page);
        LoginButton = new Button(LoginButtonElementById, page);
    }

    public String getURLTitle()
    {
       return page.url();
    }

    public Boolean elementExist(String path){
        return page.isVisible(path);
    }

}