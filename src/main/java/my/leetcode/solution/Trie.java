package my.leetcode.solution;

import java.util.ArrayList;
import java.util.List;

class Trie {
    private class Node {
        public List<Node> children = new ArrayList<>();
        public char value;
    }

    private List<Node> trees = new ArrayList<>();

    /**
     * Initialize your data structure here.
     */
    public Trie() {

    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        if (word == null || word.length() == 0) {
            return;
        }

        Node root = getRoot(word);
        if (root == null) {
            root = new Node();
            root.value = word.charAt(0);
            trees.add(root);
        }

        if (word.length() > 1) {
            build(root, word.substring(1));
        }
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        if (word == null || word.length() == 0) {
            return true;
        }

        Node root = getRoot(word);
        if (root == null) {
            return false;
        }

        while (word.length() > 1) {
            String fixWord = word;
            Node child = root.children.stream().filter(t -> t.value == fixWord.charAt(1)).findAny().orElse(null);
            if (child == null) {
                return false;
            }

            word = word.substring(1);
            root = child;
        }

        return root.children.isEmpty();
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String word) {
        if (word == null || word.length() == 0) {
            return true;
        }

        Node root = getRoot(word);
        if (root == null) {
            return false;
        }

        while (word.length() > 1) {
            String fixWord = word;
            Node child = root.children.stream().filter(t -> t.value == fixWord.charAt(1)).findAny().orElse(null);
            if (child == null) {
                return false;
            }

            word = word.substring(1);
            root = child;
        }

        return true;
    }

    private Node getRoot(String word) {
        Node root = trees.stream().filter(t -> t.value == word.charAt(0)).findAny().orElse(null);
        return root;
    }

    private void build(Node root, String word) {
        if (word == null || word.length() == 0) {
            return;
        }

        Node child = root.children.stream().filter(t -> t.value == word.charAt(0)).findAny().orElse(null);
        if (child == null) {
            child = new Node();
            child.value = word.charAt(0);
            root.children.add(child);
        }

        if (word.length() > 1) {
            build(child, word.substring(1));
        }
    }
}
