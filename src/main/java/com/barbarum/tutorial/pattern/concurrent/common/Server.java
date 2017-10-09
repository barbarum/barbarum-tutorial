package com.barbarum.tutorial.pattern.concurrent.common;

import java.io.IOException;
import java.net.*;
import java.text.MessageFormat;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class Server {


    public static final String DEFAULT_LOCAL_ADDRESS = "127.0.0.1";

    protected final InetAddress ip;
    protected final int port;


    protected AtomicBoolean isRunning = new AtomicBoolean(false);


    public Server(String hostname, int port) throws UnknownHostException {
        this(InetAddress.getByName(hostname), port);
    }

    public Server(int port) throws UnknownHostException {
        this(InetAddress.getLocalHost(), port);
    }

    public Server(InetAddress ip, int port) {
        this.port = port;
        this.ip = ip;
    }

    public InetAddress getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }

    public boolean isRunning() {
        return this.isRunning.get();
    }

    public void start() throws IOException {
        if (!this.isRunning.compareAndSet(false, true)) {
            System.out.println(MessageFormat.format("{0} - Server {1} already started.", this.getServerAddress(), this));
            return;
        }
        doStart();
        System.out.println(MessageFormat.format("{0} - Server {1} has started.", this.getServerAddress(), this));
    }

    public void shutDown() throws IOException {
        if (!this.isRunning.compareAndSet(true, false)) {
            System.err.println(MessageFormat.format("{0} - Server {1} is not running, skip the shut down operation.", this.getServerAddress(), this));
            return;
        }
        doShutdown();
    }

    public void setUp() throws IOException {
        System.out.println(MessageFormat.format("Setting up server {0}...", this.getClass()));
        doSetUp();
    }

    protected abstract void doSetUp() throws IOException;

    protected abstract void doStart() throws IOException;

    protected abstract void doShutdown() throws IOException;

    protected abstract SocketAddress getServerAddress() throws IOException;
}
