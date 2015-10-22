package it.sp4te.css.model;

import java.util.ArrayList;

/** <p>Titolo: Signal</p>
 * <p>Descrizione: Classe per la generazione del segnale</p>
 * @author Pietro Coronas **/

public class Signal extends AbstractSignal{

	int lenght;

	ArrayList<Double> samplesRe;
	ArrayList<Double> samplesIm;

	/**
	 * Costruttore del segnale modulato QPSK e con potenza unitaria. L'oggetto segnale contiene 2 array,
	 * rispettivamente relativi a parte reale e parte immaginaria.
	 * 
	 * @param signalLenght Lunghezza del segnale
	 **/

	public Signal(int signalLenght) {
		this.lenght = signalLenght;
		this.samplesRe = new ArrayList<Double>();
		this.samplesIm = new ArrayList<Double>();
		for (int i = 0; i < this.lenght; i++) {
			double v = Math.random();
			if (v < 0.5) {
				this.samplesRe.add(i, v / Math.sqrt(this.lenght));}
			else{
				this.samplesRe.add(i, -v / Math.sqrt(this.lenght));}
			double p = Math.random();
			if (p < 0.5) {
				this.samplesIm.add(i,p / Math.sqrt(this.lenght));}
			else{
				this.samplesIm.add(i, -p / Math.sqrt(this.lenght));}

			}
		}
	


	public int getSignalLenght() {
		return lenght;
	}

	public void setSignalLenght(int signalLenght) {
		this.lenght = signalLenght;
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
