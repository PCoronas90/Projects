package org.explore3.tagmining.Extractor;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.explore3.tagmining.creator.Regex;
import org.explore3.tagmining.creator.Result;


public class Extractor {

	/**Per ogni warc presente nella cartella datasetWarc, richiama il metodo WarcExtractor**/
	public static void htmlExtractor() throws IOException {	
		String inizio="Inizio:"+new Date();
		final File sourceDocDir = new File("datasetWarc");
		if (sourceDocDir.canRead()) {
			if (sourceDocDir.isDirectory()) {
				String[] files = sourceDocDir.list();
				// an IO error could occur
				if (files != null) {
					for (int i = 0; i < files.length; i++) {
						System.out.println(files[i]);
						FromWarcToHtml.WarcExtractor(new File(sourceDocDir, files[i]));

					}
				}
			} 



		}

		System.out.println("Tempo Estrazione HTML:"+"Inizio: "+inizio+"Fine: "+new Date());

	}
	/**Per ogni pagina html presente nella cartella, richiama il metodo Extractor**/
	public static void tagExtractor() throws IOException {	
		Date inizio=new Date();
		//Inizializzo le Regex
		Regex regex= new Regex();
		Regex.CreateRegex();
		//Inizializzo i file di output
		Result.inizialize();

		final File sourceDocDir = new File("C:/Users/Pietro/Desktop/datasetTagMining");
		if (sourceDocDir.canRead()) {
			if (sourceDocDir.isDirectory()) {
				String[] files = sourceDocDir.list();
				// an IO error could occur
				if (files != null) {
					for (int i = 0; i < files.length; i++) {
						System.out.println(files[i]);
						TagExtractor.Extractor(new File(sourceDocDir, files[i]),regex);

					}
				}
			} 



		}
		Date fine=new Date();
		long tempo=(fine.getTime() - inizio.getTime())/1000;
		Result.writeStats("Tempo Estrazione Tag: "+" "+ tempo);
		Result.close();




	}

}
