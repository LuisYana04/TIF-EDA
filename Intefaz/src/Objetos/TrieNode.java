public class TrieNode {
    private static final int ALPHABET_SIZE = 26;
    TrieNode[] children;
    boolean isEndOfWord;
    Producto producto;

    TrieNode() {
        children = new TrieNode[ALPHABET_SIZE];
        isEndOfWord = false;
        producto = null;
    }
}