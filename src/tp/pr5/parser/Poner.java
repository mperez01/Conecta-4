package tp.pr5.parser;

import tp.pr5.control.ControlConsola;
import tp.pr5.excepcion.ErrorEjecucion;

public class Poner implements Comando {

	public void execute(ControlConsola control) throws ErrorEjecucion {
		//EJECUTAR EL MOVIMIENTO
		control.poner();
	}

	@Override
	public Comando parsea(String[] cadena) throws ErrorEjecucion {
		if (cadena.length != 1)
			return null;
		else if (cadena[0].equalsIgnoreCase("PONER")) {
			return new Poner(); 
		}
		else
			return null;
	}

	public String textoAyuda() {
		return "PONER: utilizalo para poner la siguiente ficha.";
	}

}
