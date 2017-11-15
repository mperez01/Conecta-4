 package tp.pr5.vistas.grafica;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import tp.pr5.control.ControlGUI;

//Muestra el lado derecho de la ventana
public class VentanaDer  extends JPanel implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private ControlGUI control;

	public VentanaDer(ControlGUI control) {
		this.control = control;
		this.setLayout(new BorderLayout());
		
		this.add(new ArribaDer(control), BorderLayout.NORTH);
		this.add(new BotonSalir(), BorderLayout.SOUTH);
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
