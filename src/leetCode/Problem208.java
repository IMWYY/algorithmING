package leetCode;

/**
 * Implement a trie with insert, search, and startsWith methods.
 * <p>
 * Example:
 * <p>
 * Trie trie = new Trie();
 * <p>
 * trie.insert("apple");
 * trie.search("apple");   // returns true
 * trie.search("app");     // returns false
 * trie.startsWith("app"); // returns true
 * trie.insert("app");
 * trie.search("app");     // returns true
 * create by stephen on 2018/6/22
 */
public class Problem208 {

    private TrieNode root;

    /** Initialize your data structure here. */
    public Problem208() {
        root = new TrieNode();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode curNode = root;
        for (char c : word.toCharArray()) {
            if (!curNode.containsKey(c)) {
                curNode.put(c, new TrieNode());
            }
            curNode = curNode.get(c);
        }
        curNode.setEnd(true);
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode curNode = root;
        for (char c: word.toCharArray()) {
            if (!curNode.containsKey(c)) {
                return false;
            }
            curNode = curNode.get(c);
        }
        return curNode.isEnd;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode curNode = root;
        for (char c: prefix.toCharArray()) {
            if (!curNode.containsKey(c)) {
                return false;
            }
            curNode = curNode.get(c);
        }
        return true;
    }

    private class TrieNode {
        private TrieNode[] nodes;
        private boolean isEnd;

        public TrieNode() {
            this.nodes = new TrieNode[26];
        }

        public boolean containsKey(char c) {
            return nodes[c - 'a'] != null;
        }


        public void put(char c, TrieNode node) {
            nodes[c - 'a'] = node;
        }

        public TrieNode get(char c) {
            return nodes[c - 'a'];
        }

        public boolean isEnd() {
            return isEnd;
        }

        public void setEnd(boolean end) {
            isEnd = end;
        }
    }

}
