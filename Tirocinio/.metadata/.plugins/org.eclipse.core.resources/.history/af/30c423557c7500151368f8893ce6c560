package it.sp4te.CooperativeSpectrumSensing.Main;

import java.util.ArrayList;
import java.util.HashMap;

import it.sp4te.CooperativeSpectrumSensing.Agents.SecondaryUser;
import it.sp4te.CooperativeSpectrumSensing.DomainModel.Signal;
import it.sp4te.CooperativeSpectrumSensing.Functions.SignalFunctions;
import it.sp4te.CooperativeSpectrumSensing.GraphGenerator.GraphGenerator;

/**
 * Classe SpectrumSensing. Tramite il metodo main Si occupa di creare: -Il
 * segnale -L'utente Secondario Tramite l'utente secondario effettua i 2 diversi
 * tipi di Detection. Successivamente passa alla creazione del grafico.
 **/

public class SpectrumSensing {

	public static void main(String args[]) throws Exception {
		ArrayList<Double> EnergyDetection = new ArrayList<Double>();
		ArrayList<Double> ProposedDetection = new ArrayList<Double>();

		// Creo una mappa per creare il grafico. La mappa deve essere formata da
		// nomeDetection->valori
		HashMap<String, ArrayList<Double>> DetectionGraph = new HashMap<String, ArrayList<Double>>();

		// Setto i parametri
		int length = 1000; // poi 10000
		int attempts = 1000;
		int inf = -30;
		int sup = 5;

		// Genero il segnale
		Signal s = new Signal(length);

		// Genero utente secondario
		SecondaryUser SU = new SecondaryUser(s, length, SignalFunctions.signalEnergy(s), attempts, inf, sup);

		// Calcolo EnergyDetection
		EnergyDetection = SU.spectrumSensingEnergyDetector();
		// Calcolo ProposedDetection
		ProposedDetection = SU.spectrumSensingProposedDetector();

		// Inizializzo la Mappa per il grafico
		DetectionGraph.put("Energy Detection", EnergyDetection);
		DetectionGraph.put("Proposed Detection", ProposedDetection);

		GraphGenerator.drawGraph(DetectionGraph, inf, sup);

	}
}
