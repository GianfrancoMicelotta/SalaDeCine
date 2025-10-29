package candyBar;


import java.util.Objects;

public abstract class Producto implements Comparable<Producto> {
    String nombre;
    double precioBase;
    int Stock;

    protected abstract double calcularPrecioFinal();

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(double precioBase) {
        this.precioBase = precioBase;
    }

    public int getStock() {
        return Stock;
    }

    public void setStock(int stock) {
        Stock = stock;
    }

    public Producto(String nombre, double precioBase, int stock) {
        this.nombre = nombre;
        this.precioBase = precioBase;
        this.Stock = stock;
    }

    // Orden natural por stock
    @Override
    public int compareTo(Producto o) {
        return Integer.compare(this.Stock, o.Stock);
    }

    // Evitar duplicados en Sets
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Producto)) return false;
        Producto producto = (Producto) o;
        return nombre.equalsIgnoreCase(producto.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre.toLowerCase());
    }
}

