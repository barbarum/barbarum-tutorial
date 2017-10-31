package com.barbarum.tutorial.distributed.usecase.ticking.core;

import com.barbarum.tutorial.distributed.usecase.ticking.zookeeper.TickingZKConfig;

import java.io.IOException;
import java.util.Properties;

public class TickingConfig {

    private long clusterId;
    private long workId;
    private long lastTimestamp;

    private Properties properties;


    public TickingConfig() throws IOException {
        this.properties = new Properties();
        this.properties.load(ClassLoader.getSystemResourceAsStream(TickingZKConfig.DEFAULT_PROPERTY_PATH));
    }

    public TickingConfig(Properties properties) {
        this.properties = new Properties();
    }

    public long getClusterId() {
        return clusterId;
    }

    public long getWorkId() {
        return workId;
    }

    public String getZookeeperRoot() {
        return this.properties.getProperty(TickingZKConfig.PROPERTY_ZOOKEEPER_HOST) + this.properties.getProperty(TickingZKConfig.PROPERTY_ZOOKEEPER_ROOT);
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
