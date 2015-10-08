package EsercizioB;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class Main {

	public static void main(String[] args) throws Exception {		
		
		Configuration conf = new Configuration();
		Job job = new Job(conf, "Partition_Offset");		

		job.setJarByClass(Main.class);
		job.setMapperClass(PartitionOffset_Mapper.class);
		job.setCombinerClass(PartitionOffset_Reducer.class);
		job.setReducerClass(PartitionOffset_Reducer.class);

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		FileInputFormat.addInputPath(job, new Path(args[0]));	
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		job.waitForCompletion(true);
		
	}}
		