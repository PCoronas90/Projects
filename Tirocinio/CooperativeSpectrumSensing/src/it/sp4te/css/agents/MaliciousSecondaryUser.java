package it.sp4te.css.agents;

import java.util.ArrayList;

import it.sp4te.css.signalprocessing.SignalProcessor;

public class MaliciousSecondaryUser extends SecondaryUser {

	
	/**Questo metodo ritorna, per ogni valore di SNR , una lista di decisioni lunga quanto il numero di prove contenente l'assenza dell'utente
	 * primario per ogni misurazione. E' utilizzato per la creazione del vettore di decisione dell'utente malevolo
	 * @param attempts Numero di prove su cui effettuare la simulazione
	 * @param inf Estremo inferiore di SNR su cui effettuare la simulazione
	 * @param sup Estremo superiore di SNR su cui effettuare la simulazione 
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
		for(int i=inf;i<sup;i++){
			
			ArrayList<ArrayList<Double>> VectorNoiseEnergy=SignalProcessor.computeVectorEnergy(null, length, energy, attempts, i, i+1);	
			double threshold=SignalProcessor.computeEnergyDetectorThreshold(pfa, VectorNoiseEnergy.get(0));
			
			ArrayList<Integer> snrDecisions= new  ArrayList<Integer>();
			ArrayList<ArrayList<Double>> EnergyVector;
			
			if(s!=null){
			EnergyVector=SignalProcessor.computeVectorEnergy(s, length,energy,attempts, i,i+1);		}	
			else{
				EnergyVector=VectorNoiseEnergy;
			}
			for(int j=0;j<EnergyVector.get(0).size();j++){
				if(EnergyVector.get(0).get(j)>threshold){
					snrDecisions.add(0);
				}
				else{snrDecisions.add(1);}
				
			}
			decisions.add(snrDecisions);
		
			
		}
		return decisions;	
		}
}
