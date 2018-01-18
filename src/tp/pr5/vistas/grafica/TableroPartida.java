package tp.pr5.vistas.grafica;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.plaf.metal.MetalLookAndFeel;

import tp.pr5.control.ControlGUI;
import tp.pr5.enums.*;
import tp.pr5.excepcion.MovimientoInvalido;
import tp.pr5.logica.Observador;
import tp.pr5.logica.TableroInmutable;

//Muestra el tablero donde se desarrollara la partida.
public class TableroPartida extends JPanel implements ActionListener, Observador {
	
	
	private JButton [][] buttons;
	private static final long serialVersionUID = 1L;
	private ControlGUI control;
	private Frame ventana;
	public TableroPartida(ControlGUI control, Frame v) {
		this.control = control;
		this.ventana = v;
		
		TableroInmutable tab = this.control.getTablero();
		this.buttons = new JButton[this.control.getAlto()][this.control.getAncho()];
		//Tipo "GridLayout" para q lo haga tipo fila-col
		this.setLayout(new GridLayout(this.control.getAlto(), this.control.getAncho()));
		
		/* ------------- */
		// DE ESTA MANERA SI SE pintan los colores en Mac OS X
		try {
	    	UIManager.setLookAndFeel(new MetalLookAndFeel());
	    }	
	    catch(Exception e) {}
		/*-------------------*/
		
		//MODIFICADO PARA EL COMIENZO DE PARTIDA
		crearBotonera( tab); 
		

	}
	

	public void actionPerformed(ActionEvent arg) {
		
		// PULSACION ENVIA COORDENADA CORRECTA!!
		for(int i = 0; i <  this.control.getAlto(); i++){
			for(int j = 0; j < this.control.getAncho(); j++){
				if (arg.getSource() == this.buttons[i][j] ) {
					this.control.poner(i + 1, j + 1);
				}
			}
		}
	}
	
	
	@Override
	public void onResetPartida(TableroInmutable tabIni, Ficha turno) {
	
		TableroInmutable tab = this.control.getTablero();
		this.removeAll();
		this.buttons = new JButton[this.control.getAlto()][this.control.getAncho()];
		this.setLayout(new GridLayout(this.control.getAlto(), this.control.getAncho()));
		
		crearBotonera(tab); 
		
		this.ventana.pack();
		this.updateUI();
	}
	
	private void crearBotonera(TableroInmutable tab) {
		for(int i = 0; i < tab.getAlto(); i++){
			for(int j = 0; j < tab.getAncho(); j++){
				
				this.buttons[i][j] = new JButton();
				this.buttons[i][j].setPreferredSize(new Dimension(30,30));
				if ((this.control.getTab().getCasilla(i + 1, j + 1) == Ficha.BLANCA)) {
					this.buttons[i][j].setBackground(Color.WHITE);
					
				}
				else
					if ((this.control.getTab().getCasilla(i + 1, j + 1) == Ficha.NEGRA)) {
						this.buttons[i][j].setBackground(Color.BLACK);
					}
					else
						this.buttons[i][j].setBackground(Color.GREEN);//Color.GREEN);
				this.buttons[i][j].addActionListener(this);
				control.addObservador(this);
				this.add(buttons[i][j]);
			}
		}
	}

	@Override
	public void onPartidaTerminada(TableroInmutable tabFin, Ficha ganador) {
		Ficha color = ColorPusoFicha(ganador);
		this.PintarBotones(tabFin, color);
		desactivarBotones(tabFin, false);
	}

	private void desactivarBotones(TableroInmutable tabFin, boolean activar) {
		for(int i = 0; i < tabFin.getAlto(); i++) {
			for(int j = 0; j < tabFin.getAncho(); j++){
				this.buttons[i][j].setEnabled(activar);
			}
		}
	}
	
	@Override
	public void onCambioJuego(TableroInmutable tab, TipoJuego tipo, Ficha turno) {
		// TODO Auto-generated method stub
		
	}
	
	private void PintarBotones(TableroInmutable tab, Ficha turno) {
		Color color;

		Ficha colorTab;
		for(int i = 0; i <  tab.getAlto(); i++){
			for(int j = 0; j < tab.getAncho(); j++) {
				if (tableroBotones(i,j) != tab.getCasilla(i+1, j+1)) {
					colorTab = tab.getCasilla(i + 1, j +1);
					color = CambiarColor(colorTab);
					this.buttons[i][j].setBackground(color);
				}
			}
		}
	}

	@Override
	public void onMovimientoEnd(TableroInmutable tab, Ficha turno, boolean hay) {
		// TODO Auto-generated method stub
		Ficha color = ColorPusoFicha(turno);
		PintarBotones(tab, color);
	}

	private Ficha tableroBotones(int fila, int col) {
		if (this.buttons[fila][col].getBackground() == Color.GREEN) {
			return Ficha.VACIA;
		}
		else
			if (this.buttons[fila][col].getBackground() == Color.BLACK)
				return Ficha.NEGRA;
			else
				if (this.buttons[fila][col].getBackground() == Color.WHITE)
					return Ficha.BLANCA;
		
		return null;
	}
	
	private Ficha ColorPusoFicha(Ficha color) {
		
		Ficha turnoPuso = null;
		
		if (color == Ficha.BLANCA)
			turnoPuso = Ficha.NEGRA;
		if (color == Ficha.NEGRA)
			turnoPuso = Ficha.BLANCA;
		if (color == Ficha.VACIA)
			turnoPuso = Ficha.VACIA;
		
		return turnoPuso; 
	}
	
	private Color CambiarColor(Ficha color) {
		Color tipo = null;
		if (color == Ficha.BLANCA)
			tipo = Color.WHITE;
		if (color == Ficha.NEGRA)
			tipo = Color.BLACK;
		if (color == Ficha.VACIA)
			tipo = Color.GREEN;
		
		return tipo; 
		
	}
	@Override
	public void onUndo(TableroInmutable tab, Ficha turno, boolean hayMas) {
		// TODO Auto-generated method stub
		PintarBotones(tab, Ficha.VACIA);
		
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
		//De este modo si es automatico poner en el tablero se desactivara y al ser humano se activara
		/*
		if(this.control.getTipo(turno)==TipoTurno.AUTOMATICO) {
			desactivarBotones(this.control.getTablero(), false);
		}
		else
			desactivarBotones(this.control.getTablero(), true);
			
			*/
		
	}
}
