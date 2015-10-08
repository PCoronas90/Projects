package Operazioni;

import java.util.ArrayList;

import FunzioniMat.FunzioniMatematiche;

import Segnali.RumoreIncertezza;
import Segnali.Segnale;

public class CalcoloPotenzaIncertezza {

	  public ArrayList<Double> Potenza;
	  public ArrayList<Double> segnaleReale;
	  public ArrayList<Double> segnaleImm;
	  double incertezza;
	  double potenza;
	  int prove;
	  

public CalcoloPotenzaIncertezza(Segnale s,double potenza,double incertezza,int prove){
	if(s!=null){
		this.segnaleReale= s.getCampioniSegnaleRe();
		this.segnaleImm= s.getCampioniSegnaleImm();}
		
	else{
		this.segnaleReale=null;
		this.segnaleImm=null;
		
		}
	this.incertezza=incertezza;
	this.prove=prove;
	this.potenza=potenza;
	this.Potenza= new ArrayList<Double>();
}
	public  ArrayList<Double> CalcoloPotenza(double snr,int lunghezza) {
     
            
			for(int i=0; i<prove;i++){
			RumoreIncertezza noise = new RumoreIncertezza(snr,incertezza,lunghezza,this.potenza);
			
			
			Segnale y= new Segnale(noise.getLunghezzaSegnale());
			
			y.setCampioniSegnaleRe(FunzioniMatematiche.Sommavettori(noise.getCampioniRumoreRe(),
					this.segnaleReale));
			y.setCampioniSegnaleImm(FunzioniMatematiche.Sommavettori(noise.getCampioniRumoreImm(),
					this.segnaleImm));
			
			this.Potenza.add(i,y.CalcoloPotenza());
}
	
	return this.Potenza;}


}