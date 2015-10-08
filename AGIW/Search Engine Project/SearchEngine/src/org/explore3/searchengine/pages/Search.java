package org.explore3.searchengine.pages;

import java.util.List;



public class Search {
	
	private List<Result> topHits;
	private int totalHitCount;
	private long searchDuration;
	private String originalQuery;
	private String suggestedQuery;

	public Search(List<Result> topHits, long searchDuration, String originalQuery) {
		this(topHits, searchDuration, originalQuery, null);
	}

	public Search(List<Result> topHits, long searchDuration, String originalQuery, String suggestedQuery) {
		this.topHits = topHits;
		this.totalHitCount = topHits.size();
		this.searchDuration = searchDuration;
		this.originalQuery = originalQuery;
		this.suggestedQuery = suggestedQuery;
	}

	public List<Result> getTopHits() {
		return topHits;
	}
	
	@Override
	public int hashCode() {
		return this.originalQuery.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		Search that = (Search) obj;
		return this.originalQuery.equals(that.getOriginalQuery());
	}

	public int getTotalHitCount() {
	    return totalHitCount;
	}
	
	public long getSearchDuration() {
		return searchDuration;
	}

	public String getOriginalQuery() {
		return originalQuery;
	}
	public String getSuggestedQuery() {
		return suggestedQuery;
	}
	
	
}
	
