package com.barbarum.tutorial.distributed.zookeeper.showcase.tutorial;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.text.MessageFormat;
import java.util.List;
import java.util.OptionalInt;

public class Queue extends SyncPrimitive {

    Queue(String address, String name) throws KeeperException, InterruptedException, IOException {
        super(address);
        this.root = name;

        // Create ZK node name
        if (zk == null || zk.exists(root, false) != null) {
            return;
        }
        zk.create(root, new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    boolean produce(int i) throws KeeperException, InterruptedException {
        // Add child with value i
        System.out.println("Produce " + i + " into the node...");
        byte value[] = ByteBuffer.allocate(4).putInt(i).array();
        zk.create(root + "/element", value, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);

        return true;
    }

    int consume() throws KeeperException, InterruptedException {
        // Get the first element available
        while (true) {
            synchronized (mutex) {
                List<String> children = zk.getChildren(root, true);
                OptionalInt min = children.stream()
                        .mapToInt(item -> Integer.parseInt(item.substring(7)))
                        .min();

                if (!children.isEmpty() && min.isPresent()) {
                    String node = MessageFormat.format("{0}/element{1}", root, String.format("%010d", min.getAsInt()));
                    System.out.println("Temporary value: " + node);
                    byte[] b = zk.getData(node, false, null);
                    zk.delete(node, 0);
                    return ByteBuffer.wrap(b).getInt();
                }

                System.out.println("Going to wait");
                mutex.wait();
            }
        }

    }
}
