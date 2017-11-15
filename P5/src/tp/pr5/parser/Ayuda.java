package tp.pr5.parser;

import tp.pr5.control.ControlConsola;
import tp.pr5.excepcion.DatosIncorrectos;
import tp.pr5.excepcion.ErrorEjecucion;

public class Ayuda implements Comando {
	public Comando parsea(String[] cadena) throws DatosIncorrectos {
		if (cadena.length != 1)
			throw new DatosIncorrectos("No ha introducido ninguna accion");
		else if (cadena[0].equalsIgnoreCase("AYUDA"))
			return new Ayuda();
		else 
			return null;
	}
	public String textoAyuda() {
		return "AYUDA: Muestra la ayuda";
	}
	@Override
	public void execute(ControlConsola control) throws ErrorEjecucion {
		System.out.println(ParserAyudaComandos.AyudaComandos());
	}
}
