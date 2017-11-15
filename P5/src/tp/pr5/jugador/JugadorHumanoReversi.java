package tp.pr5.jugador;

import java.util.Scanner;

import tp.pr5.enums.Ficha;
import tp.pr5.logica.Tablero;

public class JugadorHumanoReversi extends Jugador {

	private Scanner sc;
	
	public JugadorHumanoReversi (Scanner sc) {
		this.sc = sc;
	}
	
	@Override
	protected void obtenFilaColumna(Tablero tab, Ficha color) {
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
