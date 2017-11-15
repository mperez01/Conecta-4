package tp.pr5.vistas.grafica;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import tp.pr5.control.ControlGUI;

//Ventana izquierda donde se jugara y aparecera el jugador que juega.
public class GraficaJuego extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ControlGUI control;
	private Frame ventana;
	public TableroPartida tablero;
	
	public GraficaJuego(ControlGUI control, Frame v){
		this.ventana = v;
		this.control  = control;
		this.setLayout(new BorderLayout());
		
		TableroPartida tablero = new TableroPartida(this.control, this.ventana);
		this.tablero = tablero;
		
		this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(), BorderFactory.createLoweredBevelBorder()));
		
		this.add(new GraficaVacia(), BorderLayout.CENTER);
		this.add(new CartelJugador(this.control), BorderLayout.SOUTH);
		
		this.add(this.tablero, BorderLayout.NORTH);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
