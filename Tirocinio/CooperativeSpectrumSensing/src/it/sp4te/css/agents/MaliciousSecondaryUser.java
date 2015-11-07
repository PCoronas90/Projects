package it.sp4te.css.agents;

import java.util.ArrayList;

import it.sp4te.css.detection.Detector;
import it.sp4te.css.signalprocessing.SignalProcessor;

public class MaliciousSecondaryUser extends SecondaryUser {


	/**Questo metodo ritorna, per ogni valore di SNR , una lista di decisioni lunga quanto il numero di prove contenente l'assenza dell'utente
	 * primario per ogni misurazione. E' utilizzato per la creazione del vettore di decisione dell'utente malevolo
	 * @return Una lista di liste contenente per ogni SNR, una lista decisioni binarie che affermanol'assenza dell'utente primario di cardinalit� pari al numero di prove
	 * @throws Exception **/

	public ArrayList<ArrayList<Integer>> computeAbsenceBinaryDecisionVector() throws Exception{
		ArrayList<ArrayList<Integer>> decisions= new  ArrayList<ArrayList<Integer>>();
		for(int i=inf;i<sup;i++){
			ArrayList<Integer> snrDecisions= new  ArrayList<Integer>();			
			for(int j=0;j<attempts;j++){
				snrDecisions.add(0);
			}
			decisions.add(snrDecisions);


		}
		return decisions;	
	}


	/**Questo metodo ritorna, per ogni valore di SNR , una lista di decisioni lunga quanto il numero di prove contenente la presenza dell'utente
	 * primario per ogni misurazione. E' utilizzato per la creazione del vettore di decisione dell'utente malevolo
	 * @return Una lista di liste contenente per ogni SNR, una lista decisioni binarie che affermano la presenza dell'utente primario di cardinalit� pari al numero di prove
	 * @throws Exception **/

	public ArrayList<ArrayList<Integer>> computePresenceBinaryDecisionVector() throws Exception{
		ArrayList<ArrayList<Integer>> decisions= new  ArrayList<ArrayList<Integer>>();
		for(int i=inf;i<sup;i++){
			ArrayList<Integer> snrDecisions= new  ArrayList<Integer>();			
			for(int j=0;j<attempts;j++){
				snrDecisions.add(1);
			}
			decisions.add(snrDecisions);


		}
		return decisions;	
	}


	/**Questo metodo ritorna, per ogni valore di SNR , una lista di decisioni lunga quanto il numero di prove contenente la presenza (1) o
	 * l'assenza(0) dell'utente primario. Inquesto caso rappresenta un vettore di decisioni prodotto da un utente malevolo che riporta l'ooposto
	 * dell'energy detector: 0 se � presente l'utente, 1 se � assente.
	 * @param pfa Probabilit� di falso allarme
	 * @return Una lista di liste contenente per ogni SNR, una lista decisioni binarie Opporte sulla presenza o assenza dell'utente primario di cardinalit� pari al numero di prove
	 * @throws Exception **/

	public ArrayList<ArrayList<Integer>> computeOppositeBinaryDecisionVector(double pfa) throws Exception{
ArrayList<ArrayList<Integer>> decisions= new  ArrayList<ArrayList<Integer>>();
		
		ArrayList<ArrayList<Double>> VectorNoiseEnergy=SignalProcessor.computeVectorsEnergy(null, length, energy, attempts, inf, sup);	

		ArrayList<ArrayList<Double>> VectorSignalEnergy;
		if(s!=null){
			VectorSignalEnergy=SignalProcessor.computeVectorsEnergy(s, length, energy, attempts, inf, sup);
		}	
		else{VectorSignalEnergy=SignalProcessor.computeVectorsEnergy(null, length, energy, attempts, inf, sup);}
		//int snr=(inf-1);
		for (int i = 0; i < VectorSignalEnergy.size(); i++) {
			//System.out.println(inf-1);
			ArrayList<Integer> snrDecisions = Detector.oppositeBinaryDetector(
					SignalProcessor.computeEnergyDetectorThreshold(pfa, VectorNoiseEnergy.get(i)), VectorSignalEnergy.get(i));
			decisions.add(snrDecisions);
			
		}

		return decisions;
	
	}
	
	public ArrayList<ArrayList<Integer>> computeIntelligentBinaryDecisionVector(double pfa) throws Exception{
ArrayList<ArrayList<Integer>> decisions= new  ArrayList<ArrayList<Integer>>();
		
		ArrayList<ArrayList<Double>> VectorNoiseEnergy=SignalProcessor.computeVectorsEnergy(null, length, energy, attempts, inf, sup);	

		ArrayList<ArrayList<Double>> VectorSignalEnergy;
		if(s!=null){
			VectorSignalEnergy=SignalProcessor.computeVectorsEnergy(s, length, energy, attempts, inf, sup);
		}	
		else{VectorSignalEnergy=SignalProcessor.computeVectorsEnergy(null, length, energy, attempts, inf, sup);}
		//int snr=(inf-1);
		for (int i = 0; i < VectorSignalEnergy.size(); i++) {
			//System.out.println(inf-1);
			ArrayList<Integer> snrDecisions = Detector.intelligentMaliciousBinaryDetector(
					SignalProcessor.computeEnergyDetectorThreshold(pfa, VectorNoiseEnergy.get(i)), VectorSignalEnergy.get(i));
			decisions.add(snrDecisions);
			
		}

		return decisions;
	
	}
	
}
