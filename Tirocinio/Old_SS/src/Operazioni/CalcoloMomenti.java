package Operazioni;


import java.util.ArrayList;

import FunzioniMat.FunzioniMatematiche;
import Segnali.Rumore;
import Segnali.Segnale;

//Calcola i vettori media e varianza


/**
 * @author  Pietro
 */
public class CalcoloMomenti {

	    /**
		 * @uml.property  name="snr"
		 */
	    public double snr;
		public ArrayList<Double> segnaleReale;
		public ArrayList<Double> segnaleImm;
		public int prove;
		/**
		 * @uml.property  name="media"
		 */
		public ArrayList<Double> media;
		/**
		 * @uml.property  name="q"
		 */
		public ArrayList<Double> q;
		public ArrayList<Double> PotenzaSegnale;
        
		
	/*Costruttore*/
		
		public CalcoloMomenti(Segnale s, int prove) {
			this.media= new ArrayList<Double>();
			this.q= new ArrayList<Double>();;
			this.prove = prove;
			this.PotenzaSegnale=new ArrayList<Double>();
			if(s!=null){
			
			this.segnaleReale= s.getCampioniSegnaleRe();
			this.segnaleImm= s.getCampioniSegnaleImm();}
			else{
				
				this.segnaleReale=null;
				this.segnaleImm=null;
				
			}
			
		}

		/*Calcola momenti del secondo e quarto ordine*/
		
		public void calcolo(double potenza,double snr,int lunghezza) {
			this.snr=snr;
           
			for (int i = 0; i < this.prove; i++) {
				double j = 0.0;
				double h=0.0;
				
				Rumore noise = new Rumore(snr,lunghezza,potenza);
				
				
				Segnale y= new Segnale(noise.getLunghezzaSegnale());
				
				y.setCampioniSegnaleRe(FunzioniMatematiche.Sommavettori(noise.getCampioniRumoreRe(),
						this.segnaleReale));
				y.setCampioniSegnaleImm(FunzioniMatematiche.Sommavettori(noise.getCampioniRumoreImm(),
						this.segnaleImm));
				
				
				this.PotenzaSegnale.add(i,y.CalcoloPotenza());
				
				for (int k = 1; k < noise.getLunghezzaSegnale()-1; k++) {
                
	 
					j =  (j + Math.pow(y.getCampioniSegnaleRe().get(k), 2)+ Math.pow(y.getCampioniSegnaleImm().get(k), 2));
					h =  (h + Math.pow(y.getCampioniSegnaleRe().get(k), 4)+ Math.pow(y.getCampioniSegnaleImm().get(k), 4));
				}
				
				this.media.add(i, j * (1 / (double) y.getLunghezzaSegnale()));
				this.q.add(i, h * (1 /(double) y.getLunghezzaSegnale()));
			}

		
		}

		
		
		
		/**
		 * @return
		 * @uml.property  name="media"
		 */
		public ArrayList<Double> getMedia() {
			return media;
		}

		

		/**
		 * @return
		 * @uml.property  name="q"
		 */
		public ArrayList<Double> getQ() {
			return q;
		}

		/**
		 * @return
		 * @uml.property  name="snr"
		 */
		public double getSnr(){
			return this.snr;
		}
		
		public ArrayList<Double> getPotenza(){
			return this.PotenzaSegnale;
			
		}

		

	}
