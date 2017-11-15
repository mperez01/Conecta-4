package tp.pr5.reglas;

import tp.pr5.enums.Ficha;
import tp.pr5.logica.Tablero;

/**
 * Constructora de ReglaJuegoCuatroEnLinea.
 * 
 * @param GANAS
 * 			Parámetro entero que al que hay que llegar para ganar la partida.
 */
public class ReglasJuegoCuatroEnLinea {
	private final static int GANAS = 4;

	/**
	 * Método que nos calcula cuando un jugador ha conseguido hacer 4 en raya, y por tanto gana la partida.
	 * @param f
	 * 			Recibe la fila en que esta la combinación ganadora.
	 * @param c
	 * 			Recibe la columna en que esta la combinación ganadora.
	 * @param t
	 * 			Recibe el tablero jugado.
	 * 
	 * @return enLinea
	 * 			Booleano que nos dice si se ha conseguido hacer 4 en raya o no.
	 */
	static public boolean cuatroEnLinea(Tablero t, Ficha turno, int f, int c) {
		boolean enLinea = false;
		if (comprobarHorizontal(f, c, t)) {
			enLinea = true;

		} else if (comprobarVertical(f, c, t)) {
			enLinea = true;
		} else if (comprobarDiagonal(f, c, t)) {
			enLinea = true;
		}
		return enLinea;
	}

	/**
	 * Metodo privado, que  comprueba si existen 4 (o más)
	 * fichas iguales en todas las diagonales posibles partiendo de la posición que se pasa como
	 * parametro.
	 * 
	 * @param fila
	 *            Numero que representa la fila donde se encuentra la ficha
	 * @param columna
	 *            Número que representa la columna donde se encuentra la ficha
	 * @param tablero
	 * 			  Recibe el tablero actual para comprobar si alguien ha ganado.
	 * 
	 * @return boolean "enLinea". True en caso de haber encontrado 4 fichas iguales, false
	 *         en caso contrario
	 */
	private static boolean comprobarDiagonal(int fila, int columna,
			Tablero tablero) {
		boolean enLinea = false;

		int conectadas = 1, vueltas = 1;
		// en estos dos bucles while se comprubea la diagonal de izquierda a
		// derecha
		while (columna + vueltas <= tablero.getAncho() && fila - vueltas > 0
				&& tablero.getCasilla(fila - vueltas, columna + vueltas) == tablero.getCasilla(fila, columna)) {
			conectadas++;
			vueltas++;
		}
		vueltas = 1;
		while (fila + vueltas <= tablero.getAlto() && columna - vueltas > 0
				&& tablero.getCasilla(fila + vueltas, columna - vueltas) == tablero.getCasilla(fila, columna)) {
			conectadas++;
			vueltas++;
		}
		vueltas = 1;

		/*
		 * COMPROBACION IGUAL QUE LA ANTERIOR PERO EN SENTIDO CONTRARIO
		 */
		// si de izquierda a derecha no existiera ningun grupo de cuatro pasamos
		// a la comprobacion de derecha a izquierda
		if (conectadas < ReglasJuegoCuatroEnLinea.GANAS) {
			conectadas = 1;
			while (fila - vueltas > 0
					&& columna - vueltas > 0
					&& tablero.getCasilla(fila - vueltas, columna - vueltas) == tablero
							.getCasilla(fila, columna)) {
				conectadas++;
				vueltas++;
			}
			vueltas = 1;
			while (fila + vueltas <= tablero.getAlto()
					&& columna + vueltas <= tablero.getAncho()
					&& tablero.getCasilla(fila + vueltas, columna + vueltas) == tablero
							.getCasilla(fila, columna)) {
				conectadas++;
				vueltas++;
			}
		}
		if (conectadas >= ReglasJuegoCuatroEnLinea.GANAS)
			enLinea = true;
		// si conectadas llega a 4 (o lo supera) devolvemos true
		return enLinea;
	}

	/**
	 * Metodo privado, que comprueba si existen 4 (o más)
	 * fichas iguales en vertical partiendo de la posición que se pasa como
	 * parametro.
	 * 
	 * @param fila
	 *            Numero que representa la fila donde se encuentra la ficha.
	 * @param columna
	 *            Número que representa la columna donde se encuentra la ficha.
	 * @param tablero
	 * 			  Recibe el tablero actual para comprobar si alguien ha ganado.
	 *            
	 * @return boolean "enLinea". True en caso de haber encontrado 4 fichas iguales, false
	 *         en caso contrario
	 */
	private static boolean comprobarVertical(int fila, int columna,
			Tablero tablero) {

		int conectadas = 1, vueltas = 1;
		boolean enLinea = false;

		while (fila - vueltas > 0
				&& tablero.getCasilla(fila - vueltas, columna) == tablero
						.getCasilla(fila, columna)) {
			conectadas++;
			vueltas++;
		}
		vueltas = 1;
		while (fila + vueltas <= tablero.getAlto()
				&& tablero.getCasilla(fila + vueltas, columna) == tablero
						.getCasilla(fila, columna)) {
			conectadas++;
			vueltas++;
		}
		if (conectadas >= ReglasJuegoCuatroEnLinea.GANAS) {
			enLinea = true;
		}
		return enLinea;
	}

	/**
	 * Metodo privado, que comprueba si existen 4 (o más)
	 * fichas iguales en horizontal partiendo de la posición que se pasa como
	 * parametro.
	 * 
	 * @param fila
	 *            Numero que representa la fila donde se encuentra la ficha.
	 * @param columna
	 *            Numero que representa la columna donde se encuentra la ficha.
	 * @param tablero
	 * 			  Recibe el tablero actual para comprobar si alguien ha ganado.
	 * 
	 * @return boolean "enLinea". True en caso de haber encontrado 4 fichas iguales, false
	 *         en caso contrario
	 */
	private static boolean comprobarHorizontal(int fila, int columna,
			Tablero tablero) {

		int conectadas = 1, vueltas = 1;
		boolean enLinea = false;
		while (columna - vueltas > 0
				&& tablero.getCasilla(fila, columna - vueltas) == tablero
						.getCasilla(fila, columna)) {
			conectadas++;
			vueltas++;
		}
		vueltas = 1;
		while (columna + vueltas <= tablero.getAncho()
				&& tablero.getCasilla(fila, columna + vueltas) == tablero
						.getCasilla(fila, columna)) {
			conectadas++;
			vueltas++;
		}

		if (conectadas >= ReglasJuegoCuatroEnLinea.GANAS) {
			enLinea = true;
		}

		return enLinea;
	}

}
