package com.barbarum.tutorial.pattern.concurrent.reactor.nio;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.text.MessageFormat;

public class ClientSocketAcceptEventHandler extends GenericSelectableEventHandler {

    @Override
    boolean isInterested(SelectionKey key) {
        return key.isAcceptable() && (key.channel() instanceof ServerSocketChannel);
    }

    @Override
    void doExecute(SelectionKey key) throws IOException {
        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
        System.out.println(MessageFormat.format("{0} - Handle new connection...", serverSocketChannel));

        SocketChannel socketChannel = serverSocketChannel.accept();
        socketChannel.configureBlocking(false);

        socketChannel.register(key.selector(), SelectionKey.OP_READ, new SocketReadEventHandler());
    }
}
