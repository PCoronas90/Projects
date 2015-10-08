package org.explore3.searchengine.bean;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.faces.bean.*;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.explore3.searchengine.pages.Page;
import org.explore3.searchengine.pages.Result;
import org.explore3.searchengine.pages.Search;
import org.explore3.searchengine.searcher.ImageSearcher;
import org.explore3.searchengine.searcher.Searcher;

@ManagedBean(name = "searcherBean", eager = true)
@SessionScoped
public class searcherBean {
	
	private List<Page> linkedPPrev;
	private List<Page> linkedPNext;
	private String originalQuery;
	private String suggestedQuery;
	private List<Page> pages;
	private Page currentPage;
	private Searcher searcher;
	private ImageSearcher imagesearcher;
	private int numberOfResults;
	private long searchTime;
	// lista per ricerche effettuate
	private Set<Search> searchresultList;

	public searcherBean() {

		this.pages = null;
		this.searcher = null;
		this.linkedPPrev = new ArrayList<Page>();
		this.linkedPNext = new ArrayList<Page>();
		this.searcher = new Searcher();
		this.imagesearcher= new ImageSearcher();
		this.searchresultList = new HashSet<Search>();
	}

	public String search() throws Exception {

		this.pages = new ArrayList<Page>();
		// Res nelle pagine precedenti
		int numbResInserted = 0;
		int pageNumber = 1;
		Search searchResult = searcher.search(originalQuery);
		this.searchresultList.add(searchResult);
		List<Result> resultsAll = searchResult.getTopHits();
		this.setSuggestedQuery(searchResult.getSuggestedQuery());

		if (resultsAll.isEmpty() && suggestedQuery.isEmpty()) {
			return "empty";
		}

		if (resultsAll.isEmpty() && !suggestedQuery.isEmpty()) {
			searchResult = searcher.search(suggestedQuery);
			return "suggested";
		}

		this.numberOfResults = searchResult.getTotalHitCount();
		this.searchTime = searchResult.getSearchDuration();
		this.originalQuery = searchResult.getOriginalQuery();
		this.setSuggestedQuery(searchResult.getSuggestedQuery());

		while (numbResInserted < numberOfResults) {
			Page page = new Page(pageNumber);
			List<Result> resultsPage = new ArrayList<Result>();

			// 3 Res per volta
			for (int i = numbResInserted; i < numbResInserted + 5; i++) {
				if (i < numberOfResults)
					resultsPage.add(resultsAll.get(i));
			}

			// Aggiungo alla Page
			page.setResults(resultsPage);
			this.pages.add(page);
			numbResInserted = numbResInserted + 5;
			pageNumber++;
		}

		setCurrentPage(pages.get(0));
		this.updateLinkedPages(1);
		return "search";
	}

	// Metodo per "forse cercavi". Stessa search ma con suggesteQuery
	public String searchSuggested() throws Exception {

		this.pages = new ArrayList<Page>();
		int numbResInserted = 0;
		int pageNumber = 1;
		Search searchResult = searcher.search(originalQuery);
		this.setSuggestedQuery(searchResult.getSuggestedQuery());

		searchResult = searcher.search(suggestedQuery);
		this.searchresultList.add(searchResult);
		List<Result> resultsAll = searchResult.getTopHits();

		
		if (resultsAll.isEmpty() && suggestedQuery.isEmpty()) {
			return "empty";
		}

		if (resultsAll.isEmpty() && !suggestedQuery.isEmpty()) {
			searchResult = searcher.search(suggestedQuery);
			return "suggested";
		}

		this.numberOfResults = searchResult.getTotalHitCount();
		this.searchTime = searchResult.getSearchDuration();
		this.originalQuery = searchResult.getOriginalQuery();
		this.setSuggestedQuery(searchResult.getSuggestedQuery());

		while (numbResInserted < numberOfResults) {
			Page page = new Page(pageNumber);
			List<Result> resultsPage = new ArrayList<Result>();

			for (int i = numbResInserted; i < numbResInserted + 5; i++) {
				if (i < numberOfResults)
					resultsPage.add(resultsAll.get(i));
			}

			page.setResults(resultsPage);
			this.pages.add(page);
			numbResInserted = numbResInserted + 5;
			pageNumber++;
		}

		setCurrentPage(pages.get(0));
		this.updateLinkedPages(1);
		return "search";
	}

	public String feelLucky() throws Exception {

		Search searchResult = searcher.search(originalQuery);
		this.searchresultList.add(searchResult);
		List<Result> resultsAll = searchResult.getTopHits();

		if (resultsAll.isEmpty())
			return "empty";

		else {
			int range = resultsAll.size();
			Random r = new Random();
			int n = r.nextInt(range);
			String path = resultsAll.get(n).getPath();
			System.out.println(path);

			ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
			context.redirect(path);
			return "search";
		}
	}
	
	public String searchImage() throws Exception {

		this.pages = new ArrayList<Page>();
		
		int numbResInserted = 0;
		int pageNumber = 1;
		Search searchResult = imagesearcher.search(originalQuery);
		this.searchresultList.add(searchResult);
		List<Result> resultsAll = searchResult.getTopHits();
		this.setSuggestedQuery(searchResult.getSuggestedQuery());

		if (resultsAll.isEmpty() && suggestedQuery.isEmpty()) {
			return "empty";
		}

		if (resultsAll.isEmpty() && !suggestedQuery.isEmpty()) {
			searchResult = searcher.search(suggestedQuery);
			return "suggested";
		}


		
		

		this.numberOfResults = searchResult.getTotalHitCount();
		this.searchTime = searchResult.getSearchDuration();
		this.originalQuery = searchResult.getOriginalQuery();
		

		while (numbResInserted < numberOfResults) {
			Page page = new Page(pageNumber);
			List<Result> resultsPage = new ArrayList<Result>();

			// 3 Res per volta
			for (int i = numbResInserted; i < numbResInserted + 5; i++) {
				if (i < numberOfResults)
					resultsPage.add(resultsAll.get(i));
			}

			// Aggiungo alla Page
			page.setResults(resultsPage);
			this.pages.add(page);
			numbResInserted = numbResInserted + 5;
			pageNumber++;
		}

		setCurrentPage(pages.get(0));
		this.updateLinkedPages(1);
		return "search";
	}


	public String goToPage(String selectedNumber) {
		int number = Integer.parseInt(selectedNumber);
		for (Page page : pages) {
			if (page.getNumber() == number) {
				setCurrentPage(page);
				this.updateLinkedPages(number);
			}
		}
		return "search";
	}

	public int getMiddle(int number) {
		if (number % 2 == 0)
			return number / 2;
		else
			return number / 2 + 1;
	}

	public void updateLinkedPages(int currentPageNumber) {

		int totalLinkedPages = 10;
		int middlePage = this.getMiddle(totalLinkedPages);
		// Num pag di mezzo rispetto a quelle da visualizzare
		int otherPages = totalLinkedPages - middlePage;

		List<Page> tmpPrev = null;
		List<Page> tmpNext = null;

		if (currentPageNumber <= middlePage) {
			tmpNext = new ArrayList<Page>();
			// La prima pagina non ha precedenti
			if (currentPageNumber != 1) {
				tmpPrev = new ArrayList<Page>();
				for (int i = 1; i < currentPageNumber; i++) {
					tmpPrev.add(pages.get(i - 1));
				}
			}

			int min = Math.min(totalLinkedPages, pages.size());
			for (int i = currentPageNumber + 1; i <= min; i++) {
				tmpNext.add(pages.get(i - 1));
			}
		}

		// Pagine intermedie. Link a prec e succ
		else if (currentPageNumber > middlePage && currentPageNumber <= this.pages.size() - otherPages) {
			tmpPrev = new ArrayList<Page>();
			tmpNext = new ArrayList<Page>();
			for (int i = currentPageNumber - otherPages; i < currentPageNumber; i++) {
				tmpPrev.add(pages.get(i - 1));
			}
			for (int i = currentPageNumber + 1; i <= currentPageNumber + otherPages; i++) {
				tmpNext.add(pages.get(i - 1));
			}
		}

		else if (currentPageNumber > this.pages.size() - otherPages) {
			tmpPrev = new ArrayList<Page>();
			for (int i = this.pages.size() - totalLinkedPages + 1; i < currentPageNumber; i++) {
				tmpPrev.add(pages.get(i - 1));
			}
			if (currentPageNumber != this.pages.size()) { // l'ultima pagina non
															// ha pagine
															// successive
				tmpNext = new ArrayList<Page>();
				for (int i = currentPageNumber + 1; i <= this.pages.size(); i++) {
					tmpNext.add(pages.get(i - 1));
				}
			}
		}
		setLinkedPagesPrev(tmpPrev);
		setLinkedPagesNext(tmpNext);
	}

	public String reloadSearchResult(String query) {

		int numbResInserted = 0;
		int pageNumber = 1;
		Search searchResult = null;

		for (Search s : searchresultList) {
			if (s.getOriginalQuery().equals(query)) {
				searchResult = s;
				break;
			}
		}

		List<Result> resultsAll = searchResult.getTopHits();

		this.numberOfResults = searchResult.getTotalHitCount();
		this.searchTime = searchResult.getSearchDuration();
		this.originalQuery = searchResult.getOriginalQuery();
		this.setSuggestedQuery(searchResult.getSuggestedQuery());

		while (numbResInserted < numberOfResults) {
			Page page = new Page(pageNumber);
			List<Result> resultsPage = new ArrayList<Result>();

			for (int i = numbResInserted; i < numbResInserted + 5; i++) {
				if (i < numberOfResults)
					resultsPage.add(resultsAll.get(i));
			}

			page.setResults(resultsPage);
			this.pages.add(page);
			numbResInserted = numbResInserted + 5;
			pageNumber++;
		}

		// Pagina corrente nella array list in posizione zero
		setCurrentPage(pages.get(0));
		this.updateLinkedPages(1);
		return "search";

	}

	public List<Page> getLinkedPagesPrev() {
		return linkedPPrev;
	}

	public void setLinkedPagesPrev(List<Page> linkedPagesPrev) {
		this.linkedPPrev = linkedPagesPrev;
	}

	public List<Page> getLinkedPagesNext() {
		return linkedPNext;
	}

	public void setLinkedPagesNext(List<Page> linkedPagesNext) {
		this.linkedPNext = linkedPagesNext;
	}

	public Page getCurrentPage() {
		return this.currentPage;
	}

	public void setCurrentPage(Page page) {
		this.currentPage = page;
	}

	public String getOriginalQuery() {
		return originalQuery;
	}

	public void setOriginalQuery(String query) {
		this.originalQuery = query;
	}

	public String getSuggestedQuery() {
		return suggestedQuery;
	}

	public void setSuggestedQuery(String suggestedQuery) {
		this.suggestedQuery = suggestedQuery;
	}

	public List<Page> getPages() {
		return pages;
	}

	public void setPages(List<Page> pages) {
		this.pages = pages;
	}

	public int getNumberOfResults() {
		return numberOfResults;
	}

	public void setNumberOfResults(int numberOfResults) {
		this.numberOfResults = numberOfResults;
	}

	public long getSearchTime() {
		return searchTime;
	}

	public void setSearchTime(long searchTime) {
		this.searchTime = searchTime;
	}

}