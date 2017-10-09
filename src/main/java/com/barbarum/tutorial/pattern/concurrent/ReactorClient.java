package com.barbarum.tutorial.pattern.concurrent;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.text.MessageFormat;
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

        ByteBuffer writeBuffer = ByteBuffer.allocate(data.length() + 1);

        writeBuffer.put(data.getBytes());
        writeBuffer.put((byte) 0x3B);
        writeBuffer.flip();
        channel.write(writeBuffer);

        System.out.println("Length of client data transferred: " + (data.length() + 1));

        ByteBuffer readBuffer = ByteBuffer.allocate(2048);
        StringBuilder builder = new StringBuilder();

        int byteReads;

        while ((byteReads = channel.read(readBuffer)) >= 0) {
            if (byteReads == 1) {
                String output = builder.toString();
                System.out.println(MessageFormat.format("{0} - {1}", output.length(), output));
                channel.close();
                return;
            } else {
                readBuffer.flip();
                byte[] tmp = new byte[byteReads];
                readBuffer.get(tmp);
                builder.append(new String(tmp));
            }
            readBuffer.clear();
        }

        channel.close();
    }
}
