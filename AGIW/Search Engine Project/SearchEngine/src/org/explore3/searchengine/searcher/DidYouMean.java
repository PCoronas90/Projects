package org.explore3.searchengine.searcher;

import java.io.IOException;

import org.apache.lucene.search.spell.SpellChecker;
import org.apache.lucene.store.Directory;

public class DidYouMean {


	private static SpellChecker spellChecker;
	
	public static String suggest(String queryString,Directory indexDirectory) throws IOException {

		String suggestedQuery = null;   
		String[] similarWords = null;

		
		spellChecker = new SpellChecker(indexDirectory);

		similarWords = spellChecker.suggestSimilar(queryString, 5);

		if(similarWords != null) {
			suggestedQuery= similarWords[0];
			
		}
		return suggestedQuery;}}
