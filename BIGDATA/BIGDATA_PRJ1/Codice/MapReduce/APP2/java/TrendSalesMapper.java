package App2;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.util.Date;


public class TrendSalesMapper extends Mapper<LongWritable, Text, Text, Text> {

	private static final IntWritable one = new IntWritable(1);
	
	
	private Text word = new Text();
	private Text dataText = new Text();
	private Date dataSoglia = new Date(2015, 4, 1);
	
	public void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		
		
		String line = value.toString();
		
		StringTokenizer tokenizer = new StringTokenizer(line, ",");
		
		dataText.set(tokenizer.nextToken());
		
		while (tokenizer.hasMoreTokens()){
			word.set(tokenizer.nextToken());
			try {
				if(getDate(dataText.toString()).before(dataSoglia))
					context.write(word, dataText);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private Date getDate(String data) throws ParseException{
		String string = data;
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		Date date = format.parse(string);
		return date;
	}
	
}

