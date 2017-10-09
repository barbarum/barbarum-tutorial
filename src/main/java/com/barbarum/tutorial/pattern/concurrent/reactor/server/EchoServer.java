package com.barbarum.tutorial.pattern.concurrent.reactor.server;

import com.barbarum.tutorial.pattern.concurrent.common.Server;
import com.barbarum.tutorial.pattern.concurrent.reactor.nio.SelectableEventHandler;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;

public class EchoServer extends Server {

    private ServerSocketChannel serverSocketChannel;
    private Selector selector;


    public EchoServer(String hostname, int port) throws UnknownHostException {
        super(hostname, port);
    }

    public EchoServer(int port) throws UnknownHostException {
        super(port);
    }

    public EchoServer(InetAddress ip, int port) {
        super(ip, port);
    }

    @Override
    protected void doSetUp() throws IOException {
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

    @Override
    protected void doStart() throws IOException {
        while (this.isRunning()) {
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


    @Override
    protected void doShutdown() throws IOException {
        this.selector.close();
        this.serverSocketChannel.close();

        this.selector = null;
        this.serverSocketChannel = null;
    }

    @Override
    protected SocketAddress getServerAddress() throws IOException {
        return this.serverSocketChannel.getLocalAddress();
    }
}
