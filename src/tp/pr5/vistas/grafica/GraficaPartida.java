package tp.pr5.vistas.grafica;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import tp.pr5.control.ControlGUI;
import tp.pr5.enums.*;
import tp.pr5.excepcion.MovimientoInvalido;
import tp.pr5.logica.Observador;
import tp.pr5.logica.TableroInmutable;

//Grafica que muestra  le recueadro de partida(boton deshacer, boton reiniciar)
public class GraficaPartida extends JPanel implements ActionListener, Observador {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ControlGUI control;
	JButton botonDeshacer = new JButton();
	JButton botonReiniciar = new JButton();
	
	

	public GraficaPartida(ControlGUI control) {
		this.control = control;
		
		//Para que ponga en el recuadro partida
		this.setBorder(BorderFactory.createTitledBorder("Partida"));
		
		botonDeshacer = new JButton("deshacer");
		ImageIcon imagenDeshacer = new ImageIcon("iconos/undo.png");
		botonDeshacer.setIcon(imagenDeshacer);
		botonDeshacer.addActionListener(this);
		 //Dar permiso para que funcione el boton
		botonDeshacer.setEnabled(false);
		this.add(botonDeshacer, BorderLayout.WEST);
		
		//Boton reiniciar
		botonReiniciar = new JButton("Reiniciar");
		ImageIcon imagenReiniciar = new ImageIcon("iconos/reiniciar.png");
		botonReiniciar.setIcon(imagenReiniciar);
		botonReiniciar.addActionListener(this);
		//Dar permiso para que funcione el boton
		botonDeshacer.setEnabled(false); 
		this.add(botonReiniciar, BorderLayout.EAST);
		
		control.addObservador(this);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == botonDeshacer){
			this.control.undo();
		}
		else{
			if(e.getSource() == botonReiniciar){
				this.control.reset(this.control.getFactoria());
			}
		}
		
	}

	@Override
	public void onResetPartida(TableroInmutable tabIni, Ficha turno) {
		this.botonDeshacer.setEnabled(false);
		this.botonReiniciar.setEnabled(true);
		
	}

	@Override
	public void onPartidaTerminada(TableroInmutable tabFin, Ficha ganador) {
		this.botonDeshacer.setEnabled(false);
		
	}

	@Override
	public void onCambioJuego(TableroInmutable tab, TipoJuego tipo, Ficha turno) {
		this.botonDeshacer.setEnabled(false);
		
	}

	@Override
	public void onMovimientoEnd(TableroInmutable tab, Ficha turno, boolean hay) {
		this.botonDeshacer.setEnabled(true);
		
	}

	@Override
	public void onUndo(TableroInmutable tab, Ficha turno, boolean hayMas) {
		if(hayMas == true){
			this.botonDeshacer.setEnabled(true);
		}else{
			this.botonDeshacer.setEnabled(false);
		}
		
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
	public void onError(String mensaje) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCambioJugador(Ficha turno) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMovimientoStart(Ficha turno, boolean hay) {
		
		/*
		if (this.control.getTipo(turno) == TipoTurno.AUTOMATICO) {
			this.botonDeshacer.setEnabled(false);
		}
		else
			this.botonDeshacer.setEnabled(true);
			*/
	}

}
