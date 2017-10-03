package com.barbarum.tutorial.pattern.concurrent.reactor.service;

public interface Repository {

    /**
     * @param data
     * @return
     */
    public <R> R process(byte[] data);
}
