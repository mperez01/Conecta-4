package tp.pr5.excepcion;

public class ColumnaLlena extends MovimientoInvalido {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ColumnaLlena() {
		super();
	}

	public ColumnaLlena(String mensaje) {
		super(mensaje);
	}
	
	public ColumnaLlena(int n) {
		super(n);
	}

}
