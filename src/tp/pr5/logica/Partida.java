package tp.pr5.logica;

import java.util.ArrayList;
import java.util.Stack;

import tp.pr5.enums.Ficha;
import tp.pr5.excepcion.MovimientoInvalido;
import tp.pr5.movimientos.Movimiento;
import tp.pr5.reglas.ReglasJuego;

public class Partida implements Observable {

	private Tablero tablero;
	private Ficha turno;
	private boolean terminada;
	private ReglasJuego reglas;
	
	
	
	private Stack <Movimiento> undo;

	public ArrayList <Observador> obs;

	/**
	 * Constructura de partida.
	 */
	public Partida (ReglasJuego juego)  {
		this.obs = new ArrayList<Observador>();
		this.reglas = juego;
		this.reset(this.reglas);
		this.terminada = false;
	}
	

	/**MVC
	 * Dado un parámetro mov, ejecutar un movimiento (comprobando si se puede o no)
	 * se incluye un movimiento a la pila de undo's de haber terminado la
	 * partida, modifica el objeto terminada si no ha terminado, cambia el turno
	 * 
	 * @param mov
	 *            Parámetro movimiento donde se quiera introducir una ficha
	 * @return booleano que indica si ha podido realizarse o no el movimiento
	 */
	public void ejecutaMovimiento(Movimiento mov)  {
		MovimientoInvalido inv;
		Ficha turno = this.turno;
	
		boolean hay = true;
		
		Ficha color = mov.getJugador();
		if (color != this.getTurno()) {
			inv = new MovimientoInvalido("Turno incorrecto.");
			for (Observador o : obs)
				o.onMovimienoIncorreto(inv);
		}
		
		//Probamos a hacer el movimiento, de existir algun problema lo notificamos
		try {
			for (Observador o : obs)
				o.onMovimientoStart(turno, hay);
			mov.ejecutaMovimiento(this.tablero);
			
			this.undo.push(mov);
			Ficha ganador = this.reglas.hayGanador(mov.getFila(), mov.getColumna(), turno, this.tablero);
			if (ganador == null) {
					this.terminada = false;
					this.turno = this.reglas.siguienteTurno(turno);//***************
					
					hay = hayMovimiento(mov);
					
			}
			//¡TABLAS!
			else if (ganador == Ficha.VACIA) {
				this.turno = Ficha.VACIA;
				this.terminada = true;
				for (Observador o : obs)
					o.onPartidaTerminada(this.tablero, this.turno);
			}	
			else if (ganador != null) {
				this.terminada = true;
				for (Observador o : obs)
					o.onPartidaTerminada(this.tablero, ganador);
			}
		}
		catch(MovimientoInvalido e) {
			for (Observador o : obs) 
				o.onMovimienoIncorreto(e);
		}
		
		finally {
			if (terminada == false) {
				
				for (Observador o : obs) {		
					o.onMovimientoEnd(this.tablero, this.turno, hay);
				}
				//puede darse el caso de que en la partida tras un movimiento ambos jugadores no puedan poner ficha
				//en ese caso quedaran en tablas, aquí previamente estamos comprondo dicho supuesto
				if(!hay) {
					this.turno = this.reglas.siguienteTurno(this.turno);
					hay = hayMovimiento(mov);
					for (Observador o : obs) {		
						o.onMovimientoEnd(this.tablero, this.turno, hay);
					}
					if(!hay) {
						this.terminada = true;
						for(Observador o : obs)
							o.onPartidaTerminada(this.tablero, Ficha.VACIA);
					}
				}
				if (mov.getJugador() != this.turno) {
					for (Observador o: obs)
						o.onCambioJugador(this.turno);
				}
			}
		}
	}

	private boolean hayMovimiento (Movimiento mov) {
		
		int cont = 0;
		for(int i = 1; i <= this.tablero.getAlto(); i++) {
			for (int j = 1; j <= this.tablero.getAncho(); j++){
				if (mov.hayMovimiento(tablero, turno, j, i)) {
					cont ++;
					
				}
			}
		}
		if (cont > 0)
			return true;
		else
			return false;
	}
	
	/**MVC
	 * Llama al metodo de tablero reset() para resetear el tablero, si se cambia de tipo se reinicia el turno de juego.
	 */
	public void reset(ReglasJuego reglas) {
		this.reglas = reglas;
		this.turno = this.reglas.jugadorInicial();
		this.undo = new Stack <Movimiento>();
		this.undo.clear();
		this.tablero = reglas.iniciaTablero();
		
		for(Observador o : this.obs) {
			o.onResetPartida(this.tablero, this.turno);
			o.onCambioJugador(this.turno);
		}

	}
	
	//OJO CON STACK
	/**MVC
	 * Metodo que deshace el ultimo movimiento hecho en el tablero y guardado
	 * dentro de la pila
	 * 
	 * @return boolean True si ha podido deshacerse el movimiento, y False en
	 *         caso contrario
	 */
	public void undo() {
		if (this.undo.empty()) {
			for (Observador o : this.obs)
				o.onUndoNotPossible();
			return;
		}
		Movimiento m = undo.pop();
		m.undo(this.tablero);
		Ficha turno = this.turno;
		this.turno = this.reglas.siguienteTurno(turno);
		
		for (Observador o : this.obs) {
			o.onUndo(this.tablero, this.turno,!undo.empty());
			o.onCambioJugador(this.turno);
		}
	}


	/**
	 * @return Devuelve el objeto turno, con el color del jugador al que le toca
	 *         poner.
	 */
	public Ficha getTurno() {
		return this.turno;
	}
	
	public Tablero getTablero() {
		return this.tablero;
	}

	public boolean partidaTerminada() {
		return this.terminada;
	}
	
	public String toString() {
		String tablero = "";

		tablero = tablero + this.tablero.toString();
		
		return tablero;
	}


	@Override
	public void addObservador(Observador o) {
		this.obs.add(o);
	}
	@Override
	public void removeObservador(Observador o) {
		this.obs.remove(o);
	}
	
}
