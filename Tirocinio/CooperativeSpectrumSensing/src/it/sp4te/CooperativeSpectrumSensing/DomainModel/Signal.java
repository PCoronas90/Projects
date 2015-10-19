package it.sp4te.CooperativeSpectrumSensing.DomainModel;

import java.util.ArrayList;

/** Classe per la generazione del segnale **/

public class Signal {

	int signalLenght;

	ArrayList<Double> samplesRe;
	ArrayList<Double> samplesIm;

	/**
	 * Costruttore del segnale modulato QPSK e con potenza unitaria. L'oggetto segnale contiene 2 array,
	 * rispettivamente relativi a parte reale e parte immaginaria.
	 * 
	 * @param signalLenght Lunghezza del segnale
	 **/

	public Signal(int signalLenght) {
		this.signalLenght = signalLenght;
		this.samplesRe = new ArrayList<Double>();
		this.samplesIm = new ArrayList<Double>();
		for (int i = 0; i < this.signalLenght; i++) {
			double v = Math.random();
			if (v < 0.5) {

				this.samplesRe.add(i, v / Math.sqrt(this.signalLenght));
				this.samplesIm.add(i, v / Math.sqrt(this.signalLenght));
			} else {
				this.samplesRe.add(i, -v / Math.sqrt(this.signalLenght));
				this.samplesIm.add(i, -v / Math.sqrt(this.signalLenght));

			}
		}
	}


	public int getSignalLenght() {
		return signalLenght;
	}

	public void setSignalLenght(int signalLenght) {
		this.signalLenght = signalLenght;
	}

	public ArrayList<Double> getSamplesRe() {
		return samplesRe;
	}

	public void setSamplesRe(ArrayList<Double> samplesRe) {
		this.samplesRe = samplesRe;
	}

	public ArrayList<Double> getSamplesIm() {
		return samplesIm;
	}

	public void setSamplesIm(ArrayList<Double> samplesIm) {
		this.samplesIm = samplesIm;
	}

}
