package com.barbarum.tutorial.distributed.zookeeper.showcase;

import com.google.common.io.ByteStreams;
import com.google.common.io.Files;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * A simple example program to use DataMonitor to start and
 * stop executables based on a znode. The program watches the
 * specified znode and saves the data that corresponds to the
 * znode in the filesystem. It also starts the specified program
 * with the specified arguments when the znode exists and kills
 * the program if the znode goes away.
 */
public class Executor implements Watcher, Runnable, DataMonitorListener {

    public static final int DEFAULT_SESSION_TIMEOUT = 3000;

    private String znode;

    private DataMonitor dataMonitor;

    private ZooKeeper zkInstance;

    private String filename;

    private String exec[];

    private Process child;

    private ReentrantLock lock = new ReentrantLock();
    private Condition closeCondition = lock.newCondition();

    public Executor(String hostPort, String znode, String filename, String exec[]) throws KeeperException, IOException {

        this.znode = znode;

        this.filename = filename;
        this.exec = exec;

        this.zkInstance = new ZooKeeper(hostPort, DEFAULT_SESSION_TIMEOUT, this);
        this.dataMonitor = new DataMonitor(zkInstance, this.znode, null, this);
    }

    /***************************************************************************
     * We do process any events ourselves, we just need to forward them on.
     *
     * @see org.apache.zookeeper.Watcher#process(WatchedEvent)
     */
    public void process(WatchedEvent event) {
        dataMonitor.process(event);
    }

    public void run() {
        lock.lock();

        while (!dataMonitor.isClosed()) {
            closeCondition.awaitUninterruptibly();
        }

        lock.unlock();

    }

    public void closing(KeeperException.Code rc) {
        lock.lock();

        closeCondition.signalAll();

        lock.unlock();
    }

    public void exists(byte[] data) {
        closeChildProcess();
        if (data == null) return;

        try {
            Files.write(data, new File(filename));
            System.out.println("Starting child...");
            child = Runtime.getRuntime().exec(exec);

            new Thread(() -> streamCopy(child.getInputStream(), System.out)).run();
            new Thread(() -> streamCopy(child.getErrorStream(), System.err)).run();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void closeChildProcess() {
        if (child == null) {
            return;
        }
        System.out.println("Killing child process");
        child.destroy();
        try {
            child.waitFor();
        } catch (InterruptedException e) {
            // Do nothing
        } finally {
            child = null;
        }
    }

    private void streamCopy(InputStream inputStream, OutputStream outputStream) {
        try {
            ByteStreams.copy(inputStream, outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}