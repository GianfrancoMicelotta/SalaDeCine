package candyBar;

public class Combo extends Producto{
	
	private Snack snack;
	private Bebida bebida;
	private double porcentajeDescuento; 
	
	public Combo(String nombre, Snack snack, Bebida bebida, double porcentajeDescuento, int stock) {	
		super(nombre, (snack.getPrecioBase() + bebida.getPrecioBase()) , stock);
		this.snack = snack;
		this.bebida = bebida;
		this.porcentajeDescuento = porcentajeDescuento;
	}

	@Override
	protected double calcularPrecioFinal() {
		return precioBase - (precioBase * porcentajeDescuento);
		
	}
	
}
