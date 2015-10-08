package PageRank.Twitter.main;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.TextInputFormat;
import org.apache.hadoop.mapred.TextOutputFormat;


import PageRank.Twitter.job1.*;
import PageRank.Twitter.job2.*;
import PageRank.Twitter.job3.*;



@SuppressWarnings("deprecation")
public class TwitterRank {
	
	private static NumberFormat nf = new DecimalFormat("00");
	private static int iterations = 5;
    private static String amazonPath ="s3://explore2/output/";
      
    public static void main(String[] args) throws Exception {
        TwitterRank HITS = new TwitterRank();
        
       /*Parsing dell'input*/
       HITS.runParsing(args[0], amazonPath+"Twitter/Rank/iteration"+nf.format(0)); 
       /*Prima Iterazione*/
       //HITS.runFirstHITSCalculation("Twitter/Rank/iteration"+nf.format(0), "Twitter/Rank/iteration"+nf.format(1));
       /*Iterazioni Successive*/
       int runs = 0;
        for (; runs < iterations; runs++) {
            HITS.runHITSCalculation(amazonPath+"Twitter/Rank/iteration"+nf.format(runs), amazonPath+"Twitter/Rank/iteration"+nf.format(runs + 1));
        }
        
        HITS.runRankOrdering(amazonPath+"Twitter/Rank/iteration"+nf.format(runs), amazonPath+"Twitter/Rank/results");
        
    }
    
    private void runRANKParsing(String inputPath, String outputPath) throws IOException {
        JobConf conf = new JobConf(TwitterRank.class);
        
        
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
    
   

    private void runRANKCalculation(String inputPath, String outputPath) throws IOException {
        JobConf conf = new JobConf(TwitterRank.class);
        
        conf.setOutputKeyClass(Text.class);
        conf.setOutputValueClass(Text.class);
        
        conf.setInputFormat(TextInputFormat.class);
        conf.setOutputFormat(TextOutputFormat.class);
        
        FileInputFormat.setInputPaths(conf, new Path(inputPath));
        FileOutputFormat.setOutputPath(conf, new Path(outputPath));
        
        conf.setMapperClass(RankMapper.class);
        conf.setReducerClass(RankReducer.class);
        
        JobClient.runJob(conf);
    }
    
    
    
    private void runRankOrdering(String inputPath, String outputPath) throws IOException {
        JobConf conf = new JobConf(TwitterRank.class);
        
        conf.setOutputKeyClass(FloatWritable.class);
        conf.setOutputValueClass(Text.class);
        conf.setInputFormat(TextInputFormat.class);
        conf.setOutputFormat(TextOutputFormat.class);
        
        FileInputFormat.setInputPaths(conf, new Path(inputPath));
        FileOutputFormat.setOutputPath(conf, new Path(outputPath));
        
        conf.setMapperClass(FinalRankingMapper.class);
        
        JobClient.runJob(conf);
    }
}
