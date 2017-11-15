package tp.pr5.parser;

import tp.pr5.excepcion.DatosIncorrectos;
import tp.pr5.excepcion.ErrorEjecucion;

public class ParserAyudaComandos {
	private static Comando[] comandos = {
		new Salir(), new Deshacer(), new Jugar(), new Poner(), new PonJugador(), new Ayuda(), new Reiniciar()
	};
	
	static public String AyudaComandos() {
		String mostrar = "Los comandos disponibles son:" + System.getProperty("line.separator") + System.getProperty("line.separator");
		for (int i = 0; i < comandos.length; i ++) {
			mostrar += comandos[i].textoAyuda() + System.getProperty("line.separator");
		}
		mostrar += System.getProperty("line.separator");
		return mostrar;
	}
	
	static public Comando parsea(String [] cadena) throws ErrorEjecucion {
		//Por cada Comando[] se llama al parsea de cada clase
		for (Comando c: comandos) {
			c = c.parsea(cadena);
			if (c != null)
				return c;
		}
		throw new DatosIncorrectos();
		
	}

}
