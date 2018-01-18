package tp.pr5.control.factorias;

import java.util.Scanner;

import tp.pr5.enums.Ficha;
import tp.pr5.jugador.Jugador;
import tp.pr5.jugador.JugadorAleatorioComplica;
import tp.pr5.jugador.JugadorHumanoComplica;
import tp.pr5.movimientos.Movimiento;
import tp.pr5.movimientos.MovimientoComplica;
import tp.pr5.reglas.ReglasComplica;
import tp.pr5.reglas.ReglasJuego;

public class FactoriaComplica implements FactoriaJuego{

	@Override
	public ReglasJuego CrearReglas() {
		return new ReglasComplica();
	}

	@Override
	public Movimiento CrearMovimiento(int fila, int col, Ficha color) {
		return new MovimientoComplica(col, color);
	}

	@Override
	public Jugador creaJugadorAleatorio() {
		return new JugadorAleatorioComplica();
	}

	@Override
	public Jugador creaJugadorHumano(Scanner sc) {
		return new JugadorHumanoComplica(sc);
	}

}
