package tp.pr5.control.factorias;

import java.util.Scanner;

import tp.pr5.enums.Ficha;
import tp.pr5.jugador.Jugador;
import tp.pr5.jugador.JugadorAleatorioReversi;
import tp.pr5.jugador.JugadorHumanoReversi;
import tp.pr5.movimientos.Movimiento;
import tp.pr5.movimientos.MovimientoReversi;
import tp.pr5.reglas.ReglasJuego;
import tp.pr5.reglas.ReglasReversi;

public class FactoriaReversi implements FactoriaJuego {


	public ReglasJuego CrearReglas() {
		
		return new ReglasReversi();
	}

	public Movimiento CrearMovimiento(int fila, int col, Ficha color) {
		
		 return new MovimientoReversi(col, fila, color);
	}

	public Jugador creaJugadorAleatorio() {
		
		return new JugadorAleatorioReversi();
	}

	public Jugador creaJugadorHumano(Scanner sc) {
		return new JugadorHumanoReversi(sc);
	}

}
