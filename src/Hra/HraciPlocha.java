package Hra;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.Timer;

import obrazek.Obrazek;
import obrazek.ZdrojObrazkuSoubor;

public class HraciPlocha extends JPanel{
	public static final int VYSKA = 800;
	public static final int SIRKA = 600;
	
	//rychlost behu pozadi
	public static final int RYCHLOST = -2;
	
	//TODO
	
	private BufferedImage imgPozadi;
	private Timer casovacAnimace;
	private boolean pauza = false;
	private boolean hraBezi = false;
	private int posunPozadiX = 0;
	
	public HraciPlocha() {
		ZdrojObrazkuSoubor z = new ZdrojObrazkuSoubor();
		z.naplMapu();
		z.setZdroj(Obrazek.POZADI.getKlic());
		try {
			imgPozadi = z.getObrazek();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void paint(Graphics g){
		super.paint(g);
		//dvepozadi za sebe
		//prvni
		g.drawImage(imgPozadi, posunPozadiX, 0, null);
		//druhe je posunuto o sirku obrazku
		g.drawImage(imgPozadi, posunPozadiX+imgPozadi.getWidth(), 0, null);
		
	}
	
	private void posun(){
		if(!pauza && hraBezi){
			//TODO
			
			
			
			
			
			
			
			//posub pozice hraci plochy (skrolovani)
			posunPozadiX = posunPozadiX + HraciPlocha.RYCHLOST;
			//když se pozaci cele doposouva, zacni od zacatku
			if (posunPozadiX == -imgPozadi.getWidth()) {
				posunPozadiX= 0;
			}
		}
	}
	
	private void spustHru(){
		casovacAnimace = new Timer(20, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				repaint();
				posun();
			}
		});
		
		hraBezi = true;
		casovacAnimace.start();
	}
	
	public void pripravHraciPlochu() {
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					//vyskok hrace
				}
				
				//pauza
				if(e.getButton() == MouseEvent.BUTTON3){
					if (hraBezi) {
						if (pauza) {
							pauza = false;
						} else {
							pauza = true;
						}
					} else {
						pripravNovouHru();
						spustHru();
					}
				}
				
				
			}
		});
		
		setSize(SIRKA, VYSKA);
	}

	protected void pripravNovouHru() {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
}
