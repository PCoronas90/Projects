package PageRank.Twitter.job1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

@SuppressWarnings("deprecation")
public class ParserReducer
extends MapReduceBase implements Reducer<Text, Text, Text, Text> {
	
		 public void reduce(Text key, Iterator<Text> values, OutputCollector<Text, Text> out, Reporter reporter) throws IOException {		//lista dei seguti
			List<String> followOUT = new ArrayList<String>();
			
			
			Text list= new Text();
			while(values.hasNext()){
			Text value = values.next();
				if(value.toString().contains("out >")){					
					followOUT.add(value.toString().substring(5));
				} 
				
				String listFollow = followOUT.toString().substring(1,followOUT.toString().length()-1).replaceAll(", ", ",");				
				list.set(listFollow);				
			}
			out.collect(new Text(key+"\t"+"1.0"),list);
		}
	
}
			