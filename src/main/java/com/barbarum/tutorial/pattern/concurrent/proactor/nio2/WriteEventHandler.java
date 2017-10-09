package com.barbarum.tutorial.pattern.concurrent.proactor.nio2;

import java.io.IOException;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.text.MessageFormat;

public class WriteEventHandler implements CompletionHandler<Integer, Object> {

    private final AsynchronousSocketChannel socketChannel;

    public WriteEventHandler(AsynchronousSocketChannel socketChannel) {
        this.socketChannel = socketChannel;
    }

    @Override
    public void completed(Integer result, Object attachment) {
        try {
            System.out.println(MessageFormat.format("{0} - {1} bytes transferred, data transfer completed.", socketChannel.toString(), result));
            this.socketChannel.close();
            System.out.println(MessageFormat.format("{0} - socket closed.", socketChannel.toString()));
        } catch (IOException e) {
            this.failed(e, attachment);
        }
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
}
