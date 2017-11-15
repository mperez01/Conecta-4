package tp.pr5.jugador;

import tp.pr5.enums.Ficha;
import tp.pr5.logica.Tablero;
import tp.pr5.movimientos.*;

public class JugadorAleatorioReversi extends Jugador {

	@Override
	protected void obtenFilaColumna(Tablero tab, Ficha color) {
		
		//NO FUNCIONA
			Movimiento mov = null;
			
			boolean fin = false;
			int columna = 0, fila = 0;
			while(!fin) {
				columna = (int)(tab.getAncho()*Math.random()) + 1;
				fila = (int)(tab.getAlto()*Math.random()) + 1;
				mov = new MovimientoReversi(columna, fila, color);
				if (mov.hayMovimiento(tab, color, columna, fila))
					fin = true;
			}
			this.fila = fila;
			this.columna = columna;
			
		
	}

}
