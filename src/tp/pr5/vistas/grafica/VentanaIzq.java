package tp.pr5.vistas.grafica;

import java.awt.BorderLayout;
import java.awt.Frame;

import javax.swing.JPanel;

import tp.pr5.control.ControlGUI;

//Ventana que sera la de la parte izquierda
public class VentanaIzq extends JPanel {
	//Action listener ya que va a ser el q realice la interfaz 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ControlGUI control;
	public GraficaJuego grafJuego;
	public Frame ventana;

	public VentanaIzq (ControlGUI control, Frame v){
		this.control = control;
		this.ventana = v;
		GraficaJuego grafJuego = new GraficaJuego(this.control, this.ventana); 
		this.grafJuego = grafJuego;
		
		this.setLayout(new BorderLayout());
		
		this.add(this.grafJuego, BorderLayout.NORTH);
		this.add(new BotonPonerAleatorio(control), BorderLayout.SOUTH);
		
		
	}

}
