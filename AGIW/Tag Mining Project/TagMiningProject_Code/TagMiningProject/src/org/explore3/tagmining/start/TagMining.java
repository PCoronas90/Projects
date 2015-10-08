package org.explore3.tagmining.start;

import java.io.IOException;
import java.util.HashMap;

import org.explore3.tagmining.Extractor.Extractor;


public class TagMining {
	static HashMap<String,HashMap<String,Integer>> contatoreTag;
	public static void main(String[] args) throws IOException  {
		Extractor.htmlExtractor();		
		Extractor.tagExtractor();
	}	
}


