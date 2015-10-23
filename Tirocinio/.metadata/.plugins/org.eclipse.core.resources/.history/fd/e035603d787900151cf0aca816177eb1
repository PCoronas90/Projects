package it.sp4te.css.detection;

import java.util.ArrayList;

import it.sp4te.css.signalprocessing.SignalProcessor;

/** 
 * <p>Titolo: Detection</p>
 * <p>Descrizione: Classe per il calcolo dei Diversi tipi di Detection </p>
 * @author Pietro Coronas
 **/

public class Detector {

	/**
	 * Metodo proposto per il calcolo della detection che è stato
	 * argomento di tesi triennale. Invece di effettuare il calcolo della soglia e il successivo confronto
	 * sull'energia calcolata a partire dai Momenti del secondo e quarto ordine, questo metodo effettua lo stesso
	 * procedimento utilizzando i parametri Pr invece dei momenti.
	 * 
	 * @param threshold Soglia utilizzata per la Detection
	 * @param pr Parametro Pr calcolato a partire dai momenti
	 * @return La percentuale di Detection calcolata ad uno specifico SNR
	 * @see  SignalProcessor#computePr
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
	 * Metodo per l'Energy Detector . Prende in input la soglia e un array di
	 * energia. Per ogni valore dell'array che supera la soglia incrementa un
	 * contatore. Successivamente riporta la % di Detection per un dato SNR. 
	 * 
	 * @param threshold Soglia utilizzata per la Detection
	 * @param energy Array di energia. Nello specifico è un vettore di oggetti momento, contenente momenti del 
	 * secondo e quarto ordine
	 * @return Percentuale di Detection per un dato SNR
	 **/

	public static double energyDetection(double threshold, ArrayList<Double> energy) {
		double cont = 0;
		for (int i = 0; i < energy.size(); i++) {
			if (energy.get(i) > threshold) {
				cont++;
			}
		}
		return (double) 100 / (double) (energy.size() / cont);
	}
}
