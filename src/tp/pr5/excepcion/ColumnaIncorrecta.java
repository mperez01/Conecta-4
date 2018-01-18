package tp.pr5.excepcion;

public class ColumnaIncorrecta extends MovimientoInvalido {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ColumnaIncorrecta() {
		super();
	}
	
	public ColumnaIncorrecta(String mensaje) {
		super(mensaje);
	}
	
	public ColumnaIncorrecta(int n) {
		super(n);
	}
}
