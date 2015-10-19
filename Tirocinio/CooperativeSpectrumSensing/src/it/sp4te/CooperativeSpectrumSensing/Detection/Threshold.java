package it.sp4te.CooperativeSpectrumSensing.Detection;

import java.util.ArrayList;

import it.sp4te.CooperativeSpectrumSensing.Functions.MathFunctions;
import it.sp4te.CooperativeSpectrumSensing.Functions.Pr;

/**
 * <p>Titolo: Threshold</p>
 * <p>Descrizione: Classe per il calcolo delle soglie necessarie per il calcolo dei diversi tipi
 * di Detection</p>
 * @author Pietro Coronas
 **/
public class Threshold {

	/**
	 * Metodo per il calcolo della soglia necessaria per la Detection del metodo
	 * proposto.
	 * 
	 * @param Pfa Probabilit� di falso allarme
	 * @param pr Oggetto Pr calcolato nell'ipotesi in cui il segnale primario � assente (solo rumore)
	 * @return La soglia
	 * @throws Exception L'argomento della funzione InvErf deve essere compreso tra -1 e 1
	 **/

	public static double proposedThreshold(double Pfa, Pr pr) throws Exception {
		double M = MathFunctions.Avarege(pr.getPr());
		double V = MathFunctions.Variance(pr.getPr());

		double implThreshold = M + Math.sqrt(2 * V) * MathFunctions.InvErf(1 - 2 * Pfa);
		return implThreshold;
	}

	/**
	 * Metodo per il calcolo della soglia necessaria per l'energy Detector.
	 * 
	 * @param Pfa Probabilit� di falso allarme
	 * @param energy Momento calcolato nell'ipotesi in cui il segnale primario � assente (solo rumore)
	 * @return La soglia necessaria per l'energy Detector
	 * @throws Exception L'argomento della funzione InvErf deve essere compreso tra -1 e 1
	 **/

	public static double energyDetectorThreshold(double Pfa, ArrayList<Double> energy) throws Exception {

		double M = MathFunctions.Avarege(energy);
		double V = MathFunctions.Variance(energy);

		double edThreshold = M + Math.sqrt(2 * V) * MathFunctions.InvErf(1 - 2 * Pfa);
		return edThreshold;
	}
}
