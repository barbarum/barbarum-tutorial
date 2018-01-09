package com.barbarum.tutorial.pattern.concurrent.proactor.nio2;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ReadEventHandler implements CompletionHandler<Integer, Object> {

    private final AsynchronousSocketChannel socketChannel;
    private final ByteBuffer buffer;

    private final List<Byte> data = new ArrayList<>();


    private final WriteEventHandler writeEventHandler;

    public ReadEventHandler(AsynchronousSocketChannel socketChannel, ByteBuffer buffer) {
        this.socketChannel = socketChannel;
        this.buffer = buffer;

        this.writeEventHandler = new WriteEventHandler(socketChannel);
    }

    @Override
    public void completed(Integer result, Object attachment) {
        if (result == -1) {
            writeEventHandler.close(attachment, this);
            return;
        }
        if (result == 0) {
            System.out.println(MessageFormat.format("{0} - Data transferring, but not data this time."
                    , this.socketChannel.toString()));
            return;
        }

        byte[] temp = readData(result, attachment);
        System.out.println(MessageFormat.format("{0} - Data transferring, {1} bytes transferred this time."
                , this.socketChannel.toString(), temp.length));

        // examine items if 0x3B is expected.
        for (byte item : temp) {
            if (item == 0x3B) {
                this.transferData(attachment);
                this.data.add(item);
                this.transferData(attachment);
            } else {
                this.data.add(item);
            }
        }

        this.socketChannel.read(this.buffer, attachment, this);
    }

    private byte[] readData(Integer byteReads, Object attachment) {
        // Read data and reset read buffer.
        buffer.rewind();
        byte[] temp = new byte[byteReads];
        buffer.get(temp);
        buffer.clear();

        return temp;
    }


    private void transferData(Object attachment) {
        ByteBuffer buffer = ByteBuffer.allocate(this.data.size());
        this.data.forEach(buffer::put);
        buffer.flip();

        this.socketChannel.write(buffer, attachment, this.writeEventHandler);

        this.data.clear();
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
