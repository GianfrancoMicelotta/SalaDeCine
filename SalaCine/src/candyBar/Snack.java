package candyBar;


public class Snack extends Producto {
    private Tamano tamano;

    public Snack(String nombre, double precioBase, Tamano tamano, int stock) {
        super(nombre, precioBase, stock);
        this.tamano = tamano;
    }

    @Override
    protected double calcularPrecioFinal() {
        switch (this.tamano) {
            case PEQUENO:
                return precioBase * 0.85; // descuento 15%
            case GRANDE:
                return precioBase * 1.20; // recargo 20%
            default:
                return precioBase;
        }
    }
}
