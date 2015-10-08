package org.explore3.searchengine.pages;

import java.util.List;


public class Page {
	
	public int number;
	public List<Result> results;
	public float score;

	public Page(int number){
		this.number = number;
	}

	public int getNumber() {
		return this.number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public List<Result> getResults() {
		return this.results;
	}

	public void setResults(List<Result> results) {
		this.results = results;
	}
	public float getScore(float score){
		return this.score;}
	
	public void setScore(float score){
		this.score=score;
	}
	}


