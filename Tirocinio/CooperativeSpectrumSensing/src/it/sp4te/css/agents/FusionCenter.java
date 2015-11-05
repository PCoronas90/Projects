
package it.sp4te.css.agents;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import it.sp4te.css.detection.Detector;

public class FusionCenter {

//Mappa snr->lista di utenti che dichiarano la presenza del PU di cardinalitÓ pari ad ogni prova
HashMap<Integer,ArrayList<ArrayList<String>>>  snrToPresenceUsers;
//Mappa snr->lista di utenti che dichiarano l'assenza del PU di cardinalitÓ pari ad ogni prova
HashMap<Integer,ArrayList<ArrayList<String>>> snrToAbsenceUsers;
//Mappa idUtente->reputazione
HashMap<String,Integer> userReputation;


  public FusionCenter(){
	 double threshold=0.5;
	 snrToPresenceUsers= new HashMap<Integer,ArrayList<ArrayList<String>>>();
	 snrToAbsenceUsers= new HashMap<Integer,ArrayList<ArrayList<String>>>();
	 userReputation= new HashMap<String,Integer>();
  }
	/**
	 * Questo metodo prende in input una mappa, contenente per ogni utente secondario (chiave) una lista di decisioni binarie calcolate
	 * per ogni SNR su un numero di prove P. Per ogni SNR prende le decisioni degli utenti secondari, li inserisce in un vettore e richiama il metodo
	 * di detection secondo la tecnica AND. RitornerÓ la percentuale di Detection da parte Fusion Center
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
	 * di detection secondo la tecnica OR. RitornerÓ la percentuale di Detection da parte Fusion Center
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
	 * di detection secondo la tecnica MAJORITY. RitornerÓ la percentuale di Detection da parte Fusion Center
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
	
	public  ArrayList<Double> reputationBasedDecision(int inf,int sup,HashMap<String,ArrayList<ArrayList<Integer>>> userToBinaryDecision,int attempts){
		ArrayList<Double> reputationBasedDetection=new ArrayList<Double>();
		//creo due mappe snr->lista di utenti che dichiarano la presenza/assenza del PU di cardinalitÓ pari ad ogni prova
		createSnrToUsers(inf,sup,userToBinaryDecision,attempts);
		for(Integer snr: this.snrToPresenceUsers.keySet()){
			ArrayList<Integer> globalDecisions= new ArrayList<Integer>();
			//Inizializzo la reputazione degli utenti
			inizializeReputation(userToBinaryDecision);
			for(int attempt=0;attempt<this.snrToPresenceUsers.get(snr).size();attempt++){
				HashMap<String,Integer> binaryDecisions=computeUserToDecision(this.snrToPresenceUsers.get(snr).get(attempt),
						this.snrToAbsenceUsers.get(snr).get(attempt));
			Integer globalDecision=computeGlobalDecision(binaryDecisions);
			globalDecisions.add(globalDecision);
			updateReputation(globalDecision,this.snrToPresenceUsers.get(snr).get(attempt),this.snrToAbsenceUsers.get(snr).get(attempt));
			}
			reputationBasedDetection.add(Detector.reputationBasedDetection(globalDecisions));
		}
		return reputationBasedDetection;
		
	}
	
	
	public void updateReputation(int globalDecision,ArrayList<String>  presenceSU,ArrayList<String>  absenceSU){
		for(int i=0;i<presenceSU.size();i++){
			int newReputation=this.userReputation.get(presenceSU.get(i))+((-1)^(1+globalDecision));
			this.userReputation.replace(presenceSU.get(i),newReputation);
		}
		
		for(int i=0;i<absenceSU.size();i++){
			int newReputation=this.userReputation.get(absenceSU.get(i))+((-1)^(0+globalDecision));
			this.userReputation.replace(absenceSU.get(i),newReputation);
		}
	}
	
	public void createSnrToUsers(int inf,int sup,HashMap<String,ArrayList<ArrayList<Integer>>> userToBinaryDecision,int attempts){
		for(int i=0;i<(sup-inf);i++){
			ArrayList<ArrayList<String>> listOfPresenceUser =new ArrayList<ArrayList<String>>();	
			ArrayList<ArrayList<String>> listOfAbsenceUser=new ArrayList<ArrayList<String>>();	
			for(int j=0;j<attempts;j++){
				ArrayList<String> presenceUser =new ArrayList<String>();	
				ArrayList<String> nonPresenceUser=new ArrayList<String>();	
			for(String SU: userToBinaryDecision.keySet()){
				if(userToBinaryDecision.get(SU).get(i).get(j)==0){
					nonPresenceUser.add(SU);}
				else{
					presenceUser.add(SU);}
			}
			listOfPresenceUser.add(presenceUser);
			listOfAbsenceUser.add(nonPresenceUser);
		}
		this.snrToPresenceUsers.put((sup-inf)+i,listOfPresenceUser )	;
		this.snrToAbsenceUsers.put((sup-inf)+i, listOfAbsenceUser);
		}
	}
	
	public void inizializeReputation(HashMap<String,ArrayList<ArrayList<Integer>>> userToBinaryDecision){
		for(String SU: userToBinaryDecision.keySet()){
			this.userReputation.put(SU, 5);
		}
		
	}
	
	/**Ritorna una mappa utente->Decisione**/
	public HashMap<String,Integer> computeUserToDecision(ArrayList<String> presenceUsers,ArrayList<String> absenceUsers){
		HashMap<String,Integer> binaryDecisions= new HashMap<String,Integer>();
		for(int SU=0;SU<presenceUsers.size();SU++){
			double reputation = userReputation.get(presenceUsers.get(SU));
			if(reputation>=1){
				binaryDecisions.put(presenceUsers.get(SU), 1);
			}}
		for(int SU2=0;SU2<absenceUsers.size();SU2++){
			double reputation = userReputation.get(absenceUsers.get(SU2));
			if(reputation>=1){
				binaryDecisions.put(absenceUsers.get(SU2),0);
				}
		}
		return binaryDecisions;
	}
	
	public int computeGlobalDecision(HashMap<String,Integer> binaryDecisions){
		int globalDecision;
		double cont=0;
		int maxReputation= getMaxReputation(binaryDecisions.keySet());
		//sommatoria w'(j)
		double totalPartialWeight= getPartialTotalWeigth(binaryDecisions.keySet(),maxReputation);
		for(String SU: binaryDecisions.keySet()){
			cont=cont+( binaryDecisions.get(SU)*computeWeight(SU,totalPartialWeight,maxReputation));
		}
		
		if(cont>=0.5){globalDecision=1;}
		else{globalDecision=0;}
		return globalDecision;
	}
	
	public double computeWeight(String SU,double totalPartialWeight,int maxReputation){
		double weight= (userReputation.get(SU)/maxReputation)/totalPartialWeight;
		return weight;
		
	}
	
	public double getPartialTotalWeigth(Set<String> listSU,int maxReputation){
		int  totalPartialWeigth=0;
		for(String su: listSU){
			 totalPartialWeigth= totalPartialWeigth+(userReputation.get(su)/maxReputation); 
		}
		return totalPartialWeigth;
	}
	public int getMaxReputation(Set<String> listSU){
		int maxReputation=0;
		for(String su: listSU){
			if(userReputation.get(su)>maxReputation){
				maxReputation=userReputation.get(su);
			}
		}
		return maxReputation;
	}

}

