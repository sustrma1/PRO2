package app;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import hra.HraciPlocha;
import obrazek.ManazerObrazku;
import obrazek.ZdrojObrazkuSoubor;
import obrazek.ZdrojObrazkuURL;

public class FlappyHlavniApp extends JFrame{
	private ManazerObrazku mo;
	
	
	public FlappyHlavniApp() {
		mo = new ManazerObrazku(new ZdrojObrazkuSoubor());
		//mo = new ManazerObrazku(new ZdrojObrazkuURL());	
		
	}
	
	public void spust(){
		class Vlakno extends SwingWorker<Object, Object>{
			private JFrame vlastnik;
			private JLabel lb;
			private HraciPlocha hp;
			
			public void setVlastnik(JFrame vlastnik) {
				this.vlastnik = vlastnik;
			}
			
			public void doBeforeExecute(){
				lb = new JLabel("Pripravuji hru...");
				lb.setFont(new Font("Arial", Font.BOLD, 42));
				lb.setForeground(Color.RED);
				lb.setHorizontalAlignment(SwingConstants.CENTER);
				
				vlastnik.add(lb);
				lb.setVisible(true);
				vlastnik.revalidate();
				vlastnik.repaint();
			}
			
			@Override
			protected Object doInBackground() throws Exception {
				mo.pripravObrazky();
				hp = new HraciPlocha(mo);
				hp.pripravHraciPlochu();
				return null;
			}
			
			@Override
			protected void done() {
				super.done();
				
				vlastnik.remove(lb);
				vlastnik.revalidate();
				vlastnik.add(hp);
				hp.setVisible(true);
				vlastnik.revalidate();				
				vlastnik.repaint();
			}
			
		}
		
		Vlakno vl = new Vlakno();
		vl.setVlastnik(this);
		vl.doBeforeExecute();
		vl.execute();
		
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
