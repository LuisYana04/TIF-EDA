package Objetos;

public class Producto {
    private int codigo;
    private double precio;
    private String descripcion;
    private Integer stock;

    public Producto(int codigo, String nombre, double precio, int stock) {
        this.codigo = codigo;
        this.precio = precio;
        this.descripcion = nombre;
        this.stock = stock;
    }
    public Producto() {
        this(0, null, 0, 0);
    }
    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }
    public Integer getCodigo() {
        return codigo;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setStock(Integer stock) {
        this.stock = stock;
    }
    public Integer getStock() {
        return stock;
    }
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    @Override
    public String toString () {
        return "Codigo >>> "+this.codigo+"\nDescripcion >>> "+this.descripcion+"Stock >>> "+this.stock;
    }
}
