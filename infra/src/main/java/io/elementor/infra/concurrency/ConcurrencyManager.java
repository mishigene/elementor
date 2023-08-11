package io.elementor.infra.concurrency;


import com.google.common.base.Stopwatch;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ConcurrencyManager {
    private static ConcurrencyManager instance;

    public static ConcurrencyManager getInstance() {
        if (instance == null) {
            instance = new ConcurrencyManager();
        }
        return instance;
    }

    public void parallelExecution(Runnable task, int count) {
        Stopwatch started = Stopwatch.createStarted();
        ExecutorService es = Executors.newFixedThreadPool(count);
        for (int i = 0; i < count; i++) {
            es.execute(task);
        }
        es.shutdown();
        try {
            es.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Parallel execution took " + started.elapsed(TimeUnit.MILLISECONDS) + "\n");

    }
}


