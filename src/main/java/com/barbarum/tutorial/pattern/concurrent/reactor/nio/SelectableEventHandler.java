package com.barbarum.tutorial.pattern.concurrent.reactor.nio;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.util.function.Supplier;

public interface SelectableEventHandler {

    void execute(Supplier<SelectionKey> supplier) throws IOException;
}
