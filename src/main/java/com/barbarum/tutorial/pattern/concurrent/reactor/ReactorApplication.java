package com.barbarum.tutorial.pattern.concurrent.reactor;


import com.barbarum.tutorial.pattern.concurrent.reactor.nio.ClientSocketAcceptEventHandler;
import com.barbarum.tutorial.pattern.concurrent.reactor.server.EchoServer;

import java.io.IOException;
import java.nio.channels.SelectionKey;

public class ReactorApplication {

    public static void main(String[] args) throws IOException {
        EchoServer server = EchoServer.build(4333);
        server.registerEvent(SelectionKey.OP_ACCEPT, new ClientSocketAcceptEventHandler());
        server.start();
    }
}
