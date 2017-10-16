package com.barbarum.tutorial.distributed.zookeeper.showcase;

import org.apache.zookeeper.AsyncCallback.StatCallback;
import org.apache.zookeeper.KeeperException.Code;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.util.Arrays;
import java.util.Optional;

/*
 * A simple class that monitors the data and existence of a ZooKeeper node. It uses asynchronous ZooKeeper APIs.
 */
public class DataMonitor implements Watcher, StatCallback {

    private ZooKeeper zk;

    private String znode;

    private Optional<Watcher> chainedWatcher;

    private boolean closed;

    private DataMonitorListener listener;

    private byte prevData[];

    public DataMonitor(ZooKeeper zk, String znode, Watcher chainedWatcher,
                       DataMonitorListener listener) {
        this.zk = zk;
        this.znode = znode;
        this.chainedWatcher = Optional.ofNullable(chainedWatcher);
        this.listener = listener;

        // Get things started by checking if the node exists. We are going to be completely event driven
        zk.exists(znode, true, this, null);
    }

    public void process(WatchedEvent event) {
        String path = event.getPath();

        if (event.getType() == Event.EventType.None) {
            // We are are being told that the state of the connection has changed
            switch (event.getState()) {
                case SyncConnected:
                    // In this particular example we don't need to do anything here - watches are automatically re-registered with server
                    // and any watches triggered while the client was disconnected will be delivered (in order of course)
                    break;
                case Expired:
                    // It's all over
                    setClosed(true);
                    listener.closing(Code.SESSIONEXPIRED);
                    break;
            }
        } else {
            if (path != null && path.equals(znode)) {
                // Something has changed on the node, let's find out
                zk.exists(znode, true, this, null);
            }
        }

        this.chainedWatcher.ifPresent(item -> item.process(event));
    }

    public void processResult(int rc, String path, Object ctx, Stat stat) {
        boolean exists;

        switch (rc) {
            case Code.Ok:
                exists = true;
                break;
            case Code.NoNode:
                exists = false;
                break;
            case Code.SessionExpired:
            case Code.NoAuth:
                setClosed(true);
                listener.closing(Code.NOAUTH);
                return;

            default:
                // Retry errors
                zk.exists(znode, true, this, null);
                return;
        }

        byte b[] = null;

        if (exists) {
            try {
                b = zk.getData(znode, false, null);
            } catch (Exception e) {
                // We don't need to worry about recovering now. The watch callbacks will kick off any exception handling
                e.printStackTrace();
            }
        }

        if (!Arrays.equals(prevData, b)) {
            listener.exists(b);
            prevData = b;
        }

    }

    boolean isClosed() {
        return closed;
    }

    private void setClosed(boolean closed) {
        this.closed = closed;
    }

}