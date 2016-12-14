package hra;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Hrac {
	public static final int SIRKA = 40;
	public static final int VYSKA = 33;
	
	
	//velikost skoku hr��e
	private static final int KOEF_ZRYCHLENI = 1;
	//rychlost padu hrace
	private static final int KOEF_RYCHLOST = 2;
	private BufferedImage img = null;
	//pocatecni x-ova pozice hrace, nemeni se (hrac neskace dopredu a dozadu)
	private int x;
	//pocatecni y-ova pozice hrace, meni se (hrac skace nahoru a dolu)
	private int y;
	private int rychlost;
	
	
	private int c = 1;//animace barvy hrace
	
	public Hrac(BufferedImage img) {
		this.img=img;
		y = HraciPlocha.VYSKA/2;
		x = (HraciPlocha.SIRKA/2)-(img.getWidth()/2);
		
		rychlost = KOEF_RYCHLOST;
	}
	
	//vol� se po n�razu do zdi, kraje okna
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
		
		
		//animace hrace
		/*if(c>240){
			c=10;
		}
		else{
			c+=10;
		}*/
		
	}
	
	public void paint(Graphics g){
		g.drawImage(img, x, y, null);
		/*animace hrace
		g.setColor(new Color(c,c, c));
		g.fillRect(x, y, Hrac.SIRKA, Hrac.VYSKA);
		*/
		if (HraciPlocha.DEBUG) {
			g.setColor(Color.RED);
			g.drawString("[x="+x+", y="+y+", rychlost="+rychlost+"]", x, y-5);
		}
	}
	
	public int getVyskaHrace() {
		return img.getHeight();
	}
	
	//vrac� pomysln� �tverec/obdelnik, kter� opisuje toho hrace
	public Rectangle getMez(){
		return new Rectangle(x,y,img.getWidth(),img.getHeight());
	}
	
}
