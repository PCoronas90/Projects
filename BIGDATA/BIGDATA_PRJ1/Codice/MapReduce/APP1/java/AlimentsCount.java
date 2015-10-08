package App1;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class AlimentsCount {
	public static void main(String[] args) throws Exception {
		System.out.print(args);
		
		Job job = new Job(new Configuration(), "AlimentsCount");
		job.setJarByClass(AlimentsCount.class);
		job.setMapperClass(AlimentsCountMapper.class);
		job.setReducerClass(AlimentsCountReducer.class);
			
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path("tmp/result"));

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);
		job.waitForCompletion(true);
		
		Job job2 = new Job(new Configuration(), "SortCount");
		
		job2.setJarByClass(AlimentsCount.class);
		job2.setMapperClass(AlimentsSortMapper.class);
		job2.setReducerClass(AlimentsSortReducer.class);
		job2.setSortComparatorClass(AlimentsComparator.class);

		FileInputFormat.addInputPath(job2, new Path("tmp/result"));
		FileOutputFormat.setOutputPath(job2, new Path(args[1]));

		job2.setOutputKeyClass(Text.class);
		job2.setOutputValueClass(IntWritable.class);
		job2.setMapOutputKeyClass(IntWritable.class);
		job2.setMapOutputValueClass(Text.class);
		job2.waitForCompletion(true);
		
		
	}
}
