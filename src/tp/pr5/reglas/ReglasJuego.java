package tp.pr5.reglas;

import tp.pr5.enums.Ficha;
import tp.pr5.logica.Tablero;

/**
 * Método que inicializa los tableros de ambos juegos.
 * 
 */
public abstract class ReglasJuego {
	
	public abstract Tablero iniciaTablero();
	
	public abstract Ficha jugadorInicial();
	
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
	public abstract Ficha hayGanador(int f, int c, Ficha ultimo, Tablero tablero); 
	
	/**
	 * Método que muestra si el tablero termina en empate.
	 * @param t
	 * 			Recibe el tablero del juego.
	 */
	public abstract boolean tablas (Tablero t);
	
	/**
	 * Método que cambia el turno de los jugadores, según vayan poniendo su ficha.
	 * @param ultimo
	 * 			Recibe el parámetro del turno del último jugador que ha puesto ficha.
	 * 
	 * @return Ficha
	 * 			Devuelve el color del jugador que tiene que jugar ahora.
	 */
	public Ficha siguienteTurno(Ficha ultimo){
		if (ultimo == Ficha.BLANCA)
			return Ficha.NEGRA;
		else if (ultimo == Ficha.NEGRA)
			return Ficha.BLANCA;
		else
			return Ficha.VACIA;
	}
	
	/**
	 * Obtiene la columna.
	 */
	public abstract int getCol();
	
	/**
	 * Obtiene la fila. 
	 */
	public abstract int getFila();
}
