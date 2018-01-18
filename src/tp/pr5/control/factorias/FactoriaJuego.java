package tp.pr5.control.factorias;

import java.util.Scanner;

import tp.pr5.enums.Ficha;
import tp.pr5.jugador.Jugador;
import tp.pr5.movimientos.Movimiento;
import tp.pr5.reglas.ReglasJuego;

public interface FactoriaJuego {
	public ReglasJuego CrearReglas();

	public Movimiento CrearMovimiento(int fila, int col, Ficha color);

	public Jugador creaJugadorAleatorio();

	public Jugador creaJugadorHumano(Scanner sc);
}
