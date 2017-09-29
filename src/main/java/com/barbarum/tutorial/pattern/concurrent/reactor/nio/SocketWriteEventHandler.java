package com.barbarum.tutorial.pattern.concurrent.reactor.nio;

import com.barbarum.tutorial.pattern.concurrent.reactor.service.RepositoryCentral;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.text.MessageFormat;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;

public class SocketWriteEventHandler extends GenericSelectableEventHandler {

    private String responseData;
    private AtomicBoolean hasProcessFinished = new AtomicBoolean(false);

    @Override
    boolean isInterested(SelectionKey key) {
        return key.isWritable() && (key.channel() instanceof SocketChannel) && this.hasProcessFinished.get();
    }

    @Override
    void doExecute(SelectionKey key) throws IOException {
        SocketChannel socketChannel = (SocketChannel) key.channel();
        socketChannel.write(ByteBuffer.wrap(responseData.getBytes()));

        System.out.println(MessageFormat.format("{0} - Data written: {1}"
                , socketChannel.toString()
                , this.responseData));

        socketChannel.close();
        
        System.out.println(MessageFormat.format("{0} - socket closed.", socketChannel.toString()));
    }

    public void processDataAsyc(Supplier<byte[]> supplier) {
        RepositoryCentral.getCentral()
                .process(supplier.get())
                .thenAcceptAsync(this::handleResponse);
    }

    private void handleResponse(String responseData) {
        if (!this.hasProcessFinished.compareAndSet(false, true)) {
            return;
        }
        this.responseData = responseData;
    }
}
