package tp.pr5.parser;

import tp.pr5.control.ControlConsola;
import tp.pr5.excepcion.ErrorEjecucion;

public interface Comando {
	Comando parsea(String[] cadena) throws ErrorEjecucion;
	String textoAyuda();
	void execute(ControlConsola controlConsola) throws ErrorEjecucion;
}
