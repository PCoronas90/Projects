package org.explore3.tagmining.creator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;

/**Classe per la scrittura su file dei 4 output**/
public class Result {
	static String path1="C:/Users/Pietro/Desktop/TagMining/"+ "Clueweb09_StringaDaRimpiazzare(Output1).txt";
	static String path2="C:/Users/Pietro/Desktop/TagMining/"+ "Clueweb09_FraseSenzaTag(Output2).txt";
	static String path3="C:/Users/Pietro/Desktop/TagMining/"+ "Clueweb09_FraseConTag(Output3).txt";
	static String path4="C:/Users/Pietro/Desktop/TagMining/"+ "Clueweb09_Stats.txt";
	static String path5="C:/Users/Pietro/Desktop/TagMining/"+ "Clueweb09_Noise.txt";
	static HashMap<String,Integer> contatoreTag;

	static File file1 = new File(path1);
	static File file2 = new File(path2);
	static File file3 = new File(path3);
	static File file4 = new File(path4);
	static File file5 = new File(path5);
	static FileWriter fileWR;
	static FileWriter fileWR2;
	static FileWriter fileWR3;
	static FileWriter fileWR4;
	static FileWriter fileWR5;
	static int noiseString;

	public static void inizialize() throws IOException{
		fileWR = new FileWriter(file1);
		fileWR2 = new FileWriter(file2);
		fileWR3 = new FileWriter(file3);
		fileWR4 = new FileWriter(file4);
		fileWR5 = new FileWriter(file5);
		contatoreTag = new HashMap<String,Integer>();
		noiseString=0;
	}



	public static void createResult1(String TREC_ID,Matcher matcher,String Tag) throws IOException{
		String trec_id="||Trec-id: "+TREC_ID+" ";
		String startend="||start: "+matcher.start()+" || "+"end: "+matcher.end();
		String find="|| String Found : "+matcher.group();    	


		String finalRes1= trec_id+startend+find.replaceFirst("\">", "")
		.replaceFirst("\"[\\s]?target=\"[a-zA-Z_0-9]*", "")+Tag+'\n';
		fileWR.write(finalRes1);
		fileWR.write(System.getProperty("line.separator"));   
		fileWR.write(System.getProperty("line.separator"));   
		createStats(Tag);


	}

	public static void createResult2(String TREC_ID,String ContentUtf8) throws IOException{
		String trec_id="||Trec-id: "+TREC_ID+" ";
		String finalRes2=trec_id+" |Original String: "+ContentUtf8 ;

		fileWR2.write(finalRes2);
		fileWR2.write(System.getProperty("line.separator"));
		fileWR2.write(System.getProperty("line.separator"));

	}

	public static void createResult3(String TREC_ID,String ContentUtf8) throws IOException{
		String trec_id="||Trec-id: "+TREC_ID+" ";
		String finalRes3=trec_id+" |Tag String: "+ContentUtf8;
		fileWR3.write(finalRes3);
		fileWR3.write(System.getProperty("line.separator"));
		fileWR3.write(System.getProperty("line.separator"));

	}

	public static void space() throws IOException{
		fileWR.write(System.getProperty("line.separator"));
		fileWR.write(System.getProperty("line.separator"));
		fileWR2.write(System.getProperty("line.separator"));
		fileWR2.write(System.getProperty("line.separator"));
		fileWR3.write(System.getProperty("line.separator"));
		fileWR3.write(System.getProperty("line.separator"));
	}

	public static void close() throws IOException{
		fileWR.flush();
		System.out.println("Write file "+ path1);
		fileWR2.flush();
		System.out.println("Write file "+ path2);
		fileWR3.flush();
		fileWR4.flush();
		System.out.println("Write file "+ path3);
		fileWR.close();
		fileWR2.close();
		fileWR3.close();
		fileWR4.close();
		System.out.println("Write file "+ path4);
		fileWR5.close();
	}

	public static void createStats(String tag){
		if(contatoreTag.containsKey("String")){
			int totString=contatoreTag.get("String")+1;
			contatoreTag.put("String", totString);
		}
		else if(!contatoreTag.containsKey("String")){
			contatoreTag.put("String", 1);	
		}


		if(contatoreTag.containsKey(tag)){
			int cont=contatoreTag.get(tag)+1;
			contatoreTag.put(tag,  cont);
		}
		else{
			contatoreTag.put(tag,  1);
		}


	}

	public static void findNoiseString(String TREC_ID,String Content) throws IOException{		
		noiseString++;	
		fileWR5.write("||Trec_id: "+TREC_ID+" "+"||String: "+Content);
		fileWR5.write(System.getProperty("line.separator"));
		fileWR5.write(System.getProperty("line.separator"));
	}

	public static void writeStats(String tempo) throws IOException{
		fileWR4.write("Stringhe Utili Estratte: " + " " + contatoreTag.get("String"));
		fileWR4.write(System.getProperty("line.separator"));
		fileWR4.write(tempo+" s");
		fileWR4.write(System.getProperty("line.separator"));
		fileWR4.write(System.getProperty("line.separator"));
		fileWR4.write("Stringhe scartate: "+" "+noiseString );
		fileWR4.write(System.getProperty("line.separator"));
		fileWR4.write(System.getProperty("line.separator"));
		fileWR4.write("Tag totali Trovati:");
		fileWR4.write(System.getProperty("line.separator"));
		for(String key: contatoreTag.keySet()){
			if(key!="String"){
				fileWR4.write(key+" " + contatoreTag.get(key) );
				fileWR4.write(System.getProperty("line.separator"));}
		}
	}



}
