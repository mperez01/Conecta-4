package tp.pr5.excepcion;

public class PosicionOcupada extends MovimientoInvalido {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PosicionOcupada() {
		super();
	}
	
	public PosicionOcupada(String mensaje) {
		super(mensaje);
	}

	public PosicionOcupada(int n) {
		super(n);
	}

}
