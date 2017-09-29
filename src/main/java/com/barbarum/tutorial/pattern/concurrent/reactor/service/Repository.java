package com.barbarum.tutorial.pattern.concurrent.reactor.service;

import java.util.concurrent.CompletionStage;

public interface Repository {

    /**
     * @param data
     * @return
     */
    public <R> R process(byte[] data);
}
