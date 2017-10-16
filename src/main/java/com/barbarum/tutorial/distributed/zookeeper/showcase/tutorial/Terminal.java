package com.barbarum.tutorial.distributed.zookeeper.showcase.tutorial;

import org.apache.zookeeper.KeeperException;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Terminal {

    public static void main(String[] args) throws InterruptedException, IOException, KeeperException {
        ExecutorService executorService = Executors.newCachedThreadPool();

        int numOfProcess = 1;

        final Barrier barrier = new Barrier("127.0.0.1:2181", "/tutorial/barrier", numOfProcess);
        final Queue queue = new Queue("127.0.0.1:2181", "/tutorial/queue");

        final CountDownLatch latch = new CountDownLatch(numOfProcess);

        for (int index = 0; index < numOfProcess; index++) {
            executorService.submit(() -> {
                try {
                    barrier.enter();
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    latch.countDown();
                }
            });
        }
        latch.await();

        System.out.println("Processing the queue...");

        for (int i = 0; i < (int) ((1 + Math.random()) * 10); i++) {
            queue.produce((int) ((Math.random() * Integer.MAX_VALUE)));
        }

        System.out.println("Consuming the queue...");

        for (int i = 0; i < (int) (Math.random() * 10); i++) {
            System.out.println(queue.consume());
        }

    }
}
