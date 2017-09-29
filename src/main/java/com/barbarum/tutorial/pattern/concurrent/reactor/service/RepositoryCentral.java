package com.barbarum.tutorial.pattern.concurrent.reactor.service;

import java.util.concurrent.*;

public class RepositoryCentral {

    private ExecutorService executor;

    private Repository delegate;

    private static class Internal {
        public static final RepositoryCentral INSTANCE = new RepositoryCentral();

    }

    private RepositoryCentral() {
        this.executor = Executors.newFixedThreadPool(8);
        this.delegate = new RepositoryImpl();
    }

    public static RepositoryCentral getCentral() {
        return Internal.INSTANCE;
    }

    public CompletionStage<String> process(byte[] data) {
        return CompletableFuture.supplyAsync(() -> this.delegate.process(data), this.executor);
    }

}
