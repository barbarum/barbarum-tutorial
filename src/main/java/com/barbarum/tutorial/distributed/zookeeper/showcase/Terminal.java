package com.barbarum.tutorial.distributed.zookeeper.showcase;

import org.apache.zookeeper.KeeperException;

import java.io.IOException;
import java.util.Scanner;

public class Terminal {


    public static void main(String[] a) throws IOException, KeeperException {
        System.out.println("USAGE: Execute hostPort znode filename program [args ...]");
        String temp[] = new Scanner(System.in).nextLine().split(" ", -1);
        if (temp.length < 5) {
            System.err
                    .println("USAGE: Execute hostPort znode filename program [args ...]");
            System.exit(2);
        }

        String args[] = new String[temp.length - 1];
        System.arraycopy(temp, 1, args, 0, args.length);

        String hostPort = args[0];
        String znode = args[1];
        String filename = args[2];
        String exec[] = new String[args.length - 3];

        System.arraycopy(args, 3, exec, 0, exec.length);

        new Executor(hostPort, znode, filename, exec).run();
    }
}
