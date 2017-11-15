package tp.pr5.excepcion;

public class FilaIncorrecta extends MovimientoInvalido {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FilaIncorrecta() {
		super();
	}
	
	public FilaIncorrecta(String mensaje) {
		super(mensaje);
	}
	
	public FilaIncorrecta(int n) {
		super(n);
	}
}