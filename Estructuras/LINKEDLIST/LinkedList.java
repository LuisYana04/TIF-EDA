package Estructuras.LINKEDLIST;

public class LinkedList <A> {
    private Node <A> root;
    public LinkedList (Node <A> node) {
        this.root = node;
    }
    public LinkedList () {
        this(null);
    }
    public void setRoot(Node<A> root) {
        this.root = root;
    }
    public Node<A> getRoot() {
        return root;
    }

    public boolean isEmpty () {
        if(root == null)
            return true;
        return false;
    }
    public boolean search(A x) {
		Node<A> aux = this.root;
		for(; aux != null; aux = aux.getNextNode()) {
			if (aux.getData().toString().equals(x.toString())) {
				return true;
			}
		}
		return false;
	}

    public A searchData(A x) {
		Node<A> aux = this.root;
		while(aux != null) {
			if (aux.getData().toString().equals(x.toString())) {
				return aux.getData();
			}
			aux = aux.getNextNode();
		}
		return null;
	}
	
	public void insertFirst(A x) {
		this.root = new Node<A>(x, this.root);
	}
	
	public void insertLast(A x) {
		if (this.isEmpty()) {
			this.insertFirst(x); 
		}
		else {
			Node <A> aux = this.root;
			while (aux != null && aux.getNextNode() != null)
				aux = aux.getNextNode(); 
			if (aux != null) {
				aux.setNextNode(new Node<A> (x)); 
			}
		}
	}
	
	public void remove(A x) {
		if (!isEmpty() && this.root.getData().equals(x)) { 
			this.root = this.root.getNextNode();
		}
		else {
			Node<A> aux = this.root;
			while (aux.getNextNode() != null && !aux.getNextNode().getData().equals(x)) {
				aux = aux.getNextNode();
			}
			if (aux.getNextNode()!= null) {
				aux.setNextNode(aux.getNextNode().getNextNode());
			}
		}
	}
	
	@Override
	public String toString() {
		String str = "";
		for(Node<A> aux = this.root; aux != null; aux = aux.getNextNode()) { 
			str = str + aux.toString() + ", "; 
		}
		return str;
	}
}