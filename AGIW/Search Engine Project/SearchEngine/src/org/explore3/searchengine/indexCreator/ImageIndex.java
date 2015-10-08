package org.explore3.searchengine.indexCreator;

/*
004 * Licensed to the Apache Software Foundation (ASF) under one or more
005 * contributor license agreements.  See the NOTICE file distributed with
006 * this work for additional information regarding copyright ownership.
007 * The ASF licenses this file to You under the Apache License, Version 2.0
008 * (the "License"); you may not use this file except in compliance with
009 * the License.  You may obtain a copy of the License at
010 *
011 *     http://www.apache.org/licenses/LICENSE-2.0
012 *
013 * Unless required by applicable law or agreed to in writing, software
014 * distributed under the License is distributed on an "AS IS" BASIS,
015 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
016 * See the License for the specific language governing permissions and
017 * limitations under the License.
018 */

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.explore3.searchengine.utils.HtmlImageParsing;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

public class ImageIndex {

	private ImageIndex() {
	}

	public static void main(String[] args) {

		String indexPath = "Imageindex";
		String DocsPath = "C:/Users/Pietro/Desktop/dataset/";
		boolean create = true;

		final File DocDir = new File(DocsPath);
		if (!DocDir.exists() || !DocDir.canRead()) {
			System.out.println("Document directory '" + DocDir.getAbsolutePath()
					+ "' does not exist or is not readable, please check the path");
			System.exit(1);
		}

		Date start = new Date();

		try {
			System.out.println("Indexing to directory '" + indexPath + "'...");

			Directory indexDir = FSDirectory.open(new File(indexPath));
			// :Post-Release-Update-Version.LUCENE_XY:
			Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_47);
			IndexWriterConfig indexWriterConf = new IndexWriterConfig(Version.LUCENE_47, analyzer);

			if (create) {
				// Creo nuovo indice e rimuovo i precedenti
				indexWriterConf.setOpenMode(OpenMode.CREATE);
			} else {
				// Aggiungo il documento ad un indice esistente
				indexWriterConf.setOpenMode(OpenMode.CREATE_OR_APPEND);
			}

			IndexWriter writer = new IndexWriter(indexDir, indexWriterConf);
			indexDocument(writer, DocDir);

			writer.close();
			IndexWriterConfig indexWriterConf2 = new IndexWriterConfig(Version.LUCENE_47, analyzer);
			System.out.println("Creating spell index...");
			Spellindex.createSpellIndex("image", indexDir, indexDir, indexWriterConf2);

			Date end = new Date();
			System.out.println(end.getTime() - start.getTime() + " total milliseconds");

		} catch (IOException e) {
			System.out.println(" caught a " + e.getClass() + "\n with message: " + e.getMessage());
		}
	}

	static void indexDocument(IndexWriter writer, File file) throws IOException {
		// do not try to index files that cannot be read
		if (file.canRead()) {
			if (file.isDirectory()) {
				String[] files = file.list();
				// an IO error could occur
				if (files != null) {
					for (int i = 0; i < files.length; i++) {
						indexDocument(writer, new File(file, files[i]));
					}
				}
			}

			else {

				try {

					Map<String, String> infoWithField = HtmlImageParsing.parse(file);

					// make a new, empty document
					Document doc = new Document();
					

					FieldType type = new FieldType();
					type.setIndexed(true);
					type.setStored(true);
					type.setStoreTermVectors(true);
					type.setTokenized(true);
					type.setStoreTermVectorOffsets(true);
					
					if(!infoWithField.isEmpty()){
					Field image = new TextField("image", infoWithField.get("image").toString(), Field.Store.YES);		
					System.out.println(infoWithField.get("image").toString());
					doc.add(image);
					Field title = new StringField("title", infoWithField.get("title"), Field.Store.YES);
					doc.add(title);


					Field path = new StringField("path", file.getPath(), Field.Store.YES);
					doc.add(path);}

					if (writer.getConfig().getOpenMode() == OpenMode.CREATE) {
						//System.out.println("adding " + file);
						writer.addDocument(doc);
					}

					else {
						System.out.println("updating " + file);
						writer.updateDocument(new Term("path", file.getPath()), doc);
					}

				} finally {
				}
			}
		}
	}

}