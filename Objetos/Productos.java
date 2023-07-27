package Objetos;

public class Productos {
    private Integer codigo;
    private String descripcion;
    private Integer stock;
    public Productos (int code, String description, int stock) {
        this.codigo = code;
        this.descripcion = description;
        this.stock = stock;
    }
    public Productos () {
        this(0, null, 0);
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
    @Override
    public String toString () {
        return "Codigo >>> "+this.codigo+"\nDescripcion >>> "+this.descripcion+"Stock >>> "+this.stock;
    }
}
