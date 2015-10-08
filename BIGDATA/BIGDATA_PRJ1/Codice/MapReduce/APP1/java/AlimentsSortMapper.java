package App1;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class AlimentsSortMapper extends Mapper<LongWritable, Text, IntWritable, Text>	{
	
	private int number;
	private Text word = new Text();

	public void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {

		String line = value.toString();
		StringTokenizer tokenizer = new StringTokenizer(line);
		word.set(tokenizer.nextToken());
		number = Integer.parseInt(tokenizer.nextToken());
		
		context.write(new IntWritable(number), word);
		}
}


