package org.explore3.searchengine.warc;

import java.io.File;
import java.io.IOException;
import org.explore3.searchengine.warc.WarcReader;

public class WarcWriter {
	static void writer() throws IOException {		
	


final File sourceDocDir = new File("datasetWarc");
if (sourceDocDir.canRead()) {
	if (sourceDocDir.isDirectory()) {
		String[] files = sourceDocDir.list();
		// an IO error could occur
		if (files != null) {
			for (int i = 0; i < files.length; i++) {
				System.out.println(files[i]);
				WarcReader.WarcWriter(new File(sourceDocDir, files[i]));
				
			}
		}
	} 



}}
	public static void main(String[] args) throws IOException  {
		writer();
	}	
}
