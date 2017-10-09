package com.barbarum.tutorial.pattern.concurrent.proactor.server;

import com.barbarum.tutorial.pattern.concurrent.common.Server;
import com.barbarum.tutorial.pattern.concurrent.proactor.nio2.AcceptEventHandler;

import java.io.IOException;
import java.net.*;
import java.nio.channels.AsynchronousServerSocketChannel;

public class ProactorServer extends Server {

    private AsynchronousServerSocketChannel serverSocketChannel;

    public ProactorServer(String hostname, int port) throws UnknownHostException {
        super(hostname, port);
    }

    public ProactorServer(int port) throws UnknownHostException {
        super(port);
    }

    public ProactorServer(InetAddress ip, int port) {
        super(ip, port);
    }

    @Override
    protected void doSetUp() throws IOException {
        this.serverSocketChannel = AsynchronousServerSocketChannel.open().bind(new InetSocketAddress(this.getIp(), this.getPort()));
    }


    @Override
    protected void doStart() throws IOException {
        this.serverSocketChannel.accept(null, new AcceptEventHandler(this.serverSocketChannel));
    }

    @Override
    protected void doShutdown() throws IOException {
        this.serverSocketChannel.close();

        this.serverSocketChannel = null;
    }

    @Override
    protected SocketAddress getServerAddress() throws IOException {
        return this.serverSocketChannel.getLocalAddress();
    }
}
