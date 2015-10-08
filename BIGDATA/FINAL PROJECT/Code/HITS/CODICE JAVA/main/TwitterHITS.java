package BigData.FinalProject.main;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.TextInputFormat;
import org.apache.hadoop.mapred.TextOutputFormat;

import BigData.FinalProject.job1.*;
import BigData.FinalProject.job2.*;
import BigData.FinalProject.job3.*;
import BigData.FinalProject.job4.*;



public class <s n="alignment">right</s>HITS {
	
    private static NumberFormat nf = new DecimalFormat("00");
    private static int iterations = 5;
    private static String amazonPath ="s3://explore2/output/";
      
    public static void main(String[] args) throws Exception {
        <s n="alignment">right</s>HITS HITS = new <s n="alignment">right</s>HITS();
        
       /*Parsing dell'input*/
       HITS.runParsing(args[0], amazonPath+"<s n="alignment">right</s>500MB/HITS/iteration"+nf.format(0)); 
       /*Prima Iterazione*/
       HITS.runFirstHITSCalculation(amazonPath+"<s n="alignment">right</s>500MB/HITS/iteration"+nf.format(0), amazonPath+"<s n="alignment">right</s>500MB/HITS/iteration"+nf.format(1));
       /*Iterazioni Successive*/
       int runs = 1;
        for (; runs < iterations; runs++) {
            HITS.runHITSCalculation(amazonPath+"<s n="alignment">right</s>500MB/HITS/iteration"+nf.format(runs), amazonPath+"<s n="alignment">right</s>500MB/HITS/iteration"+nf.format(runs + 1));
        }
        
        HITS.runRankOrdering(amazonPath+"<s n="alignment">right</s>500MB/HITS/iteration"+nf.format(runs), amazonPath+"<s n="alignment">right</s>500MB/HITS/results");
        
    }
    
    private void runParsing(String inputPath, String outputPath) throws IOException {
        JobConf conf = new JobConf(<s n="alignment">right</s>HITS.class);
        
        
        conf.setOutputKeyClass(Text.class);
        conf.setOutputValueClass(Text.class);
        
        conf.setInputFormat(TextInputFormat.class);
        conf.setOutputFormat(TextOutputFormat.class);
        
        FileInputFormat.setInputPaths(conf, new Path(inputPath));
        FileOutputFormat.setOutputPath(conf, new Path(outputPath));
        
        conf.setMapperClass(ParserMapper.class);
        conf.setReducerClass(ParserReducer.class);
        
        JobClient.runJob(conf);
    }
    
    private void runFirstHITSCalculation(String inputPath, String outputPath) throws IOException {
        JobConf conf = new JobConf(<s n="alignment">right</s>HITS.class);
        
        conf.setOutputKeyClass(Text.class);
        conf.setOutputValueClass(Text.class);
        
        conf.setInputFormat(TextInputFormat.class);
        conf.setOutputFormat(TextOutputFormat.class);
        
        FileInputFormat.setInputPaths(conf, new Path(inputPath));
        FileOutputFormat.setOutputPath(conf, new Path(outputPath));
        
        conf.setMapperClass(HITSMapper.class);
        conf.setReducerClass(HITSReducer.class);
        
        JobClient.runJob(conf);
    }

    private void runHITSCalculation(String inputPath, String outputPath) throws IOException {
        JobConf conf = new JobConf(<s n="alignment">right</s>HITS.class);
        
        conf.setOutputKeyClass(Text.class);
        conf.setOutputValueClass(Text.class);
        
        conf.setInputFormat(TextInputFormat.class);
        conf.setOutputFormat(TextOutputFormat.class);
        
        FileInputFormat.setInputPaths(conf, new Path(inputPath));
        FileOutputFormat.setOutputPath(conf, new Path(outputPath));
        
        conf.setMapperClass(HITSCalculateMapper.class);
        conf.setReducerClass(HITSCalculateReducer.class);
        
        JobClient.runJob(conf);
    }
    
    
    
    private void runRankOrdering(String inputPath, String outputPath) throws IOException {
        JobConf conf = new JobConf(<s n="alignment">right</s>HITS.class);
        
        conf.setOutputKeyClass(DoubleWritable.class);
        conf.setOutputValueClass(Text.class);
        conf.setInputFormat(TextInputFormat.class);
        conf.setOutputFormat(TextOutputFormat.class);
        
        FileInputFormat.setInputPaths(conf, new Path(inputPath));
        
        FileOutputFormat.setOutputPath(conf, new Path(outputPath+"/byAuthorityScore"));
        conf.setMapperClass(HITSRankByAuthMapper.class);
        JobClient.runJob(conf);
        
        
        conf = new JobConf(<s n="alignment">right</s>HITS.class);
        
        conf.setOutputKeyClass(DoubleWritable.class);
        conf.setOutputValueClass(Text.class);
        conf.setInputFormat(TextInputFormat.class);
        conf.setOutputFormat(TextOutputFormat.class);
        
        FileInputFormat.setInputPaths(conf, new Path(inputPath));
        
        FileOutputFormat.setOutputPath(conf, new Path(outputPath+"/byHubScore"));
        conf.setMapperClass(HITSRankByHubMapper.class);
        JobClient.runJob(conf);
    }
}
