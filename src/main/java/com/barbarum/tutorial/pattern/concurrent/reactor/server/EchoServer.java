package com.barbarum.tutorial.pattern.concurrent.reactor.server;

import com.barbarum.tutorial.pattern.concurrent.reactor.nio.SelectableEventHandler;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;

public class EchoServer {

    private final InetAddress ip;
    private final int port;

    private ServerSocketChannel serverSocketChannel;
    private Selector selector;


    private AtomicBoolean isRunning = new AtomicBoolean(false);


    public static final String DEFAULT_LOCAL_ADDRESS = "127.0.0.1";


    private EchoServer(String ip, int port) throws UnknownHostException {
        this(InetAddress.getByName(ip), port);
    }

    private EchoServer(InetAddress ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    private EchoServer(int port) throws UnknownHostException {
        this(DEFAULT_LOCAL_ADDRESS, port);
    }

    private void setUp() throws IOException {
        System.out.println("Setting up echo server...");
        this.serverSocketChannel = ServerSocketChannel.open().bind(new InetSocketAddress(this.ip, this.port));
        this.serverSocketChannel.configureBlocking(false);

        this.selector = Selector.open();

        // register OP_ACCEPT into server channel
    }

    public static EchoServer build(String hostname, int port) throws IOException {
        EchoServer server = new EchoServer(hostname, port);
        server.setUp();
        return server;
    }

    public static EchoServer build(int port) throws IOException {
        return build(DEFAULT_LOCAL_ADDRESS, port);
    }

    public <T extends SelectableEventHandler> SelectionKey registerEvent(int ops, T object) throws ClosedChannelException {
        return this.serverSocketChannel.register(this.selector, ops, object);
    }

    public void start() throws IOException {
        if (!this.isRunning.compareAndSet(false, true)) {
            System.out.println("Echo server already started.");
            return;
        }

        System.out.println("Echo server has started.");
        while (this.isRunning.get()) {
            this.selector.select();

            Iterator<SelectionKey> iterator = this.selector.selectedKeys().iterator();

            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                Object attachment = key.attachment();
                if (attachment != null && attachment instanceof SelectableEventHandler) {
                    ((SelectableEventHandler) attachment).execute(() -> key);
                }
                iterator.remove();
            }

        }
    }


    public void shutDown() throws IOException {
        if (!this.isRunning.compareAndSet(true, false)) {
            System.err.println("Echo server is not running, skip the shut down operation.");
            return;
        }
        this.selector.close();
        this.serverSocketChannel.close();

        this.selector = null;
        this.serverSocketChannel = null;
    }
}
