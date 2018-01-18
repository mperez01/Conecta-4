package tp.pr5.movimientos;

import tp.pr5.enums.Ficha;
import tp.pr5.excepcion.*;
import tp.pr5.logica.Tablero;

public class MovimientoConecta4 extends Movimiento{
	
	/**
	 * Constructora de MovimientoConecta4 que viene heredada de su clase padre (Movimiento).
	 * @param columna
	 * 				Parámetro heredado de la clase padre.
	 * @param turno
	 * 				Parámetro heredado de la clase padre.
	 */
	public MovimientoConecta4(int columna, Ficha turno) {
		super(columna, 0, turno);
	}

	/**
	 * Método que realiza el movimiento del juego conecta4.
	 * @param tablero
	 * 			Recibe el tablero donde se hará el movimiento.
	 * 
	 * @return devolver
	 * 			Devuelve un booleano se el movimiento se ha realizado correctamente o no. 
	 */
	public void ejecutaMovimiento(Tablero t) throws MovimientoInvalido { //LANZA EXCEPCION MOVIMIENTO INVALIDO
		if (!this.colNoValido(t)) 
			throw new ColumnaIncorrecta("Columna incorrecta. Debe estar entre 1 y " + t.getAncho());
		else{
			int cont = this.buscaLinea(this.columna, t);
			if (cont <= 0)
				throw new ColumnaLlena("Columna llena");
			t.setCasilla(cont, this.columna, this.turno);
		}	
	}
	
	/**
	 * Obtiene el color de la ficha en este caso será "VACIA".
	 */
	public Ficha getFicha() {
		return Ficha.VACIA;
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
		boolean encontrado = false;
		while (!encontrado && i <= t.getAlto()) {
			if (t.getCasilla(i, this.columna) != Ficha.VACIA) {
				t.setCasilla(i, this.columna, Ficha.VACIA);
				encontrado = true;
			}
			i++;
		}
		return encontrado;
	}

	@Override
	public boolean hayMovimiento(Tablero t, Ficha Color, int i, int j) {
		// TODO Auto-generated method stub
		return true;
	}
}
