package org.explore3.searchengine.indexCreator;

import java.io.IOException;

import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.spell.Dictionary;
import org.apache.lucene.search.spell.LuceneDictionary;
import org.apache.lucene.search.spell.SpellChecker;
import org.apache.lucene.store.Directory;

public class Spellindex {
	
	static void createSpellIndex(String field, Directory originalIndexDirectory, Directory spellIndexDirectory,
			IndexWriterConfig indexWriterConf) throws IOException {

		SpellChecker spellChecker = null;
		IndexReader indexReader = null;
		try {
			indexReader = DirectoryReader.open(originalIndexDirectory);
			Dictionary dictionary = new LuceneDictionary(indexReader, field);
			spellChecker = new SpellChecker(spellIndexDirectory);
			spellChecker.indexDictionary(dictionary, indexWriterConf, true);

		} finally {
			if (indexReader != null) {
				indexReader.close();
			}
			if (spellChecker != null) {
				spellChecker.close();
			}
		}

	}

}

