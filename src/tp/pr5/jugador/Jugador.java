package tp.pr5.jugador;

import java.util.InputMismatchException;

import tp.pr5.control.factorias.FactoriaJuego;
import tp.pr5.enums.Ficha;
import tp.pr5.excepcion.DatosIncorrectos;
import tp.pr5.logica.Tablero;
import tp.pr5.movimientos.Movimiento;

public abstract class Jugador {
	protected int fila;
	protected int columna;
	protected abstract void obtenFilaColumna(Tablero tab, Ficha color);
	
	public Movimiento getMovimiento(FactoriaJuego factoria,Tablero tab, Ficha color) throws DatosIncorrectos {
		 try { 
			 this.obtenFilaColumna(tab,color);           
			 return factoria.CrearMovimiento(this.fila,this.columna,color);      
		 }
		 
		 catch (InputMismatchException e){      
			 throw new DatosIncorrectos();  
		} 
	}
}
