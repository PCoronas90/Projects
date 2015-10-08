package org.explore3.tagmining.Extractor;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import org.explore3.tagmining.warcUtils.WarcHTMLResponseRecord;
import org.explore3.tagmining.warcUtils.WarcRecord;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**Questa classe prende in input un file warc in formato compresso. Estrare ogni record, effettua il parsing, divide il
  file in righe e lo salva in un file html. Tale file avrà il nome del TREC_ID del record da cui è estratto**/

public class FromWarcToHtml {

	public static  void WarcExtractor(File file) throws IOException {

		GZIPInputStream gzInputStream=new GZIPInputStream(new FileInputStream(file));
		DataInputStream inStream=new DataInputStream(gzInputStream);

		WarcRecord thisWarcRecord;
		while ((thisWarcRecord=WarcRecord.readNextWarcRecord(inStream))!=null) {
			if (thisWarcRecord.getHeaderRecordType().equals("response")) {
				WarcHTMLResponseRecord htmlRecord=new WarcHTMLResponseRecord(thisWarcRecord);

				String TREC_ID= htmlRecord.getTargetTrecID().replaceFirst("clueweb09-en0000-00-", "");
				String ContentUtf8 = htmlRecord.getRawRecord().getContentUTF8();
				String thisContentUtf8="";
				int i=0;
				int j=0;
				boolean find=false;

				/** Per prima cosa rimuovo le informazioni sul warc che rimangono nell'html**/
				while(!find){
					if(ContentUtf8.codePointAt(i)!='<'){
						i++;
						j++;}
					if( i==ContentUtf8.length()-2){
						j=0;
						find=true;}
					if(ContentUtf8.codePointAt(i)=='<' && i<ContentUtf8.length()){
						find=true;

					}}

				thisContentUtf8= ContentUtf8.substring(j, ContentUtf8.length());

				/**Effettuo il parsing per eliminare i tag. Il metodo doc.text è lanciato due volte per eliminare i tag annidati. **/      

				Document doc2 = Jsoup.parse(thisContentUtf8, "UTF-8");	

				String resultPartial=doc2.text();
				Document doc = Jsoup.parse(resultPartial, "UTF-8");

				doc.getElementsByTag("select").remove();
				doc.getElementsByTag("button").remove();
				doc.getElementsByTag("script").remove();
				doc.getElementsByTag("head").remove();
				doc.getElementsByTag("style").remove();
				doc.getElementsByTag("form").remove();
				doc.getElementsByTag("font").remove();
				doc.getElementsByTag("img").remove();
				doc.getElementsByTag("img[src]").remove();	
				doc.getElementsByTag("a[href]").remove();
				doc.getElementsByTag("link[href]").remove();		
				String result=doc.text();	

				/**Divido il contenuto in linee. **/





				try {
					String path1="C:/Users/Pietro/Desktop/datasetTagMining/"+ TREC_ID+".htm";        
					File file1 = new File(path1);
					FileWriter fileWR = new FileWriter(file1);

					int control =0;
					int inizio=0;
					for(int h=0; h< result.length()-1;h++){
						if(result.charAt(h)==' ' && h!=0){
							control++;
						}

						if(h==result.length()-2){
							fileWR.write(result.substring(inizio)+'\n');			
						}

						else if(control>20 ||(result.charAt(h)==' ') && (result.charAt(h+1)==' ') ||(result.charAt(h)=='.') &  result.charAt(h+1)==' ' ){
							fileWR.write(result.substring(inizio, h)+'\n');		        			
							inizio=h+1;
							control=0;
						}

					}     

					fileWR.flush();
					System.out.println("Write file "+ path1);        
					fileWR.close();        

				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}	}


	}
}



