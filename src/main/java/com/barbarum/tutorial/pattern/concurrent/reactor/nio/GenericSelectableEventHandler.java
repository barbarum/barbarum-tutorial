package com.barbarum.tutorial.pattern.concurrent.reactor.nio;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.util.function.Supplier;

public abstract class GenericSelectableEventHandler implements SelectableEventHandler {


    abstract boolean isInterested(SelectionKey key);

    @Override
    public final void execute(Supplier<SelectionKey> supplier) throws IOException {
        SelectionKey key = supplier.get();

        if (key == null) {
            return;
        }
        if (!this.isInterested(key)) {
            return;
        }

        doExecute(key);
    }

    abstract void doExecute(SelectionKey key) throws IOException;
}
