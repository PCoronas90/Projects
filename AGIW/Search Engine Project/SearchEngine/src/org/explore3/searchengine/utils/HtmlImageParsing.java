package org.explore3.searchengine.utils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class HtmlImageParsing {
			
	public static Map<String, String> parse(File file) throws IOException {

		Document doc = Jsoup.parse(file, "UTF-8");
		String title = doc.title();
		Map<String, String> infoWithField = new HashMap<String, String>();
		
		doc.getElementsByTag("select").remove();
		doc.getElementsByTag("button").remove();
		doc.getElementsByTag("script").remove();


		
		    
		Elements get = doc.select("img");
		if(!get.isEmpty() && get.attr("src").contains("http")){
			if(get.attr("src").endsWith(".jpeg")||get.attr("src").endsWith(".jpg")||get.attr("src").endsWith(".gif")||
					get.attr("src").endsWith(".png")){
			
			infoWithField.put("title", title);
			infoWithField.put("image", get.attr("src"));
			}}

		
	

		return infoWithField;
	
}}
