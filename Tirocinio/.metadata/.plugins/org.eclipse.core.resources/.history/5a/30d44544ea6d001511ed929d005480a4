package it.sp4te.CooperativeSpectrumSensing.Main;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import it.CooperativeSpectrumSensing.Agents.SecondaryUser;
import it.sp4te.CooperativeSpectrumSensing.DomainModel.Signal;
import it.sp4te.CooperativeSpectrumSensing.Functions.SignalFunctions;

public class SpectrumSensing {

	public static void main(String args[]) throws Exception {	
		
		//Setto i parametri
		int length=1000; //poi 10000
		int attempts= 1000;
		int inf= -30;
		int sup= 5;
		
		//Genero il segnale
		Signal s = new Signal(length);
		
		//Genero utente secondario
		SecondaryUser SU= new SecondaryUser();
		
		//Calcolo EnergyDetector
		HashMap<Double,Double> PowerDetection=
		SU.SpectrumSensingEnergyDetector(s, length, SignalFunctions.signalEnergy(s), attempts, inf, sup);
		ArrayList<Double> snr= new ArrayList<Double>();
		
		//Stampo i valori
		for (Double key : PowerDetection.keySet()) {
			snr.add(key);}
		Collections.sort(snr);
		for (Double key : snr) {
        System.out.println("snr: "+ key + " " + "Detection: "+ PowerDetection.get(key).toString());
		}
		
		
		
	}
}
