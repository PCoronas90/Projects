package App3;
import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class CoupleAlimentsReducer2 extends Reducer<IntWritable, Text, Text, IntWritable>  {
	
	private int c = 0;

	//Primi 10 risultati. Non ho trovato meglio di un contatore esterno. Funziona
	//solo se dichiara private fuori dal metodo reduce.
	public void reduce(IntWritable key, Iterable<Text> values,
			Context context) throws IOException, InterruptedException {

		for (Text value : values) {
			if(c<10)
				context.write(value, key);
			else break;
			c++;
		}
		
	}
}
