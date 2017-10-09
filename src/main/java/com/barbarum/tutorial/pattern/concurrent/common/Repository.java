package com.barbarum.tutorial.pattern.concurrent.common;

public interface Repository {

    /**
     * @param data
     * @return
     */
    public <R> R process(byte[] data);
}
