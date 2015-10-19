package it.sp4te.CooperativeSpectrumSensing.Functions;

import java.util.ArrayList;

import it.sp4te.CooperativeSpectrumSensing.DomainModel.Noise;
import it.sp4te.CooperativeSpectrumSensing.DomainModel.Signal;

/** Classe relativa ai momenti del secondo e quarto ordine **/

public class Moment {

	public double snr;
	public ArrayList<Double> samplesRe;
	public ArrayList<Double> samplesIm;
	public int attempts;
	public ArrayList<Double> avarage;
	public ArrayList<Double> q;
	public ArrayList<Double> Energy;

	/**
	 * Costruttore dell'oggetto Momento
	 * 
	 * @param s Il segnale
	 * @param attempts Numero di prove su cui effettuare la simulazione
	 * @param energy Energia del segnale
	 * @param snr L'SNR di riferimento
	 * @param length Lunghezza del segnale
	 **/

	public Moment(Signal s, int attempts, double energy, double snr, int length) {
		// Inizializza i parametri
		this.avarage = new ArrayList<Double>();
		this.q = new ArrayList<Double>();
		;
		this.attempts = attempts;
		this.Energy = new ArrayList<Double>();
		if (s != null) {

			this.samplesRe = s.getSamplesRe();
			this.samplesIm = s.getSamplesIm();
		} else {

			this.samplesRe = null;
			this.samplesIm = null;

		}

		// Generazione dell'oggetto momento. Calcolo dell'energia, della media e
		// del parametro q
		this.snr = snr;

		for (int i = 0; i < this.attempts; i++) {
			double j = 0.0;
			double h = 0.0;

			Noise noise = new Noise(snr, length, energy);

			Signal signal = new Signal(noise.getNoiseLenght());

			signal.setSamplesRe(MathFunctions.SumVector(noise.getSamplesRe(), this.samplesRe));
			signal.setSamplesIm(MathFunctions.SumVector(noise.getSamplesIm(), this.samplesIm));

			this.Energy.add(i, SignalFunctions.signalEnergy(signal));

			for (int k = 1; k < noise.getNoiseLenght() - 1; k++) {

				j = (j + Math.pow(signal.getSamplesRe().get(k), 2) + Math.pow(signal.getSamplesIm().get(k), 2));
				h = (h + Math.pow(signal.getSamplesRe().get(k), 4) + Math.pow(signal.getSamplesIm().get(k), 4));
			}

			this.avarage.add(i, j * (1 / (double) signal.getSignalLenght()));
			this.q.add(i, h * (1 / (double) signal.getSignalLenght()));
		}

	}

	
	public double getSnr() {
		return snr;
	}

	public void setSnr(double snr) {
		this.snr = snr;
	}

	public int getAttempts() {
		return attempts;
	}

	public void setAttempts(int attempts) {
		this.attempts = attempts;
	}

	public ArrayList<Double> getAvarage() {
		return avarage;
	}

	public void setAvarage(ArrayList<Double> avarage) {
		this.avarage = avarage;
	}

	public ArrayList<Double> getQ() {
		return q;
	}

	public void setQ(ArrayList<Double> q) {
		this.q = q;
	}

	public ArrayList<Double> getEnergy() {
		return Energy;
	}

	public void setEnergy(ArrayList<Double> energy) {
		Energy = energy;
	}

}

