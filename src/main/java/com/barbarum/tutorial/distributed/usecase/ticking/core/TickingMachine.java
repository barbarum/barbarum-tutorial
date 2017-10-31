package com.barbarum.tutorial.distributed.usecase.ticking.core;

import org.apache.curator.framework.CuratorFramework;

public class TickingMachine {

    private final CuratorFramework zkClient;
    private final TickingConfig config;

    public TickingMachine(CuratorFramework zkClient, TickingConfig config) {
        this.zkClient = zkClient;
        this.config = config;
    }

    /**
     * Start ticking machine
     * 1. Find correct cluster by configuration, and initial zookeeper client.
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
