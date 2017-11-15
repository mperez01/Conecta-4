package tp.pr5.parser;

import tp.pr5.control.ControlConsola;
import tp.pr5.enums.Ficha;
import tp.pr5.excepcion.ErrorEjecucion;
import tp.pr5.excepcion.FichaIncorrecta;
import tp.pr5.excepcion.JugadorIncorrecto;

public class PonJugador implements Comando {
	private Ficha color;
	private String TipoJugador;
	
	public PonJugador(Ficha color, String TipoJugador) {
		this.color = color;
		this.TipoJugador = TipoJugador;
	}
	public PonJugador() {
		
	}
	
	@Override
	public void execute(ControlConsola control) throws ErrorEjecucion {
		//Manda al controlador que pongal el tipo de jugador y la ficha
		
		control.jugador(this.color, this.TipoJugador);
		
	}
			
	public Comando parsea(String[] cadena) throws ErrorEjecucion {
		if (cadena.length <= 1)
			return null;
		else if (cadena[0].equalsIgnoreCase("JUGADOR")) {
			if (cadena[1].equalsIgnoreCase("BLANCAS")) {
				if (cadena[2].equalsIgnoreCase("HUMANO"))
					return new PonJugador(Ficha.BLANCA, "HUMANO");
				else
					if (cadena[2].equalsIgnoreCase("ALEATORIO"))
						return new PonJugador(Ficha.BLANCA, "ALEATORIO");
					else 
						throw new JugadorIncorrecto("No ha introducido un modo de jugador correcto");
			}
			else
				if (cadena[1].equalsIgnoreCase("NEGRAS")) {
					if (cadena[2].equalsIgnoreCase("HUMANO"))
						return new PonJugador(Ficha.NEGRA, "HUMANO");
					else
						if (cadena[2].equalsIgnoreCase("ALEATORIO"))
							return new PonJugador(Ficha.NEGRA, "ALEATORIO");
						else
							throw new JugadorIncorrecto("No ha introducido un modo de jugador correcto");
				}
				else
					throw new FichaIncorrecta("No ha introducido un color de ficha correcto (BLANCAS o NEGRAS)"); //Throw no se ha leido bien la ficha
		}
		else
			return null;
	}


	public String textoAyuda() {
		return "JUGADOR [ blancas | negras ] [ humano | aleatorio ]: cambia el tipo de jugador,";
	}

}
