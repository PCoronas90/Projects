package App3;


	import java.io.IOException;

	import org.apache.hadoop.io.IntWritable;
	import org.apache.hadoop.io.Text;
	import org.apache.hadoop.mapreduce.Reducer;

	public class CoupleAlimentsReducer extends Reducer<Text, IntWritable, Text, IntWritable>  {
		
		
		public void reduce(Text key, Iterable<IntWritable> values,
				Context context) throws IOException, InterruptedException {

			int conteggioCoppie = 0;
			for (IntWritable value : values) {
				conteggioCoppie += value.get();
			}
			context.write(key, new IntWritable(conteggioCoppie));
		}
	}
