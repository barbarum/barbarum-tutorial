package com.barbarum.tutorial.distributed.usecase.ticking.core;

public interface Ticking {

    /**
     * Ticking a new number as an unique sequence ID.
     *
     * @return
     */
    long next();

    /**
     * Get current ticking configuration.
     *
     * @return
     */
    TickingConfig getTickConfig();
}
