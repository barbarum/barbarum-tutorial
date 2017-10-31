package com.barbarum.tutorial.distributed.usecase.ticking.core.policy;

public interface SystemClockBackoffPolicy {

    void handle(long lastEpochTimestamp);
}
