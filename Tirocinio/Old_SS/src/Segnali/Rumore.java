package Segnali;

import java.util.ArrayList;
import java.util.Random;



/**
 * @author  Pietro
 */
public class Rumore {



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
	Random campione;

	public Rumore(double snr, int lunghezzasegnale,double potenza) {

		double a =Math.pow(10, (snr/10));
		this.Varianza =  (potenza /a);
		this.LunghezzaSegnale = lunghezzasegnale;

		this.campioniRumoreRe = new ArrayList<Double>();
		for (int i = 0; i < this.LunghezzaSegnale; i++) {
			campione = new Random();
			campioniRumoreRe.add(i, (campione.nextGaussian() * Math
					.sqrt(Varianza)));
		
			

		}
		this.campioniRumoreImm = new ArrayList<Double>();
		for (int i = 0; i < this.LunghezzaSegnale; i++) {
			campione = new Random();
			campioniRumoreImm.add(i, (campione.nextGaussian() * Math
					.sqrt(Varianza)));
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