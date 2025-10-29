package candyBar;


import java.util.*;
import java.util.stream.Collectors;

public class CandyBar {
    private Set<Producto> productos;

    public CandyBar() {
        this.productos = new TreeSet<>(); // orden natural por stock
    }

    public boolean agregarProducto(Producto producto) throws ProductoDuplicadoException {
        if (productos.contains(producto)) {
            throw new ProductoDuplicadoException("El producto ya existe: " + producto.getNombre());
        }
        return productos.add(producto);
    }

    public boolean eliminarProducto(String nombreABuscar) throws ProductoNoEncontradoException {
        Optional<Producto> productoAEliminar = productos.stream()
                .filter(p -> p.getNombre().equalsIgnoreCase(nombreABuscar))
                .findFirst();

        if (productoAEliminar.isPresent()) {
            productos.remove(productoAEliminar.get());
            return true;
        } else {
            throw new ProductoNoEncontradoException("Producto no encontrado: " + nombreABuscar);
        }
    }

    public List<Producto> obtenerInventario() {
        return new ArrayList<>(this.productos);
    }

    public List<Bebida> obtenerBebidasOrdenadasPorPrecio() {
        return productos.stream()
                .filter(p -> p instanceof Bebida)
                .map(p -> (Bebida) p)
                .sorted(Comparator.comparingDouble(Bebida::getPrecioBase))
                .collect(Collectors.toList());
    }

    public List<Snack> obtenerSnacksOrdenadosPorNombre() {
        return productos.stream()
                .filter(p -> p instanceof Snack)
                .map(p -> (Snack) p)
                .sorted(Comparator.comparing(Producto::getNombre))
                .collect(Collectors.toList());
    }
}
