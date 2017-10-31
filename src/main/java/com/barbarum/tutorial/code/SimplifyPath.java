package com.barbarum.tutorial.code;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.stream.Stream;

public class SimplifyPath {

    public static String simplifyPath(String path) {
        if (path == null || path.isEmpty()) {
            return "";
        }

        String[] data = path.split("/", -1);
        Deque<String> queue = new LinkedList<>();

        Arrays.stream(data)
                .skip(1)
                .filter(item -> !("".equals(item) || ".".equals(item)))
                .forEach(item -> {
                    if ("..".equals(item)) {
                        if (!queue.isEmpty()) {
                            queue.removeLast();
                        }
                    } else {
                        queue.addLast(item);
                    }
                });

        if (queue.isEmpty()) {
            return "/";
        }

        StringBuilder builder = new StringBuilder("/");
        queue.forEach((item) -> builder.append(item).append("/"));

        return builder.substring(0, builder.length() - 1);
    }

    public static void main(String args[]) {
        System.out.println(simplifyPath("/test"));
        System.out.println(simplifyPath("/"));
        System.out.println(simplifyPath("/test/../"));
        System.out.println(simplifyPath("/a/./b/../../c/"));
    }
}
