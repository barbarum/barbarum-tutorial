package com.barbarum.tutorial.pattern.concurrent.proactor.nio2;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.text.MessageFormat;
import java.util.concurrent.atomic.AtomicInteger;

public class AcceptEventHandler implements CompletionHandler<AsynchronousSocketChannel, Object> {

    private final AsynchronousServerSocketChannel serverSocketChannel;
    private final AtomicInteger requestCount = new AtomicInteger(0);

    public AcceptEventHandler(AsynchronousServerSocketChannel serverSocketChannel) {
        this.serverSocketChannel = serverSocketChannel;
    }

    @Override
    public void completed(AsynchronousSocketChannel socketChannel, Object attachment) {
        int index = this.requestCount.incrementAndGet();

        // Release and accept next new connection.
        this.serverSocketChannel.accept(null, this);

        System.out.println(MessageFormat.format("{0} - Handling new connection {1}....", this.serverSocketChannel, index));
        // Handle current connections.

        ByteBuffer buffer = ByteBuffer.allocate(2048);
        socketChannel.read(buffer, attachment, new ReadEventHandler(socketChannel, buffer));
    }

    @Override
    public void failed(Throwable exc, Object attachment) {
        System.out.println(MessageFormat.format("{0} - Failed to connect new connection {1}.", this, this.requestCount.get()));
    }

}
