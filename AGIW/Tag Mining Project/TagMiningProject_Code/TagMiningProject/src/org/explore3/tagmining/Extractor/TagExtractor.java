package org.explore3.tagmining.Extractor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;

import org.explore3.tagmining.creator.Regex;
import org.explore3.tagmining.creator.Result;

/**Classe per estrazione delle informazioni da file html**/

public class TagExtractor {


	private static BufferedReader br;

	public static  void Extractor(File file,Regex regex) throws IOException {


		br = new BufferedReader(new FileReader(file));	
		String ContentUtf8; 
		boolean isFind=false;
		boolean isWrite=false;
		while ((ContentUtf8 = br.readLine()) != null) {
			boolean MatchFind=false;

			Matcher matcherNoise = regex.getPatternNoise().matcher(ContentUtf8);
			String TREC_ID= file.getPath().substring(41, 46);
			//Se la linea non contiene rumore
			if(!matcherNoise.find()){

				boolean writeContent=false;


				//System.out.println(TREC_ID);

				//Metto i matcher prima dell'if, im modo che se ci sono più di un tag da sostituire tiene in memoria il tag già sostituito

				Matcher matcher = regex.getPatternLink().matcher(ContentUtf8);
				String patternString ="|| Tag: #Link";

				if(matcher.find()){		
					isFind=true;    
					MatchFind=true;
					Result.createResult1(TREC_ID,matcher,patternString);     	     	
					if(writeContent==false){
						Result.createResult2(TREC_ID,ContentUtf8);
						writeContent=true;}


					ContentUtf8=matcher.replaceAll(" #Link ");      	}


				Matcher matcher2= regex.getPatternDate().matcher(ContentUtf8);
				String patternString2 ="|| Tag: #Date";

				if(matcher2.find()){
					isFind=true;
					MatchFind=true;
					Result.createResult1(TREC_ID,matcher2,patternString2);
					if(writeContent==false){
						Result.createResult2(TREC_ID,ContentUtf8);
						writeContent=true;}

					ContentUtf8=matcher2.replaceAll(" #Date ");        	
				}


				Matcher matcher8= regex.getPatternDimension().matcher(ContentUtf8);
				String patternString8 ="|| Tag: #Dimension";

				if(matcher8.find()){
					MatchFind=true;
					isFind=true;
					Result.createResult1(TREC_ID,matcher8,patternString8);
					if(writeContent==false){
						Result.createResult2(TREC_ID,ContentUtf8);
						writeContent=true;}

					ContentUtf8=matcher8.replaceAll(" #Dimension ");

				}				


				Matcher matcher3= regex.getPatternDistance().matcher(ContentUtf8);
				String patternString3 ="|| Tag: #Distance";
				if(matcher3.find()){
					MatchFind=true;
					isFind=true;
					Result.createResult1(TREC_ID,matcher3,patternString3);
					if(writeContent==false){
						Result.createResult2(TREC_ID,ContentUtf8);
						writeContent=true;}

					ContentUtf8=matcher3.replaceAll(" #Distance ");

				}


				Matcher matcher4= regex.getPatternUnit().matcher(ContentUtf8);
				String patternString4 ="|| Tag: #Unit";
				if(matcher4.find()){
					MatchFind=true;
					isFind=true;
					Result.createResult1(TREC_ID,matcher4,patternString4);
					if(writeContent==false){
						Result.createResult2(TREC_ID,ContentUtf8);
						writeContent=true;}

					ContentUtf8=matcher4.replaceAll(" #Unit ");

				}


				Matcher matcher5= regex.getPatternNumber().matcher(ContentUtf8);
				String patternString5 ="|| Tag: #Number";

				if(matcher5.find()){
					MatchFind=true;
					isFind=true;
					Result.createResult1(TREC_ID,matcher5,patternString5);
					if(writeContent==false){
						Result.createResult2(TREC_ID,ContentUtf8);
						writeContent=true;}

					ContentUtf8=matcher5.replaceAll(" #Number ");

				}


				Matcher matcher6= regex.getPatternEmail().matcher(ContentUtf8);
				String patternString6 ="|| Tag: #Email";

				if(matcher6.find()){
					MatchFind=true;
					isFind=true;
					Result.createResult1(TREC_ID,matcher6,patternString6);
					if(writeContent==false){
						Result.createResult2(TREC_ID,ContentUtf8);
						writeContent=true;}

					ContentUtf8=matcher6.replaceAll(" #Email ");

				}


				Matcher matcher7= regex.getPatternTime().matcher(ContentUtf8);
				String patternString7 ="|| Tag: #Time";

				if(matcher7.find()){
					MatchFind=true;
					isFind=true;

					Result.createResult1(TREC_ID,matcher7,patternString7);
					if(writeContent==false){
						Result.createResult2(TREC_ID,ContentUtf8);
						writeContent=true;}

					ContentUtf8=matcher7.replaceAll(" #Time ");

				}		


				Matcher matcher9= regex.getPatternAddress().matcher(ContentUtf8);
				String patternString9 ="|| Tag: #Address";

				if(matcher9.find()){
					MatchFind=true;
					isFind=true;
					Result.createResult1(TREC_ID,matcher9,patternString9);
					if(writeContent==false){
						Result.createResult2(TREC_ID,ContentUtf8);
						writeContent=true;}

					ContentUtf8=matcher9.replaceAll(" #Address ");

				}	


				//Matcher matcher10= regex.getPatternUsStateOrProvince().matcher(ContentUtf8);
				//String patternString10 ="|| Tag: #UsState";

				//if(matcher10.find()){
				//	isFind=true;		
				//	Result.createResult1(TREC_ID,matcher10,patternString10);
				//	Result.createResult2(TREC_ID,ContentUtf8);
				//	Result.createResult3(TREC_ID,matcher10," #UsState ");
				//	ContentUtf8=matcher10.replaceAll(" #UsState ");

				//}


				Matcher matcher11= regex.getPatternIPV4Address().matcher(ContentUtf8);
				String patternString11 ="|| Tag: #IPV4Address";

				if(matcher11.find()){
					MatchFind=true;
					isFind=true;
					Result.createResult1(TREC_ID,matcher11,patternString11);
					if(writeContent==false){
						Result.createResult2(TREC_ID,ContentUtf8);
						writeContent=true;}

					ContentUtf8=matcher11.replaceAll(" #IPV4Address ");

				}

				Matcher matcher12= regex.getPatternFileFormat().matcher(ContentUtf8);
				String patternString12 ="|| Tag: #FileFormat";


				if(matcher12.find()){
					MatchFind=true;
					isFind=true;
					Result.createResult1(TREC_ID,matcher12,patternString12);
					if(writeContent==false){
						Result.createResult2(TREC_ID,ContentUtf8);
						writeContent=true;}

					ContentUtf8=matcher12.replaceAll(" #FileFormat ");

				}

				Matcher matcher13= regex.getPatternMoney().matcher(ContentUtf8);
				String patternString13 ="|| Tag: #Money";


				if(matcher13.find()){
					MatchFind=true;
					isFind=true;
					Result.createResult1(TREC_ID,matcher13,patternString13);
					if(writeContent==false){
						Result.createResult2(TREC_ID,ContentUtf8);
						writeContent=true;}        	
					ContentUtf8=matcher13.replaceAll(" #Money ");

				}

				if(MatchFind==true){	
					Result.createResult3(TREC_ID,ContentUtf8);

				}
			}

			else{

				Result.findNoiseString(TREC_ID,ContentUtf8);
			}


		}	
		//Controlli necessari per mettere correttamente la divisione delle linee nel file di output
		if(isFind && !isWrite){
			Result.space();
			isFind=false;
			isWrite=true;
		}

	}
}



