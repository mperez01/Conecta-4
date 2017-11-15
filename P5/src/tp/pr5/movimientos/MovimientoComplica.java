package tp.pr5.movimientos;

import tp.pr5.enums.Ficha;
import tp.pr5.excepcion.ColumnaIncorrecta;
import tp.pr5.excepcion.MovimientoInvalido;
import tp.pr5.logica.Tablero;

public class MovimientoComplica extends Movimiento {

	//Guarda la ficha que va a desaparecer al introducir una nueva ficha en una columna llena.
	private Ficha PrimeraFicha; 
	
	/**
	 * Constructora de MovimientoComplica que viene heredada de su clase padre (Movimiento).
	 * @param columna
	 * 				Parámetro heredado de la clase padre.
	 * @param turno
	 * 				Parámetro heredado de la clase padre.
	 * @param PrimeraFicha
	 * 				Parámetro de esta clase inicializado.
	 */
	public MovimientoComplica(int columna, Ficha turno) {
		super(columna, 0, turno);
		this.PrimeraFicha = Ficha.VACIA;
	}

	/**
	 * Método que realiza el movimiento del juego complica.
	 * @param tablero
	 * 			Recibe el tablero donde se hará el movimiento.
	 * 
	 * @return devolver
	 * 			Devuelve un booleano se el movimiento se ha realizado correctamente o no. 
	 */
	public void ejecutaMovimiento(Tablero tab) throws MovimientoInvalido {
		if (!this.colNoValido(tab))
			throw new ColumnaIncorrecta("Columna incorrecta. Debe estar entre 1 y " + tab.getAncho());
		int cont = this.buscaLinea(this.columna, tab);
		
		if (cont <= 0) {
			cont ++;
			Tablero aux;
			aux = tab;
			this.PrimeraFicha = aux.getCasilla(tab.getAlto(), this.columna);
			for (int i = tab.getAlto(); i > this.fila + 1; i--) {
				Ficha ficha;
				ficha = aux.getCasilla(i - 1, this.columna);
				tab.setCasilla(i, this.columna, ficha);
			}
			tab.setCasilla(cont, this.columna, this.turno);
			}
		else{
			tab.setCasilla(cont, this.columna, this.turno);
			//Añadir movimiento en la lista de "undo's"
		}	
	}
	
	/**
	 * Obtiene el color de la ficha guardada en la variable "PrimeraFicha".
	 */
	public Ficha getFicha() {
		return this.PrimeraFicha;
	}
	
	/**
	 * Método booleano undo que deshace un movimiento y devuelve true si lo ha deshecho correctamente.
	 */
	public boolean undo(Tablero t) {
		if (this.retirar(t))
			return true;
		else
			return false;
	}
	
	/**
	 * Método que retira una ficha del tablero.
	 * @param t
	 * 			Recibe el tablero que se está jugando en ese momento.
	 * 
	 * @return encontrado
	 * 			Devuelve un booleano si se ha podido deshacer el movimiento o no.
	 */
	private boolean retirar (Tablero t) {
		int i = 1;
		Tablero aux;
		aux = t;
		int fila = 1;
		
		boolean encontrado = false;
		
		if (this.PrimeraFicha != Ficha.VACIA){
			for(int j = fila; j < t.getAlto(); j++){
				Ficha ficha;
				ficha = aux.getCasilla(j + 1, this.columna);
				t.setCasilla(j, this.columna, ficha);	
			}
			t.setCasilla(t.getAlto(), this.columna, this.PrimeraFicha);
			encontrado = true;
			
		}
			else{
		
		while (!encontrado && i <= t.getAlto()) {
			if (t.getCasilla(i, this.columna) != Ficha.VACIA) {
				// Deberia no cambiarse por ficha vacia, en caso de haber solapado una ficha deberia hacerse ese cambio
				t.setCasilla(i, this.columna, Ficha.VACIA);
				encontrado = true;
			}
			i++;
			
			}
		}
		
		return encontrado;
	}

	@Override
	public boolean hayMovimiento(Tablero t, Ficha Color, int columna, int fila) {
		// TODO Auto-generated method stub
		return true;
	}	
}