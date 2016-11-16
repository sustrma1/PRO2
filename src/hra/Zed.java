package hra;

import java.awt.image.BufferedImage;

public class Zed {
	public final static int SIRKA = 45;
	//rychlost pohybu zdi
	public final static int RYCHLOST = -6;
	public final static int MEZERA_VE_ZDI = 200;
	
	//TODO
	
	//ruzne zdi(obrazky) nesmí být static
	private static BufferedImage img = null;
	//x-ova souradnice zdi, meni se zprava doleva
	private int x; 
	//y-ova souradnice zdi (horni souradnice spodni casti zdi)
	private int y;
	private int vyska;
	
	public Zed(int vzdalenostOdZacatku) {
		this.x=vzdalenostOdZacatku;
		//TODO
		
		
	}
}
