package tp.pr5.excepcion;

public class StringIncorrecto extends Throwable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public StringIncorrecto() {
		super();
	}
	public StringIncorrecto(String mensaje) {
		super(mensaje);
	}
}
