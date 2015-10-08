package it.sp4te.CooperativeSpectrumSensing.Agents;

import java.util.*;


import it.sp4te.CooperativeSpectrumSensing.Detection.Detection;
import it.sp4te.CooperativeSpectrumSensing.Detection.Threshold;
import it.sp4te.CooperativeSpectrumSensing.DomainModel.Signal;
import it.sp4te.CooperativeSpectrumSensing.Functions.Moment;
import it.sp4te.CooperativeSpectrumSensing.Functions.Pr;
import it.sp4te.CooperativeSpectrumSensing.Functions.SignalFunctions;



public class SecondaryUser {
	
	
	public HashMap<Double,Double> SpectrumSensingEnergyDetector(Signal s,int length,double energy,int attempts,int inf,int sup) throws Exception {
	//Genero i momenti nelle due ipotesi h0 e h1
	ArrayList<Moment> MomentsSignal =SignalFunctions.momentGenerator(s, length, energy, attempts, inf, sup);
	ArrayList<Moment> MomentsNoise =SignalFunctions.momentGenerator(null, length, energy, attempts, inf, sup);
    
	//Calcolo l'energia
	ArrayList<ArrayList<Double> > MomentSignalEnergy = SignalFunctions.momentEnergy(MomentsSignal);
	ArrayList<ArrayList<Double> > MomentNoiseEnergy = SignalFunctions.momentEnergy(MomentsNoise);
	
	//Genero Pr
	ArrayList<Pr> Pr = SignalFunctions.prGenerators(MomentsSignal);
	
	//Creo una mappa snr->detection
	HashMap<Double,Double> PowerDetection= new HashMap<Double,Double>();

	//Calcolo la detection
	for(int i=0;i<MomentSignalEnergy.size();i++){
	
	Double PD= Detection.EnergyDetection(Threshold.EnergyDetectorThreshold(0.01, MomentNoiseEnergy.get(i)), MomentSignalEnergy.get(i));
	//PR SERVE? PER ME NO.
	PowerDetection.put(Pr.get(i).getMoment().getSnr(),PD);
	
	
}
	return PowerDetection;
	}}
