package EsercizioA;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class Couple_Mapper extends Mapper<Object, Text, Coppie, IntWritable> {

	private Coppie couple = new Coppie();
	private IntWritable one = new IntWritable(1);

	public void map(Object key, Text value, Context context)
			throws IOException, InterruptedException {

		ArrayList<String> line = new ArrayList<String>();
		StringTokenizer values = new StringTokenizer(value.toString(), ",");
		if (values.hasMoreTokens())
			values.nextToken();
		while (values.hasMoreTokens()) {
			String word = values.nextToken();
			line.add(word);
		}
		for (int i = 0; i < line.size(); i++) {
			couple.set(line.get(i), "NULL");
			context.write(couple, one);
			for (int j = 0; j < line.size(); j++) {
				if (i != j) {
					couple.set(line.get(i), line.get(j));
					context.write(couple, one);
				}
			}
		}
	}
}
