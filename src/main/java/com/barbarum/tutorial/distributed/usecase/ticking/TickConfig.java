package com.barbarum.tutorial.distributed.usecase.ticking;

import java.io.IOException;
import java.util.Properties;

public class TickConfig {

    private long clusterId;
    private long workId;
    private long lastTimestamp;

    private Properties properties;

    public static final String DEFAULT_PROPERTY_PATH = "ticking-config.properties";

    //
    public static final String PROPERTY_ZOOKEEPER_HOST = "zookeeper.host";
    public static final String PROPERTY_ZOOKEEPER_ROOT = "zookeeper.root";

    public TickConfig() throws IOException {
        this.properties = new Properties();
        this.properties.load(ClassLoader.getSystemResourceAsStream(DEFAULT_PROPERTY_PATH));
    }

    public TickConfig(Properties properties) {
        this.properties = new Properties();
    }

    public long getClusterId() {
        return clusterId;
    }

    public long getWorkId() {
        return workId;
    }

    public String getZookeeperRoot() {
        return this.properties.getProperty(PROPERTY_ZOOKEEPER_HOST) + this.properties.getProperty(PROPERTY_ZOOKEEPER_ROOT);
    }

    public void setLastTimestamp(long lastTimestamp) {
        this.lastTimestamp = lastTimestamp;
    }

    public long getLastTimestamp() {
        return this.lastTimestamp;
    }

    public void setClusterId(long clusterId) {
        this.clusterId = clusterId;
    }

    public void setWorkId(long workId) {
        this.workId = workId;
    }
}
