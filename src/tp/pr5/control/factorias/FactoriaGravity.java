package tp.pr5.control.factorias;

import java.util.Scanner;

import tp.pr5.enums.Ficha;
import tp.pr5.jugador.Jugador;
import tp.pr5.jugador.JugadorAleatorioGravity;
import tp.pr5.jugador.JugadorHumanoGravity;
import tp.pr5.movimientos.Movimiento;
import tp.pr5.movimientos.MovimientoGravity;
import tp.pr5.reglas.ReglasGravity;
import tp.pr5.reglas.ReglasJuego;

public class FactoriaGravity implements FactoriaJuego{
	
	private int col = 10;
	private int fil = 10;
	public FactoriaGravity() {
		
	}
	
	public FactoriaGravity(int fila, int col) {
		this.fil = fila;
		this.col = col;
	}

	public ReglasJuego CrearReglas() {
		if (this.col != 0 && this.fil != 0 )
			return new ReglasGravity(this.fil, this.col);
		else
			return new ReglasGravity();
	}

	public Movimiento CrearMovimiento(int fila, int col, Ficha color) {
		 return new MovimientoGravity(fila, col, color);
	}

	public Jugador creaJugadorAleatorio() {
		return new JugadorAleatorioGravity();
		}

	public Jugador creaJugadorHumano(Scanner sc) {
		return new JugadorHumanoGravity(sc);
	}

}
