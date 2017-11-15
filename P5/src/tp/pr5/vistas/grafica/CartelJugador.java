package tp.pr5.vistas.grafica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import tp.pr5.control.ControlGUI;
import tp.pr5.enums.Ficha;
import tp.pr5.enums.TipoJuego;
import tp.pr5.excepcion.MovimientoInvalido;
import tp.pr5.logica.Observador;
import tp.pr5.logica.TableroInmutable;

//Grafica que muestra el letrero de quien le toca jugar
public class CartelJugador extends JPanel implements ActionListener, Observador {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Label textoJugador;
	private ControlGUI control;
	public CartelJugador(ControlGUI control) {
		
		this.control = control;
		
		this.setBackground(Color.WHITE);
		
		Font tipoLetra = new Font("Times New Roman", Font.BOLD, 11);
		
		this.textoJugador = new Label();
		this.textoJugador.setText("Juegan " +  this.getColor(this.control.getTurno()));
		textoJugador.setFont(tipoLetra);
		textoJugador.setForeground(Color.BLUE);
		this.add(textoJugador, BorderLayout.CENTER);
		this.control.addObservador(this);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onResetPartida(TableroInmutable tabIni, Ficha turno) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onPartidaTerminada(TableroInmutable tabFin, Ficha ganador) {
		// TODO Auto-generated method stub
		if (ganador == Ficha.VACIA)
			this.textoJugador.setText("Han quedado en tablas ");
		else
			this.textoJugador.setText("Ganan las " + this.getColor(ganador));
		
		
	}


	@Override
	public void onCambioJuego(TableroInmutable tab, TipoJuego tipo, Ficha turno) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onMovimientoEnd(TableroInmutable tab, Ficha turno, boolean hay) {
		// TODO Auto-generated method stub
		
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
	public void onCambioJugador(Ficha turno) {
		// TODO Auto-generated method stub
		this.textoJugador.setText("Juegan " + this.getColor(turno));
	}
	
	private String getColor(Ficha color) {
		
		if (color == Ficha.BLANCA) 
			return "blancas";
		if (color == Ficha.NEGRA)
			return "negras";
		
		return null;
	}


	@Override
	public void onError(String mensaje) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onMovimientoStart(Ficha turno, boolean hay) {
		// TODO Auto-generated method stub
		
	}
}
