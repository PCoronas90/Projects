package org.explore3.searchengine.warc;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.zip.GZIPInputStream;



public class WarcReader {


public static  void WarcWriter(File file) throws IOException {
    // use a callback class for handling WARC record data:
	//GZIPInputStream gzInputStream=new GZIPInputStream(new FileInputStream(file));
	//DataInputStream inStream=new DataInputStream(gzInputStream);  
	GZIPInputStream gzInputStream=new GZIPInputStream(new FileInputStream(file));
    DataInputStream inStream=new DataInputStream(gzInputStream);

    WarcRecord thisWarcRecord;
    while ((thisWarcRecord=WarcRecord.readNextWarcRecord(inStream))!=null) {
      if (thisWarcRecord.getHeaderRecordType().equals("response")) {
        WarcHTMLResponseRecord htmlRecord=new WarcHTMLResponseRecord(thisWarcRecord);
        
        String ContentUtf8 = htmlRecord.getRawRecord().getContentUTF8();
        String thisContentUtf8="";
        int i=0;
        int j=0;
        boolean find=false;
        
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
        


        try {
         String path1="C:/Users/Pietro/Desktop/dataset/"+ htmlRecord.hashCode()+".htm";
        
         File file1 = new File(path1);
         FileWriter fileWR = new FileWriter(file1);
         
         fileWR.write(thisContentUtf8);
         fileWR.flush();
         System.out.println("Write file "+ path1);
        
         fileWR.close();

          
          
        }
        catch (IOException e) {
          e.printStackTrace();
        }
      }
        
       
		
			
		}

        // handle WARC record content:
        //processor.process(thisTargetURI, thisContentUtf8);
      }
    }

  

