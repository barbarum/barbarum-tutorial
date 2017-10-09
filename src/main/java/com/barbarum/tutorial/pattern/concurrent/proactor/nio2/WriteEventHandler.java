package com.barbarum.tutorial.pattern.concurrent.proactor.nio2;

import java.io.IOException;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.text.MessageFormat;
import java.util.concurrent.atomic.AtomicInteger;

public class WriteEventHandler implements CompletionHandler<Integer, Object> {

    private final AsynchronousSocketChannel socketChannel;

    private final AtomicInteger count = new AtomicInteger(0);

    public WriteEventHandler(AsynchronousSocketChannel socketChannel) {
        this.socketChannel = socketChannel;
    }

    @Override
    public void completed(Integer result, Object attachment) {
        this.count.addAndGet(result);
        System.out.println(MessageFormat.format("{0} - {1} bytes transferred, data transfer completed.", socketChannel.toString(), result));
    }

    @Override
    public void failed(Throwable exc, Object attachment) {
        if (exc == null) {
            return;
        }
        if (exc instanceof IOException) {
            System.err.println(MessageFormat.format("{0} - failed to close this connection.", socketChannel.toString()));
        }
        exc.printStackTrace();
    }

    void close(Object attachment, ReadEventHandler readEventHandler) {
        try {
            System.out.println(MessageFormat.format("{0} - Data transfer completed, total {1} bytes transferred."
                    , this.socketChannel.toString()
                    , this.count.get()));
            this.socketChannel.close();
            System.out.println(MessageFormat.format("{0} - socket closed.", this.socketChannel.toString()));
        } catch (IOException e) {
            readEventHandler.failed(e, attachment);
        }
    }
}
