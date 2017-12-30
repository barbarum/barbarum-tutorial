package com.barbarum.tutorial.code.tree.data;

import com.barbarum.tutorial.util.BasicUtil;
import com.sun.istack.internal.NotNull;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;

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
public class Dict<T extends Comparable<T>> {

    private Node<T> root;

    public Dict() {
        this.root = new Node<>(Character.MIN_VALUE, false);
    }

    public Dict<T> insert(String word) {
        if (!BasicUtil.hasContent(word)) {
            return this;
        }
        Node<T> current = root;
        for (char item : word.toCharArray()) {
            current = current.addChild(item);
        }
        current.markWord();

        return this;
    }

    public Dict<T> insert(String word, int start, Function<Integer, T> function) {
        return insert(word, start, word.length() - 1, function);
    }

    public Dict<T> insert(String word, int start, int end, Function<Integer, T> function) {
        if (!BasicUtil.hasContent(word)) {
            return this;
        }
        Node<T> current = root;
        for (int i = start; i <= end; i++) {
            current = current.addChild(word.charAt(i), function.apply(i));
        }
        current.markWord();
        return this;
    }

    public boolean lookup(String word) {
        if (!BasicUtil.hasContent(word)) {
            return false;
        }
        Node<T> current = findNode(word);
        return current != null && current.isWord();
    }

    public List<T> match(String pattern) {
        if (!BasicUtil.hasContent(pattern)) {
            return Collections.emptyList();
        }
        Node<T> node = findNode(pattern);
        return node != null && node.isWord() ? node.values() : Collections.emptyList();
    }

    public boolean delete(String word) {
        if (!lookup(word)) {
            return false;
        }
        delete(root, -1, word);
        return true;
    }

    private Node<T> delete(Node<T> root, int depth, String word) {
        if (depth != word.length() - 1) {
            char child = word.charAt(depth + 1);
            root.setChild(child, delete(root.findChild(child), depth + 1, word));
        } else {
            root.exists = false;
        }
        return root.hasChildren() ? root : null;
    }

    private Node<T> findNode(String word) {
        Node<T> current = root;
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
        Node<T> current = findNode(prefix);
        List<String> list = new ArrayList<>();

        addWord(current, prefix, list);

        return list;
    }

    public Collection<String> startWith(String prefix, int depth) {
        if (!BasicUtil.hasContent(prefix)) {
            return Collections.emptyList();
        }
        Node<T> current = findNode(prefix);
        List<String> list = new ArrayList<>();

        addWord(list, current, prefix, depth);

        return list;
    }

    public String findLongPrefixKey(String pattern) {
        if (pattern == null || pattern.isEmpty()) {
            return null;
        }
        int index = findLongPrefixIndex(root, pattern, -1);
        return isValid(pattern, index) ? pattern.substring(0, index + 1) : null;
    }

    private int findLongPrefixIndex(Node<T> root, String pattern, int current) {
        if (root == null || current == pattern.length()) {
            return -1;
        }
        int childIndex = current + 1;
        char childKey = isValid(pattern, childIndex) ? pattern.charAt(childIndex) : 0;
        int result = findLongPrefixIndex(root.findChild(childKey), pattern, childIndex);
        if (isValid(pattern, result)) {
            return result;
        }

        return root.isWord() ? current : -1;
    }

    private boolean isValid(String pattern, int index) {
        return index >= 0 && index < pattern.length();
    }

    private void addWord(List<String> list, Node<T> current, String prefix, int depth) {
        if (current == null) {
            return;
        }
        if (current.isWord()) {
            list.add(prefix);
        }
        if (depth == 0) {
            return;
        }

        for (Node<T> node : current) {
            if (node != null) {
                addWord(list, node, prefix + node.getKey(), depth - 1);
            }
        }
    }

    private void addWord(Node<T> current, String currentWord, List<String> list) {
        if (current == null) {
            return;
        }
        if (current.isWord()) {
            list.add(currentWord);
        }
        for (Node<T> node : current) {
            if (node != null) {
                addWord(node, currentWord + node.getKey(), list);
            }
        }
    }

    public static class Node<T extends Comparable<T>> implements Iterable<Node<T>> {

        private static char BASE_CHAR = 'a';
        private static int CHILDREN_LENGTH = 26;

        private Node<T>[] children;
        private boolean exists;
        private final char key;
        private List<T> data = new ArrayList<>();

        Node(char ch) {
            this(ch, false);
        }

        Node(char ch, boolean exists) {
            this.children = new Node[CHILDREN_LENGTH];
            this.key = ch;
            this.exists = exists;
        }

        @Override
        public Iterator<Node<T>> iterator() {

            return new Iterator<Node<T>>() {

                private int cursor = 0;

                @Override
                public boolean hasNext() {
                    return cursor != children.length - 1;
                }

                @Override
                public Node<T> next() {
                    if (!this.hasNext()) {
                        throw new IndexOutOfBoundsException("There is no more children found from the list.");
                    }
                    return children[cursor++];
                }
            };
        }

        Node<T> addChild(char item) {
            checkArgument(BasicUtil.inRangeOf(children, index(item)),
                    "the given char (%s) is not a lower case english letter.", item);
            if (this.children[index(item)] == null) {
                this.children[index(item)] = new Node<T>(item);
            }
            return this.children[index(item)];
        }

        Node<T> addChild(char key, T value) {
            checkArgument(BasicUtil.inRangeOf(children, index(key)),
                    "the given char (%s) is not a lower case english letter.", key);
            if (this.children[index(key)] == null) {
                this.children[index(key)] = new Node<T>(key);
            }
            this.children[index(key)].data.add(value);
            return this.children[index(key)];
        }

        boolean hasChildren() {
            for (Node child : this.children) {
                if (child != null) {
                    return true;
                }
            }
            return false;
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

        Node<T> findChild(char item) {
            if (!BasicUtil.inRangeOf(children, index(item))) {
                return null;
            }
            return this.children[index(item)];
        }

        void setChild(char item, Node<T> node) {
            if (!BasicUtil.inRangeOf(children, index(item))) {
                return;
            }
            this.children[index(item)] = node;
        }

        public char getKey() {
            return key;
        }

        public List<T> values() {
            return new ArrayList<>(this.data);
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
