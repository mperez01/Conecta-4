package tp.pr5.main;

import java.util.Scanner;

import tp.pr5.control.*;
import tp.pr5.control.factorias.FactoriaComplica;
import tp.pr5.control.factorias.FactoriaConecta4;
import tp.pr5.control.factorias.FactoriaGravity;
import tp.pr5.control.factorias.FactoriaJuego;
import tp.pr5.control.factorias.FactoriaReversi;
import tp.pr5.enums.TipoJuego;
import tp.pr5.enums.TipoVista;
import tp.pr5.excepcion.*;
import tp.pr5.logica.*;
import tp.pr5.parser.ParseoArgumentos;
import tp.pr5.reglas.ReglasJuego;
import tp.pr5.vistas.grafica.VistaGUI;

public class Main {

	private static Scanner in = new Scanner (System.in);
	
	public static void main(String[] args) throws MovimientoInvalido, ErrorEjecucion, DatosIncorrectos {
		
		TipoJuego tipo = null;
		TipoVista vista = null;
		boolean console = true;
		FactoriaJuego factoria = null;
		ParseoArgumentos argumentos = new ParseoArgumentos(args);
		
		if (argumentos.tieneAyuda()) {
			argumentos.mostrarAyuda();
		}
		else {
			try {
				tipo = argumentos.getTipoJuego();
				vista = argumentos.getInterfaz();
			}
			catch (ExcepcionArgumento e) {
				System.err.println("Uso incorrecto: " + e.getMessage());
				System.err.println("Uso -h/--help para más detalles.");
				System.exit(1);
			}
			
			switch (vista) {
			case CONSOLE : 
				console = true;
				break;
			case WINDOW :
				console = false;
				break;
			default :
					break;
			}
			
			switch (tipo) {
			case COMPLICA :
				factoria = new FactoriaComplica();
				break;
			case CONECTA4 :
				factoria = new FactoriaConecta4();
				break;
			case GRAVITY :
				int x;
				int y;
				x = argumentos.getTamX();
				y = argumentos.getTamY();
				factoria = new FactoriaGravity(x, y) ;
				break;
			case REVERSI :
				factoria = new FactoriaReversi();
			default :
				break;
			}
		}
		
		ReglasJuego juego = factoria.CrearReglas();
		Partida partida = new Partida(juego);
		//CON ESTR
		if (console == true) {
			ControlConsola c = new ControlConsola(factoria, in, partida);
			c.run();
		}
		else
			if (console == false) {
				@SuppressWarnings("unused")
				VistaGUI v = new VistaGUI("Practica 5", new ControlGUI(partida, factoria));
			}
				
	}
}
