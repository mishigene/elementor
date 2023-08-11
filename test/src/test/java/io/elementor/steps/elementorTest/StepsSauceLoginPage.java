package io.elementor.steps.elementorTest;

import io.elementor.TestContext;
import io.elementor.infra.browser.Browser;
import io.elementor.steps.StepsBase;
import io.elementor.steps.LoginPage;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;

public class StepsSauceLoginPage extends StepsBase {
    private final String ADDRESS = "https://www.saucedemo.com/";

    public StepsSauceLoginPage(TestContext context) {
        super(context);
    }

    @Test
    public void navigateToSauceMainPage() throws InterruptedException {
        Browser browser = context.getResource(Browser.class);
        browser.navigate(ADDRESS, LoginPage.class);
        browser.createPage(LoginPage.class);
//        browser.wait(10000);
    }
}

