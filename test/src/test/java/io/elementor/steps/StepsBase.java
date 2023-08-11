package io.elementor.steps;

import io.elementor.TestContext;

public class StepsBase {

    protected static TestContext context;

    public StepsBase(TestContext context) {
        this.context = context;
    }
}
