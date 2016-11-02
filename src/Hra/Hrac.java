package Hra;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Hrac {
	public static final int SIRKA = 40;
	public static final int VYSKA = 33;
	//velikost skoku hráèe
	private static final int KOEF_ZRYCHLENI = 1;
	//rychlost padu hrace
	private static final int KOEF_RYCHLOST = 2;
	private BufferedImage img = null;
	//pocatecni x-ova pozice hrace, nemeni se (hrac neskace dopredu a dozadu)
	private int x;
	//pocatecni y-ova pozice hrace, meni se (hrac skace nahoru a dolu)
	private int y;
	private int rychlost;
	
	
	public Hrac(BufferedImage img) {
		this.img=img;
		y = HraciPlocha.VYSKA/2;
		x = (HraciPlocha.SIRKA/2)-(img.getWidth()/2);
		
		rychlost = KOEF_RYCHLOST;
	}
	
	//volá se po nárazu do tdi, kraje okna
	public void reset() {
		y = HraciPlocha.VYSKA/2;
		rychlost = KOEF_RYCHLOST;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void skok(){
		rychlost = -17;
	}
	
	//Zajistuje pohyb hrace (simulace gravitace)
	public void posun(){
		rychlost = rychlost + KOEF_ZRYCHLENI;
		y = y + rychlost;
	}
	
	public void paint(Graphics g){
		g.drawImage(img, x, y, null);	
	}
	
	public int getVyskaHrace() {
		return img.getHeight();
	}
	
	//vrací pomyslný ètverec/obdelnik, který opisuje toho hrace
	public Rectangle getMez(){
		return new Rectangle(x,y,img.getWidth(),img.getHeight());
	}
	
}
