package tp.pr5.vistas.grafica;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import tp.pr5.control.ControlGUI;
import tp.pr5.enums.*;
import tp.pr5.excepcion.DatosIncorrectos;
import tp.pr5.excepcion.MovimientoInvalido;
import tp.pr5.logica.Observador;
import tp.pr5.logica.TableroInmutable;
import tp.pr5.movimientos.Movimiento;

//Grafica que muestra el boton poner aleatorio
public class BotonPonerAleatorio extends JPanel implements ActionListener, Observador {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton botonAleatorio = new JButton();
	private ControlGUI control;
	
	public BotonPonerAleatorio(ControlGUI control) {
		
		this.control = control;
		this.botonAleatorio = new JButton("Poner Aleatorio");
		ImageIcon imagenAleatorio = new ImageIcon("iconos/random.png");
		this.botonAleatorio.setIcon(imagenAleatorio);
		this.botonAleatorio.addActionListener(this);
		this.control.addObservador(this);
		this.add(botonAleatorio, BorderLayout.CENTER);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		try {
			Movimiento mov = this.control.getFactoria().creaJugadorAleatorio().
			getMovimiento(this.control.getFactoria(), this.control.getTab(), this.control.getTurno());
			this.control.poner(mov.getFila(), mov.getColumna());
		} catch (DatosIncorrectos e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Override
	public void onResetPartida(TableroInmutable tabIni, Ficha turno) {
		// TODO Auto-generated method stub
		this.botonAleatorio.setEnabled(true);
	}

	@Override
	public void onPartidaTerminada(TableroInmutable tabFin, Ficha ganador) {
		// TODO Auto-generated method stub
		this.botonAleatorio.setEnabled(false);
	}

	@Override
	public void onCambioJuego(TableroInmutable tab, TipoJuego tipo, Ficha turno) {
		// TODO Auto-generated method stub
		this.botonAleatorio.setEnabled(true);
	}

	@Override
	public void onMovimientoEnd(TableroInmutable tab, Ficha turno, boolean hay) {
		// TODO Auto-generated method stub
		this.botonAleatorio.setEnabled(true);
	}

	@Override
	public void onUndo(TableroInmutable tab, Ficha turno, boolean hayMas) {
		// TODO Auto-generated method stub
		this.botonAleatorio.setEnabled(true);
	}

	@Override
	public void onUndoNotPossible() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMovimienoIncorreto(MovimientoInvalido movimientoException) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCambioJugador(Ficha turno) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onError(String mensaje) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMovimientoStart(Ficha turno, boolean hay) {
		// TODO Auto-generated method stub
		/*
		if (this.control.getTipo(turno) == TipoTurno.AUTOMATICO) {
			this.botonAleatorio.setEnabled(false);
		}
		*/
	}
	
}
