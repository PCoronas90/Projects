package App3;

import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class CoupleAlimentsMapper2 extends Mapper<LongWritable, Text, IntWritable, Text>{

	private Text word = new Text();
	private IntWritable conteggio = new IntWritable();

	@Override
	public void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {

		//Splitto la stringa
		String[] result2 = value.toString().split("\t");
		
		word.set(result2[0]);
		conteggio.set(Integer.parseInt(result2[1]));
		context.write(conteggio, word);
		
	}
}
