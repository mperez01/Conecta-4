package tp.pr5.jugador;

import tp.pr5.enums.Ficha;
import tp.pr5.logica.Tablero;

public class JugadorAleatorioGravity extends Jugador{

	//Jugador aleatorio gravity temporal y sin comprobar veracidad
	protected void obtenFilaColumna(Tablero tab, Ficha color) {
		boolean fin = false;
		int columna = 0, fila = 0;
		while(!fin) {
			columna = (int)(tab.getAncho()*Math.random()) + 1;
			fila = (int)(tab.getAlto()*Math.random()) + 1;
			if (tab.getCasilla(fila, columna) == Ficha.VACIA) {
				fin = true;
			}
			else
				fin = false;	
		}
		this.fila = fila;
		this.columna = columna;
		
	}

}
