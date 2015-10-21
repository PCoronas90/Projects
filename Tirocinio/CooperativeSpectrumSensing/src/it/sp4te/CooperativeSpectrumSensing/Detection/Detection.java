package it.sp4te.CooperativeSpectrumSensing.Detection;

import java.util.ArrayList;

import it.sp4te.CooperativeSpectrumSensing.Functions.SignalFunctions;

/** 
 * <p>Titolo: Detection</p>
 * <p>Descrizione: Classe per il calcolo dei Diversi tipi di Detection </p>
 * @author Pietro Coronas
 **/

public class Detection {

	/**
	 * Metodo proposto per il calcolo della detection che � stato
	 * argomento di tesi triennale. Invece di effettuare il calcolo della soglia e il successivo confronto
	 * sull'energia calcolata a partire dai Momenti del secondo e quarto ordine, questo metodo effettua lo stesso
	 * procedimento utilizzando i parametri Pr invece dei momenti.
	 * 
	 * @param threshold Soglia utilizzata per la Detection
	 * @param pr Parametro Pr calcolato a partire dai momenti
	 * @return La percentuale di Detection calcolata ad uno specifico SNR
	 * @see SignalFunctions#prGenerator
	 **/

	public static double proposedMethodDetection(double threshold, ArrayList<Double> pr) {
		double cont = 0;
		for (int i = 0; i < pr.size(); i++) {
			if (pr.get(i) >= threshold) {
				cont++;
			}
		}
		return (double) 100 / (double) (pr.size() / cont);
	}

	/**
	 * Metodo per l'Energy Detector calcolato dividento il segnale in M blocchi da N
	 * campioni. Successivamente, per ogni blocco, si calcola la media
	 * dell'energia e si confronta con la soglia. A differenza dell'energy
	 * Detector tradizionale, evita i picchi e d� un risultato pi� accurato.
	 * 
	 * @param threshold Soglia utilizzata per la Detection
	 * @param energy Array di energia. Nello specifico sono i Momenti del secondo e quarto ordine
	 * @param block Numero di blocchi in cui dividere il segnale per mediare il valore dell'energia ed evitare picchi
	 * @return Percentuale di Detection per un dato SNR
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
				avg = avg + energy.get(i);			}			
			
			if ((avg / samples) > threshold) { // Confronto la media del blocco di energia con la soglia
				cont++;	}

			if (end >= energy.size()-1) {
				complete = true;
			} 			
				start = end;
				end = end + samples;			
		}		
		return (double) 100 / (double) (block/ cont);
	}

	/**
	 * Metodo per l'Energy Detector tradizionale. Prende in input la soglia e un array di
	 * energia. Per ogni valore dell'array che supera la soglia incrementa un
	 * contatore. Successivamente riporta la % di Detection per un dato SNR
	 * 
	 * @param threshold Soglia utilizzata per la Detection
	 * @param energy Array di energia. Nello specifico sono i Momenti del secondo e quarto ordine
	 * @return Percentuale di Detection per un dato SNR
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
