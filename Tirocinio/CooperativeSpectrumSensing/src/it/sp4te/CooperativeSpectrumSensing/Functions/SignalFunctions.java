package it.sp4te.CooperativeSpectrumSensing.Functions;

import java.util.ArrayList;

import it.sp4te.CooperativeSpectrumSensing.DomainModel.Signal;

public class SignalFunctions {

	
	//Energia del segnale
	public static double signalEnergy(Signal s){
		double p=0.0;
		for(int i=0;i<s.getSignalLenght();i++){
		p=p + Math.abs(Math.pow(s.getSamplesRe().get(i),2))+Math.abs((Math.pow(s.getSamplesIm().get(i),2)));
		}
		return p/s.getSignalLenght();
	}
	
	//Generatore dei momenti secondo e quarto ordine
	public static ArrayList<Moment> momentGenerator(Signal s,int length,double energy,int attempts,int inf,int sup){
		ArrayList<Moment> Moments = new ArrayList<Moment>();	
		for(double i=inf;i<sup;i++){
			Moment m= new Moment(s,attempts,energy,i,length);
			Moments.add(m);}
		return Moments;
	}
	
	//Calcolo energia dei momenti
	public static  ArrayList<ArrayList<Double> > momentEnergy(ArrayList<Moment> Moment){
		ArrayList<ArrayList<Double> > energy = new ArrayList<ArrayList<Double> >();
		for(int i=0;i<Moment.size();i++){
			energy.add(Moment.get(i).getEnergy());
		}
		return energy;
	}
	
	
	//Generatore dei pr
	public static ArrayList<Pr>  prGenerators(ArrayList<Moment> Moment){
		ArrayList<Pr> PrResult = new ArrayList<Pr>();
		for(int i=0;i<Moment.size();i++){
			Pr prTemp = new Pr(Moment.get(i));
			PrResult.add(prTemp);

		}
		return PrResult;
	}
	
	

	
	
}