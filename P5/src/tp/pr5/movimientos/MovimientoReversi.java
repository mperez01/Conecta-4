package tp.pr5.movimientos;

import tp.pr5.enums.Ficha;
import tp.pr5.excepcion.ColumnaIncorrecta;
import tp.pr5.excepcion.FilaIncorrecta;
import tp.pr5.excepcion.MovimientoInvalido;
import tp.pr5.excepcion.PosicionOcupada;
import tp.pr5.logica.Tablero;

public class MovimientoReversi extends Movimiento {

	/*
	 * Boolean para conocer si estamos ejecutamos un movimiento 
	 * o por el contrario en deshacer, para ejecutar flanquea de una forma u otra.
	 */
	private boolean undo = false;
	
	/*
	 * Enteros para guardar los flancos que se han pintado en cada movimiento, para conocerlo
	 * para deshacer
	 */
	private int abajo = 0, arriba = 0, izquierda = 0, derecha = 0,
			diagonalIzquierdaArriba = 0, diagonalDerechaArriba = 0,
			diagonalIzquierdaAbajo = 0, diagonalDerechaAbajo = 0;

	public MovimientoReversi(int columna, int fila, Ficha turno) {
		super(columna, fila, turno);
	}

	public void ejecutaMovimiento(Tablero t) throws MovimientoInvalido {

		boolean mov;

		if (!this.colNoValido(t))
			throw new ColumnaIncorrecta(
					"Columna incorrecta. Debe estar entre 1 y " + t.getAncho());
		if (!this.filNoValida(t))
			throw new FilaIncorrecta("Fila incorrecta. Debe estar entre 1 y "
					+ t.getAlto());

		if (t.getCasilla(this.fila, this.columna) == Ficha.VACIA) {
			mov = flanquea(this.turno, this.fila, this.columna, t);
			if (!mov)
				throw new MovimientoInvalido(
						"En esa posicion no se puede flanquear");

		} else
			throw new PosicionOcupada("Esa posicion esta ocupada");

	}

	private Ficha cambioColor(Ficha color) {
		if (color == Ficha.NEGRA)
			return Ficha.BLANCA;
		if (color == Ficha.BLANCA)
			return Ficha.NEGRA;
		else
			return Ficha.VACIA;
	}

	private int FlanqueoAbajo(Ficha color, int c, int f, Tablero t,
			boolean pintar, int mov) {
		int cont = 0;
		// ------- Para deshacer-------
		Ficha flanq = null;
		if (!this.undo) {
			flanq = cambioColor(color);
			// this.abajo = 0;
		} else
			flanq = color;
		// ------------------
		int filaAbajo = f;
		if (filaAbajo < t.getAlto()) {
			// Si la ficha de �ABAJO! es del color contrario, comenzamos
			if (t.getCasilla(filaAbajo + 1, c) == flanq) {
				boolean ok = true;
				filaAbajo++;
				// Para no sobrepasar los limites del tablero
				while (ok && t.getCasilla(filaAbajo, c) == flanq) {
					cont++;
					filaAbajo++;
					// Ha llegado al tope!
					if (filaAbajo > t.getAlto()) {
						filaAbajo--;

						ok = false;
					}
				}
			}
			if (!this.undo) {
				if ((t.getCasilla(filaAbajo, c) == color) && (cont > 0)) {
					mov++;

					if (pintar) {
						this.abajo = cont;
						for (int i = filaAbajo; i >= f; i--)
							t.setCasilla(i, c, color);
					}
				}
			} else {
				if (f + 1 <= t.getAlto()) {
					if (t.getCasilla(f + 1, c) == color && cont > 1) {
						mov++;
						int paint = f + 1;
						while (cont > 1 && this.abajo > 0) {
							t.setCasilla(paint, c, cambioColor(color));
							paint++;
							this.abajo--;
							cont--;
						}
						t.setCasilla(f, c, Ficha.VACIA);
					}
				}
			}
		}
		return mov;
	}

	private int FlanqueoArriba(Ficha color, int c, int f, Tablero t,
			boolean pintar, int mov) {
		int cont = 0;
		// ------- Para deshacer-------
		Ficha flanq = null;
		boolean ok = true;
		if (!this.undo) {
			flanq = cambioColor(color);
			// this.arriba = 0;
		} else
			flanq = color;
		// ------------------
		int filaArriba = f;
		// �ARRIBA!
		if (f > 1) {
			if (t.getCasilla(f - 1, c) == flanq) {

				filaArriba--;
				// Para no sobrepasar los limites del tablero

				while (ok && t.getCasilla(filaArriba, c) == flanq) {
					cont++;
					filaArriba--;
					if (filaArriba < 1) {
						ok = false;
						filaArriba++;
					}
				}
			}
		}
		if (!this.undo) {
			// Pintamos tablero cuando se encuentran hacia arriba
			if ((t.getCasilla(filaArriba, c) == color) && (cont > 0)) {
				mov++;

				if (pintar) {
					this.arriba = cont;
					for (int i = filaArriba; i <= f; i++)
						t.setCasilla(i, c, color);
				}
			}
		}
		// FUNCIONA!
		else {
			if (f - 1 > 0) {
				if (t.getCasilla(f - 1, c) == color && cont > 1) {
					mov++;
					int paint = f - 1;
					while (cont > 1 && this.arriba > 0) {

						t.setCasilla(paint, c, cambioColor(color));
						paint--;
						cont--;
						this.arriba--;
					}
					// this.arriba = 0;
					t.setCasilla(f, c, Ficha.VACIA);
				}
			}
		}
		return mov;
	}

	private int FlanqueoDerecha(Ficha color, int c, int f, Tablero t,
			boolean pintar, int mov) {
		int cont = 0;
		int colDerecha = c;
		// ------- Para deshacer-------
		Ficha flanq = null;
		boolean ok = true;
		if (!this.undo) {
			flanq = cambioColor(color);
			// this.derecha = 0;
		} else
			flanq = color;
		// ------------------
		// hacemos el intento de flanqueo �DERECHO!
		if (c < t.getAncho()) {
			if (t.getCasilla(f, c + 1) == flanq) {

				colDerecha++;
				while (ok && t.getCasilla(f, colDerecha) == flanq) {
					cont++;
					colDerecha++;
					if (colDerecha > t.getAncho()) {
						colDerecha--;
						ok = false;
					}
				}
			}
		}
		if (!this.undo) {
			// Pintamos tablero cuando se encuentran a la derecha
			if ((t.getCasilla(f, colDerecha) == color) && (cont > 0)) {
				mov++;

				if (pintar) {
					this.derecha = cont;
					for (int i = c; i <= colDerecha; i++)
						t.setCasilla(f, i, color);
				}
			}
		} else {
			if (c + 1 <= t.getAncho()) {
				if (t.getCasilla(f, c + 1) == color && cont > 1) {
					mov++;
					int paint = c + 1;
					while (cont > 1 && this.derecha > 0) {

						t.setCasilla(f, paint, cambioColor(color));
						paint++;
						this.derecha--;
						cont--;
					}
					// this.izquierda = 0;
					t.setCasilla(f, c, Ficha.VACIA);
				}
			}
		}
		return mov;
	}

	private int FlanqueoIzquierda(Ficha color, int c, int f, Tablero t,
			boolean pintar, int mov) {
		// hacemos el intento de flanqueo �IZQUIERDO!
		// ------- Para deshacer-------
		Ficha flanq = null;
		boolean ok = true;
		if (!this.undo) {
			flanq = cambioColor(color);
			// this.izquierda = 0;
		} else
			flanq = color;
		// ------------------
		int colIzquierda = c;
		int cont = 0;
		if (c > 1) {
			if (t.getCasilla(f, c - 1) == flanq) {

				colIzquierda--;
				while (ok && t.getCasilla(f, colIzquierda) == flanq) {
					cont++;
					colIzquierda--;
					if (colIzquierda < 1) {
						colIzquierda++;
						ok = false;
					}
				}
			}
		}
		if (!this.undo) {
			// Pintamos tablero cuando se encuentra a la izquierda
			if ((t.getCasilla(f, colIzquierda) == color) && (cont > 0)) {
				mov++;

				if (pintar) {
					this.izquierda = cont;
					for (int i = colIzquierda; i <= c; i++)
						t.setCasilla(f, i, color);
				}
			}
		} else {
			if (c - 1 > 0) {
				if (t.getCasilla(f, c - 1) == color && cont > 1) {

					mov++;
					int paint = c - 1;
					while (cont > 1 && this.izquierda > 0) {

						t.setCasilla(f, paint, cambioColor(color));
						paint--;
						this.izquierda--;
						cont--;
					}
					t.setCasilla(f, c, Ficha.VACIA);
				}
			}
		}
		return mov;
	}

	private int FlanqueoDiagonalAbajoDerecha(Ficha color, int c, int f,
			Tablero t, boolean pintar, int mov) {

		// ------- Para deshacer-------
		Ficha flanq = null;
		if (!this.undo) {
			flanq = cambioColor(color);
			// this.diagonalDerechaAbajo = 0;
		} else
			flanq = color;

		// ------------------
		int cont = 0;
		boolean ok = true;
		int filaDiagoAbaDrch = f, colDiagoAbaDrch = c;
		// DIAGONAL ABAJO DERECHA
		if (f < t.getAlto() && c < t.getAncho()) {

			if (t.getCasilla(f + 1, c + 1) == flanq) {
				filaDiagoAbaDrch++;
				colDiagoAbaDrch++;

				while (ok
						&& t.getCasilla(filaDiagoAbaDrch, colDiagoAbaDrch) == flanq) {
					cont++;
					filaDiagoAbaDrch++;
					colDiagoAbaDrch++;
					if (colDiagoAbaDrch > t.getAncho()) {
						ok = false;
						colDiagoAbaDrch--;
						filaDiagoAbaDrch--;
					}
					if (filaDiagoAbaDrch > t.getAlto()) {
						ok = false;
						filaDiagoAbaDrch--;
						colDiagoAbaDrch--;
					}
				}

			}
		}
		if (!this.undo) {
			// Pintamos tablero cuando se encuentre diagonal abajo derecga
			if ((t.getCasilla(filaDiagoAbaDrch, colDiagoAbaDrch) == color)
					&& (cont > 0)) {
				mov++;
				if (pintar) {
					this.diagonalDerechaAbajo = cont;
					while (filaDiagoAbaDrch != f && colDiagoAbaDrch != c) {
						t.setCasilla(filaDiagoAbaDrch, colDiagoAbaDrch, color);
						filaDiagoAbaDrch--;
						colDiagoAbaDrch--;
					}
					t.setCasilla(f, c, color);
				}
			}
		} else {
			// NICE!!
			if (f + 1 <= t.getAlto() && c + 1 <= t.getAncho()) {
				if (t.getCasilla(f + 1, c + 1) == color && cont > 1) {
					mov++;
					// De haber llegado a parez, solo sumar 1 para no
					// sobresaltar posicion

					int paintC = c + 1;
					int paintF = f + 1;

					while (cont > 1 && this.diagonalDerechaAbajo > 0) {
						t.setCasilla(paintF, paintC, cambioColor(color));
						paintF++;
						paintC++;
						this.diagonalDerechaAbajo--;
						cont--;
					}
					
					t.setCasilla(f, c, Ficha.VACIA);
				}
			}
		}
		return mov;
	}

	private int FlanqueoDiagonalAbajoIzq(Ficha color, int c, int f, Tablero t,
			boolean pintar, int mov) {
		int cont = 0;
		// ------- Para deshacer-------
		Ficha flanq = null;
		boolean ok = true;
		if (!this.undo) {
			flanq = cambioColor(color);
			// this.diagonalIzquierdaAbajo = 0;
		} else
			flanq = color;
		// ------------------
		int filaDiagoAbaIzq = f, colDiagoAbaIzq = c;
		// DIAGONAL ABAJO IZQUIERDA
		if (f < t.getAlto() && c > 1) {
			if (t.getCasilla(f + 1, c - 1) == flanq) {

				filaDiagoAbaIzq++;
				colDiagoAbaIzq--;
				while (ok
						&& t.getCasilla(filaDiagoAbaIzq, colDiagoAbaIzq) == flanq) {
					filaDiagoAbaIzq++;
					colDiagoAbaIzq--;
					cont++;
					if (colDiagoAbaIzq < 1) {
						colDiagoAbaIzq++;
						filaDiagoAbaIzq--;
						ok = false;
					}
					if (filaDiagoAbaIzq > t.getAlto()) {
						filaDiagoAbaIzq--;
						colDiagoAbaIzq++;
						ok = false;
					}
				}

			}
		}
		if (!this.undo) {
			// Pintamos tablero cuando se encuentre diagonal abajo izquierda
			if ((t.getCasilla(filaDiagoAbaIzq, colDiagoAbaIzq) == color)
					&& (cont > 0)) {
				mov++;

				if (pintar) {
					this.diagonalIzquierdaAbajo = cont;
					while (filaDiagoAbaIzq != f && colDiagoAbaIzq != c) {
						t.setCasilla(filaDiagoAbaIzq, colDiagoAbaIzq, color);
						filaDiagoAbaIzq--;
						colDiagoAbaIzq++;
					}
					t.setCasilla(f, c, color);
				}
			}
		} else {
			if (f + 1 <= t.getAlto() && c - 1 > 0) {
				if (t.getCasilla(f + 1, c - 1) == color && cont > 1) {
					mov++;

					int paintC = c - 1;
					int paintF = f + 1;
					while (cont > 1 && this.diagonalIzquierdaAbajo > 0) {

						t.setCasilla(paintF, paintC, cambioColor(color));
						paintF++;
						paintC--;
						this.diagonalIzquierdaAbajo--;
						cont--;
					}
					t.setCasilla(f, c, Ficha.VACIA);
				}
			}
		}
		return mov;
	}

	private int FlanqueoDiagonalArribaDerecha(Ficha color, int c, int f,
			Tablero t, boolean pintar, int mov) {
		// ------- Para deshacer-------
		Ficha flanq = null;
		boolean ok = true;
		if (!this.undo) {
			flanq = cambioColor(color);
			// this.diagonalDerechaArriba = 0;
		} else
			flanq = color;
		// ------------------
		int cont = 0;
		int filaDiagoArribaDrch = f, colDiagoArribaDrch = c;
		// Diagonal Arriba Derecha
		if (f > 1 && c < t.getAncho()) {
			if (t.getCasilla(f - 1, c + 1) == flanq) {

				filaDiagoArribaDrch--;
				colDiagoArribaDrch++;
				// Para no sobrepasar los limites del tablero

				while (ok
						&& t.getCasilla(filaDiagoArribaDrch, colDiagoArribaDrch) == flanq) {
					cont++;
					colDiagoArribaDrch++;
					filaDiagoArribaDrch--;
					if ((filaDiagoArribaDrch < 1)) {
						filaDiagoArribaDrch++;
						colDiagoArribaDrch--;
						ok = false;
					}
					if (colDiagoArribaDrch > t.getAncho()) {
						colDiagoArribaDrch--;
						filaDiagoArribaDrch++;
						ok = false;
					}
				}
			}
		}
		if (!this.undo) {
			// Pintamos tablero cuando se encuentre diagonal ARRIBA derechac
			if ((t.getCasilla(filaDiagoArribaDrch, colDiagoArribaDrch) == color)
					&& (cont > 0)) {
				mov++;

				if (pintar) {
					this.diagonalDerechaArriba = cont;
					while (filaDiagoArribaDrch != f && colDiagoArribaDrch != c) {
						t.setCasilla(filaDiagoArribaDrch, colDiagoArribaDrch,
								color);
						filaDiagoArribaDrch++;
						colDiagoArribaDrch--;
					}
					t.setCasilla(f, c, color);
				}
			}
		} else {
			if (f - 1 > 0 && c + 1 <= t.getAncho()) {
				if (t.getCasilla(f - 1, c + 1) == color && cont > 1) {
					mov++;

					int paintC = c + 1;
					int paintF = f - 1;

					while (cont > 1 && this.diagonalDerechaArriba > 0) {

						t.setCasilla(paintF, paintC, cambioColor(color));
						paintF--;
						paintC++;
						this.diagonalDerechaArriba--;
						cont--;
					}
					// this.diagonalDerechaArriba = 0;
					t.setCasilla(f, c, Ficha.VACIA);
				}
			}
		}
		return mov;
	}

	private int FlanqueoDiagonalArribaIzquierda(Ficha color, int c, int f,
			Tablero t, boolean pintar, int mov) {

		// ------- Para deshacer-------
		Ficha flanq = null;
		if (!this.undo) {
			flanq = cambioColor(color);
		} else
			flanq = color;
		// ------------------
		int filaDiagoArribaIzq = f, colDiagoArribaIzq = c;
		int cont = 0;
		boolean ok = true;
		// Diagonal Arriba Izquierda
		if (f > 1 && c > 1) {
			if (t.getCasilla(f - 1, c - 1) == flanq) {

				filaDiagoArribaIzq--;
				colDiagoArribaIzq--;
				// Para no sobrepasar los limites del tablero

				while (ok
						&& t.getCasilla(filaDiagoArribaIzq, colDiagoArribaIzq) == flanq) {
					cont++;
					colDiagoArribaIzq--;
					filaDiagoArribaIzq--;
					if ((filaDiagoArribaIzq < 1)) {
						filaDiagoArribaIzq++;
						colDiagoArribaIzq++;
						ok = false;
					}
					if (colDiagoArribaIzq < 1) {
						colDiagoArribaIzq++;
						filaDiagoArribaIzq++;
						ok = false;
					}
				}
			}
		}
		if (!this.undo) {
			// Pintamos tablero cuando se encuentre diagonal ARRIBA Izquierda
			if ((t.getCasilla(filaDiagoArribaIzq, colDiagoArribaIzq) == color)
					&& (cont > 0)) {
				mov++;

				if (pintar) {
					this.diagonalIzquierdaArriba = cont;
					while (filaDiagoArribaIzq != f && colDiagoArribaIzq != c) {
						t.setCasilla(filaDiagoArribaIzq, colDiagoArribaIzq,
								color);
						filaDiagoArribaIzq++;
						colDiagoArribaIzq++;
					}
					t.setCasilla(f, c, color);
				}
			}
		} else {

			if (f - 1 > 0 && c - 1 > 0) {
				if (t.getCasilla(f - 1, c - 1) == color && cont > 1) {
					mov++;

					int paintC = c - 1;
					int paintF = f - 1;

					while (cont > 1 && this.diagonalIzquierdaArriba > 0) {
						t.setCasilla(paintF, paintC, cambioColor(color));
						paintF--;
						paintC--;
						this.diagonalIzquierdaArriba--;
						cont--;
					}
					// this.diagonalIzquierdaArriba = 0;
					t.setCasilla(f, c, Ficha.VACIA);
				}
			}
		}

		return mov;
	}

	/**
	 * flanque llama a las 8 funciones (8 posibles direcciones de flanqueo) 
	 * 
	 * @param color Color del jugador o del movimiento a deshacer
	 * @param f Fila donde se encuetnra la posicion de la nueva ficha o de la que queremos deshacer
	 * @param c Columna donde se encuetnra la posicion de la nueva ficha o de la que queremos deshacer
	 * @param t Tablero donde queremos modificar
	 * @return true en caso de flanqueo, false en caso contrario
	 */
	public boolean flanquea(Ficha color, int f, int c, Tablero t) {
		boolean pintar = true;

		int mov = 0;

		mov = FlanqueoAbajo(color, c, f, t, pintar, mov);

		mov = FlanqueoArriba(color, c, f, t, pintar, mov);

		mov = FlanqueoDerecha(color, c, f, t, pintar, mov);

		mov = FlanqueoIzquierda(color, c, f, t, pintar, mov);

		mov = FlanqueoDiagonalAbajoDerecha(color, c, f, t, pintar, mov);

		mov = FlanqueoDiagonalAbajoIzq(color, c, f, t, pintar, mov);

		mov = FlanqueoDiagonalArribaDerecha(color, c, f, t, pintar, mov);

		mov = FlanqueoDiagonalArribaIzquierda(color, c, f, t, pintar, mov);

		if (mov > 0)
			return true;
		else
			return false;
	}

	// Queremos probar si existe movimiento valido para el jugador de este turno
	// NO FUNCIONA COMO DEBERIA
	public boolean hayMovimiento(Tablero t, Ficha color, int columna, int fila) {
		// Recorremos el array
		int mov = 0;

		if (t.getCasilla(fila, columna) == Ficha.VACIA) {
			mov = FlanqueoAbajo(color, columna, fila, t, false, mov);

			mov = FlanqueoArriba(color, columna, fila, t, false, mov);

			mov = FlanqueoDerecha(color, columna, fila, t, false, mov);

			mov = FlanqueoIzquierda(color, columna, fila, t, false, mov);

			mov = FlanqueoDiagonalAbajoDerecha(color, columna, fila, t, false,
					mov);

			mov = FlanqueoDiagonalAbajoIzq(color, columna, fila, t, false, mov);

			mov = FlanqueoDiagonalArribaDerecha(color, columna, fila, t, false,
					mov);

			mov = FlanqueoDiagonalArribaIzquierda(color, columna, fila, t,
					false, mov);
		}

		if (mov > 0)
			return true;
		else
			return false;
	}

	public Ficha getFicha() {
		return Ficha.VACIA;
	}

	public boolean undo(Tablero t) {
		// TODO Auto-generated method stub
		// Con el mov dado( Fila y columna)
		this.undo = true;

		if (flanquea(this.turno, this.fila, this.columna, t)) {
			this.undo = false;
			return true;
		} else {
			this.undo = false;
			return false;
		}
	}
}
