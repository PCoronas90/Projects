package EsercizioB;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import EsercizioB.Utils;


public class PartitionOffset_Mapper  extends
		Mapper<LongWritable, Text, Text, IntWritable> {

	private final static int OffSet = 4;

	public void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {

		HashSet<Text> values = new HashSet<Text>();
		String line = value.toString();
		StringTokenizer token = new StringTokenizer(line, ",");			
		token.nextToken();			
		
		while ( token.hasMoreTokens() )
			values.add(new Text( token.nextToken() ));	
		
		HashSet<HashSet<Text>>  finalSet = Utils.createSet(values, OffSet);			
		for (HashSet<Text> subOffset :  finalSet)
			if ( !subOffset.isEmpty() ) {
				String subsequence = new String();
				Iterator<Text> iterator = subOffset.iterator();				
				while ( iterator.hasNext() )
					subsequence += iterator.next().toString() + " ";				
				context.write(new Text(subsequence), new IntWritable(1));
			}
		
	}
	


}