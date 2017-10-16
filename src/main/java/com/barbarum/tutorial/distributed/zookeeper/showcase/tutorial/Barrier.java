package com.barbarum.tutorial.distributed.zookeeper.showcase.tutorial;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;

import java.io.IOException;
import java.net.InetAddress;
import java.util.List;

public class Barrier extends SyncPrimitive {

    private int size;
    private String name;

    Barrier(String address, String name, int size) throws KeeperException, InterruptedException, IOException {
        super(address);

        // My node name
        this.name = InetAddress.getLocalHost().getCanonicalHostName();

        this.root = name;
        this.size = size;

        // Create barrier node
        if (zk == null || zk.exists(root, false) != null) {
            return;
        }
        zk.create(root, new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    boolean enter() throws KeeperException, InterruptedException {
        zk.create(root + "/" + this.name, new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        this.perform(item -> item.size() >= size);
        return true;
    }

    boolean leave() throws KeeperException, InterruptedException {
        zk.delete(root + "/" + this.name, 0);
        this.perform(List::isEmpty);
        return true;
    }
}
