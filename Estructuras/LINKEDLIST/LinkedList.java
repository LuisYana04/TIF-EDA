package Estructuras.LINKEDLIST;

public class LinkedList <E> {
    private Node <E> root;
    public LinkedList (Node <E> node) {
        this.root = node;
    }
    public LinkedList () {
        this(null);
    }
    public void setRoot(Node<E> root) {
        this.root = root;
    }
    public Node<E> getRoot() {
        return root;
    }

    public boolean isEmpty () {
        if(root == null)
            return true;
        return false;
    }
    public boolean search(E x) {
		Node<E> aux = this.root;
		for(; aux != null; aux = aux.getNextNode()) {
			if (aux.getData().equals(x)) {
				return true;
			}
		}
		return false;
	}
    public E searchData(E x) {
		Node<E> aux = this.root;
		for(; aux != null; aux = aux.getNextNode()) {
			if (aux.getData().equals(x)) {
				return aux.getData();
			}
		}
		return null;
	}
	
	public void insertFirst(E x) {
		this.root = new Node<E>(x, this.root);
	}
	
	public void insertLast(E x) {
		if (this.isEmpty()) {
			this.insertFirst(x); 
		}
		else {
			Node <E> aux = this.root;
			while (aux != null && aux.getNextNode() != null)
				aux = aux.getNextNode(); 
			if (aux != null) {
				aux.setNextNode(new Node<E> (x)); 
			}
		}
	}
	
	public void remove(E x) {
		if (this.root != null && this.root.getData().equals(x)) { 
			this.root = this.root.getNextNode();
		}
		else {
			Node<E> aux = this.root;
			while (aux.getNextNode() != null && !aux.getNextNode().getData().equals(x)) {
				aux = aux.getNextNode();
			}
			if (aux.getNextNode()!= null) {
				aux.setNextNode(aux.getNextNode().getNextNode());
			}
		}
	}
	
	
	public String toString() {
		String str = "";
		for(Node<E> aux = this.root; aux != null; aux = aux.getNextNode()) { 
			str = str + aux.toString() + " >--> "; 
		}
		return str;
	}
}