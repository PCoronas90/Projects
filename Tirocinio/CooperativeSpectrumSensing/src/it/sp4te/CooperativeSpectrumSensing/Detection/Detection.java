package it.sp4te.CooperativeSpectrumSensing.Detection;

import java.util.ArrayList;

import it.sp4te.CooperativeSpectrumSensing.Functions.Pr;

public class Detection {

	
//Detection Metodo Proposto	
	public static double proposedMethodDetection(double threshold,Pr pr){
		double cont=0;
		for(int i=0;i<pr.getPr().size();i++){
			if (pr.getPr().get(i)>=threshold){
				cont++;
			}}


		return (double)100/(double)(pr.getPr().size()/cont);

	}
	
	//Energy Detector calcolata dividendo il segnale in 100 blocchi da 10 campioni e calcolando la media
	//dell'energia. Evita i picchi.
	public static double energyDetection(double threshold, ArrayList<Double> energy){
		double cont=0;
		boolean complete=false;
		int start=0;
		int end=10;
		
		while(complete==false){
			//media
			double avg=0;
			//campioni analizzati
			int div=0;
			//Scorro il segnale
			for(int i=start;i<end;i++){
				avg=avg+energy.get(i);
				div++;
			}
			
			//Confronto la media del blocco di energia con la soglia
			if ((avg/div)>threshold){
				cont++;}
			///Se ho finito il campioni finisco
			if(end==energy.size()){complete=true;}
			
			//Altrimenti aggiorno i valori e scorro il segnale
			else{
			start=end+1;
			end=end+10;}
		}
		//Ritorno la percentuale
		return (double)100/(double)(100/cont);
	}
	
	
	//Calcola la energy detection Tradizionale
    public static double TraditionalEnergyDetection(double threshold, ArrayList<Double> energy){
    	double cont=0;
		for(int i=0;i<energy.size();i++){
			if (energy.get(i)>threshold){
				cont++;
			}}


		return (double)100/(double)(energy.size()/cont);
    }
}
