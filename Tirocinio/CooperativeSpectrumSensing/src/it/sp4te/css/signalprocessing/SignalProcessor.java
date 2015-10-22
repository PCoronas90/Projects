package it.sp4te.css.signalprocessing;

import java.util.ArrayList;

import it.sp4te.css.model.AbstractSignal;
import it.sp4te.css.model.Noise;
import it.sp4te.css.model.Signal;

/**
 * <p>Titolo:SignalFunctions</p>
 * <p>Descrizione: Classe che contiene Funzioni utili da applicare sul segnale: -Calcolo
 * dell'energia -Generazione Momenti -Generazione PR -Calcolo dell'energia dei
 * momenti</p>
 * @author Pietro Coronas
 **/

public class SignalProcessor {

	/** 
	 * Metodo per il calcolo dell'energia del segnale 
	 * 
	 * @param s Segnale su cui calcolare l'energia
	 * @return energia del segnale **/
	
	public static double computeEnergy(AbstractSignal s) {
		double p = 0.0;
		for (int i = 0; i < s.getSamplesIm().size(); i++) {
			p = p + Math.abs(Math.pow(s.getSamplesRe().get(i), 2)) + Math.abs((Math.pow(s.getSamplesIm().get(i), 2)));
		}
		return p / s.getLenght();
	}

	/** 
	 * Metodo per la generazione dei momenti del secondo e quarto ordine.
	 * 
	 * @param s Il segnale
	 * @param length Lunghezza del segnale 
	 * @param energy Energia del segnale
	 * @param attempts Numero di prove su cui effettuare la simulazione
	 * @param inf Estremo inferiore di SNR su cui effettuare la simulazione
	 * @param sup Estremo superiore di SNR su cui effettuare la simulazione 
	 * @return Una lista di Momenti**/
	
	
	public static ArrayList<Moment> computeMoment(Signal s, int length, double energy, int attempts, int inf,
			int sup) {
		ArrayList<Moment> Moments = new ArrayList<Moment>();
		for (double i = inf; i < sup; i++) {
			Moment m = new Moment(s, attempts, energy, i, length);
			Moments.add(m);
		}
		return Moments;
	}

	/**
	 *  Metodo per il calcolo dell'energia dei momenti 
	 * 
	 * @param Moment Array di oggetti momento
	 * @return Energia **/
	
	public static ArrayList<ArrayList<Double>> computeMomentEnergy(ArrayList<Moment> Moment) {
		ArrayList<ArrayList<Double>> energy = new ArrayList<ArrayList<Double>>();
		for (int i = 0; i < Moment.size(); i++) {
			energy.add(Moment.get(i).getEnergy());
		}
		return energy;
	}

	/** 
	 * Metodo per la generazione degli parametri Pr. I parametri Pr sono particolari valori
	 * ottenuti a partire dai momenti del secondo e quarto ordine utilizzati per il calcolo della
	 * detection nel metodo proposto. 
	 * 
	 * @param Moment Lista di Oggetti Momento su cui calcolare Pr
	 * @return una lista di parametri Pr **/
	
	public static ArrayList<ArrayList<Double>> computePr(ArrayList<Moment> Moment) {
		ArrayList<ArrayList<Double>> PrResult = new ArrayList<ArrayList<Double>>();
		for (int i = 0; i < Moment.size(); i++) {
			ArrayList<Double> m = Moment.get(i).getSecondOrder();
			ArrayList<Double> q = Moment.get(i).getFourthOrder();
			ArrayList<Double> prTemp=new ArrayList<Double>();
			for (int j = 0; j< m.size(); j++) {
				prTemp.add((double) ((2 * (Math.pow(m.get(j), 2)) - q.get(j))));
			}
			PrResult.add(prTemp);

		}
		return PrResult;
	}
	
	
	/**
	 * Metodo per la generazione del vettore di energie medie necessario per il calcolo dell'energy Detector.
	 * Per ogni prova genero il rumore e lo sommo al segnale.Divido il segnale in M blocchi di N campioni ciascuno e per ogni
	 * blocco calcolo l'energia. Faccio la media sommando le energie dei blocchi e dividendo per M
	 * **/
	
	public static ArrayList<ArrayList<Double>> computeMediumEnergy(Signal s, int length, double energy, int attempts, int inf,
			int sup,int block) {
		
		ArrayList<ArrayList<Double>> MediumEnergy = new ArrayList<ArrayList<Double>>();
		//Prendo l'intervallo Snr
		for (double snr = inf; snr < sup; snr++) {
			ArrayList<Double> MediumEnergyTemp = new ArrayList<Double>();
			//Per ogni prova
			for (int j = 0; j < attempts; j++) {
				//Genero il rumore.
				Noise noise = new Noise(snr,length, energy);
				
				double avg = 0;
				int samples = (length/block);
				int startIndex=0;
				for(int i=0;i<length;i++){
					if(i%samples==0 & i!=0){
						
						Signal signal = new Signal(samples);
						if(s!=null){
						ArrayList<Double> samplesRe=MathFunctions.SumVector(noise.splitNoise(startIndex,i-1).getSamplesRe(), s.splitSignal(startIndex,i-1).getSamplesRe());
						ArrayList<Double> samplesIm=MathFunctions.SumVector(noise.splitNoise(startIndex,i-1).getSamplesIm(), s.splitSignal(startIndex,i-1).getSamplesIm());
						signal.setSamplesRe(samplesRe);
						signal.setSamplesIm(samplesIm);
						startIndex=i;
						avg=avg+computeEnergy(signal);}
						else{
							ArrayList<Double> samplesRe=noise.splitNoise(startIndex,i-1).getSamplesRe();
							ArrayList<Double> samplesIm=noise.splitNoise(startIndex,i-1).getSamplesIm();
							signal.setSamplesRe(samplesRe);
							signal.setSamplesIm(samplesIm);
							startIndex=i;
							avg=avg+computeEnergy(signal);}
						
					}
					if(i==length-1){
					MediumEnergyTemp.add(avg/block);}	
				}
				
			}
			MediumEnergy.add(MediumEnergyTemp);
		}
		return MediumEnergy;
	}
	
	
	/**Metodo per il calcolo dei vettori di Energia nelle ipotesi di segnale+rumore e di solo rumore.
	 * Questo metodo calcola i valori necessari per l'energy Detector tradizionale, in quanto effettua il
	 * calcolo dell'energya diretto sui segnali senza operazioni intermedie**/
	public static ArrayList<ArrayList<Double>> computeVectorEnergy(Signal s, int length, double energy, int attempts, int inf,
			int sup){
		ArrayList<ArrayList<Double>> EnergyVector = new ArrayList<ArrayList<Double>>();
		for (double snr = inf; snr < sup; snr++) {
		ArrayList<Double> EnergyVectorTemp = new ArrayList<Double>();
		for (int j = 0; j < attempts; j++) {
			Noise noise = new Noise(snr,length, energy);
			Signal Resultsignal = new Signal(length);
			ArrayList<Double> samplesRe;
			ArrayList<Double> samplesIm;
			if(s!=null){
			 samplesRe=MathFunctions.SumVector(noise.getSamplesRe(),s.getSamplesRe());
			 samplesIm=MathFunctions.SumVector(noise.getSamplesIm(),s.getSamplesIm());
			}
			else{
				samplesRe=noise.getSamplesRe();
				samplesIm=noise.getSamplesIm();
			}
			Resultsignal.setSamplesRe(samplesRe);
			Resultsignal.setSamplesIm(samplesIm);
			
			EnergyVectorTemp.add(SignalProcessor.computeEnergy(Resultsignal));
		}
		
		EnergyVector.add(EnergyVectorTemp);
	}
		return EnergyVector;}
	
	/**
	 * Metodo per il calcolo della soglia necessaria per la Detection del metodo
	 * proposto.
	 * 
	 * @param Pfa Probabilità di falso allarme
	 * @param pr Lista di Double contenente i valori del parametro Pr
	 * calcolato nell'ipotesi in cui il segnale primario è assente (solo rumore)
	 * @return La soglia
	 * @throws Exception L'argomento della funzione InvErf deve essere compreso tra -1 e 1
	 **/

	public static double computeProposedThreshold(double Pfa, ArrayList<Double> pr) throws Exception {
		double M = MathFunctions.Mean(pr);
		double V = MathFunctions.Variance(pr);

		double implThreshold = M + Math.sqrt(2 * V) * MathFunctions.InvErf(1 - 2 * Pfa);
		return implThreshold;
	}
	
	/**
	 * Metodo per il calcolo della soglia necessaria per l'energy Detector.
	 * 
	 * @param Pfa Probabilità di falso allarme
	 * @param energy Momento calcolato nell'ipotesi in cui il segnale primario è assente (solo rumore)
	 * @return La soglia necessaria per l'energy Detector
	 * @throws Exception L'argomento della funzione InvErf deve essere compreso tra -1 e 1
	 **/

	public static double computeEnergyDetectorThreshold(double Pfa, ArrayList<Double> energy) throws Exception {

		double M = MathFunctions.Mean(energy);
		double V = MathFunctions.Variance(energy);

		double edThreshold = M + Math.sqrt(2 * V) * MathFunctions.InvErf(1 - 2 * Pfa);
		return edThreshold;
	}
	

}
