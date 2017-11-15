package tp.pr5.vistas.grafica;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

//Grafica que muestra el boton salir
public class BotonSalir extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JButton botonSalir = new JButton();
	
	public BotonSalir() {
		ImageIcon imagenSalir = new ImageIcon("iconos/exit.png");
		botonSalir = new JButton("Salir");
		
		botonSalir.addActionListener(this);
		
		botonSalir.setIcon(imagenSalir);
		this.add(botonSalir, BorderLayout.CENTER);
		
	}

	public void actionPerformed(ActionEvent e) {
		int n = JOptionPane.showOptionDialog(new JFrame(), "Seguro que deseas salir", "Salir",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
		if (n == 0) {
			System.exit(0);
		}
	}

}
