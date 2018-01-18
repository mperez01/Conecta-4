package tp.pr5.modo;

public class ModoJuegoAutomatico  extends Thread  implements ModoJuego{

	//tiempo retardo
	private static final int DELAY = 2000;
	
	public ModoJuegoAutomatico( ){
	}
	
	@SuppressWarnings("static-access")
	@Override
	public void comenzar() {
		// TODO Auto-generated method stub
		this.start();
		try {
			this.sleep(DELAY);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void terminar() {
		// TODO Auto-generated method stub
		
		this.interrupt();
	}

	@Override
	public void deshacerPulsado() {
		// TODO Auto-generated method stub
		
	}

}
