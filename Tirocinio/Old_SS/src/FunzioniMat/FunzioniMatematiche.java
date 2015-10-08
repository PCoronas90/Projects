package FunzioniMat;

import java.util.ArrayList;

public class FunzioniMatematiche {
	double varianza = 0;
	
	/*Calcola la media*/
	public static double CalcoloMedia(ArrayList<Double> a) {
		double somma = 0;
		for (int i = 0; i < a.size(); i++) {
			somma = somma + a.get(i);
		}
		return somma / a.size();
	}
  
	/*Calcola la varianza*/
	
	public static double CalcoloVarianza(ArrayList<Double> a) {
		ArrayList<Double> b = new ArrayList<Double>();
		for (int i = 0; i < a.size() ; i++) {
			b.add(Math.pow(a.get(i),2));}
         
		

		return (CalcoloMedia(b)) - (Math.pow(CalcoloMedia(a), 2));
	}

	/*Somma due vettori*/
	public static ArrayList<Double> Sommavettori(ArrayList<Double> noise, ArrayList<Double> segnale) {
		
		if (segnale == null) {
			return noise;
		} else {
			ArrayList<Double> c = new ArrayList<Double>();
			for (int i = 0; i < segnale.size(); i++) {
				c.add(i,noise.get(i) + segnale.get(i));
			}
			return c;
		}
	}
}
