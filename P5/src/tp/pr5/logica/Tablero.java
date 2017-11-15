package tp.pr5.logica;

import tp.pr5.enums.Ficha;


public class Tablero implements TableroInmutable{
	private Ficha [][]tablero;
	private int filas;
	private int columnas;
	
	/**
	 * Constructora por defecto de tablero
	 * 
	 * @param filas Numero de filas que tendra el tablero
	 * @param columnas Numero de columnas que tendra el tablero
	 */
	public Tablero (int filas,int columnas){
		this.filas = filas;
		this.columnas =  columnas;
		this.tablero = new Ficha [this.filas][this.columnas];
		this.reset();
	}
	/**
	 * Comprueba si el tablero esta completo o no. Si todas las posiciones estan ocupadas
	 * por fichas distintas de blancas
	 * 
	 * @return booleano True si esta completo, false en caso contrario
	 */
	
	public boolean completo() {
		boolean completo = true;
		for(int i = 0; i < this.filas; i++) {
			for (int j = 0; j < this.columnas; j++){
				if (this.tablero[i][j] == Ficha.VACIA) {
					completo = false;
				}
			}
		}	
		return completo;
	}
	/**
	 * @return el objeto filas
	 */
	public int getAlto(){
		return this.filas;
	}
	/**
	 * @return el objeto columnas
	 */
	public int getAncho(){
		return this.columnas;
	}
	/**Devuelve la posicion de la casilla del tablero, usando menos unos debido a que el usuario
	 * empleara numeros empezando desde el 1
	 * 
	 * @param x Numero que corresponde a la fila
	 * @param y Numero que corresponde a la columna
	 * @return devuelve la posición del objeto tablero
	 */
	public Ficha getCasilla(int x, int y) {
		
		return this.tablero [x - 1][y - 1];	
	}
	
	/**
	 * Dada unas coordenads y un color, cambiar el objeto tablero
	 * segun el color dado.
	 * 
	 * @param x Numero que corresponde a la fila
	 * @param y Numero que corresponde a la columna
	 * @param color 
	 */
	public void setCasilla(int x, int y, Ficha color) {
		
		this.tablero[x - 1][y - 1] = color;
	}
	/**
	 * Da el valor de Ficha.Vacia a todas las posiciones del tablero.
	 */
	public void reset(){
		for (int i = 0 ; i < this.filas; i++) {
			for (int j = 0; j < this.columnas; j++){
			this.tablero[i][j] = Ficha.VACIA;
			}
		}	
	}
	
	/**
	 * Para borrar los numeros de las filas (laterales) Borrar el primer aux dentro del primer for, eliminar un espacio
	 * despues del ultimo line.separator
	 * @return tabla, String donde ira almacenado el tablero, con las respectivas fichas
	 */
	public String toString(){
		String tabla = "";
		
		for(int i = 0; i < this.filas; i ++) {
			int aux = i + 1;
			tabla += (aux % 10) + "|";
			
			for(int j = 0; j < this.columnas; j ++) {
				if(this.tablero[i][j] == Ficha.BLANCA){
					tabla += "0";
				}
				if(this.tablero[i][j] == Ficha.NEGRA){
					tabla += "X";
				}
				if(this.tablero[i][j] == Ficha.VACIA /*&& i != this.filas -1*/){
					tabla += " ";
					
				}
			}
			tabla += "|"+ System.getProperty("line.separator");
		}
		// +2 porque los "+" ocupan 1 espacio
		for(int i = 0; i < this.columnas + 2; i ++) {
			if (i == 0)
				tabla += " +";
			else
				if (i != this.columnas + 1)
					tabla += "-";
				else
					tabla += "+";
		}
		tabla += System.getProperty("line.separator") + "  ";
		//comenzamos en 1 y aumentamos this.columnas en 1 para que las cifras no comiencen en 0.
		for (int i = 1; i < this.columnas + 1; i ++) {
			int aux = i;
			//De esta forma no habrá numeros en las columnas mayores de un digito
			tabla += aux % 10;
				
		}
		tabla += System.getProperty("line.separator");
		
		return tabla;
	}
	
}

