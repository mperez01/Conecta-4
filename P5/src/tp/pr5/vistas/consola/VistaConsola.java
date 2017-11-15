package tp.pr5.vistas.consola;


import tp.pr5.enums.Ficha;
import tp.pr5.enums.TipoJuego;
import tp.pr5.excepcion.MovimientoInvalido;
import tp.pr5.logica.*;

public class VistaConsola implements Observador {

	/*private Scanner in;
	private ControlConsola control;
	*/
	private int cont = 0;
	//metodo run e implementa la interfaz observador
	//Atributos Scanner y controlcosola
	//run() tiene que tener
	//debe mostrar al usuario las modificaciones de la partida
	
	public VistaConsola (Observable juego) {
		juego.addObservador(this);
	}
	public VistaConsola() {
		
	}
	
	@Override
	public void onPartidaTerminada(TableroInmutable tabFin, Ficha ganador) {
		// TODO Auto-generated method stub
		String mensaje = "Partida terminada, ";
		
		System.out.print(System.getProperty("line.separator")+ tabFin.toString());
		if (ganador == Ficha.VACIA) {
			mensaje += "han quedado en tablas.";
		}
		else
			if (ganador == Ficha.BLANCA)
				mensaje += "las blancas";
			else
				if (ganador == Ficha.NEGRA)
					mensaje += "las negras";
		System.out.println(mensaje);
		
	}
	
	private String turnoJugador(Ficha turno){
		
		String color = null;
		if (turno == Ficha.BLANCA) {
			color = "blancas";
		} else if (turno == Ficha.NEGRA) {
			color = "negras";
		} else if (turno == Ficha.VACIA) {
			color = null;
		}
		return color;
	}

	@Override
	public void onMovimientoEnd(TableroInmutable tab, Ficha turno, boolean hay) {
		// TODO Auto-generated method stub
		String color = null;

		color = turnoJugador(turno);
		
		System.out.print(System.getProperty("line.separator") + tab.toString()
				+ System.getProperty("line.separator") + "Juegan " + color 
				+ System.getProperty("line.separator"));
		cont ++;
	}

	@Override
	public void onUndo(TableroInmutable tab, Ficha turno, boolean hayMas) {
		// TODO Auto-generated method stub
		String color = null;

		color = turnoJugador(turno);
		System.out.print(System.getProperty("line.separator") + tab.toString() + "Movimiento deshecho" + System.getProperty("line.separator") + "Juegan "
		+ color + System.getProperty("line.separator"));
	}

	public void onResetPartida(TableroInmutable tabIni, Ficha turno) {
		String color = null;

		color = turnoJugador(turno);
		if (cont != 0) {
			System.out.print("Partida reiniciada correctamente, "
					+ "todos los jugdaores volveran a ser humanos"
					+ System.getProperty("line.separator") + tabIni.toString() + System.getProperty("line.separator") + "Juegan " 
					+ color + System.getProperty("line.separator"));
			cont = 0;
		}
		else {
			System.out.print(tabIni + System.getProperty("line.separator") + "Juegan " 
			+ color + System.getProperty("line.separator"));
			//cont ++ para que nada mas empezar la partida no muestre el mensaje
		}
	}

	public void onUndoNotPossible() {
		System.out.println("No hay movimiento que deshacer");
		
	}

	public void onCambioJuego(TableroInmutable tab, TipoJuego tipo, Ficha turno) {
		//En consola, al cambiar de juego se reinicia la partida y vuelve a por defecto
		// por tanto no se muestra nada
	}

	public void onMovimienoIncorreto(MovimientoInvalido movimientoException) {
		
		System.err.println(movimientoException.getMessage());
		
	}
	
	public void onError (String mensaje) {
		System.err.println(mensaje);
	}
	
	public void textoPedirOpcion() {
		System.out.print("Que quieres hacer? " );
	}
	
	public void verTableroInmutable(TableroInmutable tab, Ficha turno) {
		String color;
		color = turnoJugador(turno);
		System.out.print(tab + "Juegan "  + color + System.getProperty("line.separator"));
	}
	@Override
	public void onCambioJugador(Ficha turno) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onMovimientoStart(Ficha turno, boolean hay) {
		// TODO Auto-generated method stub
		
	}
}
