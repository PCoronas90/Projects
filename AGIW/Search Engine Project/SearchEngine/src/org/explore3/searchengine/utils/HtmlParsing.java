package org.explore3.searchengine.utils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class HtmlParsing {
			
	public static Map<String, String> parse(File file) throws IOException {

		Document doc = Jsoup.parse(file, "UTF-8");
		String title = doc.title();
		
		
		doc.getElementsByTag("select").remove();
		doc.getElementsByTag("button").remove();
		doc.getElementsByTag("script").remove();
	

		String text = doc.text();
		Map<String, String> infoWithField = new HashMap<String, String>();
		infoWithField.put("title", title);
		infoWithField.put("text", text);

		return infoWithField;
	}

}
