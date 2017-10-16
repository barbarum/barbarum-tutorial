package com.barbarum.tutorial.distributed.usecase.ticking;

import org.apache.curator.framework.CuratorFramework;

public class TickingMachine {

    private final CuratorFramework zkClient;
    private final TickConfig config;

    public TickingMachine(CuratorFramework zkClient, TickConfig config) {
        this.zkClient = zkClient;
        this.config = config;
    }

    /**
     * Start ticking machine
     * 1. register current server into zookeeper.
     * 2. proofread server timestamp with peers, mount current server to prepare for ticking.
     * <p>
     * <p>
     * 3. Initial ticking service, set up configurations.
     * 4. start ticking
     */
    public void start() {
//        this.registerIntoZKServer();
//        this.proofread();
    }

}
