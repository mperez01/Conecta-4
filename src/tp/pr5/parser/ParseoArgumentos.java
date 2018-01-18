package tp.pr5.parser;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;

import tp.pr5.enums.TipoJuego;
import tp.pr5.enums.TipoVista;
import tp.pr5.excepcion.ExcepcionArgumento;

public class ParseoArgumentos {
	private final String mensajeUso = "tp.pr1.Main [-g <game>] [-h] [-x <columnNumber>] [-y <rowNumber>]";
	private Options options;
	private CommandLine line;
	
	@SuppressWarnings({ "static-access" })
	public ParseoArgumentos(String[] args){
		this.options = new Options();
		CommandLineParser parser = new PosixParser();
		
		
		Option help  = OptionBuilder.withDescription("Muestra esta ayuda" ).withLongOpt("help").create( 'h' );
		
		Option game = OptionBuilder.withArgName( "game" ).hasOptionalArg().
				withDescription("Tipo de juego (c4, co, gr, rv) . Por defecto, c4." )
				.withLongOpt("game").create('g');
		
		Option tamX  = OptionBuilder.withArgName( "numberRows" ).hasArg().withDescription( "Número de filas del tablero (sólo para Gravity). "
				+"Por defecto, 10") .withLongOpt("tamX").create('x' );
		
		Option tamY  = OptionBuilder.withArgName( "numberColumns" ).hasArg().withDescription( "Número de columnas del tablero (sólo para Gravity)."
				+"Por defecto, 10." ).withLongOpt("tamY").create( 'y' );
		
		Option userInterface = OptionBuilder.withArgName( "User Interface" ).hasArg().withDescription("Tipo de interfaz (console, window). "
				+ "Por defecto, console.").withLongOpt( "ui" ).create('u'); 
				
		this.options.addOption(help);
		this.options.addOption(game);
		this.options.addOption(tamX);
		this.options.addOption(tamY);
		this.options.addOption(userInterface);
		
		try {
			this.line = parser.parse(options, args);
		} catch (ParseException e) {
			System.err.println("Uso incorrecto: " + e.getMessage());
			System.err.println("Uso -h/--help para más detalles.");
			System.exit(1);
		}

	}
	
	public boolean tieneAyuda(){
		return line.hasOption("h");
	}
	
	// https://commons.apache.org/proper/commons-cli/apidocs/org/apache/commons/cli/HelpFormatter.html
	public void mostrarAyuda(){
		HelpFormatter formatter = new HelpFormatter();
		formatter.printHelp(this.mensajeUso, this.options);
		System.exit(0);
	}
	
	public TipoJuego getTipoJuego() throws ExcepcionArgumento{
		if (this.line.hasOption('g')){
			
			//Mas argumentos sobrantes
			if (this.line.getArgs().length > 0) {
				throw new ExcepcionArgumento ("Argumentos no entendidos " + this.line.getArgList());
			}
			
			if("co".equalsIgnoreCase(line.getOptionValue('g'))){
				return TipoJuego.COMPLICA;
			}
			
			else if ("gr".equalsIgnoreCase(this.line.getOptionValue('g'))){
				return TipoJuego.GRAVITY;
			}
			
			else if("c4".equalsIgnoreCase(this.line.getOptionValue('g'))){
				return TipoJuego.CONECTA4;
			}
			if("rv".equalsIgnoreCase(line.getOptionValue('g'))){
				return TipoJuego.REVERSI;
			}
			//Si no hay ninguna opcion (NULL), se comienza como conecta 4
			else if(line.getOptionValue('g') == null){
				return TipoJuego.CONECTA4;
			}
			else{
				throw new ExcepcionArgumento("Juego '" + this.line.getOptionValue('g') + "' incorrecto.");
			}
		}
		return TipoJuego.CONECTA4;
	}
	
	public int getTamX(){
		if (this.line.hasOption('x')){
			try{
				return Integer.parseInt(this.line.getOptionValue('x'));
			}catch (NumberFormatException e){
				return 10;
			}
		}
		else {
			return 10;
		}
	}
	
	public int getTamY(){
		if (this.line.hasOption("y")){
			try{
				return Integer.parseInt(line.getOptionValue("y"));
			}catch (NumberFormatException e){
				return 10;
			}
		}
		else {
			return 10;
		}
	}
	
	public TipoVista getInterfaz() throws ExcepcionArgumento {
		if (this.line.hasOption('u')){
			
			//Mas argumentos sobrantes
			if (this.line.getArgs().length > 0) {
				throw new ExcepcionArgumento ("Argumentos no entendidos " + this.line.getArgList());
			}
			
			if("console".equalsIgnoreCase(line.getOptionValue('u'))){
				return TipoVista.CONSOLE;
			}
			
			else if ("window".equalsIgnoreCase(this.line.getOptionValue('u'))){
				return TipoVista.WINDOW;
			}
			//Si no hay ninguna opcion (NULL), se comienza como consola
			else if(line.getOptionValue('u') == null){
				return TipoVista.CONSOLE;
			}
			else{
				throw new ExcepcionArgumento("Tipo vista " + this.line.getOptionValue('u') + "' incorrecto.");
			}
		}
		return TipoVista.CONSOLE;
	}
}
