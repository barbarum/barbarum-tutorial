package com.barbarum.tutorial.pattern.concurrent.reactor.nio;

import com.google.common.primitives.Bytes;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class SocketReadEventHandler extends GenericSelectableEventHandler {

    private List<byte[]> dataRef;

    public SocketReadEventHandler() {
        this.dataRef = new ArrayList<>(3);
    }

    @Override
    protected boolean isInterested(SelectionKey key) {
        return key.isReadable() && (key.channel() instanceof SocketChannel);
    }

    @Override
    protected void doExecute(SelectionKey key) throws IOException {
        SocketChannel socket = (SocketChannel) key.channel();

        // Read dataRef from the socket
        if (transferData(socket)) return;

        // Complete read, and convert to list write event
        SocketWriteEventHandler writeEventHandler = new SocketWriteEventHandler();
        socket.register(key.selector(), SelectionKey.OP_WRITE, writeEventHandler);

        // organize dataRef and send dataRef to handle in the service.
        writeEventHandler.processDataAsyc(this::convertInput);

    }

    private boolean transferData(SocketChannel socket) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(2048);

        int size = -1;

        if ((size = socket.read(buffer)) == -1) {
            System.out.println(MessageFormat.format("{0} - Data transfer completed.", socket.toString()));
            return false;
        }
        if (size == 0) {
            System.out.println(MessageFormat.format("{0} - Data transferring, but not data this time.", socket.toString()));
            return true;
        }

        byte[] realData = new byte[size];
        System.arraycopy(buffer.array(), 0, realData, 0, size);
        this.dataRef.add(realData);

        System.out.println(MessageFormat.format("{0} - Data transferring - [{1}].", socket.toString(), new String(realData)));
        return true;
    }

    private byte[] convertInput() {
        return Bytes.concat(this.dataRef.toArray(new byte[this.dataRef.size()][]));
    }
}
