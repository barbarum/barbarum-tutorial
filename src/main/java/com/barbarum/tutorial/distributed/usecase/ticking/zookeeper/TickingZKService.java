package com.barbarum.tutorial.distributed.usecase.ticking.zookeeper;

/**
 * Ticking ZooKeeper Hierarchy
 *
 * /ticking/ (Data: cluster ID, cluster Name, etc)
 * |_ worker (Data: )      |_ session (Data: )
 *    |- Worker 1             |- Active Worker 1
 *    |_ Worker 2             |_ Active Worker 2
 */
public interface TickingZKService {

    /**
     * Get cluster Id from zookeeper server.
     *
     * @return
     */
    long getClusterId();


}
