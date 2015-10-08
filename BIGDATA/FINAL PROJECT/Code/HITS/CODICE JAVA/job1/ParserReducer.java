package BigData.FinalProject.job1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

public class ParserReducer
extends MapReduceBase implements Reducer<Text, Text, Text, Text> {
	
		 public void reduce(Text key, Iterator<Text> values, OutputCollector<Text, Text> out, Reporter reporter) throws IOException {		//lista dei seguti
			List<String> followOUT = new ArrayList<String>();
			//Lista dei seguaci
			List<String> followersIN = new ArrayList<String>();
			Text list= new Text();
			while(values.hasNext()){
			Text value = values.next();
				if(value.toString().contains("out >")){					
					followOUT.add(value.toString().substring(5));
				} 
				if(value.toString().contains("in >")){
					followersIN.add(value.toString().substring(4));
				}
				String listFollow = followOUT.toString().substring(1,followOUT.toString().length()-1).replaceAll(", ", ",");
				String listFollowers = followersIN.toString().substring(1,followersIN.toString().length()-1).replaceAll(", ", ",");
				String listString = listFollow + "\t\t" + listFollowers;				
				list.set(listString);				
			}
			out.collect(new Text(key+"*1.0-1.0_"),list);
		}
	
}