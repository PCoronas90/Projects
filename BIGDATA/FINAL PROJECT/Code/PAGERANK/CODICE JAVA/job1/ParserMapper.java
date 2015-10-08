package PageRank.Twitter.job1;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

@SuppressWarnings("deprecation")
public class ParserMapper extends MapReduceBase implements Mapper<LongWritable, Text, Text, Text>{
	private Text firstUscente = new Text();
	private Text secondUscente = new Text();
	
	public void map(LongWritable key, Text value, OutputCollector<Text, Text> output, Reporter reporter) throws IOException {
		String line = value.toString();		
		StringTokenizer tokenizer = new StringTokenizer(line);
		String follow = tokenizer.nextToken();
		String follower = tokenizer.nextToken();
		
		
		this.firstUscente.set(follow);	
		this.secondUscente.set("out >"+follower);
		
		output.collect(firstUscente, secondUscente);
		//il firstEntrante è seguito dal secondEntrante
		
	}
}