package tp.pr5.movimientos;

import tp.pr5.enums.Ficha;
import tp.pr5.excepcion.ColumnaIncorrecta;
import tp.pr5.excepcion.FilaIncorrecta;
import tp.pr5.excepcion.MovimientoInvalido;
import tp.pr5.excepcion.PosicionOcupada;
import tp.pr5.logica.Tablero;

public class MovimientoGravity extends Movimiento {
	//numFil y numCol como atributos de clase para poder modificarse en cualquier metodo
	private int filaFinal, colFinal, numFil = 0, numCol = 0;
	
	public MovimientoGravity(int fila, int columna, Ficha turno) {
		super(columna, fila, turno);
	}

	@Override
	public void ejecutaMovimiento(Tablero t) throws MovimientoInvalido {
		if (!this.colNoValido(t))
			throw new ColumnaIncorrecta("Columna incorrecta. Debe estar entre 1 y " + t.getAncho());
		if (!this.filNoValida(t))
			throw new FilaIncorrecta("Fila incorrecta. Debe estar entre 1 y " + t.getAlto());
		
		if (t.getCasilla(this.fila, this.columna) == Ficha.VACIA) {
			//Esto es para tableros X*X (siendo X el mismo numero)
			// Tenemos que tener en cuenta los tableros de dimensiones pares e impares
			int par = (t.getAlto() * t.getAncho()) % 2;
			if (par == 0)
				buscaParedPar(t);
			else
				if (par == 1) {
					//TABLERO DIMENSIONES X*Y IMPAR
					buscaParedImpar (t);	
					}
			t.setCasilla(this.filaFinal, this.colFinal, this.turno);
			//de esta forma, se comprobara despues la veracida del ganador
			this.fila = this.filaFinal;
			this.columna = this.colFinal;
		} else
			throw new PosicionOcupada("Esa posicion esta ocupada");

	}
	
	private void comprobarPos (int vertical, int horizontal, Tablero t) {
		if (vertical == 1 && horizontal == 1) {
			//this.fila en esta posicion representa también el numero de posiciones hasta el borde de arriba
			this.numFil = this.fila - 1;
			this.numCol = t.getAncho() - this.columna;
		}
		else 
			//posicion ARRIBA IZQUIERDA
			if (vertical == 1 && horizontal == 0) {
				//para dar una posicion mas exacta podríamos restar la unidad de su posicion, pero al usar como referncia
				//para ambos la posicion real, no se ve afectado
				// AUN ASI;LO HE PUESTO
				this.numFil = this.fila - 1;
				this.numCol = this.columna - 1;
			}
			else 
				//POSICION ABAJO IZQUIERDA (AL REVES! FILA 4, COLUMNA 2 == ARRIBA IZQUIERDA, FILAS AL REVES
				if (vertical == 0 && horizontal == 0 ) {
					this.numFil = t.getAlto() - this.fila;
					//Quitamos uno, ese uno representa su posicion
					this.numCol = this.columna - 1;
				}
				else
					//POSICION ABAJO DERECHA
					if (vertical == 0 && horizontal == 1) {
						this.numFil = t.getAlto() - this.fila - 1;
						this.numCol = t.getAncho() - this.columna - 1;
					}
					else
						if (vertical == -1 && horizontal == 0) {
							this.numFil = this.fila;
							this.numCol = this.columna;
						} 
						else
							if (vertical == -1 && horizontal == 1) {
								this.numFil = this.fila - 1;
								this.numCol = t.getAncho() - this.columna;
							}
							else
								if (vertical == 1 && horizontal == -1) {
									this.numFil = this.fila - 1;
									this.numCol = this.columna - 1;
								}
								else
									if (vertical == 0 && horizontal == -1) {
										this.numFil = t.getAlto() - this.fila;
										this.numCol = this.fila - 1;
									}
	}
	
	private void posicionFinal(Tablero t, int horizontal, int vertical) {
		if (this.numFil > this.numCol) {
			//HACIA COLUMNA
			if (horizontal == 1) {
				//DERECHA OK
				int cont = t.getAncho();
				while (cont > t.getAncho()/2 && t.getCasilla(this.fila, cont) != Ficha.VACIA ) {
					cont --;
				}
				this.colFinal = cont;
				this.filaFinal = this.fila;
			}
			else
				if (horizontal == 0) {
					//IZQUIERDA OK
					int cont = 1;
					while (cont < t.getAncho()/2 && t.getCasilla(this.fila, cont) != Ficha.VACIA) {
						cont ++;
					}
					this.colFinal = cont;		
					this.filaFinal = this.fila;

				}
		}
		else 
			if (this.numFil < this.numCol) {
				//HACIA FILA
				if (vertical == 1) {
					//ARRIBA
					int cont = 1;
					while (cont < t.getAlto()/2 && t.getCasilla(cont, this.columna) != Ficha.VACIA) {
						cont ++;
					}
					this.filaFinal = cont;
					this.colFinal = this.columna;
				}
				else 
					if(vertical == 0) {
						//ABAJO
						this.filaFinal = buscaLinea(this.columna, t);
						this.colFinal = this.columna;
					}
			}
			else
				if (this.numFil == this.numCol) {
					//DIAGONAL!
					
					if (vertical == 0 && horizontal == 1){ 
						//HACIA ABAJO DERECHA OK!
						int auxCol = t.getAncho();
						int auxFil = t.getAlto();
						while (auxCol > t.getAncho()/2 && auxFil > t.getAlto()/2 && t.getCasilla(auxFil, auxCol) != Ficha.VACIA) {
							auxCol --;
							auxFil --;
						}
						this.colFinal = auxCol;
						this.filaFinal = auxFil;
						
					}
					if (vertical == 0 && horizontal == 0 ) {
						//HACIA ABAJO IZQUIERDA
						int auxCol = 1;
						int auxFil = t.getAlto();
						while (auxCol <= (t.getAncho()/2) && (auxFil > (t.getAlto()/2)) && t.getCasilla(auxFil, auxCol) != Ficha.VACIA) {
								auxCol ++;
								auxFil --;
						}
						this.colFinal = auxCol;
						this.filaFinal = auxFil;
					}
					if (vertical == 1 && horizontal == 0) {
						//HACIA ARRIBA IZQUIERDA
						int auxCol = 1;
						int auxFil = 1;
						//MENOR O IGUAL PORQUE EN FILA EMPEZAMOS DESDE ARRIBA,Y ES PAR
						while (auxCol <= t.getAncho()/2 && auxFil <= t.getAlto()/2 && t.getCasilla(auxFil, auxCol) != Ficha.VACIA) {
							auxCol ++;
							auxFil ++;
						}
						this.colFinal = auxCol;
						this.filaFinal = auxFil;
					}
					if (vertical == 1 && horizontal == 1) {
						//HACIA ARRIBA DERECHA
						int auxCol = t.getAncho();
						int auxFil = 1;
						while (auxCol > t.getAncho()/2 && auxFil <= t.getAlto()/2 && t.getCasilla(auxFil, auxCol) != Ficha.VACIA) {
							auxCol --;
							auxFil ++;
						}
						this.colFinal = auxCol;
						this.filaFinal = auxFil;
					}
					/*
					 * Para cuando se trate de un tablero irregular 
					 */
					if (vertical == -1 && horizontal == -1) {
						this.colFinal = this.columna;
						this.filaFinal = this.fila;
					}
					//DERECHA
					if (vertical == -1 && horizontal == 1) {
						//DERECHA OK
						int cont = t.getAncho();
						while (cont > t.getAncho()/2 && t.getCasilla(this.fila, cont) != Ficha.VACIA ) {
							cont --;
						}
						this.colFinal = cont;
						this.filaFinal = this.fila;
					}
					//ARRIBA
					if (vertical == 1 && horizontal == -1) {
						//ARRIBA
						int cont = 1;
						while (cont < t.getAlto()/2 && t.getCasilla(cont, this.columna) != Ficha.VACIA) {
							cont ++;
						}
						this.filaFinal = cont;
						this.colFinal = this.columna;
					}
					//ABAJO
					if (vertical == 0 && horizontal == -1) {
						this.filaFinal = buscaLinea(this.columna, t);
						this.colFinal = this.columna;
					}
					//IZQUIERDA
					if (vertical == -1 && horizontal == 0) {
						//IZQUIERDA OK
						int cont = 1;
						while (cont < t.getAncho()/2 && t.getCasilla(this.fila, cont) != Ficha.VACIA) {
							cont ++;
						}
						this.colFinal = cont;		
						this.filaFinal = this.fila;
					}
				}
	}
	
	private void buscaParedPar(Tablero t) {
		// vertical 1 = Arriba, 0 = Abajo, -1 = medio
		int vertical = 0;
		// horizontal 1 = derecha, izquierda = 0; medio -1;
		int horizontal = 0;
		this.numCol = 0;
		this.numFil = 0;
		//OK!
		if (this.fila > t.getAlto() / 2) {
			vertical = 0;
			// abajo (coordenadas empiezan arriba)
			} 
		//MENOR o IGUAL ( PARA LOS PARES )
		else if (this.fila <= t.getAlto() / 2) {
			vertical = 1;
			// arriba
			}
		if (this.columna > t.getAncho() / 2) {
			horizontal = 1;
			} 
		//Como antes, para los pares
		else if (this.columna <= t.getAncho() / 2) {
			horizontal = 0;
		}
		// AHORA TENEMOS UN VECTOR DE DOS ENTEROS PARA CONOCER LA POSICIÓN DE LA FICHA EN EL TABLERO.
		
		//Si hay dos MENOS UNO, la ficha se queda en el sitio (versión tablero cuadrado)
		//POSICION ARRIBA DERECHA
		comprobarPos ( vertical, horizontal, t);
								
		//AHORA TENEMOS UN VALOR PARA NUM FIL Y NUM COL, el menor representara la dirección a la que irá la ficha
		// de ser iguales, irán de forma diagonal
		
		posicionFinal(t, horizontal, vertical);

	}
	
	private void buscaParedImpar (Tablero t) {
		// vertical 1 = Arriba, 0 = Abajo, -1 = medio
				int vertical = 0;
				// horizontal 1 = derecha, izquierda = 0; medio -1;
				int horizontal = 0;
				
				//Con el ejemplo 11 -> 5,5
				double cuadranteFila = t.getAlto() / 2;
				double cuadranteColumna = t.getAncho() / 2;
				//Menos uno, por el impar, 5,5 en pos (6,6) fila 6 > 5,5
				// pero necesariamente debe ser 5 > 5
				if (this.fila - 1 > cuadranteFila) {
					vertical = 0;
					// abajo (coordenadas empiezan arriba)
					} 
				//MENOR o IGUAL ( PARA LOS PARES )
				else if (this.fila <= cuadranteFila ) {
					vertical = 1;
					// arriba
					}
				else
					vertical = -1;
				if (this.columna - 1> cuadranteColumna) {
					horizontal = 1;
					} 
				//Como antes, para los pares
				else if (this.columna <= cuadranteColumna) {
					horizontal = 0;
				}
				else
					horizontal = -1;
				comprobarPos ( vertical, horizontal, t);
				posicionFinal(t, horizontal, vertical);
	}
	
	public Ficha getFicha() {
		return Ficha.VACIA;
	}

	@Override
	public boolean undo(Tablero t) {
		if (this.retirar(t))
			return true;
		else
			return false;
	}
	
	private boolean retirar (Tablero t) {
		boolean encontrada = false;
		if (t.getCasilla(this.fila, this.columna) != Ficha.VACIA) {
			t.setCasilla(this.fila, this.columna, Ficha.VACIA);
			encontrada = true;
		}
		return encontrada;
	}

	@Override
	public boolean hayMovimiento(Tablero t, Ficha Color, int i, int j) {
		// TODO Auto-generated method stub
		return true;
	}
}
