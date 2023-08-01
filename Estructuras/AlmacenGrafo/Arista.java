package Estructuras.AlmacenGrafo;

public class Arista <A>{
   private int peso;
   private Vertice <A> nexVertice;

   public Arista (int peso, Vertice <A> next) {
      this.peso = peso;
      this.nexVertice = next;
   }
   public Arista (){
      this(0, null);
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
}
