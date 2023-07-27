package Objetos;
public class Almacen{

    private Integer codigo;
    private String nombre;
    private String direccion;

    public Almacen (int code, String name, String adress) {
        this.codigo = code;
        this.nombre = name;
        this.direccion = adress;
    }

    public Almacen () {
        this(0, null, null);
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return codigo;
    }
    
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString () {
        return "Nombre >>> "+this.nombre+"\nCodigo >>> "+this.codigo+"Direccion >>> "+this.direccion;
    }
}