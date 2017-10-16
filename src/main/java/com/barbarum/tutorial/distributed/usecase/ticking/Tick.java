package com.barbarum.tutorial.distributed.usecase.ticking;

public interface Tick {

    /**
     * Tick a new number as an unique sequence ID.
     *
     * @return
     */
    long next();

    /**
     * Get current
     *
     * @return
     */
    TickConfig getTickConfig();
}
