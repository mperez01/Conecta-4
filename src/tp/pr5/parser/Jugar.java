package tp.pr5.parser;

import tp.pr5.control.ControlConsola;
import tp.pr5.control.factorias.FactoriaComplica;
import tp.pr5.control.factorias.FactoriaConecta4;
import tp.pr5.control.factorias.FactoriaGravity;
import tp.pr5.control.factorias.FactoriaJuego;
import tp.pr5.control.factorias.FactoriaReversi;
import tp.pr5.excepcion.DimensionIncorrecta;
import tp.pr5.excepcion.ErrorEjecucion;
import tp.pr5.excepcion.ModoIncorrecto;

public class Jugar implements Comando {
	
	private FactoriaJuego factoria;
	
	public Jugar() {
		
	}
	
	public Jugar(FactoriaJuego factoria) {
		this.factoria = factoria;
	}

	@Override
	public void execute(ControlConsola control) throws ErrorEjecucion {
		control.jugar(factoria);
	}

	public Comando parsea(String[] cadena) throws ErrorEjecucion {
		
		if (cadena.length <= 1)
			return null;
		else if (cadena[0].equalsIgnoreCase("JUGAR")) {
			if (cadena[1].equalsIgnoreCase("GR")) {
				if (cadena.length >= 3) {
					//convierte cadena en entero OJO '!
						try {
						int fila = Integer.parseInt(cadena[2]);
						int col = Integer.parseInt(cadena[3]);
						this.factoria = new FactoriaGravity(fila,col);	
						return new Jugar(this.factoria);
						}
						catch (NumberFormatException e) {
							throw new DimensionIncorrecta();
						}
						catch (ArrayIndexOutOfBoundsException e) {
							throw new DimensionIncorrecta();
						}
						//Hay que crear la constructora para modificarlos
				}
				else {
					this.factoria = new FactoriaGravity();
					return new Jugar(this.factoria);
				}
			}
			else if (cadena[1].equalsIgnoreCase("C4")) {
				this.factoria = new FactoriaConecta4();
				return new Jugar(this.factoria);
			}
			else if (cadena[1].equalsIgnoreCase("CO")) {
				this.factoria = new FactoriaComplica();
				return new Jugar(this.factoria);
			}
			else if (cadena[1].equalsIgnoreCase("RV")) {
				this.factoria = new FactoriaReversi();
				return new Jugar(this.factoria);
			}
			else
				throw new ModoIncorrecto("No ha introducido ningun modo de juego valido");
		}
		else 
			return null;
	}

	public String textoAyuda() {
		return "JUGAR [ c4 | co | gr ] [ tamX tamY ]:";
	}

}
