package it.sp4te.css.main;

import java.util.ArrayList;
import java.util.HashMap;

import it.sp4te.css.agents.FusionCenter;
import it.sp4te.css.agents.MaliciousSecondaryUser;
import it.sp4te.css.agents.PrimaryUser;
import it.sp4te.css.agents.TrustedSecondaryUser;
import it.sp4te.css.graphgenerator.GraphGenerator;
import it.sp4te.css.model.Signal;
import it.sp4te.css.signalprocessing.SignalProcessor;
import it.sp4te.css.signalprocessing.Utils;

public class ListCSS {

	
	public static void main(String args[]) throws Exception {
		ArrayList<TrustedSecondaryUser> TrustedSecondaryUsers;
		ArrayList<MaliciousSecondaryUser> MaliciousSecondaryUsers;

		HashMap<String,ArrayList<ArrayList<Integer>>> userToBinaryDecision=new HashMap<String,ArrayList<ArrayList<Integer>>>();
		HashMap<String,ArrayList<ArrayList<Integer>>> userToBinaryDecisionAbsence=new HashMap<String,ArrayList<ArrayList<Integer>>>();
		HashMap<String,ArrayList<ArrayList<Integer>>> userToBinaryDecisionOpposite=new HashMap<String,ArrayList<ArrayList<Integer>>>();
		HashMap<String,ArrayList<ArrayList<Integer>>> userToBinaryDecisionIntelligent=new HashMap<String,ArrayList<ArrayList<Integer>>>();

		
		ArrayList<Double> ListCooperativeEnergyDetection = new ArrayList<Double>();;
		ArrayList<Double> ListCooperativeEnergyDetectionAbsence = new ArrayList<Double>();;
		ArrayList<Double> ListCooperativeEnergyDetectionOpposite = new ArrayList<Double>();;
		ArrayList<Double> ListCooperativeEnergyDetectionIntelligent = new ArrayList<Double>();;
        HashMap<String, ArrayList<Double>> DetectionGraph = new HashMap<String, ArrayList<Double>>();


		// Setto i parametri
		int length = 1000; // poi 10000
		int attempts = 800;
		int inf = -13;
		int sup = -12;
		int block=10; //blocchi energy Detector
		double pfa=0.01; //probabilit� di falso allarme
		int numberTSU=20;
		int numberMSU=9;//numero di utenti fidati
		int K=2; //GRIGIA->BIANCA
		int L=2;// NERA->BIANCA
		int M=2; //BIANCA->GRIGIA
		int N=2;//GRIGIA->NERA

		//Creo il Fusion center
		FusionCenter FC=new FusionCenter();
		//Creo l'utente primario
		PrimaryUser PU= new PrimaryUser();
		//creo il segnale
		Signal s = PU.createAndSend(length);

		/**
		//Creo gli utenti secondari
		TrustedSecondaryUsers= Utils.createTrustedSecondaryUsers(numberTSU,s,s.getLenght(), SignalProcessor.computeEnergy(s), attempts, inf, sup, block);

		//Creo i vettori contenenti le decisioni binarie sulla presenza o assenza dell'utente primario.Le inserisco in una
		//mappa
		userToBinaryDecision=Utils.genereteBinaryDecisionVectors(TrustedSecondaryUsers, pfa);
		ListCooperativeEnergyDetection= FC.ListBasedDecision(inf, sup, userToBinaryDecision, attempts, K, L, M, N,"null");
		DetectionGraph.put("Without malicious users", ListCooperativeEnergyDetection);
		
		TrustedSecondaryUsers.clear();
		
		
		TrustedSecondaryUsers= Utils.createTrustedSecondaryUsers(numberTSU,s,s.getLenght(), SignalProcessor.computeEnergy(s), attempts, inf, sup, block);
		MaliciousSecondaryUsers=Utils.createMaliciousSecondaryUsers(numberMSU,s,s.getLenght(), SignalProcessor.computeEnergy(s), attempts, inf, sup, block);
		
		userToBinaryDecisionAbsence=Utils.genereteBinaryDecisionVectors(TrustedSecondaryUsers, pfa);
		//Gli utenti malevoli di questa tipologia generano un vettore di decisioni in cui l'utente primario � sempre assente
		userToBinaryDecisionAbsence.putAll(Utils.genereteAbsenceBinaryDecisionVectors(MaliciousSecondaryUsers));
		ListCooperativeEnergyDetectionAbsence= FC.ListBasedDecision(inf, sup, userToBinaryDecisionAbsence, attempts, K, L, M, N,"Absence");

		DetectionGraph.put("With Absence MSU", ListCooperativeEnergyDetectionAbsence);
		
		TrustedSecondaryUsers.clear();
		MaliciousSecondaryUsers.clear();
		
		TrustedSecondaryUsers= Utils.createTrustedSecondaryUsers(numberTSU,s,s.getLenght(), SignalProcessor.computeEnergy(s), attempts, inf, sup, block);
		MaliciousSecondaryUsers=Utils.createMaliciousSecondaryUsers(numberMSU,s,s.getLenght(), SignalProcessor.computeEnergy(s), attempts, inf, sup, block);
		
		userToBinaryDecisionOpposite=Utils.genereteBinaryDecisionVectors(TrustedSecondaryUsers, pfa);
		//Gli utenti malevoli di questa tipologia generano un vettore di decisioni in cui l'utente primario � sempre assente
		userToBinaryDecisionOpposite.putAll(Utils.genereteOppositeBinaryDecisionVectors(MaliciousSecondaryUsers,pfa));
		ListCooperativeEnergyDetectionOpposite= FC.ListBasedDecision(inf, sup, userToBinaryDecisionOpposite, attempts, K, L, M, N,"Opposite");

		DetectionGraph.put("With Opposite MSU", ListCooperativeEnergyDetectionOpposite);
		
		TrustedSecondaryUsers.clear();
		MaliciousSecondaryUsers.clear();
		
		TrustedSecondaryUsers= Utils.createTrustedSecondaryUsers(numberTSU,s,s.getLenght(), SignalProcessor.computeEnergy(s), attempts, inf, sup, block);
		MaliciousSecondaryUsers=Utils.createMaliciousSecondaryUsers(numberMSU,s,s.getLenght(), SignalProcessor.computeEnergy(s), attempts, inf, sup, block);
		
		userToBinaryDecisionIntelligent=Utils.genereteBinaryDecisionVectors(TrustedSecondaryUsers, pfa);
		//Gli utenti malevoli di questa tipologia generano un vettore di decisioni in cui l'utente primario � sempre assente
		userToBinaryDecisionIntelligent.putAll(Utils.genereteIntelligentMaliciousBinaryDecisionVectors(MaliciousSecondaryUsers,pfa));
		ListCooperativeEnergyDetectionIntelligent= FC.ListBasedDecision(inf, sup, userToBinaryDecisionIntelligent, attempts, K, L, M, N,"Intelligent");

		DetectionGraph.put("With Intelligent MSU", ListCooperativeEnergyDetectionIntelligent);

		
		GraphGenerator.drawGraph("Presence of PU in List Cooperative Energy Detection.K=L=2,M=N=4 AND 30% MSU ",DetectionGraph, inf, sup);
		**/
		for(int h=0;h<4;h++){
			inf=inf+h;
			sup=sup+h;
		for(int i=0;i<45;i++){
		 numberTSU=50-i;
		 numberMSU=0+i;
		 System.out.println(numberTSU+"        "+ numberMSU);
		 TrustedSecondaryUsers= Utils.createTrustedSecondaryUsers(numberTSU,s,s.getLenght(), SignalProcessor.computeEnergy(s), attempts, inf, sup, block);
		 MaliciousSecondaryUsers=Utils.createMaliciousSecondaryUsers(numberMSU,s,s.getLenght(), SignalProcessor.computeEnergy(s), attempts, inf, sup, block);
		 userToBinaryDecision=Utils.genereteBinaryDecisionVectors(TrustedSecondaryUsers, pfa);
		 userToBinaryDecision.putAll(Utils.genereteAbsenceBinaryDecisionVectors(MaliciousSecondaryUsers));
		 ListCooperativeEnergyDetection.add(FC.ListBasedDecision(inf, sup, userToBinaryDecisionAbsence, attempts, K, L, M, N,"Absence_-11Db").get(0));
        

		}
	DetectionGraph.put("List CSS: Absence MSU at+"+inf+"Db",  ListCooperativeEnergyDetection);
	
GraphGenerator.drawMaliciousUsersToDetectionGraph("List CSS:K=L=M=N=2",DetectionGraph, inf, sup);

	}}
}
