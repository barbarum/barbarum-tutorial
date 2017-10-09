package com.barbarum.tutorial.pattern.concurrent.proactor.nio2;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class ReadEventHandler implements CompletionHandler<Integer, Object> {

    private final AsynchronousSocketChannel socketChannel;
    private final ByteBuffer buffer;

    private final List<byte[]> data = new ArrayList<>();

    public ReadEventHandler(AsynchronousSocketChannel socketChannel, ByteBuffer buffer) {
        this.socketChannel = socketChannel;
        this.buffer = buffer;
    }

    @Override
    public void completed(Integer result, Object attachment) {
        if (result == -1) {
            System.out.println(MessageFormat.format("{0} - Data transfer completed, total {1} bytes transferred."
                    , this.socketChannel.toString()
                    , this.data.stream().mapToInt(item -> item.length).sum()));

            transferData(attachment);
            return;
        }
        if (result == 0) {
            System.out.println(MessageFormat.format("{0} - Data transferring, but not data this time."
                    , this.socketChannel.toString()));
            return;
        }

        buffer.rewind();

        byte[] temp = new byte[result];
        buffer.get(temp);
        this.data.add(temp);

        buffer.clear();

        System.out.println(MessageFormat.format("{0} - Data transferring, {1} bytes transferred this time."
                , this.socketChannel.toString(), result));

        this.socketChannel.read(this.buffer, attachment, this);
    }

    private void transferData(Object attachment) {
        ByteBuffer buffer = ByteBuffer.allocate(this.data.stream().mapToInt(item -> item.length).sum());
        this.data.forEach(buffer::put);

        this.socketChannel.write(buffer, attachment, new WriteEventHandler(socketChannel));
    }

    @Override
    public void failed(Throwable exc, Object attachment) {
        if (exc == null) {
            return;
        }

        exc.printStackTrace();
        System.err.println(MessageFormat.format("{0} - Failed to read data. ", this.getClass()));
    }
}
