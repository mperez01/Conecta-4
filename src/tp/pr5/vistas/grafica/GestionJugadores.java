package tp.pr5.vistas.grafica;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import tp.pr5.control.ControlGUI;
import tp.pr5.enums.Ficha;
import tp.pr5.enums.TipoTurno;

public class GestionJugadores extends JPanel implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel JBlancas, JNegras;
	//De esta forma añadimos al combobox los valores de tipoTurno
	private JComboBox jugadorBlancas = new JComboBox(TipoTurno.values());
	private JComboBox jugadorNegras = new JComboBox(TipoTurno.values());
	
	private ControlGUI control;
	
	public GestionJugadores(ControlGUI control) {
		
		this.setLayout(new GridLayout(2, 2));
		
		this.control = control;
		
		this.setBorder(BorderFactory.createTitledBorder("Gestion de jugadores"));
		//this.setPreferredSize(new Dimension(this.getWidth(), 100));
		
		this.JBlancas=new JLabel(" Jugador de blancas ", JLabel.LEFT);
		this.add(JBlancas);
		//this.jugadorBlancas.setSelectedIndex(0); //****************************
		this.jugadorBlancas.addActionListener(this);
		this.add(this.jugadorBlancas);
		
		
		this.JNegras=new JLabel(" Jugador de negras ", JLabel.LEFT);
		this.add(JNegras);
		//this.jugadorBlancas.setSelectedIndex(0); //****************************
		this.jugadorNegras.addActionListener(this);
		this.add(this.jugadorNegras);
		 
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//HACER QUE CUANDO SE CAMBIE
		if(e.getSource() == jugadorBlancas) {
			if(jugadorBlancas.getSelectedItem() == TipoTurno.AUTOMATICO) {
				JOptionPane.showMessageDialog(this, "Estamos en AUTOMATICO para BLANCAS");
				//LE DAMOS A LA FICHA BLANCA EL ROL DE AUTOMATICO
				this.control.setTipo(Ficha.BLANCA, TipoTurno.AUTOMATICO);
			}
			else
				if(jugadorBlancas.getSelectedItem() == TipoTurno.HUMANO) {
					JOptionPane.showMessageDialog(this, "Estamos en HUMANO para BLANCAS");
					//LE DAMOS A LA FICHA BLANCA EL ROL DE HUMANO
					this.control.setTipo(Ficha.BLANCA, TipoTurno.HUMANO);
				}
		}
		else
			if(e.getSource() == jugadorNegras) {
				if(jugadorNegras.getSelectedItem() == TipoTurno.AUTOMATICO) {
					JOptionPane.showMessageDialog(this, "Estamos en AUTOMATICO para NEGRAS");
					//LE DAMOS A LA FICHA NEGRA EL ROL DE AUTOMATICO 
					this.control.setTipo(Ficha.NEGRA, TipoTurno.AUTOMATICO);
				}
				else
					if(jugadorNegras.getSelectedItem() == TipoTurno.HUMANO) {
						JOptionPane.showMessageDialog(this, "Estamos en HUMANO para NEGRAS");
						//LE DAMOS A LA FICHA NEGRA EL ROL DE HUMANO
						this.control.setTipo(Ficha.NEGRA, TipoTurno.HUMANO);
					}
			}
		/*System.out.println("TIPO TURNO : " + this.control.getTurno());
		this.control.comenzarEjecucion(this.control.getTurno());*/
		
	}

}
