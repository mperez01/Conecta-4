package tp.pr5.reglas;
import tp.pr5.enums.Ficha;
import tp.pr5.logica.Tablero;

/**
 * Constructora de ReglasComplica heredadas de su clase padre "ReglasJuego".
 *@param col
 *			Describe la cantidad de columnas en el juego Complica.
 *@param fila
 *			Describe la cantidad de filas en el juego Complica.
 */
public class ReglasComplica extends ReglasJuego{
	private final int col = 4;
	private final int fila = 7;

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
	 * Método que obtiene el jugador ganador de la partida, sino hay ganador se sigue jugando hasta q se encuentre uno.
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
		//Cuando haya completado una columna, tendremos que quitar la primera ficha de la columma, y comprobar
		// todas las posibles combinaciones ganadoras en esa columna.
		// f(fila) tiene como inicial posicion la altura del tablero, cuando llega a la posición más alta 
		// f tiene el valor 0
		
			if (f == 0) {
			final int num = 2;
			int numero [];
			numero = new int [num];
			//numero[0] -> FICHAS BLANCAS
			numero[0] = 0;
			//numero[1] -> FICHAS NEGRAS
			numero[1] = 0;

			Ficha turn = ultimo;
			for (int i = this.fila; i > 0; i--) {
				
				if (ReglasJuegoCuatroEnLinea.cuatroEnLinea(tablero, turn, i, c)) {
					if (turn == Ficha.BLANCA)
						numero[0] ++;
					else if (turn == Ficha.NEGRA)
						numero[1] ++;
				}
				turn = tablero.getCasilla(i, c);
			}
			if ((numero[0] > 0) && (numero[1] > 0)) {
				tablas(tablero);
				return null;
			}
			else
				if (numero[0] > 0 && numero[1] == 0)
					return Ficha.BLANCA;
				else
					if (numero[1] > 0 && numero[0] == 0)
						return Ficha.NEGRA;
					else
						return null;
		}
		else
			if (ReglasJuegoCuatroEnLinea.cuatroEnLinea(tablero, ultimo, f, c))
				return ultimo;
		else
			return null;
	}

	/**
	 * Método que muestra si el tablero termina en empate,Como en el juego complica no se puede empatar no esta hecho.
	 * @param t
	 * 			Recibe el tablero del juego.
	 */
	public boolean tablas(Tablero t) {
		//No hay tablas en COMPLICA
		return true;
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
