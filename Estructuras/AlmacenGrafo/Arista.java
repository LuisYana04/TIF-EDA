package Estructuras.AlmacenGrafo;

public class Arista <A>{
   private int peso;
   private Vertice <A> nexVertice;

    public Arista (int distancia,Vertice <A> next) {
      this.peso = distancia;
      this.nexVertice = next;
    }
   public Arista (Vertice <A> next) {
      this.peso = -1;
      this.nexVertice = next;
   }
   public void setNexVertice(Vertice<A> nexVertice) {
       this.nexVertice = nexVertice;
   }
   public Vertice<A> getNexVertice() {
       return nexVertice;
   }
   public void setPeso(int peso) {
       this.peso = peso;
   }
   public int getPeso() {
       return peso;
   }
    public boolean equals(Object o) {
		if (o instanceof Arista<?>) {
			Arista<A> e = (Arista<A>) o;
			return this.nexVertice.equals(e.nexVertice);
		}
		return false;
	}
   @Override
	public String toString() {
		if (this.peso > -1)
			return this.nexVertice.Almacen.toString() + "[" + this.peso + "]";
		return this.nexVertice.Almacen.toString();
	}
}
