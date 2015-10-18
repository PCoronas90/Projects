package it.sp4te.CooperativeSpectrumSensing.Detection;

import java.util.ArrayList;

import it.sp4te.CooperativeSpectrumSensing.Functions.Pr;

/**Classe per il calcolo dei Diversi tipi di Detection**/

public class Detection {


	/**Metodo per il calcolo della detection del metodo proposto e che � stato argomento di tesi triennale.
	 * Prende in input la soglia ed un oggetto pr,in output produce la % du Detection per un dato SNR**/

	public static double proposedMethodDetection(double threshold,Pr pr){
		double cont=0;
		for(int i=0;i<pr.getPr().size();i++){
			if (pr.getPr().get(i)>=threshold){
				cont++;
			}}
		return (double)100/(double)(pr.getPr().size()/cont);
	}

	/**Energy Detector calcolato dividento il segnale in 100 blocchi da 10 campioni. Successivamente, per ogni
	 * blocco, si calcola la media dell'energia e si confronta con la soglia. A differenza dell'energy Detector
	 * tradizionale, evita i picchi e d� un risultato pi� accurato.**/

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


	/**Energy Detector tradizionale. Prende in input la soglia e un array di energia. Per ogni valore
	 * dell'array che supera la soglia incrementa un contatore. Successivamente riporta la % di Detection per
	 * un dato SNR**/

	public static double TraditionalEnergyDetection(double threshold, ArrayList<Double> energy){
		double cont=0;
		for(int i=0;i<energy.size();i++){
			if (energy.get(i)>threshold){
				cont++;
			}}
		return (double)100/(double)(energy.size()/cont);
	}
}
