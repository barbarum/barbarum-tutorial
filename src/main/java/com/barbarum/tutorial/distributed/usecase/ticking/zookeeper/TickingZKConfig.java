package com.barbarum.tutorial.distributed.usecase.ticking.zookeeper;

public class TickingZKConfig {

    public static final String DEFAULT_PROPERTY_PATH = "ticking-config.properties";
    //
    public static final String PROPERTY_ZOOKEEPER_HOST = "zookeeper.host";
    public static final String PROPERTY_ZOOKEEPER_ROOT = "zookeeper.root";
    public static final String PROPERTY_ZOOKEEPER_WORKER_NODE = "zookeeper.ticking.worker.node";
    public static final String PROPERTY_ZOOKEEPER_SESSION_NODE = "zookeeper.ticking.session.node";


}
