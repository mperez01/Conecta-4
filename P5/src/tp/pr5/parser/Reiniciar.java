package tp.pr5.parser;

import tp.pr5.control.ControlConsola;
import tp.pr5.excepcion.ErrorEjecucion;

public class Reiniciar implements Comando {

	@Override
	public void execute(ControlConsola control) throws ErrorEjecucion {

		control.reiniciar();
		
	}

	@Override
	public Comando parsea(String[] cadena) {
		
		if (cadena.length != 1)
			return null;
		else if (cadena[0].equalsIgnoreCase("REINICIAR")) {
			return new Reiniciar(); 
		}
		else
			return null;
	}

	public String textoAyuda() {
		return "REINICIAR: reinicia la partida.";
	}

}
