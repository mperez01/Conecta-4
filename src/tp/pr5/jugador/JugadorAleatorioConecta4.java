package tp.pr5.jugador;

import tp.pr5.enums.Ficha;
import tp.pr5.logica.Tablero;

public class JugadorAleatorioConecta4 extends Jugador {

	public void obtenFilaColumna(Tablero tab, Ficha color){
		boolean fin = false;
		int columna = 0;
		int fila = 1;
		while(!fin) {	
			columna = (int)(tab.getAncho() *Math.random() + 1);
			if(tab.getCasilla(fila , columna) == Ficha.VACIA)
				fin =true;
		}
		this.columna = columna;
	}
}
