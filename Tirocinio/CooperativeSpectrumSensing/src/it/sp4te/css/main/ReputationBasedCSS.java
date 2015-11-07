package it.sp4te.css.main;

import java.util.ArrayList;
import java.util.HashMap;

import it.sp4te.css.agents.FusionCenter;
import it.sp4te.css.agents.PrimaryUser;
import it.sp4te.css.agents.TrustedSecondaryUser;
import it.sp4te.css.graphgenerator.GraphGenerator;
import it.sp4te.css.model.Signal;
import it.sp4te.css.signalprocessing.SignalProcessor;
import it.sp4te.css.signalprocessing.Utils;


/**Questa classe modella uno scenario cooperativo ideale in cui sono presenti solamente utenti secondari fidati,
 * utilizzando la tecnica basata sulla reputazione degli utenti**/

public class ReputationBasedCSS {

	public static void main(String args[]) throws Exception {
		ArrayList<Double> reputationBasedCSS = new ArrayList<Double>();;

		HashMap<String,ArrayList<ArrayList<Integer>>> userToBinaryDecision=new HashMap<String,ArrayList<ArrayList<Integer>>>();
		ArrayList<TrustedSecondaryUser> TrustedSecondaryUsers;
		HashMap<String, ArrayList<Double>> DetectionGraph = new HashMap<String, ArrayList<Double>>();

		// Setto i parametri
		int length = 1000; // poi 10000
		int attempts = 1000;
		int inf = -30;
		int sup = 5;
		int block=10; //blocchi energy Detector
		double pfa=0.01; //probabilitÓ di falso allarme
		int numberTSU=10;//numero di utenti fidati

		//Creo il Fusion center
		FusionCenter FC=new FusionCenter();
		//Creo l'utente primario
		PrimaryUser PU= new PrimaryUser();
		//creo il segnale
		Signal s = PU.createAndSend(length);


		//Creo gli utenti secondari
		TrustedSecondaryUsers= Utils.createTrustedSecondaryUsers(numberTSU,s,s.getLenght(), SignalProcessor.computeEnergy(s), attempts, inf, sup, block);

		//Creo i vettori contenenti le decisioni binarie sulla presenza o assenza dell'utente primario.Le inserisco in una
		//mappa
		userToBinaryDecision=Utils.genereteBinaryDecisionVectors(TrustedSecondaryUsers, pfa);

		//Tutte le decisioni di tutti gli utenti secondari passano al fusion center che riporterÓ una decisione
		//globale secondo un meccanismo di reputazione
		reputationBasedCSS= FC.reputationBasedDecision(inf, sup, userToBinaryDecision, attempts);
		DetectionGraph.put("RB CSS", reputationBasedCSS);

		GraphGenerator.drawGraph("Reputation Based CSS: Presence of PU",DetectionGraph, inf, sup);

		
		//-----------------------------------------------------------
		TrustedSecondaryUsers.clear();
		userToBinaryDecision.clear();
		DetectionGraph.clear();

		//Creo gli utenti secondari
		TrustedSecondaryUsers= Utils.createTrustedSecondaryUsers(numberTSU,null,s.getLenght(), SignalProcessor.computeEnergy(s), attempts, inf, sup, block);

		//Creo i vettori contenenti le decisioni binarie sulla presenza o assenza dell'utente primario.Le inserisco in una
		//mappa
		userToBinaryDecision=Utils.genereteBinaryDecisionVectors(TrustedSecondaryUsers, pfa);

		//Tutte le decisioni di tutti gli utenti secondari passano al fusion center che riporterÓ una decisione
		//globale secondo un meccanismo di reputazione
		reputationBasedCSS= FC.reputationBasedDecision(inf, sup, userToBinaryDecision, attempts);
		DetectionGraph.put("RB CSS", reputationBasedCSS);

		GraphGenerator.drawGraph("Reputation Based CSS: Absence of PU",DetectionGraph, inf, sup);

	}}
