package EsercizioA;


import java.io.IOException;

import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;


public class Main {

	public static void main(String[] args) throws ClassNotFoundException,
			IOException, InterruptedException {

		Configuration conf = new Configuration();
	    Job job = Job.getInstance(conf, "Occorrenza_Coppie");
	    job.setJarByClass(Main.class);
	    job.setMapperClass(Couple_Mapper.class);
	    job.setReducerClass(Couple_Reducer.class);
	    job.setOutputKeyClass(Coppie.class);
	    job.setOutputValueClass(IntWritable.class);
		FileInputFormat.addInputPath(job, new Path(args[0]));	

		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		job.waitForCompletion(true);
	}
}