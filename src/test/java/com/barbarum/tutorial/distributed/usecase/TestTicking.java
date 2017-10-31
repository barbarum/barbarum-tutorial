package com.barbarum.tutorial.distributed.usecase;

import com.barbarum.tutorial.distributed.usecase.ticking.core.OverflowException;
import com.barbarum.tutorial.distributed.usecase.ticking.core.TickingConfig;
import com.barbarum.tutorial.distributed.usecase.ticking.core.TickingMachine;
import com.barbarum.tutorial.distributed.usecase.ticking.core.TickingService;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

public class TestTicking {

    private TickingService tickingService;

    @Test
    public void testTickingService() throws InterruptedException {
        long id = this.tickingService.next();

        Thread.sleep(1000);

        System.out.println((id >> 22) < System.currentTimeMillis());
        System.out.println((((id >> 22) << 5) ^ (id >> 17)) == 0);
        System.out.println((((id >> 17) << 1) ^ (id >> 16)) == 0);
        System.out.println((((id >> 16) << 5) ^ (id >> 11)) == 0);
        System.out.println((((id >> 11) << 11) ^ id) == 0);
    }

    @Test
    public void testIncrease() throws InterruptedException {
        Assert.assertTrue(this.tickingService.next() < this.tickingService.next());

        long[] result = new long[2048];

        for (int i = 0; i < 2048; i++) {
            result[i] = this.tickingService.next();
        }

        Arrays.stream(result)
                .map(item -> ((item << 53) >> 53))
                .forEach((item) -> Assert.assertTrue(item >= 0));
    }

    @Before
    public void setUp() throws IOException, OverflowException {
        TickingConfig config = new TickingConfig();

        CuratorFramework zkClient = CuratorFrameworkFactory.newClient(config.getZookeeperRoot(), new ExponentialBackoffRetry(1000, 5));
        TickingMachine machine = new TickingMachine(zkClient, config);
        machine.start();

        this.tickingService = new TickingService(config);
    }
}
