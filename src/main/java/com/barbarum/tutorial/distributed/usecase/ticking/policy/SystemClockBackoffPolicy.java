package com.barbarum.tutorial.distributed.usecase.ticking.policy;

public interface SystemClockBackoffPolicy {

    void handle(long delta);
}
