package io.elementor.steps;

import io.elementor.infra.browser.Browser;
import io.elementor.infra.concurrency.ConcurrencyManager;

public class Concurrency {
    public void concurrencyOpenSessions(int numberOfSessions, String browserType) {

        Runnable openSession = () -> {
            Browser driverManager = new Browser();
//            driverManager.navigateTo("http://www.youtube.com/");
        };

        ConcurrencyManager concurrencyManager = new ConcurrencyManager();
        concurrencyManager.parallelExecution(openSession, numberOfSessions);

    }

}
