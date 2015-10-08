package App3;


import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class CoupleAlimentsMapper extends Mapper<LongWritable, Text, Text, IntWritable>{

		private static final IntWritable one = new IntWritable(1);
		private Text word = new Text();

		public void map(LongWritable key, Text value, Context context)
				throws IOException, InterruptedException {

			String[] result1 = value.toString().split(",");
			CoupleAliments coppia;
			
			// Salto le date
			for(int i=1; i<result1.length; i++) {
				String element1 = result1[i];
				for(int j=i+1; j<result1.length; j++) {
					// Creo la coppia
					coppia = new CoupleAliments(element1, result1[j]);
					word.set(coppia.toString());
					context.write(word, one);
				}	}	}	
	}
