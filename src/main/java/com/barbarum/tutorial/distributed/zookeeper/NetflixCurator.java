package com.barbarum.tutorial.distributed.zookeeper;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;
import java.util.concurrent.TimeUnit;

public class NetflixCurator {

    private static final Logger LOG = LoggerFactory.getLogger(NetflixCurator.class);

    public static void main(String args[]) throws Exception {
        Thread.currentThread().setName(MessageFormat.format("Thread-{0}-{1,number,#}", NetflixCurator.class.getSimpleName(), System.currentTimeMillis()));

        tryDistributedBlockingLock("127.0.0.1:2181", "/curator/lock");
    }

    private static void tryDistributedBlockingLock(String connectionString, String path) throws Exception {
        CuratorFramework client = getCuratorClient(connectionString, new ExponentialBackoffRetry(1000, 5));

        InterProcessMutex lock = new InterProcessMutex(client, path);

        LOG.info("acquiring the lock....");
        if (lock.acquire(5, TimeUnit.MINUTES)) {
            try {
                LOG.info("get the lock....");
                Thread.sleep(1000 * 60 * 5);
            } finally {
                lock.release();
                LOG.info("the lock released");
            }
        }
    }

    private static CuratorFramework getCuratorClient(String connectionString, RetryPolicy retryPolicy) {
        CuratorFramework framework = CuratorFrameworkFactory.newClient(connectionString, retryPolicy);
        framework.start();
        return framework;
    }
}
