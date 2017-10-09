package com.barbarum.tutorial.pattern.concurrent;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.CountDownLatch;

public class ReactorClient {

    public static void main(String[] args) throws IOException, InterruptedException {
        for (int i = 0; i < 1; i++) {
            transferData(generateRandomData((int) (Math.random() * 4096 + 4096)));
            Thread.sleep(1000);
        }
    }

    private static String generateRandomData(int numOfCharacters) {
        StringBuilder builder = new StringBuilder();

        int temp = numOfCharacters;
        while ((temp -= 2) >= 0) {
            builder.append((char) ('a' + (int) (Math.random() * 26)));
        }

        return builder.toString();
    }

    private static void transferData(String data) throws IOException, InterruptedException {
        SocketChannel channel = SocketChannel.open();
        channel.configureBlocking(true);
        channel.connect(new InetSocketAddress("127.0.0.1", 4333));

        ByteBuffer writeBuffer = ByteBuffer.wrap(data.getBytes());
        channel.write(writeBuffer);

        writeBuffer.flip();
        System.out.println("Length of client data transferred: " + writeBuffer.limit());

        ByteBuffer readBuffer = ByteBuffer.allocate(data.length());

        while ((channel.read(readBuffer)) != -1) {
            readBuffer.flip();
        }

        System.out.println(data);

        byte[] read = new byte[readBuffer.capacity()];
        readBuffer.get(read);

        System.out.println(new String(read));


        new CountDownLatch(1).await();
    }
}
