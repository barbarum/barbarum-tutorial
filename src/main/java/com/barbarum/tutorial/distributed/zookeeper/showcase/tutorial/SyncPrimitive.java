package com.barbarum.tutorial.distributed.zookeeper.showcase.tutorial;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class SyncPrimitive implements Watcher {

    static ZooKeeper zk = null;
    static final Object mutex = new Object();

    String root;

    SyncPrimitive(String address) throws KeeperException, IOException {
        if (zk != null) {
            return;
        }

        System.out.println("Starting ZK:");
        zk = new ZooKeeper(address, 3000, this);
        System.out.println("Finished starting ZK: " + zk);
    }

    @Override
    public void process(WatchedEvent event) {
        synchronized (mutex) {
            mutex.notifyAll();
        }
    }

    protected void perform(Predicate<List<String>> predicate) throws InterruptedException, KeeperException {
        while (true) {
            synchronized (mutex) {
                if (predicate.test(zk.getChildren(root, true))) {
                    return;
                }
                mutex.wait();
            }
        }
    }

    protected <R> R perform(Predicate<List<String>> predicate, Function<List<String>, R> function) throws InterruptedException, KeeperException {
        while (true) {
            synchronized (mutex) {
                List<String> children = zk.getChildren(root, true);
                if (predicate.test(children)) {
                    return function.apply(children);
                }
                mutex.wait();
            }
        }
    }
}
