package tp.pr5.logica;

import tp.pr5.enums.Ficha;
import tp.pr5.enums.TipoJuego;
import tp.pr5.excepcion.MovimientoInvalido;


public interface Observador {
	
	//Cuando la partida modifique su estado interno se invocarán estos métodos
	/**
	 * Notifica que se ha reiniciado la partida
	 * @param tabIni Estado inicial del tablero
	 * @param turno Color del que mueve primero
	 */
	public void onResetPartida(TableroInmutable tabIni, Ficha turno);
	/**
	 * se notifica que h terminado la partida llamando a este método
	 * @param tabFin  Proporciona una vista de SOLO lectura al tablero
	 * @param ganador Devuelve el color del ganador
	 */
	public void onPartidaTerminada(TableroInmutable tabFin, Ficha ganador);
	/**
	 * Notifica que se ha cambiado el juego. TABLERO SOLO LECTURA como parametro?¿
	 * @param tipo
	 * @param turno
	 */
	public void onCambioJuego(TableroInmutable tab, TipoJuego tipo, Ficha turno);
	
	//GESTIÓN DE MOVIMIENTOS
	
	/**
	 * Notifica que se ha terminado de realizar un movimiento
	 * @param tab Vista del tablero solo lectura
	 * @param turno Turno del siguiente jugador
	 */
	public void onMovimientoEnd(TableroInmutable tab, Ficha turno, boolean hay);
	/**
	 * Notifica que se ha deshecho un movimiento
	 * @param tab estado final del tablero
	 * @param hayMas True si hay mas movimientos a deshacer, false en cc
	 */
	public void onUndo(TableroInmutable tab, Ficha turno, boolean hayMas);
	
	/**
	 * Notifica que una operación deshacer no ha tenido éxito porque
	 * no se pudee deshacer
	 */
	public void onUndoNotPossible();
	
	/**
	 * Notifica que se ha producido un movimiento incorrecto
	 * @param explicacion String con la explicacion del problema que se ha producido
	 */
	public void onMovimienoIncorreto(MovimientoInvalido movimientoException);
	
	public void onCambioJugador(Ficha turno);
	
	public void onMovimientoStart(Ficha turno, boolean hay);
	
	public void onError(String mensaje);
	
}
