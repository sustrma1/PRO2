package app;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import hra.HraciPlocha;

public class FlappyHlavniApp extends JFrame{
	private HraciPlocha hp;
	
	public FlappyHlavniApp() {
		
		
	}
	
	public void spust(){
		hp = new HraciPlocha();
		hp.pripravHraciPlochu();
		
		getContentPane().add(hp, "Center");
		hp.setVisible(true);
		this.revalidate();
		this.repaint();
		
	}
	public void initGUI(){
		setSize(HraciPlocha.SIRKA, HraciPlocha.VYSKA);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Flappy");
		setLocationRelativeTo(null);
		setVisible(true);
	}
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				FlappyHlavniApp app = new FlappyHlavniApp();
				app.initGUI();
				app.spust();
			}
		});
	}

}
