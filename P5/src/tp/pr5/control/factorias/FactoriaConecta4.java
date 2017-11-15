package tp.pr5.control.factorias;

import java.util.Scanner;

import tp.pr5.enums.Ficha;
import tp.pr5.jugador.Jugador;
import tp.pr5.jugador.JugadorAleatorioConecta4;
import tp.pr5.jugador.JugadorHumanoConecta4;
import tp.pr5.movimientos.Movimiento;
import tp.pr5.movimientos.MovimientoConecta4;
import tp.pr5.reglas.ReglasConecta4;
import tp.pr5.reglas.ReglasJuego;

public class FactoriaConecta4 implements FactoriaJuego{

	@Override
	public ReglasJuego CrearReglas() {
		return new ReglasConecta4();
	}
	@Override
	public Movimiento CrearMovimiento(int fila, int col, Ficha color) {
		//Segun enunciado, la "fila" la obviamos pero la pedimos debido al interfaz
		return new MovimientoConecta4(col, color);
	}

	@Override
	public Jugador creaJugadorAleatorio() {
		return new JugadorAleatorioConecta4();
	}

	@Override
	public Jugador creaJugadorHumano(Scanner sc) {
		return new JugadorHumanoConecta4(sc);
	}

}
