package tp.pr5.excepcion;

public class ErrorEjecucion extends Throwable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ErrorEjecucion() {
		super();
	}
	public ErrorEjecucion(String mensaje) {
		super(mensaje);
	}
	
}
