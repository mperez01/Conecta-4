package tp.pr5.jugador;

import tp.pr5.enums.Ficha;
import tp.pr5.logica.Tablero;

public class JugadorAleatorioComplica extends Jugador{
	//En complica, al no tener unas restricciones más que las dimensiones del tablero, sólo obtendremos una columna aleatoria
	public void obtenFilaColumna(Tablero tab, Ficha color){
		int columna = 0;
		this.columna = columna;
		//+1 para que el aleatorio comience en 1 y no en cero
		columna = (int)(tab.getAncho()*Math.random()) + 1;
		this.columna = columna;
	}
}
