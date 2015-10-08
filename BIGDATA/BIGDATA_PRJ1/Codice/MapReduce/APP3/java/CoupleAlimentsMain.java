package App3;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class CoupleAlimentsMain  {

	public static void main(String[] args) throws Exception {

		
		//Primo Job. Crea le coppie e le conteggia. 
		//Da tutte le coppie in ordine sparso
		Job job = new Job(new Configuration(), "App3_temp1");

		job.setJarByClass( CoupleAlimentsMain.class);
		
		job.setMapperClass(CoupleAlimentsMapper.class);
		job.setCombinerClass(CoupleAlimentsReducer.class);
		job.setReducerClass(CoupleAlimentsReducer.class);

		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);

		job.waitForCompletion(true);
		
		
		//Secondo job. Ordina le coppie in base alla quantità 
		//e prende i primi 10.
		Job job2 = new Job(new Configuration(), "App3_temp2");

		job2.setJarByClass(CoupleAlimentsMain.class);
		
		// custom comparator to reverse the order of sorting
		job2.setSortComparatorClass(CoupleAlimentsComparator.class);
		
		job2.setMapperClass(CoupleAlimentsMapper2.class);
		job2.setReducerClass(CoupleAlimentsReducer2.class);

		FileInputFormat.addInputPath(job2, new Path(args[1]));
		FileOutputFormat.setOutputPath(job2, new Path(args[2]));

		job2.setOutputKeyClass(IntWritable.class);
		job2.setOutputValueClass(Text.class);

		job2.waitForCompletion(true);
		
		
		
	}

}
