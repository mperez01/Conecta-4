package tp.pr5.parser;

import tp.pr5.control.ControlConsola;
import tp.pr5.excepcion.ErrorEjecucion;

public class Salir implements Comando{

	public void execute(ControlConsola control) throws ErrorEjecucion {
		//CONTROLADOR debe salir
		control.salir();
		
	}

	public Comando parsea(String[] cadena) {
		if (cadena.length != 1)
			return null;
		else if (cadena[0].equalsIgnoreCase("SALIR"))
			return new Salir();
		else 
			return null;
	}


	public String textoAyuda() {
		return "SALIR: termina la aplicacion.";
	}

}
