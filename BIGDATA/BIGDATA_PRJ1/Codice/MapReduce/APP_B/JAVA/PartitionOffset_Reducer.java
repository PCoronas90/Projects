package EsercizioB;



import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class PartitionOffset_Reducer extends
		Reducer<Text, IntWritable, Text, IntWritable> {

	public void reduce(Text key, Iterable<IntWritable> values, Context context)
			throws IOException, InterruptedException {

		int totale = 0;
		for (IntWritable counteggio : values)
			totale += counteggio.get();

		context.write(key, new IntWritable(totale));

	}

}
