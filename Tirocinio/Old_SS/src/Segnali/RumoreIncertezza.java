package Segnali;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author  Pietro
 */
public class RumoreIncertezza {
	Random campione;
	/**
	 * @uml.property  name="varianza"
	 */
	private double Varianza;
	/**
	 * @uml.property  name="lunghezzaSegnale"
	 */
	private int LunghezzaSegnale;
	/**
	 * @uml.property  name="campioniRumoreRe"
	 */
	ArrayList<Double> campioniRumoreRe;
	/**
	 * @uml.property  name="campioniRumoreImm"
	 */
	ArrayList<Double> campioniRumoreImm;


public RumoreIncertezza(double incertezza,int lunghezzasegnale,double potenza){}	
	
	
	public RumoreIncertezza(double snr,double incertezza, int lunghezzasegnale,double potenza) {
       
		double p=Math.pow(10, (incertezza/10));
		double a =Math.pow(10, (snr/10));
		this.LunghezzaSegnale = lunghezzasegnale;
		
		this.Varianza =  (potenza /a);
		Random rnd= new Random();
		double VarInc=rnd.nextDouble() * (p*this.Varianza - (1/p)*this.Varianza) + (1/p)*this.Varianza;
		
		

		this.campioniRumoreRe = new ArrayList<Double>();
		for (int i = 0; i < this.LunghezzaSegnale; i++) {
			campione = new Random();
			campioniRumoreRe.add(i, (campione.nextGaussian() * Math
					.sqrt((VarInc))));

			

		}
		this.campioniRumoreImm = new ArrayList<Double>();
		for (int i = 0; i < this.LunghezzaSegnale; i++) {
			campione = new Random();
			campioniRumoreImm.add(i, (campione.nextGaussian() * Math
					.sqrt((VarInc))));
		}
	}

	public double CalcoloPotenza(){
		double p=0.0;
		for(int i=0;i<this.LunghezzaSegnale;i++){
		p=p + Math.abs((Math.pow(this.campioniRumoreRe.get(i),2)))+Math.abs((Math.pow(this.campioniRumoreImm.get(i),2)));
		}
		return p/this.LunghezzaSegnale;
	}
	
/**
 * @return
 * @uml.property  name="varianza"
 */
public double getVarianza() {
	return Varianza;
}



/**
 * @return
 * @uml.property  name="lunghezzaSegnale"
 */
public int getLunghezzaSegnale() {
	return LunghezzaSegnale;
}


/**
 * @return
 * @uml.property  name="campioniRumoreRe"
 */
public ArrayList<Double> getCampioniRumoreRe() {
	return campioniRumoreRe;
}

/**
 * @return
 * @uml.property  name="campioniRumoreImm"
 */
public ArrayList<Double> getCampioniRumoreImm() {
	return campioniRumoreImm;
}
}

