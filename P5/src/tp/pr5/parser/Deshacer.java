package tp.pr5.parser;

import tp.pr5.control.ControlConsola;
import tp.pr5.excepcion.ErrorEjecucion;

public class Deshacer implements Comando {

	@Override
	public void execute(ControlConsola control) throws ErrorEjecucion {
		//Manda al controlador deshacer
		control.undo();
	}

	@Override
	public Comando parsea(String[] cadena) {
		if (cadena.length != 1)
			return null;
		else if (cadena[0].equalsIgnoreCase("DESHACER")) {
			return new Deshacer(); //?¿
		}
		else
		return null;
	}

	@Override
	public String textoAyuda() {
		// TODO Auto-generated method stub
		return "DESHACER: deshace el ultimo movimiento hecho en la partida.";
	}

}
