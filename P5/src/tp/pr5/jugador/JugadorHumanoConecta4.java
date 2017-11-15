package tp.pr5.jugador;

import java.util.Scanner;

import tp.pr5.enums.Ficha;
import tp.pr5.logica.Tablero;

public class JugadorHumanoConecta4 extends Jugador {
	
	protected Scanner sc;
	public JugadorHumanoConecta4(Scanner sc) {
		this.sc = sc;
	}
	protected void obtenFilaColumna(Tablero tab, Ficha color) {
		System.out.print("Introduce la columna: ");
		int col = sc.nextInt();
		sc.nextLine();
		this.columna = col;
		
	}

}
