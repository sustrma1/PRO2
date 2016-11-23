package hra;

import java.awt.Color;
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
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int VYSKA = 800;
	public static final int SIRKA = 600;
	
	public static final boolean DEBUG = true;
	//rychlost behu pozadi
	public static final int RYCHLOST = -2;
	
	//musi byt alespon tri zdi, jinak se zed nestihne posunotut
	//za levy okraj = nestihne zajet z alevy okraj hraci plochy
	//ne� je pot�eba ji posunout p�ed prav� okraj hrac� plochy a
	//vykreslit	
	public static final int POCET_ZDI=4;
	
	private SeznamZdi seznamZdi;
	private Zed aktualniZed;
	private Zed predchoziZed;
	
	//TODO
	private Hrac hrac;
	private BufferedImage imgHrac;
	private BufferedImage imgPozadi;
	private BufferedImage imgZed;
	private Zed zed;
	private Timer casovacAnimace;
	private boolean pauza = false;
	private boolean hraBezi = false;
	private int posunPozadiX = 0;
	
	public HraciPlocha() {
		//TODO
		ZdrojObrazkuSoubor z = new ZdrojObrazkuSoubor();
		z.naplMapu();
		z.setZdroj(Obrazek.POZADI.getKlic());
		try {
			imgPozadi = z.getObrazek();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		z.setZdroj(Obrazek.HRAC.getKlic());
		try {
			imgHrac = z.getObrazek();
			hrac = new Hrac(imgHrac);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		z.setZdroj(Obrazek.ZED.getKlic());
		try {
			imgZed = z.getObrazek();
			zed.setObrazek(imgZed);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		seznamZdi = new SeznamZdi();
		
		
	}
	
	private void vyrobZdi(int pocet){
		Zed zed;
		int vzdalenost = HraciPlocha.SIRKA;
		
		for (int i = 0; i < pocet; i++) {
			zed = new Zed(vzdalenost);
			seznamZdi.add(zed);
			vzdalenost= vzdalenost + (HraciPlocha.SIRKA/2);
		}
		
		vzdalenost = vzdalenost - HraciPlocha.SIRKA - Zed.SIRKA;
		Zed.setVzdalenostPosledniZdi(vzdalenost);
	}
	
	public void paint(Graphics g){
		super.paint(g);
		//dvepozadi za sebe
		//prvni
		g.drawImage(imgPozadi, posunPozadiX, 0, null);
		//druhe je posunuto o sirku obrazku
		g.drawImage(imgPozadi, posunPozadiX+imgPozadi.getWidth(), 0, null);
		
		if (HraciPlocha.DEBUG) {
			g.setColor(Color.RED);
			g.drawString("posun pozadiX = "+posunPozadiX, 0, 10);
		}
		
		for (Zed zed : seznamZdi) {
			zed.paint(g);
		}
		
		hrac.paint(g);
	}
	
	private void posun(){
		if(!pauza && hraBezi){
			//TODO
			
			
			for (Zed zed : seznamZdi) {
				zed.posun();
			}
			
			hrac.posun();
			
			
			//posub pozice hraci plochy (skrolovani)
			posunPozadiX = posunPozadiX + HraciPlocha.RYCHLOST;
			//kdy� se pozaci cele doposouva, zacni od zacatku
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
					if (hraBezi) {
						if(!pauza){
							hrac.skok();
							
						}
					}
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
		
		vyrobZdi(POCET_ZDI);
		
	}
	
	
	
	
	
}
