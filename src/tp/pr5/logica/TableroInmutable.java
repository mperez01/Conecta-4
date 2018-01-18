package tp.pr5.logica;

import tp.pr5.enums.Ficha;


public interface TableroInmutable {
	//DEBE SER IMPLEMENTADO EN LA CLASE TABLERO
	public int getAlto();
	public int getAncho();
	public Ficha getCasilla(int fila, int col);
	public String toString();
	
}