package tp.pr5.control;

import tp.pr5.control.factorias.FactoriaJuego;
import tp.pr5.enums.Ficha;
import tp.pr5.enums.TipoTurno;
import tp.pr5.excepcion.DatosIncorrectos;
import tp.pr5.excepcion.MovimientoInvalido;
import tp.pr5.logica.Observable;
import tp.pr5.logica.Observador;
import tp.pr5.logica.Partida;
import tp.pr5.logica.Tablero;
import tp.pr5.logica.TableroInmutable;
import tp.pr5.modo.*;
import tp.pr5.movimientos.Movimiento;

public class ControlGUI implements Observable {
	
	private Partida partida;
	private FactoriaJuego factoria;
	private TipoTurno tipoNegras;
	private TipoTurno tipoBlancas;
	private ModoJuego modoNegras;
	private ModoJuego modoBlancas;
	
	
	
	public ControlGUI (Partida partida, FactoriaJuego factoria) {
		this.partida = partida;
		this.factoria = factoria;
		this.reset(this.factoria);
	}
	
	public void reset(FactoriaJuego f) {
		this.factoria = f;
		
		//We suppose at first time the players are Human
		this.tipoNegras = TipoTurno.HUMANO;
		this.tipoBlancas = TipoTurno.HUMANO;
		this.partida.reset(this.factoria.CrearReglas());
	}
	
	public void undo() {
		this.partida.undo();
	}
	
	//***************************************************************************
	public int getAncho() {
		return partida.getTablero().getAncho();
	}
	
	public int getAlto() {
		return partida.getTablero().getAlto();
	}
	
	public FactoriaJuego getFactoria() {
		return this.factoria;
	}
	
	public TableroInmutable getTablero() {
		return this.partida.getTablero();
	}
	
	public Tablero getTab() {
		return this.partida.getTablero();
	}
	
	public Ficha getTurno(){
		return this.partida.getTurno();
	}
	
	public void comenzarEjecucion(Ficha turno) {
		if (turno == Ficha.BLANCA && tipoBlancas == TipoTurno.AUTOMATICO) {
			this.modoBlancas = new ModoJuegoAutomatico();
			this.modoBlancas.comenzar();

			
			try {
				this.ponerAutomatico();
			} catch (MovimientoInvalido e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (turno == Ficha.NEGRA && tipoNegras == TipoTurno.AUTOMATICO) {
			this.modoNegras = new ModoJuegoAutomatico();
			this.modoNegras.comenzar();
			try {
				this.ponerAutomatico();
			} catch (MovimientoInvalido e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
			if (turno == Ficha.BLANCA && tipoBlancas == TipoTurno.HUMANO) {
				this.modoBlancas = new ModoJuegoHumano();
				this.modoBlancas.comenzar();
			}
			else if (turno == Ficha.NEGRA && tipoNegras == TipoTurno.HUMANO) {
				this.modoNegras = new ModoJuegoHumano();
				this.modoNegras.comenzar();
			}
		
		
	}
	
	public void terminarEjecucion(Ficha turno) {
		if (/*turno == Ficha.BLANCA && */tipoBlancas == TipoTurno.AUTOMATICO) {
			this.modoBlancas.terminar();
		}
		else if (/*turno == Ficha.NEGRA && */tipoNegras == TipoTurno.AUTOMATICO) {
			this.modoNegras.terminar();
		}
	}
	//***************************************************************************
	
	public void poner(int fila, int col) {

		Movimiento mov = this.factoria.CrearMovimiento(fila, col, partida.getTurno());
		this.partida.ejecutaMovimiento(mov);
		
	}
	
	public void ponerAutomatico() throws MovimientoInvalido {
		
		
		Movimiento mov;
		try {
			mov = this.factoria.creaJugadorAleatorio().getMovimiento(this.factoria, this.getTab(), this.getTurno());
			
			this.partida.ejecutaMovimiento(mov);
			//mov.ejecutaMovimiento(this.getTab());
			
		} catch (DatosIncorrectos e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void setTipo(Ficha turno, TipoTurno tipo) {
		if (turno == Ficha.BLANCA) {
			if (tipo == TipoTurno.HUMANO)
				this.tipoBlancas = TipoTurno.HUMANO;
			else
				if (tipo == TipoTurno.AUTOMATICO)
					this.tipoBlancas = TipoTurno.AUTOMATICO;
		}
		else
			if (turno == Ficha.NEGRA) {
				if (tipo == TipoTurno.HUMANO)
					this.tipoNegras = TipoTurno.HUMANO;
				else
					if (tipo == TipoTurno.AUTOMATICO)
						this.tipoNegras = TipoTurno.AUTOMATICO;
			}
	}
	
	public TipoTurno getTipo(Ficha turno) {
		
		if (turno == Ficha.BLANCA)
			return this.tipoBlancas;
		else
			if (turno == Ficha.NEGRA)
				return this.tipoNegras;
			else
				return null;
	}
	
	@Override
	public void addObservador(Observador o) {
		if (!partida.obs.contains(o))
			partida.obs.add(o);
	}

	@Override
	public void removeObservador(Observador o) {
		partida.obs.remove(o);
	}

}
