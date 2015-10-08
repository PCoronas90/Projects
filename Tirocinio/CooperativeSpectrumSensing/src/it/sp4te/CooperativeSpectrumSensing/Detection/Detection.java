package it.sp4te.CooperativeSpectrumSensing.Detection;

import java.util.ArrayList;

import it.sp4te.CooperativeSpectrumSensing.Functions.Pr;

public class Detection {

	
//Detection Metodo Implementato	
	public static double implMethodDetection(double threshold,Pr pr){
		double cont=0;
		for(int i=0;i<pr.getPr().size();i++){
			if (pr.getPr().get(i)>=threshold){
				cont++;
			}}


		return (double)100/(double)(pr.getPr().size()/cont);

	}
	
	//Calcola la energy detection
    public static double EnergyDetection(double threshold, ArrayList<Double> energy){
    	double cont=0;
		for(int i=0;i<energy.size();i++){
			if (energy.get(i)>threshold){
				cont++;
			}}


		return (double)100/(double)(energy.size()/cont);
    }
}
