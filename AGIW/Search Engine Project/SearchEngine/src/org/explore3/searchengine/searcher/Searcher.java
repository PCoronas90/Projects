package org.explore3.searchengine.searcher;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.search.highlight.TextFragment;
import org.apache.lucene.search.highlight.TokenSources;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.explore3.searchengine.pages.Result;
import org.explore3.searchengine.pages.Search;

/**
 * Lucene Highlighter
 */

public class Searcher {
	private String pathOfIndex;
	
	private int minimumHits;
	private float minimumScore;
	

	public Searcher() {
		this.minimumHits = 1;
		this.minimumScore = new Float(0.05);
		this.pathOfIndex = "C:/Users/Pietro/Desktop/Workspace/Explore3SearchEngine/index";
	}

	public Search search(String queryString) throws ParseException, IOException, InvalidTokenOffsetsException {

		List<Result> finalRes = new ArrayList<Result>();
		Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_47);
		Directory indexDir = FSDirectory.open(new File(pathOfIndex));

		Date inizio = new Date();
		IndexReader reader = DirectoryReader.open(indexDir);
		IndexSearcher indexSearcher = new IndexSearcher(reader);
		QueryParser queryParser = new QueryParser(Version.LUCENE_47, "words", analyzer);
		Query query = queryParser.parse(queryString);

		TopDocs hits = indexSearcher.search(query, reader.maxDoc());
		//TopDocs hitsSuggested= new TopDocs();
		
		int numberOfResults = hits.totalHits;
		Date fine = new Date();

		long time = fine.getTime() - inizio.getTime();
		SimpleHTMLFormatter htmlFormatter = new SimpleHTMLFormatter();
		Highlighter highlighter = new Highlighter(htmlFormatter, new QueryScorer(query));

		// Estraggo gli snippet
		for (int i = 0; i < hits.totalHits; i++) {
			int id = hits.scoreDocs[i].doc;
			Document doc = indexSearcher.doc(id);
			String text = doc.get("words");
			
			
			
			//MoreLikeThis mlt = new MoreLikeThis(reader);
			
			//Query querySuggested = mlt.like(hits.scoreDocs[i].doc);
			//hitsSuggested = indexSearcher.search(querySuggested, reader.maxDoc());
			//		
			TokenStream tokenStream = TokenSources.getAnyTokenStream(indexSearcher.getIndexReader(), id, "words",
					analyzer);
			TextFragment[] frag = highlighter.getBestTextFragments(tokenStream, text, false, 4);

			Result r = new Result();
			r.setTitle(doc.get("title"));
			String path = doc.get("path");			
			r.setPath(path);
			r.setScore(hits.scoreDocs[i].score);
			String snippet = "";
			for (int j = 0; j < frag.length; j++) {
				if ((frag[j] != null) && (frag[j].getScore() > 0)) {
					snippet += frag[j].toString();
				}
			}

			String snippet1 = snippet.replace("<B>", "<b>");
			String snippetFinal = snippet1.replace("</B>", "</b>");
			r.setSnippet(snippetFinal);
			finalRes.add(r);
		}

		reader.close();
		String suggestedQuery = null;

		if (numberOfResults < minimumHits || hits.scoreDocs[0].score < minimumScore) {
			suggestedQuery = DidYouMean.suggest(queryString, indexDir);
						
					}
				
			
		Search searchRes = new Search(finalRes, time, queryString, suggestedQuery);
		return searchRes;

	}

}
