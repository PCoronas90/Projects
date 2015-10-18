package it.sp4te.CooperativeSpectrumSensing.Detection;

import java.util.ArrayList;

import it.sp4te.CooperativeSpectrumSensing.Functions.MathFunctions;
import it.sp4te.CooperativeSpectrumSensing.Functions.Pr;

/**
 * Classe per il calcolo delle soglie necessarie per il calcolo dei diversi tipi
 * di Detection
 **/
public class Threshold {

	/**
	 * Metodo per il calcolo della soglia necessaria per la Detection del metodo
	 * proposto. Prende in input probabilit� di falso allarme e oggetto PR
	 **/

	public static double proposedThreshold(double Pfa, Pr pr) throws Exception {
		double M = MathFunctions.Avarege(pr.getPr());
		double V = MathFunctions.Variance(pr.getPr());

		double implThreshold = M + Math.sqrt(2 * V) * MathFunctions.InvErf(1 - 2 * Pfa);
		return implThreshold;
	}

	/**
	 * Metodo per il calcolo della soglia necessaria per l'energy Detector.
	 * Prende in input la probabilit� di falso allarme e un vettore di energia
	 * (i momenti del secondo e quarto ordine)
	 **/

	public static double energyDetectorThreshold(double Pfa, ArrayList<Double> energy) throws Exception {

		double M = MathFunctions.Avarege(energy);
		double V = MathFunctions.Variance(energy);

		double edThreshold = M + Math.sqrt(2 * V) * MathFunctions.InvErf(1 - 2 * Pfa);
		return edThreshold;
	}
}
