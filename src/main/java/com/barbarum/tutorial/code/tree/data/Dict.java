package com.barbarum.tutorial.code.tree.data;

import com.barbarum.tutorial.util.BasicUtil;

import java.util.*;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Dict is a typical trie structure  (Example as below)
 * <pre>
 *         root (is not real node)
 *          |
 *          a
 *       /  |  \  \
 *      i   s   t  v
 *     /    |      \
 *    d     k       o
 *                  \
 *                  i
 *                  \
 *                  d
 * </pre>
 */
public class Dict {

    private Node root;

    public Dict() {
        this.root = new Node(Character.MIN_VALUE, false);
    }

    public void insert(String word) {
        if (!BasicUtil.hasContent(word)) {
            return;
        }
        Node current = root;
        for (char item : word.toCharArray()) {
            current = current.addChild(item);
        }
        current.markWord();
    }

    public boolean lookup(String word) {
        if (!BasicUtil.hasContent(word)) {
            return false;
        }
        Node current = findNode(word);
        return current != null && current.isWord();
    }

    private Node findNode(String word) {
        Node current = root;
        for (char item : word.toCharArray()) {
            if ((current = current.findChild(item)) == null) {
                return null;
            }
        }
        return current;
    }

    public Collection<String> startWith(String prefix) {
        if (!BasicUtil.hasContent(prefix)) {
            return Collections.emptyList();
        }
        Node current = findNode(prefix);
        List<String> list = new ArrayList<>();

        addWord(current, prefix, list);

        return list;
    }

    public Collection<String> startWith(String prefix, int depth) {
        if (!BasicUtil.hasContent(prefix)) {
            return Collections.emptyList();
        }
        Node current = findNode(prefix);
        List<String> list = new ArrayList<>();

        addWord(list, current, prefix, depth);

        return list;
    }

    private void addWord(List<String> list, Node current, String prefix, int depth) {
        if (current == null) {
            return;
        }
        if (current.isWord()) {
            list.add(prefix);
        }
        if (depth == 0) {
            return;
        }

        for (Node node : current) {
            if (node != null) {
                addWord(list, node, prefix + node.getValue(), depth - 1);
            }
        }
    }

    private void addWord(Node current, String currentWord, List<String> list) {
        if (current == null) {
            return;
        }
        if (current.isWord()) {
            list.add(currentWord);
        }
        for (Node node : current) {
            if (node != null) {
                addWord(node, currentWord + node.getValue(), list);
            }
        }
    }

    public static class Node implements Iterable<Node> {

        private static char BASE_CHAR = 'a';
        private static int CHILDREN_LENGTH = 26;

        private Node[] children;
        private boolean exists;
        private final char value;

        Node(char ch) {
            this(ch, false);
        }

        Node(char ch, boolean exists) {
            this.children = new Node[CHILDREN_LENGTH];
            this.value = ch;
            this.exists = exists;
        }

        @Override
        public Iterator<Node> iterator() {

            return new Iterator<Node>() {

                private int cursor = 0;

                @Override
                public boolean hasNext() {
                    return cursor != children.length - 1;
                }

                @Override
                public Node next() {
                    if (!this.hasNext()) {
                        throw new IndexOutOfBoundsException("There is no more children found from the list.");
                    }
                    return children[cursor++];
                }
            };
        }

        Node addChild(char item) {
            checkArgument(BasicUtil.inRangeOf(children, index(item)),
                    "the given char (%s) is not a lower case english letter.", item);
            if (this.children[index(item)] == null) {
                this.children[index(item)] = new Node(item);
            }
            return this.children[index(item)];
        }

        private int index(char item) {
            return item - BASE_CHAR;
        }

        void markWord() {
            this.exists = true;
        }

        boolean isWord() {
            return this.exists;
        }

        Node findChild(char item) {
            if (!BasicUtil.inRangeOf(children, index(item))) {
                return null;
            }
            return this.children[index(item)];
        }

        public char getValue() {
            return value;
        }

        public static char toChar(int index) {
            if (index < 0 || index >= CHILDREN_LENGTH) {
                throw new IndexOutOfBoundsException();
            }
            return Character.forDigit(BASE_CHAR + index, 10);
        }
//
//        public Node findChild(char character) {
//
//        }
    }

    public static void main(String args[]) {
        Dict dict = new Dict();
        System.out.println(dict.lookup("love"));
        dict.insert("love");
        System.out.println(dict.lookup("love"));
        System.out.println(dict.lookup("lov"));

        dict.insert("base");
        dict.insert("local");

        System.out.println(dict.startWith("lo", 2));
    }
}
