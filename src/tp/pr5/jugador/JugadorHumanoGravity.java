package tp.pr5.jugador;

import java.util.Scanner;

import tp.pr5.enums.Ficha;
import tp.pr5.logica.Tablero;

public class JugadorHumanoGravity extends Jugador {

	private Scanner sc;

	public JugadorHumanoGravity(Scanner sc) {
		this.sc = sc;
	}

	public void obtenFilaColumna(Tablero tab, Ficha color) {
		System.out.print("Introduce la fila: ");
		int fila = sc.nextInt();
		System.out.print("Introduce la columna: ");
		int columna = sc.nextInt();
		// quitamos el intro.
		sc.nextLine();
		this.fila = fila;
		this.columna = columna;
	}
}
