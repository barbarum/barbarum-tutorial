package com.barbarum.tutorial.distributed.usecase.ticking.policy;

import java.util.Random;

public interface SequenceOverflowPolicy {

    public default void handle() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
