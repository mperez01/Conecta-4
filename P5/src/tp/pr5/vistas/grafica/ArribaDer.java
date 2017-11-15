package tp.pr5.vistas.grafica;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import tp.pr5.control.ControlGUI;


public class ArribaDer extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private ControlGUI control;

	public ArribaDer(ControlGUI control) {
		this.control = control;
		this.setLayout(new BorderLayout());
		
		this.add(new GraficaPartida(control), BorderLayout.NORTH);
		this.add(new GraficaVacia(), BorderLayout.CENTER);
		this.add(new GestionJugadores(control), BorderLayout.CENTER);
		this.add(new CambiaJuego(control), BorderLayout.SOUTH);
		
		
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	

}
