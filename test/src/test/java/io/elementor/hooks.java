package io.elementor;

import io.cucumber.java.*;
import io.elementor.infra.browser.Browser;
import io.elementor.steps.StepsBase;

public class hooks extends StepsBase {

    public hooks(TestContext context) {
        super(context);
    }

    @BeforeAll
    public static void beforeAll(){

    }

    @Before
    public void beforeEach(){

    }

    @After("UI")
    public void afterEach(){
        Browser browser = context.getResource(Browser.class);
        browser.resetContext();
    }

    @AfterAll
    public static void afterAll(){

    }
}
