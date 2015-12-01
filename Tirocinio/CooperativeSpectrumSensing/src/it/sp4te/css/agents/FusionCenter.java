package it.sp4te.css.agents;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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
	//Mappa idUtente->array di informazioni (lista,hitsConsecutivi,Errori)
	HashMap<String,Reputation> usersToInfo;
	//Valori per il passaggio tra liste
	int K,L,M,N;
	//Mappa utente->volte che � stato in lista nera
	//HashMap<String,Integer> blackListCount;


	public FusionCenter(){
		snrToPresenceUsers= new HashMap<Double,ArrayList<ArrayList<String>>>();
		snrToAbsenceUsers= new HashMap<Double,ArrayList<ArrayList<String>>>();
		usersReliabilities= new HashMap<String,Double>();
	
		usersToInfo= new 	HashMap<String,Reputation>();
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
	 * Ritorna la % di Dectection calcolata utilizzando un metodo basato sulla divisione in liste degli utenti che tiene conto delle volte che sbagliano e delle volte
	 * che concordano con la decisione globale in modo consecutivo
	 * @param inf Estremo inferiore SNR
	 * @param sup Estremo superiore SNR 
	 * @param userToBinaryDecision Mappa che ha come chiave il nome dell'uente primario. Come valore ha una lista di liste: per ogni SNR ha una lista
	 * di lunghezza pari al numero di prove contenente la decisione binaria sulla presenza o assenza dell'utente primario da parte dell'utente secondario
	 * @param attempts Numero di prove
	 * @param K numero di hits consecutivi necessario per il passaggio da lista grigia->lista bianca
	 * @param L numero di hits consecutivi necessario per il passaggio da lista nera->lista grigia
	 * @param M numero di errori necessario per il passaggio da lista bianca->lista grigia
	 * @param N numero di errori necessario per il passaggio da lista grigia->lista nera
	 * @return  Ritorna la % di Dectection calcolata utilizzando un metodo basato sulla divisione in liste degli utenti che tiene conto delle volte che sbagliano e delle volte
	 * che concordano con la decisione globale in modo consecutivo.
	 * @throws IOException 

	 */
	
	public  ArrayList<Double> ListBasedDecision(int inf,int sup,HashMap<String,ArrayList<ArrayList<Integer>>> userToBinaryDecision,int attempts,
			int K,int L,int M,int N,String type) throws IOException{
		this.K=K; 
		this.L=L;
		this.M=M;
		this.N=N;
		FileWriter w=new FileWriter("C:/Users/Pietro/Desktop/Output/"+K+L+M+N+"_"+type+".txt");
		 BufferedWriter b=new BufferedWriter(w);
		HashMap<Double,Double> listBasedDetection=new HashMap<Double,Double>();
		createSnrToUsers(inf,sup,userToBinaryDecision,attempts);
		for(Double snr: this.snrToPresenceUsers.keySet()){
			b.write(" ");
			b.write("------------------- SNR="+snr+" -------------------------"+" \n");
			inizializeValue(userToBinaryDecision);
			ArrayList<Integer> globalDecisions= new ArrayList<Integer>();
			for(int attempt=0;attempt<this.snrToPresenceUsers.get(snr).size();attempt++){
				b.write("------------SNR="+snr+" Prova="+attempt+"-----------"+" \n");
				for(String SU: this.usersToInfo.keySet()){
					b.write(SU+" list: "+ this.usersToInfo.get(SU).getFlag()+" ConsecutiveHits: "+
							 this.usersToInfo.get(SU).getConsecutiveHits()+" ErrorCount: "+ this.usersToInfo.get(SU).getErrorCount() +" \n ");
				}
				HashMap<String,Integer> binaryDecisionsWhite=computeUserToDecisionWhite(this.snrToPresenceUsers.get(snr).get(attempt),
						this.snrToAbsenceUsers.get(snr).get(attempt));
				HashMap<String,Integer> binaryDecisionsGrey=computeUserToDecisionGrey(this.snrToPresenceUsers.get(snr).get(attempt),
						this.snrToAbsenceUsers.get(snr).get(attempt));
				
				Integer globalDecision=computeGlobalDecision(binaryDecisionsWhite,binaryDecisionsGrey);
				globalDecisions.add(globalDecision);
				updateValue(globalDecision,this.snrToPresenceUsers.get(snr).get(attempt),
						this.snrToAbsenceUsers.get(snr).get(attempt));

			}
			double detection=Detector.reputationBasedDetection(globalDecisions);
			listBasedDetection.put(snr,detection);
		}
		b.close();
		return Utils.orderSignal(listBasedDetection);
	}
		
	

	/** QUesto metodo inizializza la mappa userToINfo, inserendo come chiave l'identificativo dell'utente secondario, e come valore un 
	 * array di 3 elementi contenente gli hits consecutivi, gli errori e la lista di appartenenza. Tutti questo valori vengono 
	 * inizialmente posti a 0 (0 hits,0 errori e lista bianca)
	 * @param userToBinaryDecision Mappa che ha come chiave il nome dell'uente primario. Come valore ha una lista di liste: per ogni SNR ha una lista
	 * di lunghezza pari al numero di prove contenente la decisione binaria sulla presenza o assenza dell'utente primario da parte dell'utente secondario
	  
	 * **/
	public void inizializeValue(HashMap<String,ArrayList<ArrayList<Integer>>> userToBinaryDecision){
		this.usersToInfo.clear();
		
		for(String SU: userToBinaryDecision.keySet()){
		 this.usersToInfo.put(SU, new Reputation());		 
		}
	}
	
	/** Questo metodo ritorna una mappa contenente come chiave l'utente secondario appartenente alla lista bianca e come valore
	 * la decisione binaria relativa alla presenza o assenza dell'utente primario
	 * @param presenceUsers lista di utenti che affermano la presenza dell'utente primario
	 * @param absenceUsers Lista di utenti che affermano l'assenza dell'utente primario
	 * @return Una mappa contenente come chiave l'utente secondario appartenente alla lista bianca e come valore la decisione binaria
	 * relativa alla presenza o assenza dell'utente primario
	 */
	public HashMap<String,Integer> computeUserToDecisionWhite(ArrayList<String> presenceUsers,ArrayList<String> absenceUsers){
		HashMap<String,Integer> binaryDecisionsWhite= new HashMap<String,Integer>();
		for(String SU:presenceUsers){
			int flag=this.usersToInfo.get(SU).getFlag();
			if(flag==0){
				binaryDecisionsWhite.put(SU, 1);
			}}
		for(String SU2:absenceUsers){
			int flag=this.usersToInfo.get(SU2).getFlag();
			if(flag==0){
				binaryDecisionsWhite.put(SU2,0);
			}
		}

		return binaryDecisionsWhite;
	}
	
	/** Questo metodo ritorna una mappa contenente come chiave l'utente secondario appartenente alla lista grigia e come valore
	 * la decisione binaria relativa alla presenza o assenza dell'utente primario
	 * @param presenceUsers lista di utenti che affermano la presenza dell'utente primario
	 * @param absenceUsers Lista di utenti che affermano l'assenza dell'utente primario
	 * @return Una mappa contenente come chiave l'utente secondario appartenente alla lista grigia e come valore la decisione binaria
	 * relativa alla presenza o assenza dell'utente primario
	 */
	
	public HashMap<String,Integer> computeUserToDecisionGrey(ArrayList<String> presenceUsers,ArrayList<String> absenceUsers){
		HashMap<String,Integer> binaryDecisionsWhite= new HashMap<String,Integer>();
		for(String SU:presenceUsers){
			int flag=this.usersToInfo.get(SU).getFlag();
			if(flag==1){
				binaryDecisionsWhite.put(SU, 1);
			}}
		for(String SU2:absenceUsers){
			int flag=this.usersToInfo.get(SU2).getFlag();
			if(flag==1){
				binaryDecisionsWhite.put(SU2,0);
			}
		}

		return binaryDecisionsWhite;
	}


	/** Questo metodo aggiorna la mappa usersToInfo. In particolare aggiorna gli hits consecutivi, gli errori e si occupa del cambio di lista
	 * degli utenti in base ai valori KLMN.
	 * Se un utente ha un numero di hits consecutivi maggiore o uguale a K e si trova in lista grigia->va in lista bianca
	 * Se un utente ha un numero di hits consecutivi maggiore o uguale a L e si trova in lista nera->va in lista grigia
	 * Se un utente ha un numero di errori maggiore o uguale a M e si trova in lista bianca-> va in lista grigia
	 * Se un utente ha un numero di errori maggiore o uguale a N e si trova in lista grigia->va in lista nera
	 * @param globalDecision decisione globale
	 * @param presenceSU lista di utenti che affermano la presenza dell'utente primario
	 * @param absenceSU lista di utenti che affermano l'assenza dell'utente primario
	 */
	public void updateValue(int globalDecision,ArrayList<String>  presenceSU,ArrayList<String>  absenceSU){
		if(globalDecision==1){
		 for(int i=0;i<presenceSU.size();i++){
			 Reputation reputation=this.usersToInfo.get(presenceSU.get(i));
			 //Incremento gli hits consecutivi
			 reputation.incrementConsecutiveHits();
			 //se gli hits superano K e si trova in lista grigia
			 if(reputation.getConsecutiveHits()>=this.K & reputation.getFlag()==1){
				 reputation.setFlag(0);
				
				
			 }
			 //se gli hits superano L e si trova in lista nera
			 else if(reputation.getConsecutiveHits()>=this.L & reputation.getFlag()==2){
				 //passa in lista grigia
				 reputation.setFlag(1);
				 
				
			 }
		 }
		 for(int j=0;j<absenceSU.size();j++){
			 Reputation reputation=this.usersToInfo.get(absenceSU.get(j));
			 //incremento gli errori
			reputation.incrementErrorCount();
		
			
			
			 //se gli ERRORI superano M e si trova in lista bianca
			 if(reputation.getErrorCount()>=this.M & reputation.getFlag()==0){
				 //passa in lista grigia
				 reputation.setFlag(1);
				
				
			 }
			 //se gli errori superano L e si trova in lista grigia
			 else if(reputation.getErrorCount()>=this.N & reputation.getFlag()==1){
	              reputation.setFlag(2);;
	             

			 }
			
		 }
		}
		else if(globalDecision==0){
			for(int i=0;i<presenceSU.size();i++){
				 Reputation reputation=this.usersToInfo.get(presenceSU.get(i));
				 //incremento gli errori
				 reputation.incrementErrorCount();
					

				
				 //se gli ERRORI superano M e si trova in lista bianca
				 if(reputation.getErrorCount()>=this.M & reputation.getFlag()==0){
					reputation.setFlag(1);
					
				 }
				 //se gli errori superano L e si trova in lista grigia
				 else if(reputation.getErrorCount()>=this.N & reputation.getFlag()==1){
					reputation.setFlag(2);
					
				 }
			 }
			
			for(int j=0;j<absenceSU.size();j++){
				
				 Reputation reputation=this.usersToInfo.get(absenceSU.get(j));
				 //Incremento gli hits consecutivi
				 reputation.incrementConsecutiveHits();
			
				 //se gli hits superano K e si trova in lista grigia
				 if(reputation.getConsecutiveHits()>=this.K & reputation.getFlag()==1){
					 reputation.setFlag(0);;
					
				 }
				 //se gli hits superano L e si trova in lista nera
				 else if(reputation.getConsecutiveHits()>=this.L & reputation.getFlag()==2){
					 reputation.setFlag(1);
					
				 
			}
		}
		}
	}
	
	
	
	/** Questo metodo prende in input le decisioni binarie degli utenti presenti in lista grigia e lista bianca e ritorna
	 * una decisione globale sulla presenza o assenza dell'utente primario.
	 * @param binaryDecisionsWhite Mappa contenente le decisioni binarie degli utenti appartenenti alla lista bianca
	 * @param binaryDecisionsGrey Mappa contenente le decisioni binarie degli utenti appartenenti alla lsita grigia
	 * @return la decisione globale
	 */
	public int computeGlobalDecision(HashMap<String,Integer> binaryDecisionsWhite,HashMap<String,Integer> binaryDecisionsGrey){
		ArrayList<Integer> binaryDecisionAllList=new ArrayList<Integer>();
		if(binaryDecisionsWhite.size()!=0){
		binaryDecisionAllList.addAll(binaryDecisionsWhite.values());}
		if(binaryDecisionsGrey.size()!=0){
			ArrayList<Integer> greyDecisions= new ArrayList<Integer>();
			for(String SU: binaryDecisionsGrey.keySet() ){
				greyDecisions.add( binaryDecisionsGrey.get(SU));
			}
		binaryDecisionAllList.add(Utils.getMediumDecision(greyDecisions));
		}
		return Detector.majorityFusionRule(binaryDecisionAllList);
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
			//Inizializzo la reputazione a 5 ad ogni cambio di SNR
			inizializeReliabilities(userToBinaryDecision);
			ArrayList<Integer> globalDecisions= new ArrayList<Integer>();
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
				updateReliabilities(globalDecision,this.snrToPresenceUsers.get(snr).get(attempt),
						this.snrToAbsenceUsers.get(snr).get(attempt),snr);

				
			    	
			    
			}
			//Per ogni snr, aggiungo alla mappa l'array di decisioni globali di cardinalit� pari al numero di prove
			double detection=Detector.reputationBasedDetection(globalDecisions);
			reputationBasedDetection.put(snr,detection);
		}
		
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

			this.snrToPresenceUsers.put((double)inf+i,listOfPresenceUser );
			this.snrToAbsenceUsers.put((double)inf+i, listOfAbsenceUser);


		}
	}


	/** Inizializza la reputazione di tutti gli utenti presenti a 5
	 * @param userToBinaryDecision Mappa che ha come chiave il nome dell'uente primario. Come valore ha una lista di liste: per ogni SNR ha una lista
	 * di lunghezza pari al numero di prove contenente la decisione binaria sulla presenza o assenza dell'utente primario da parte dell'utente secondario
	 */
	
	public void inizializeReliabilities(HashMap<String,ArrayList<ArrayList<Integer>>> userToBinaryDecision){
		this.usersReliabilities.clear();
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
		
		//System.out.println(this.threshold);
		//Per ogni utente	
		for(String SU: binaryDecisions.keySet()){
			//Calcolo il peso*decisione locale e lo sommo al precedente
			//System.out.println(SU+" Reputation: "+usersReliabilities.get(SU)+" Decision: "+binaryDecisions.get(SU));
			cont=cont+( binaryDecisions.get(SU)*computeWeight(SU,totalPartialWeight,maxReputation));
		}
       // System.out.println("--------------------");
		if(cont>=0.5){globalDecision=1;}
		else{globalDecision=0;}
		return globalDecision;
	}

	
	/**Metodo per il calcolo della soglia nel metodo basato su reputazione
	
	 * @param binaryDecisions Mappa contenente per ogni utente, la decisione relativa alla presenza o assenza dell'utente primario
	 * ad un dato SNR
	 * @param maxReputation La massima reputazione 
	 * @param totalPartialWeight La somma dei pesi parziali
	 */
	
	public double computeThreshold(HashMap<String,Integer> binaryDecisions,double maxReputation,double totalPartialWeight){
		double threshold=0;
		double minWeight=1;
		for(String SU: binaryDecisions.keySet()){
			double weight=computeWeight(SU,totalPartialWeight,maxReputation);
			threshold=threshold+weight;
			if(weight<minWeight){minWeight=weight;}
			
		}
		return (threshold/2)-minWeight;
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

	public void updateReliabilities(int globalDecision,ArrayList<String>  presenceSU,ArrayList<String>  absenceSU,double snr){
		for(int i=0;i<presenceSU.size();i++){
			//Questo controllo fa si che una volta che un utente va sotto la soglia minima (1) non viene pi� considerato
			if(this.usersReliabilities.get(presenceSU.get(i))>=1){
			double newReliabilities=this.usersReliabilities.get(presenceSU.get(i))+ Math.pow(-1,(1+globalDecision));
			this.usersReliabilities.replace(presenceSU.get(i),newReliabilities);
		}
			
				
					
					
			else{
				ArrayList<String> MSU= new ArrayList<String>();
			MSU.add(presenceSU.get(i));
			}
				
			//System.out.println
				//("Malicious user: "+ presenceSU.get(i)+" Snr: "+ snr + " Attempt: " + attempt);
			}

		for(int i=0;i<absenceSU.size();i++){
			//Questo controllo fa si che una volta che un utente va sotto la soglia minima (1) non viene pi� considerato
			if(this.usersReliabilities.get(absenceSU.get(i))>=1){
			double newReliabilities=this.usersReliabilities.get(absenceSU.get(i))+ Math.pow(-1,(0+globalDecision));
			this.usersReliabilities.replace(absenceSU.get(i),newReliabilities);}
			
				else{ArrayList<String> MSU= new ArrayList<String>();
				MSU.add(absenceSU.get(i));
				}
			
}
		}}



