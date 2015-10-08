package org.explore3.searchengine.pages;

public class Result {

	private String title;
	private String snippet;
	private String path;
	private float score;

	public Result(){ 	
	}

	public Result(String title,String snippet,String path,float score){
		this.title = title;
		this.snippet= snippet;
		this.path= path;
		this.score= score;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSnippet() {
		return this.snippet;
	}

	public void setSnippet(String snippet) {
		this.snippet = snippet;
	}

	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public float getScore() {
		return this.score;
	}

	public void setScore(float score) {
		this.score = score;
	}
	
 
}
