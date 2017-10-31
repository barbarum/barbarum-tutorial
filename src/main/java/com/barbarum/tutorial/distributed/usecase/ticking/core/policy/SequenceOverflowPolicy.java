package com.barbarum.tutorial.distributed.usecase.ticking.core.policy;

public interface SequenceOverflowPolicy {

    public default void handle(long lastTimestamp) {
        while ((System.currentTimeMillis() - lastTimestamp) == 0) {
            // Do nothing if last timestamp remain the same.
        }
    }
}
