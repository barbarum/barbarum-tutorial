package com.barbarum.tutorial.pattern.concurrent.reactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class ReactorClient {

    public static void main(String[] args) throws IOException, InterruptedException {
        for (int i = 0; i < 10; i++) {
            transferData(generateRandomData(64));
        }
    }

    private static String generateRandomData(int numOfCharacters) {
        StringBuilder builder = new StringBuilder();

        int temp = numOfCharacters;
        while ((temp = temp - 2) >= 0) {
            builder.append((char) ('a' + (int) (Math.random() * 26)));
        }

        return builder.toString();
    }

    private static void transferData(String data) throws IOException {
        SocketChannel channel = SocketChannel.open();
        channel.configureBlocking(true);
        boolean isConnected = channel.connect(new InetSocketAddress("127.0.0.1", 4333));
        channel.write(ByteBuffer.wrap(data.getBytes()));
    }
}
