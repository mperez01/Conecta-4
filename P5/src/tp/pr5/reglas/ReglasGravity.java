package tp.pr5.reglas;

import tp.pr5.enums.Ficha;
import tp.pr5.logica.Tablero;

public class ReglasGravity extends ReglasJuego{
	private int col = 10;
	private int fila = 10;

	public ReglasGravity(int fil, int colum) {
		this.col = colum;
		this.fila = fil;
	}
	
	public ReglasGravity() {
		
	}

	public Tablero iniciaTablero() {
		Tablero t = new Tablero(fila, col);
		t.reset();
		return t;
	}


	public Ficha hayGanador(int f, int c, Ficha ultimo, Tablero tablero) {
		if (ReglasJuegoCuatroEnLinea.cuatroEnLinea(tablero, ultimo, f, c))
			return ultimo;
		else if (tablas(tablero))
			return Ficha.VACIA;
		else
			return null;
	}

	public boolean tablas(Tablero t) {

		boolean completo = true;
		for(int i = 0; i < t.getAlto(); i++) {
			for (int j = 0; j < t.getAncho(); j++){
				if (t.getCasilla(i + 1,j + 1) == Ficha.VACIA) {
					completo = false;
				}
			}
		}	
		return completo;
	}

	public int getCol() {
		return this.col;
	}

	@Override
	public int getFila() {
		return this.fila;
	}
	//Modifica el valor del tablero
	public void setCol(int col){
		this.col = col;
	}
	public void setFila(int fil) {
		this.fila = fil;
	}

	@Override
	public Ficha jugadorInicial() {
		// TODO Auto-generated method stub
		return Ficha.BLANCA;
	}
}
