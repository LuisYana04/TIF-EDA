package proyecto;

public class Producto implements Comparable<Producto> {
    private int codigo;
    private String descripcion;
    private int stock;
    private int codigoAlmacen; // Nuevo campo

    public Producto(int codigo, String descripcion, int stock, int codigoAlmacen) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.stock = stock;
        this.codigoAlmacen = codigoAlmacen; // Inicialización del nuevo campo
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getCodigoAlmacen() { // Getter del nuevo campo
        return codigoAlmacen;
    }

    public void setCodigoAlmacen(int codigoAlmacen) { // Setter del nuevo campo
        this.codigoAlmacen = codigoAlmacen;
    }

    @Override
    public int compareTo(Producto otroProducto) {
        return Integer.compare(this.codigo, otroProducto.codigo);
    }

    @Override
    public String toString() {
        return "Código: " + codigo + ", Descripción: " + descripcion + ", Stock: " + stock + ", Código Almacén: " + codigoAlmacen;
    }
}