package obrazek;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;

public class ZdrojObrazkuURL extends ZdrojObrazku{
	
	private static final String SOUBOR = "imgurl.csv";
	private static final String ODDELOVAC = ";";
	private static final String CESTA = "img/";
	
	@Override
	public void naplMapu() {
		nactiCSV();
	}

	private void nactiCSV() {
		//vyrobime stream
		try (BufferedReader vstup = new BufferedReader(new FileReader(CESTA+SOUBOR))) {
			//pouzijeme stream
			String radek;
			for (int i = 0; i < Obrazek.getSize(); i++) {
				if((radek = vstup.readLine())!= null){
					zpracujRadek(radek);
				}
			}
			
		} catch (IOException e) {
			System.out.println("Pri cteni CSV doslo k chybì: "+ e.getMessage());
		}
		
		//uzavreme stream - vyresi Java za nas sama
	}

	private void zpracujRadek(String radek) {
		//tvar v csv
		//hrac;url
		//zed;url
		//...
		
		//soubor mùže obsahovat více øádku, ty ale nebudeme èíst
		StringTokenizer st = new StringTokenizer(radek, ODDELOVAC);
		if (st.countTokens() == 2){//cteme jen 2 prvni tokeny
			String prvek = st.nextToken(); //hrac
			String odkaz = st.nextToken(); //url
			
			if (jePrvekVSeznamu(prvek)) {
				getMapa().put(prvek, odkaz);
			} else {
				System.out.println("Prvke "+prvek+" není v seznamu prvkù");
			}
			
		}
		
		
	}

	private boolean jePrvekVSeznamu(String prvek) {
		for(Obrazek o :Obrazek.getObrazky()){
			if (o.getKlic().equals(prvek)){
				return true;
			}
		}
		return false;
	}

	@Override
	public BufferedImage getObrazek() throws IOException {
		URL url = new URL(getZdroj());
		URLConnection urlSpojeni = url.openConnection();
		
		urlSpojeni.setReadTimeout(3000);//3 sekundy cti a pak prestan
		
		InputStream is = urlSpojeni.getInputStream();
		
		BufferedImage img = ImageIO.read(is);
		
		is.close();
		return img;
	}

}
