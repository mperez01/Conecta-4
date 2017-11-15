package tp.pr5.reglas;

import tp.pr5.enums.Ficha;
import tp.pr5.logica.Tablero;

public class ReglasReversi extends ReglasJuego {

	private final int col = 4;
	private final int fila = 4;
	@Override
	public Tablero iniciaTablero() {
		Tablero t = new Tablero(this.fila, this.col);
		//Dejamos el tablero con todas las fichas vacias
		t.reset();
		//Ahora vamos a poner las fichas con las que se inicia el tablero
		t.setCasilla(this.fila/2, this.col/2, Ficha.BLANCA);
		t.setCasilla((this.fila/2) + 1, (this.col/2) + 1, Ficha.BLANCA);
		t.setCasilla((this.fila/2) + 1, this.col/2, Ficha.NEGRA);
		t.setCasilla(this.fila/2, (this.col/2) + 1, Ficha.NEGRA);
		return t;
	}

	//No esta probado, pero funcionar�
	public Ficha hayGanador(int f, int c, Ficha ultimo, Tablero tablero) {
		if (tablero.completo()) {
			if (!this.tablas(tablero)) {
				int negras = 0;
				int blancas = 0;
				for(int i = 1; i <= tablero.getAlto(); i++) {
					for (int j = 1; j <= tablero.getAncho(); j++){
						if (tablero.getCasilla(i,j) == Ficha.NEGRA) {
							negras ++;
						}
						if (tablero.getCasilla(i,j) == Ficha.BLANCA) {
							blancas ++;
						}
					}
				}	
				
				if (negras > blancas)
					return Ficha.NEGRA;
				else
					if (negras < blancas)
						return Ficha.BLANCA;
					else
						if(negras == blancas)
							return Ficha.VACIA;
			}
			return Ficha.VACIA;
		}
		return null;
	}

	//Las tablas ya se cuentan en hayGanador
	public boolean tablas(Tablero t) {
		int negras = 0;
		int blancas = 0;
		for(int i = 0; i < t.getAlto(); i++) {
			for (int j = 0; j < t.getAncho(); j++){
				if (t.getCasilla(i + 1,j + 1) == Ficha.NEGRA) {
					negras ++;
				}
				if (t.getCasilla(i + 1,j + 1) == Ficha.BLANCA) {
					blancas ++;
				}
			}
		}	
		if (negras == blancas)
			return true;
		else
			return false;
	}	

	public int getCol() {
		return this.getCol();
	}

	public int getFila() {
		return this.getFila();
	}

	@Override
	public Ficha jugadorInicial() {
		// TODO Auto-generated method stub
		return Ficha.NEGRA;
	}

}
