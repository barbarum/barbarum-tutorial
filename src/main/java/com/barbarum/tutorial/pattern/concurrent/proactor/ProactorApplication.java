package com.barbarum.tutorial.pattern.concurrent.proactor;

import com.barbarum.tutorial.pattern.concurrent.proactor.server.ProactorServer;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class ProactorApplication {

    public static void main(String[] args) throws IOException, InterruptedException {
        ProactorServer server = new ProactorServer("127.0.0.1", 4333);
        server.setUp();
        server.start();

        new CountDownLatch(1).await();
    }
}
