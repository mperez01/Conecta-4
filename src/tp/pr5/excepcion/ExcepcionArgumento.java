package tp.pr5.excepcion;

public class ExcepcionArgumento extends Throwable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ExcepcionArgumento() {
		super();
	}

	public ExcepcionArgumento(String mensaje) {
		super(mensaje);
	}
}
