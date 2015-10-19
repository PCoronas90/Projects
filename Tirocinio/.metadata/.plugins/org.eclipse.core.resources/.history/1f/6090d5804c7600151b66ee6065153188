package it.sp4te.CooperativeSpectrumSensing.Detection;

import java.util.ArrayList;

import it.sp4te.CooperativeSpectrumSensing.Functions.Pr;

/** Classe per il calcolo dei Diversi tipi di Detection **/

public class Detection {

	/**
	 * Metodo per il calcolo della detection del metodo proposto e che è stato
	 * argomento di tesi triennale. Prende in input la soglia ed un oggetto
	 * pr,in output produce la % du Detection per un dato SNR
	 **/

	public static double proposedMethodDetection(double threshold, Pr pr) {
		double cont = 0;
		for (int i = 0; i < pr.getPr().size(); i++) {
			if (pr.getPr().get(i) >= threshold) {
				cont++;
			}
		}
		return (double) 100 / (double) (pr.getPr().size() / cont);
	}

	/**
	 * Energy Detector calcolato dividento il segnale in M blocchi da N
	 * campioni. Successivamente, per ogni blocco, si calcola la media
	 * dell'energia e si confronta con la soglia. A differenza dell'energy
	 * Detector tradizionale, evita i picchi e dà un risultato più accurato.
	 **/

	public static double energyDetection(double threshold, ArrayList<Double> energy, int block) {
		double cont = 0;
		boolean complete = false;
		int start = 0;
		int samples = (energy.size() / block);
		int end = samples;
       
		while (complete == false) {
			double avg = 0;// media
			

			for (int i = start; i < end; i++) {// Scorro il segnale
				
				avg = avg + energy.get(i);
				
				
			}
			
			
			if ((avg / samples) > threshold) { // Confronto la media del blocco di energia con la soglia
				cont++;
			}

			if (end >= energy.size()-1) {
				complete = true;
			} 			
				start = end;
				end = end + samples;
			
		}
		
		
		return (double) 100 / (double) (block/ cont);
	}

	/**
	 * Energy Detector tradizionale. Prende in input la soglia e un array di
	 * energia. Per ogni valore dell'array che supera la soglia incrementa un
	 * contatore. Successivamente riporta la % di Detection per un dato SNR
	 **/

	public static double TraditionalEnergyDetection(double threshold, ArrayList<Double> energy) {
		double cont = 0;
		for (int i = 0; i < energy.size(); i++) {
			if (energy.get(i) > threshold) {
				cont++;
			}
		}
		return (double) 100 / (double) (energy.size() / cont);
	}
}
