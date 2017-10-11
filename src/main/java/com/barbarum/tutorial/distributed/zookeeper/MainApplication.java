package com.barbarum.tutorial.distributed.zookeeper;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class MainApplication {

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        final CountDownLatch latch = new CountDownLatch(1);

        ZooKeeper zk = new ZooKeeper("127.0.0.1:2181/tutorial", 4000, event -> {
            latch.countDown();
        });

        latch.await();

        System.out.println(zk);

        zk.getChildren("", false).forEach(item -> {
            try {
                System.out.println(zk.getData("/tutorial/" + item, false, new Stat()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        System.out.println();

        zk.close();

    }
}
