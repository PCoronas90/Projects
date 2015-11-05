package it.sp4te.css.agents;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import it.sp4te.css.detection.Detector;
import it.sp4te.css.signalprocessing.Utils;

public class FusionCenter {

	//Mappa snr->lista di utenti che dichiarano la presenza del PU di cardinalit� pari ad ogni prova
	HashMap<Double,ArrayList<ArrayList<String>>>  snrToPresenceUsers;
	//Mappa snr->lista di utenti che dichiarano l'assenza del PU di cardinalit� pari ad ogni prova
	HashMap<Double,ArrayList<ArrayList<String>>> snrToAbsenceUsers;
	//Mappa idUtente->reputazione
	HashMap<String,Double> usersReliabilities;
	//soglia
	double threshold;

	public FusionCenter(){
		threshold=0.5;
		snrToPresenceUsers= new HashMap<Double,ArrayList<ArrayList<String>>>();
		snrToAbsenceUsers= new HashMap<Double,ArrayList<ArrayList<String>>>();
		usersReliabilities= new HashMap<String,Double>();
	}


	/**
	 * Questo metodo prende in input una mappa, contenente per ogni utente secondario (chiave) una lista di decisioni binarie calcolate
	 * per ogni SNR su un numero di prove P. Per ogni SNR prende le decisioni degli utenti secondari, li inserisce in un vettore e richiama il metodo
	 * di detection secondo la tecnica AND. Ritorner� la percentuale di Detection da parte Fusion Center
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
	 * di detection secondo la tecnica OR. Ritorner� la percentuale di Detection da parte Fusion Center
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
	 * di detection secondo la tecnica MAJORITY. Ritorner� la percentuale di Detection da parte Fusion Center
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



	/** Questo metodo prende in input, oltre agli estremi snr,  una Mappa che ha come chiave il nome dell'utente primario. Come valore ha una lista di liste: per ogni SNR ha una lista
	 * di lunghezza pari al numero di prove contenente la decisione binaria sulla presenza o assenza dell'utente primario da parte dell'utente secondario.
	 * Ritorna la % di Dectection calcolata utilizzando un meccanismo di reputazione per gli utenti secondari.
	 * @param inf Estremo inferiore SNR
	 * @param sup Estremo superiore SNR 
	 * @param userToBinaryDecision Mappa che ha come chiave il nome dell'uente primario. Come valore ha una lista di liste: per ogni SNR ha una lista
	 * di lunghezza pari al numero di prove contenente la decisione binaria sulla presenza o assenza dell'utente primario da parte dell'utente secondario
	 * @param attempts Numero di prove
	 * @return  Ritorna la % di Dectection calcolata utilizzando un meccanismo di reputazione per gli utenti secondari.

	 */

	public  ArrayList<Double> reputationBasedDecision(int inf,int sup,HashMap<String,ArrayList<ArrayList<Integer>>> userToBinaryDecision,int attempts){
		HashMap<Double,Double> reputationBasedDetection=new HashMap<Double,Double>();
		//Inizializzo le mappe SnrToPresenceUser e SnrToAbsenceUser. Queste mappe, per ogni snr, hanno una lista di utenti che affermano
		//la presenza o l'assenza dell'utente primario di cardinalit� pari al numero di prove
		createSnrToUsers(inf,sup,userToBinaryDecision,attempts);
		//Per ogni SNR 
		for(Double snr: this.snrToPresenceUsers.keySet()){
			ArrayList<Integer> globalDecisions= new ArrayList<Integer>();
			//Inizializzo la reputazione a 5
			
			inizializeReliabilities(userToBinaryDecision);
			for(int attempt=0;attempt<this.snrToPresenceUsers.get(snr).size();attempt++){
				//Per ogni prova mi creo una mappa UTENTE->DECISIONE dei soli utenti che partecipano alla comunicazione, e quindi
				//con reputazione >=1
				HashMap<String,Integer> binaryDecisions=computeUserToDecision(this.snrToPresenceUsers.get(snr).get(attempt),
						this.snrToAbsenceUsers.get(snr).get(attempt));
				//Mi calcolo la decisione globale
				Integer globalDecision=computeGlobalDecision(binaryDecisions);
				//Aggiungo alla decisione globale
				globalDecisions.add(globalDecision);
				//aggiorno la reputazione
				updateReliabilities(globalDecision,this.snrToPresenceUsers.get(snr).get(attempt),this.snrToAbsenceUsers.get(snr).get(attempt));
			   
			    	//for(String SU: usersReliabilities.keySet()){
			    	//	if(usersReliabilities.get(SU)<1){
			    	//		System.out.println("SNR: "+snr+" attempt: "+attempt+" User: "+SU);
			    	//	}
			    	//}
			    	
			    
			}
			//Per ogni snr, agigungo alla mappa l'array di decisioni globali di cardinalit� pari al numero di prove
			reputationBasedDetection.put(snr,Detector.reputationBasedDetection(globalDecisions));
		}
		System.out.println("---------------------------------------------------------------");
		//Ordino la detection
		return Utils.orderSignal(reputationBasedDetection);
		

	}



	/**Questo metodo Inizializza le mappe SnrToPresenceUser e SnrToAbsenceUser. Queste mappe, per ogni snr, hanno una lista di utenti che affermano
	la presenza o l'assenza dell'utente primario di cardinalit� pari al numero di prove
	 * @param inf Estremo SNR inferiore
	 * @param sup Estremo SNR superiore
	 * @param userToBinaryDecision Mappa che ha come chiave il nome dell'uente primario. Come valore ha una lista di liste: per ogni SNR ha una lista
	 * di lunghezza pari al numero di prove contenente la decisione binaria sulla presenza o assenza dell'utente primario da parte dell'utente secondario
	 * @param attempts Numero di prove
	 */
	
	public void createSnrToUsers(int inf,int sup,HashMap<String,ArrayList<ArrayList<Integer>>> userToBinaryDecision,int attempts){
		//Per ogni SNR
		for(int i=0;i<(sup-inf);i++){
			ArrayList<ArrayList<String>> listOfPresenceUser =new ArrayList<ArrayList<String>>();	
			ArrayList<ArrayList<String>> listOfAbsenceUser=new ArrayList<ArrayList<String>>();	
			//Per ogni prova
			for(int j=0;j<attempts;j++){
				ArrayList<String> presenceUser =new ArrayList<String>();	
				ArrayList<String> nonPresenceUser=new ArrayList<String>();	
				//Per ogni utente
				for(String SU: userToBinaryDecision.keySet()){
					if(userToBinaryDecision.get(SU).get(i).get(j)==0){
						nonPresenceUser.add(SU);}
					else{
						presenceUser.add(SU);}
				}
				listOfPresenceUser.add(presenceUser);
				listOfAbsenceUser.add(nonPresenceUser);
			}

			this.snrToPresenceUsers.put((double)(inf-sup)+i,listOfPresenceUser );
			this.snrToAbsenceUsers.put((double)(inf-sup)+i, listOfAbsenceUser);


		}
	}


	/** Inizializza la reputazione di tutti gli utenti presenti a 5
	 * @param userToBinaryDecision Mappa che ha come chiave il nome dell'uente primario. Come valore ha una lista di liste: per ogni SNR ha una lista
	 * di lunghezza pari al numero di prove contenente la decisione binaria sulla presenza o assenza dell'utente primario da parte dell'utente secondario
	 */
	
	public void inizializeReliabilities(HashMap<String,ArrayList<ArrayList<Integer>>> userToBinaryDecision){
		for(String SU: userToBinaryDecision.keySet()){
			this.usersReliabilities.put(SU, 5.0);
		}

	}


	/**Questo metodo ritorna una mappa contenente come chiave il nome dell'utente e come valore la decisione relativa alla
	 * presenza o assenza dell'utente primario. Considera solamente gli utenti che partecipano alla conversazione, ovvero
	 * utenti con reputazione maggiore o uguale ad 1
	 * @param presenceUsers Lista di utenti che affermano la presenza dell'utente primario
	 * @param absenceUsers Lista di utenti che affermano l'assenza dell'utente primario
	 * @return Una mappa utente->decisione
	 **/

	public HashMap<String,Integer> computeUserToDecision(ArrayList<String> presenceUsers,ArrayList<String> absenceUsers){
		HashMap<String,Integer> binaryDecisions= new HashMap<String,Integer>();
		for(int SU=0;SU<presenceUsers.size();SU++){
			double reliabilities = usersReliabilities.get(presenceUsers.get(SU));
			if(reliabilities>=1){
				binaryDecisions.put(presenceUsers.get(SU), 1);
			}}
		for(int SU2=0;SU2<absenceUsers.size();SU2++){
			double reliabilities = usersReliabilities.get(absenceUsers.get(SU2));
			if(reliabilities>=1){
				binaryDecisions.put(absenceUsers.get(SU2),0);
			}
		}

		return binaryDecisions;
	}



	/** Questo metodo calcola la decisione globale prendendo in input una mappa utente->decisione . Il calcolo della decisione
	 * globale si basa su un peso associato ad ogni utente che tiene conto della sua reputazione, dell'utente con la massima reputazione
	 * e delle reputazione di tutti gli altri utenti.
	 * @param binaryDecisions Mappa utente->Decisione
	 * @return La decisione globale basa sul peso di ogni utente
	 */

	public int computeGlobalDecision(HashMap<String,Integer> binaryDecisions){
		int globalDecision;
		double cont=0;
		//Calcolo la massima reputazione
		double maxReputation= getMaxReliability(binaryDecisions.keySet());
		//sommatoria w'(j), la somma dei pesi parziali di tutti gli utenti che partecipano alla comunicazione
		double totalPartialWeight= getPartialTotalWeigth(binaryDecisions.keySet(),maxReputation);
		//Per ogni utente		
		for(String SU: binaryDecisions.keySet()){
			//Calcolo il peso*decisione locale e lo sommo al precedente
			cont=cont+( binaryDecisions.get(SU)*computeWeight(SU,totalPartialWeight,maxReputation));
		}

		if(cont>=this.threshold){globalDecision=1;}
		else{globalDecision=0;}
		return globalDecision;
	}


	/** Questo metodo calcola il peso di ogni utente utilizzando come parametri la sommatoria dei pesi parziali,
	 * la massima reputazione tra gli utenti e la reputazione dell'utente considerato
	 * @param SU L'utente secondario
	 * @param totalPartialWeight Sommatoria dei pesi parziali
	 * @param maxReputation La massima reputazione tra gli utenti
	 * @return Il peso dell'utente passato come parametro
	 */

	public double computeWeight(String SU,double totalPartialWeight,double maxReputation){
		double weight= (usersReliabilities.get(SU)/maxReputation)/totalPartialWeight;		
		return weight;

	}


	/** Questo metodo calcola la sommatoria dei pesi parziali. Il peso parziale si calcola facendo la somamtoria
	 * dei rapporti tra la reputazione dell'utente e la massima reputazione tra tutti gli utenti
	 * @param listSU Lista degli utenti secondari che partecipano alla comunicazione
	 * @param maxReputation Massima Reputazione 
	 * @return La sommatoria dei pesi parziali degli utenti che partecipano alla counicazione
	 */

	public double getPartialTotalWeigth(Set<String> listSU,double maxReputation){
		double  totalPartialWeigth=0.0;
		for(String su: listSU){
			totalPartialWeigth= totalPartialWeigth+(usersReliabilities.get(su)/maxReputation); 
		}
		return totalPartialWeigth;
	}


	/** Questo metodo ritorna la massima reputazione tra gli utenti che partecipano alla comunicazione
	 * @param listSU Lista di utenti che partecipano alla comunicazione
	 * @return La massima reputazione
	 */

	public double getMaxReliability(Set<String> listSU){
		double maxReliabilities=0.0;
		for(String su: listSU){
			if(usersReliabilities.get(su)>maxReliabilities){
				maxReliabilities=usersReliabilities.get(su);
			}
		}
		return maxReliabilities;
	}


	/** Questo metodo aggiorna la reputazione di ogni utente, sia di quelli che partecipano alla comunicazione sia di
	 * quelli che non partecipano. Per fare ci�, si basa sulla decisione locale e la confronta con quella globale.
	 * Se coincidono, la reputazione viene incrementata di 1, altrimenti decrementata.
	 * @param globalDecision Decisione globale
	 * @param presenceSU Lista di utenti che affermano la presenza dell'utente primario
	 * @param absenceSU Lista di utenti che affermano l'assenza dell'utente primario
	 */

	public void updateReliabilities(int globalDecision,ArrayList<String>  presenceSU,ArrayList<String>  absenceSU){
		for(int i=0;i<presenceSU.size();i++){
			//Questo controllo fa si che una volta che un utente va sotto la soglia minima (1) non viene pi� considerato
			if(this.usersReliabilities.get(presenceSU.get(i))>=1){
			double newReliabilities=this.usersReliabilities.get(presenceSU.get(i))+ Math.pow(-1,(1+globalDecision));
			this.usersReliabilities.replace(presenceSU.get(i),newReliabilities);
		}
			}

		for(int i=0;i<absenceSU.size();i++){
			//Questo controllo fa si che una volta che un utente va sotto la soglia minima (1) non viene pi� considerato
			if(this.usersReliabilities.get(absenceSU.get(i))>=1){
			double newReliabilities=this.usersReliabilities.get(absenceSU.get(i))+ Math.pow(-1,(0+globalDecision));
			this.usersReliabilities.replace(absenceSU.get(i),newReliabilities);}
			
}
	}

}

