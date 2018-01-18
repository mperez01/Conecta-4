package tp.pr5.vistas.grafica;


import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import tp.pr5.control.ControlGUI;
import tp.pr5.enums.*;
import tp.pr5.excepcion.MovimientoInvalido;
import tp.pr5.logica.*;

public class VistaGUI extends JFrame implements Observador {
	
	/**
	 * 
	 */
	boolean activo = true;
	private static final long serialVersionUID = 1L;
	//private ControlGUI control;
	private ControlGUI control;

	
	
	public VistaGUI(String string, ControlGUI controlador ) {
		
		super (string);
		this.control = controlador;
		this.pack();
		//Disposicion de la ventana tipo 4 puntos cardinales
		this.setLayout(new BorderLayout()); 
		
		controlador.addObservador(this);
		//En ventana izqu pasamos "this" para que en TableroBotones se redimensione bien la ventana
		this.add(new VentanaIzq(this.control, this), BorderLayout.WEST);
		this.add(new VentanaDer(this.control), BorderLayout.EAST );
		
		this.control.addObservador(this);
		
		this.setVisible(true);
		this.pack();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void onResetPartida(TableroInmutable tabIni, Ficha turno) {
		
		JOptionPane.showMessageDialog(this, "Partida reiniciada", "Partida reiniciada", JOptionPane.INFORMATION_MESSAGE);
		
		this.pack();
		
		
	}

	public void onPartidaTerminada(TableroInmutable tabFin, Ficha ganador) {
		String mensaje = null;
		if (ganador == Ficha.NEGRA) {
			mensaje = "Han ganado las negras";
			
		}
		else
			if (ganador == Ficha.BLANCA) {
				mensaje = "Han ganado las blancas";
			}
			else
				if (ganador == Ficha.VACIA)
					mensaje = "Han quedado en tablas";
		
		JOptionPane.showMessageDialog(this, mensaje, "Partida terminada", JOptionPane.INFORMATION_MESSAGE);
	}

	@Override
	public void onCambioJuego(TableroInmutable tab, TipoJuego tipo, Ficha turno) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onMovimientoEnd(TableroInmutable tab, Ficha turno, boolean hay) {

		if (!hay) {
			JOptionPane.showMessageDialog(this, "Las fichas " + turno + " no tiene movimientos");
		}

		//this.control.terminarEjecucion(turno);
	}
	
	@Override
	public void onUndo(TableroInmutable tab, Ficha turno, boolean hayMas) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUndoNotPossible() {
		// TODO Auto-generated method stub
		String mensaje = "Imposible deshacer el ultimo movimiento";
		java.awt.Toolkit.getDefaultToolkit().beep();
		JOptionPane.showMessageDialog(this, mensaje, "Imposible deshacer", JOptionPane.ERROR_MESSAGE);
	}

	@Override
	public void onMovimienoIncorreto(MovimientoInvalido movimientoException) {
		java.awt.Toolkit.getDefaultToolkit().beep();
		JOptionPane.showMessageDialog(this, movimientoException.getMessage(), "Movimiento incorrecto", JOptionPane.ERROR_MESSAGE);
		
	}
	
	public void onError (String mensaje) {
		
	}


	@Override
	public void onCambioJugador(Ficha turno) {
		
		
		
		//System.out.println("TURNO AHORA : " +turno);
		/*
		System.out.println("TURNO AHORA en VISTA : " +turno);
		System.out.println("TIPO TURNO : " + this.control.getTipo(turno));
		
		if (this.control.getTipo(turno) == TipoTurno.AUTOMATICO)
			this.control.comenzarEjecucion(turno);
		else
			this.control.terminarEjecucion(turno);
			
			*/
			
	}


	@Override
	public void onMovimientoStart(Ficha turno, boolean hay) {
		// TODO Auto-generated method stub
				
	}
}
