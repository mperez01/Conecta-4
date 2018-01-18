package tp.pr5.movimientos;

import tp.pr5.enums.Ficha;
import tp.pr5.excepcion.MovimientoInvalido;
import tp.pr5.logica.Tablero;

public abstract class Movimiento {

	protected int columna;
	protected Ficha turno;
	protected int fila;
	
	/**
	 * Constructora de la clase Movimiento.
	 */
	public Movimiento(int columna, int fila, Ficha turno) {
		this.columna = columna;
		this.turno = turno;
		this.fila = fila;
	}

	/**
	 * Ejecuta el movimento sobre el tablero que se recibe.
	 */
	public abstract void ejecutaMovimiento(Tablero t) throws MovimientoInvalido; //LANZA EXCEPCION MOVIMIENTO INVALIDO
	
	/**
	 * Obtiene el tipo de ficha.
	 */
	public abstract Ficha getFicha();

	/**
	 * Obtiene el color de la ficha del jugador.
	 */
	public Ficha getJugador() {
		return this.turno;
	}
	
	/**
	 * Obtiene la columna.
	 */
	public int getColumna() {
		return this.columna;
	}

	/**
	 * Obtiene la fila
	 */
	public int getFila() {
		return this.fila;
	}
	
	/**
	 * Dada una columna, comprueba si esta en el rango valido
	 * 
	 * @param col
	 *            Numero que indica la columna donde se quiere poner una ficha
	 * @return boolean True en caso de ser una columna válida
	 */
	public boolean colNoValido(Tablero t) {
		boolean ok;
		// debe estar comprendido entre este rango
		if (this.columna <= t.getAncho() && this.columna > 0)
			ok = true;
		else
			ok = false;

		return ok;
	}
	
	public boolean filNoValida(Tablero t) {
		boolean ok;
		//Tablero inverso, a priori funcion implementada correctamente
		if (this.fila > 0 && this.fila <= t.getAlto())
			ok = true;
		else
			ok = false;
		return ok;
	}
	/**
	 * Deshace el movimiento en el tablero recibido como parametro.
	 */
	public abstract boolean undo(Tablero t);
	
	
	public abstract boolean hayMovimiento(Tablero t, Ficha Color, int columna , int fila);

	
	//Este metodo se va a utilizar en ambas clases heredadas, por eso que sea público
	/**Metodo publico (se usa en MovimientoComplica y MovimientoConecta4). Partiendo de una columna se devuelve la cantidad
	 * de filas que tenemos nos quedan por encima hasta llegar a lo alto del tablero
	 * 
	 * @param col Numero de la columna que queremos comprobar
	 * @return cont (entero) cantiddad de huecos restantes
	 */
	public int buscaLinea (int col, Tablero t) {
		int cont = t.getAlto();
		while (cont > 0 && t.getCasilla(cont, col) != Ficha.VACIA) {
			cont --;
		}	
		this.fila = cont;
		return cont;
	}
}
