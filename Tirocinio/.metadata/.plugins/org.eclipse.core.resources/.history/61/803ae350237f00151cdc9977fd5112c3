package it.sp4te.css.agents;

import java.util.ArrayList;
import java.util.HashMap;
import it.sp4te.css.detection.Detector;

public class FusionCenter {



	/**
	 * Questo metodo prende in input una mappa, contenente per ogni utente secondario (chiave) una lista di decisioni binarie calcolate
	 * per ogni SNR su un numero di prove P. Per ogni SNR prende le decisioni degli utenti secondari, li inserisce in un vettore e richiama il metodo
	 * di detection secondo la tecnica AND. Ritornerà la percentuale di Detection da parte Fusion Center
	 * @param inf Estremo inferiore dell'SNR
	 * @param sup Estremo superiore di SNR
	 * @param userToBinaryDecision Mappa che ha come chiave il nome dell'uente primario. Come valore ha una lista di liste: per ogni SNR ha una lista
	 * di lunghezza pari al numero di prove contenente la decisione binaria sulla presenza o assenza dell'utente primario da parte dell'utente secondario
	 * @return La percentuale di Detection da parte del Fusion Center dopo il metodo di fusione AND**/

	public  ArrayList<Double> andDecision(int inf,int sup,HashMap<String,ArrayList<ArrayList<Integer>>> userToBinaryDecision){
		ArrayList<Double> EnergyDetection = new  ArrayList<Double>();

		for(int i=0;i<(sup-inf);i++){
			ArrayList<ArrayList<Integer>> decisions=new ArrayList<ArrayList<Integer>>();
			for(String SU: userToBinaryDecision.keySet()){
				ArrayList<Integer>snrDecisionVector= userToBinaryDecision.get(SU).get(i);
				decisions.add(snrDecisionVector);}

			EnergyDetection.add(Detector.andFusionDetection(decisions));
		}
		return EnergyDetection;
	}


	/**
	 * Questo metodo prende in input una mappa, contenente per ogni utente secondario (chiave) una lista di decisioni binarie calcolate
	 * per ogni SNR su un numero di prove P. Per ogni SNR prende le decisioni degli utenti secondari, li inserisce in un vettore e richiama il metodo
	 * di detection secondo la tecnica OR. Ritornerà la percentuale di Detection da parte Fusion Center
	 * @param inf Estremo inferiore dell'SNR
	 * @param sup Estremo superiore di SNR
	 * @param userToBinaryDecision Mappa che ha come chiave il nome dell'uente primario. Come valore ha una lista di liste: per ogni SNR ha una lista
	 * di lunghezza pari al numero di prove contenente la decisione binaria sulla presenza o assenza dell'utente primario da parte dell'utente secondario
	 * @return La percentuale di Detection da parte del Fusion Center dopo il metodo di fusione OR**/

	public  ArrayList<Double> orDecision(int inf,int sup,HashMap<String,ArrayList<ArrayList<Integer>>> userToBinaryDecision){
		ArrayList<Double> EnergyDetection = new  ArrayList<Double>();

		for(int i=0;i<(sup-inf);i++){
			ArrayList<ArrayList<Integer>> decisions=new ArrayList<ArrayList<Integer>>();
			for(String SU: userToBinaryDecision.keySet()){
				ArrayList<Integer>snrDecisionVector= userToBinaryDecision.get(SU).get(i);
				decisions.add(snrDecisionVector);}

			EnergyDetection.add(Detector.orFusionDetection(decisions));
		}
		return EnergyDetection;
	}


	/**
	 * Questo metodo prende in input una mappa, contenente per ogni utente secondario (chiave) una lista di decisioni binarie calcolate
	 * per ogni SNR su un numero di prove P. Per ogni SNR prende le decisioni degli utenti secondari, li inserisce in un vettore e richiama il metodo
	 * di detection secondo la tecnica MAJORITY. Ritornerà la percentuale di Detection da parte Fusion Center
	 * @param inf Estremo inferiore dell'SNR
	 * @param sup Estremo superiore di SNR
	 * @param userToBinaryDecision Mappa che ha come chiave il nome dell'uente primario. Come valore ha una lista di liste: per ogni SNR ha una lista
	 * di lunghezza pari al numero di prove contenente la decisione binaria sulla presenza o assenza dell'utente primario da parte dell'utente secondario
	 * @return La percentuale di Detection da parte del Fusion Center dopo il metodo di fusione MAJORITY**/

	public  ArrayList<Double> majorityDecision(int inf,int sup,HashMap<String,ArrayList<ArrayList<Integer>>> userToBinaryDecision){
		ArrayList<Double> EnergyDetection = new  ArrayList<Double>();

		for(int i=0;i<(sup-inf);i++){
			ArrayList<ArrayList<Integer>> decisions=new ArrayList<ArrayList<Integer>>();
			for(String SU: userToBinaryDecision.keySet()){
				ArrayList<Integer>snrDecisionVector= userToBinaryDecision.get(SU).get(i);
				decisions.add(snrDecisionVector);}

			EnergyDetection.add(Detector.majorityFusionDetection(decisions));
		}
		return EnergyDetection;
	}

}

