package com.barbarum.tutorial.distributed.zookeeper.showcase;

import org.apache.zookeeper.KeeperException;

/**
 * Other classes use the DataMonitor by implementing this method
 */
public interface DataMonitorListener {
    /**
     * The existence status of the node has changed.
     */
    void exists(byte data[]);

    /**
     * The ZooKeeper session is no longer valid.
     *
     * @param rc the ZooKeeper reason code
     */
    void closing(KeeperException.Code rc);
}
