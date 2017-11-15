package tp.pr5.jugador;

import java.util.Scanner;

import tp.pr5.enums.Ficha;
import tp.pr5.logica.Tablero;

public class JugadorHumanoComplica extends Jugador{

	protected Scanner sc;
	public JugadorHumanoComplica(Scanner sc) {
		this.sc = sc;
	}
	protected void obtenFilaColumna(Tablero tab, Ficha color) {
		System.out.print("Introduce la columna: ");
		int col = sc.nextInt();
		sc.nextLine();
		this.columna = col;
		
	}

}
