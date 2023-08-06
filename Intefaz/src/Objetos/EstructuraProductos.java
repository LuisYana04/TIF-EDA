public class Estructura {
    private TrieNode root;

    public Estructura() {
        root = new TrieNode();
    }

    public void insert(int codigo, String nombre, double precio, int stock) {
        TrieNode node = root;
        for (char c : nombre.toLowerCase().toCharArray()) {
            int index = c - 'a';
            if (node.children[index] == null) {
                node.children[index] = new TrieNode();
            }
            node = node.children[index];
        }
        node.isEndOfWord = true;
        node.producto = new Producto(codigo, nombre, precio, stock);
    }

    public boolean search(String nombre) {
        TrieNode node = root;
        for (char c : nombre.toLowerCase().toCharArray()) {
            int index = c - 'a';
            if (node.children[index] == null) {
                return false;
            }
            node = node.children[index];
        }
        return node != null && node.isEndOfWord;
    }

    public Producto buscarProducto(String nombre) {
        TrieNode node = root;
        for (char c : nombre.toLowerCase().toCharArray()) {
            int index = c - 'a';
            if (node.children[index] == null) {
                return null;
            }
            node = node.children[index];
        }
        return node != null && node.isEndOfWord ? node.producto : null;
    }
}