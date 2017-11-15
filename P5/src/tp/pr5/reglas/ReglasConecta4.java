package tp.pr5.reglas;
import tp.pr5.enums.Ficha;
import tp.pr5.logica.Tablero;

/**
 * Constructora de ReglasConecta4 heredadas de su clase padre "ReglasJuego".
 *@param col
 *			Describe la cantidad de columnas en el juego Complica.
 *@param fila
 *			Describe la cantidad de filas en el juego Complica.
 */
public class ReglasConecta4 extends ReglasJuego {
	private final int col = 7;
	private final int fila = 6;
	
	/**
	 * Método que inicializa el juego Complica.
	 * 
	 * @return t
	 * 			Devuelve el tablero inicializado.
	 */
	public Tablero iniciaTablero() {
		Tablero t = new Tablero(fila, col);
		t.reset();
		return t;
	}

	/**
	 * Método que obtiene el jugador ganador de la partida.
	 * @param f
	 * 			Recibe la fila en que esta la combinación ganadora.
	 * @param c
	 * 			Recibe la columna en que esta la combinación ganadora.
	 * @param ultimo
	 * 			Recibe el turno del ultimo jugador que puso la ficha.
	 * @param tablero
	 * 			Recibe el tablero jugado.
	 */
	public Ficha hayGanador(int f, int c, Ficha ultimo, Tablero tablero) {
		if (ReglasJuegoCuatroEnLinea.cuatroEnLinea(tablero, ultimo, f, c))
			return ultimo;
		else if (tablas(tablero))
			return Ficha.VACIA;
		else
			return null;
	}

	/**
	 * Comprueba si el tablero esta completo o no. Si todas las posiciones estan ocupadas
	 * por fichas distintas de blancas
	 * 
	 * @return booleano True si esta completo, false en caso contrario
	 */
	public boolean tablas(Tablero t) {
		boolean completo = true;
		for(int i = 0; i < t.getAlto(); i++) {
			for (int j = 0; j < t.getAncho(); j++){
				if (t.getCasilla(i + 1,j + 1) == Ficha.VACIA) {
					completo = false;
				}
			}
		}	
		return completo;
	}
	
	/**
	 * Obtiene la columna.
	 */
	public int getCol() {
		return this.col;
	}
	
	/**
	 * Obtiene la fila. 
	 */
	public int getFila(){
		return this.fila;
	}

	@Override
	public Ficha jugadorInicial() {
		// TODO Auto-generated method stub
		return Ficha.BLANCA;
	}

}
