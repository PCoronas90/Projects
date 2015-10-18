package it.sp4te.CooperativeSpectrumSensing.Functions;

import java.util.ArrayList;

/** Classe per la Creazione dell'oggetto PR **/

public class Pr {

	Moment moment;
	ArrayList<Double> m;
	ArrayList<Double> q;
	ArrayList<Double> pr;

	/**
	 * Il costruttore prende come parametro l'oggetto Momento e tramite la
	 * formula calcola un array di Double (l'oggetto PR) con tanti elementi
	 * quanti sono quelli dell'oggetto Momento
	 **/
	public Pr(Moment moment) {
		this.moment = moment;
		this.pr = new ArrayList<Double>();
		this.m = this.moment.getAvarage();
		this.q = this.moment.getQ();
		for (int i = 0; i < m.size(); i++) {
			this.pr.add(i, (double) ((2 * (Math.pow(m.get(i), 2)) - q.get(i))));
		}
	}

	/** Metodi Getter e Setter **/
	public Moment getMoment() {
		return moment;
	}

	public void setMoment(Moment moment) {
		this.moment = moment;
	}

	public ArrayList<Double> getM() {
		return m;
	}

	public void setM(ArrayList<Double> m) {
		this.m = m;
	}

	public ArrayList<Double> getQ() {
		return q;
	}

	public void setQ(ArrayList<Double> q) {
		this.q = q;
	}

	public ArrayList<Double> getPr() {
		return pr;
	}

	public void setPr(ArrayList<Double> pr) {
		this.pr = pr;
	}

}
