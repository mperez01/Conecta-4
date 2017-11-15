package tp.pr5.control;

import java.util.Scanner;

import tp.pr5.control.factorias.FactoriaJuego;
import tp.pr5.enums.Ficha;
import tp.pr5.excepcion.*;
import tp.pr5.jugador.Jugador;
import tp.pr5.logica.Partida;
import tp.pr5.movimientos.Movimiento;
import tp.pr5.parser.Comando;
import tp.pr5.parser.ParserAyudaComandos;
import tp.pr5.reglas.ReglasJuego;
import tp.pr5.vistas.consola.VistaConsola;

public class ControlConsola/* implements Observable */{
	
	public Partida partida;
	private Jugador jugador1;
	private Jugador jugador2;
	private FactoriaJuego factoria;
	private ReglasJuego reglas;
	private boolean salir = false;
	private Scanner in;
	//SOLO para mostrar los errores por View
	private VistaConsola vista;
	
	public ControlConsola(FactoriaJuego factoria, Scanner in, Partida partida) {
		this.partida = partida;
		this.factoria = factoria;
		this.in = in;
		this.vista = new VistaConsola(this.partida);
		this.reset(factoria);
	}
	
	public void run() throws ErrorEjecucion {
		
		Comando comando;
		String[] palabras = null;
		String cadena = null;
		
		while (!this.terminada() && !this.getSalir() ) {
			
			this.vista.textoPedirOpcion();
			
			cadena = this.in.nextLine();
			// ahora no comparamos "cadena" si no un array de string
			//String[] palabras = cadena.split(" ");
			
			for (int i = 0; i < cadena.length(); i++)
				palabras = cadena.split(" ");
			
			try {
				
				comando = ParserAyudaComandos.parsea(palabras);
				comando.execute(this);
				if (cadena.equalsIgnoreCase("AYUDA")) {
					vista.verTableroInmutable(partida.getTablero(), partida.getTurno());
				}
			}
			catch (DimensionIncorrecta e) {
				vista.onError("La dimension del tablero no es correcta");
				vista.verTableroInmutable(partida.getTablero(), partida.getTurno());
			}
			catch (DatosIncorrectos e) {
				vista.onError("Los datos introducidos no son correctos");
				vista.verTableroInmutable(partida.getTablero(), partida.getTurno());
			}
			catch (FichaIncorrecta e) {
				vista.onError(e.getMessage());
				vista.verTableroInmutable(partida.getTablero(), partida.getTurno());
			}
			catch (JugadorIncorrecto e) {
				vista.onError(e.getMessage());
				vista.verTableroInmutable(partida.getTablero(), partida.getTurno());
			}
			catch (ModoIncorrecto e) {
				vista.onError(e.getMessage());
				vista.verTableroInmutable(partida.getTablero(), partida.getTurno());
			}
		}
	}
	
	public void reset (FactoriaJuego f) {
		this.jugador1 = f.creaJugadorHumano(this.in);
		this.jugador2 = f.creaJugadorHumano(this.in);
		this.partida.reset(f.CrearReglas());
	}
	
	public void undo() {
		this.partida.undo();
	}
	
	public void poner() {
		//Usa JUGADOR par aponer ficha
		Ficha turno;
		turno = this.partida.getTurno();
		Movimiento mov = null;
		try {
			if (turno == Ficha.BLANCA)
				mov = this.jugador1.getMovimiento(factoria, this.partida.getTablero(), turno);
			else
				if (turno == Ficha.NEGRA)
					mov = this.jugador2.getMovimiento(factoria, this.partida.getTablero(), turno);
			this.partida.ejecutaMovimiento(mov);
		}
		catch(DatosIncorrectos e) {
			vista.onError("Los datos introducidos no son numericos");
			in.nextLine();
			vista.verTableroInmutable(partida.getTablero(), partida.getTurno());
		}
		
		
	}
	
	public void reiniciar () {
		this.reset(this.factoria);
	}
	
	public boolean terminada() {
		return this.partida.partidaTerminada();
	}
	
	public void jugar(FactoriaJuego factoria) {
		this.factoria = factoria;
		this.reglas = factoria.CrearReglas();
		this.jugador1 = factoria.creaJugadorHumano(this.in);
		this.jugador2 = factoria.creaJugadorHumano(this.in);
		this.partida.reset(this.reglas);
	}
	
	public void jugador(Ficha color, String TipoJugador)  {
		if (color == Ficha.BLANCA) {
			if (TipoJugador == "HUMANO")
				this.jugador1 = this.factoria.creaJugadorHumano(this.in);
			else
				if (TipoJugador == "ALEATORIO")
					this.jugador1 = this.factoria.creaJugadorAleatorio();
		}
		else
			if (color == Ficha.NEGRA) {
				if (TipoJugador == "HUMANO")
					this.jugador2 = this.factoria.creaJugadorHumano(this.in);
				else
					if (TipoJugador == "ALEATORIO")
						this.jugador2 = this.factoria.creaJugadorAleatorio();
			}	
	}
	
	public void salir () {
		this.salir = true;
	}
	
	public boolean getSalir () {
		return this.salir;
	}
}
