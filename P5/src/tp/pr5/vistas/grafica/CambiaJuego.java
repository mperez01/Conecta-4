package tp.pr5.vistas.grafica;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;	
import javax.swing.JPanel;

import tp.pr5.control.ControlGUI;
import tp.pr5.control.factorias.*;
import tp.pr5.enums.*;
import tp.pr5.excepcion.MovimientoInvalido;
import tp.pr5.logica.Observador;
import tp.pr5.logica.TableroInmutable;

public class CambiaJuego extends JPanel implements ActionListener, Observador {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ControlGUI control;
	
	private JComboBox cambioJuego; //Tipo string que sera los 3 juegos (c4,co,gr)
	private JButton botonCambioJuego;
	
	private TextField tFila;
	private TextField tCol;
	
	private JLabel nFilas;
	private JLabel nColumnas;
	private static String[] op = {"CONECTA 4", "COMPLICA", "GRAVITY", "REVERSI"};
	private TipoJuego juego;
	
	public CambiaJuego(ControlGUI control) {
		this.juego = TipoJuego.CONECTA4;
		
		this.control = control;
		this.setLayout(new BorderLayout());
		
		
		this.setBorder(BorderFactory.createTitledBorder("Cambio de Juego"));
		this.setPreferredSize(new Dimension(this.getWidth(), 160));
		
		//EleccJuego elec = new EleccJuego(control);
		
		
		this.cambioJuego = new JComboBox(op);
		this.cambioJuego.setSelectedIndex(0); //****************************
		this.cambioJuego.addActionListener(this);
		this.setLayout(new BorderLayout());
		//cambioJuego = new JComboBox <String>(); //No estoy muy seguro de este funcionamiento
		
		
		this.cambioJuego.addActionListener(this);
		
		//*********************FILAS Y COLUMNAS **************************
		this.nFilas = new JLabel("Filas");
		this.nFilas.setVisible(false);
		this.nColumnas = new JLabel("Columnas");
		this.nColumnas.setVisible(false);
		
		
		this.tFila = new TextField(7);
		this.tFila.setVisible(false);
		this.tCol = new TextField(7);
		this.tCol.setVisible(false);

		
		
		//*********************CAMBIO DE JUEGO **************************
		
		ImageIcon imagenCambioJuego = new ImageIcon("iconos/aceptar.png");
		botonCambioJuego = new JButton("Cambiar");
		
		botonCambioJuego.addActionListener(this);
		botonCambioJuego.setIcon(imagenCambioJuego);
		
		//****************************AGREGAR BOTONES********************
		JPanel p1 = new JPanel(new FlowLayout());
		p1.add(nFilas);
		p1.add(tFila);
		p1.add(nColumnas);
		p1.add(tCol);
		
		JPanel p2 = new JPanel(new FlowLayout());
		p2.add(botonCambioJuego);
		
		this.add(p1, BorderLayout.SOUTH);
		this.add(cambioJuego, BorderLayout.NORTH);
		this.add(p1, BorderLayout.CENTER);
		this.add(this.botonCambioJuego, BorderLayout.SOUTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if ("Cambiar" == e.getActionCommand()) {
			if(juego== TipoJuego.GRAVITY) {
				try {
					int fil,  co;
					String f = tFila.getText();
					fil = Integer.parseInt(f);
					String c = tCol.getText();
					co = Integer.parseInt(c);
					
					this.control.reset(new FactoriaGravity(fil, co));
					
				}
				catch (NumberFormatException num) {
					this.control.reset(new FactoriaGravity());
				}
			}
			if (juego == TipoJuego.CONECTA4) {
				this.control.reset(new FactoriaConecta4());
			}
			if (juego == TipoJuego.COMPLICA) {
				this.control.reset(new FactoriaComplica());
			}
			if (juego == TipoJuego.REVERSI)
				this.control.reset(new FactoriaReversi());
		}
		else {
			//@SuppressWarnings("unchecked")
			JComboBox aux = (JComboBox) e.getSource();
			String op = (String)aux.getSelectedItem();
			
			if (op == "GRAVITY") {
				this.nFilas.setVisible(true);
				this.tFila.setVisible(true);
				this.nColumnas.setVisible(true);
				this.tCol.setVisible(true);
				this.juego = TipoJuego.GRAVITY;
			}
				else
				if (op == "CONECTA 4") {
					this.nFilas.setVisible(false);
					this.tFila.setVisible(false);
					this.nColumnas.setVisible(false);
					this.tCol.setVisible(false);
					this.juego = TipoJuego.CONECTA4;
				}
				else
					if (op == "COMPLICA") {
						this.nFilas.setVisible(false);
						this.tFila.setVisible(false);
						this.nColumnas.setVisible(false);
						this.tCol.setVisible(false);
						this.juego = TipoJuego.COMPLICA;
					}
					else
						if (op == "REVERSI") {
							//Quitamos la opcion fila/columna si estuviese
							this.nFilas.setVisible(false);
							this.tFila.setVisible(false);
							this.nColumnas.setVisible(false);
							this.tCol.setVisible(false);
							this.juego = TipoJuego.REVERSI;
						}
		}
		
	}

	@Override
	public void onResetPartida(TableroInmutable tabIni, Ficha turno) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPartidaTerminada(TableroInmutable tabFin, Ficha ganador) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCambioJuego(TableroInmutable tab, TipoJuego tipo, Ficha turno) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMovimientoEnd(TableroInmutable tab, Ficha turno, boolean hay) {
		// TODO Auto-generated method stub
		/*if(this.control.getTipo(turno) == TipoTurno.AUTOMATICO) {
			//INTERRUMPIR EL THREAD!!
			this.control.terminarEjecucion(turno);
		}*/
		
	}

	@Override
	public void onUndo(TableroInmutable tab, Ficha turno, boolean hayMas) {
		// TODO Auto-generated method stub
		
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

	}

	@Override
	public void onMovimientoStart(Ficha turno, boolean hay ) {
		// TODO Auto-generated method stub
		
	}

}
