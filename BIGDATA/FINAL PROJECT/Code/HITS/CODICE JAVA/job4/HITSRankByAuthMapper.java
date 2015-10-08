package BigData.FinalProject.job4;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;


public class HITSRankByAuthMapper extends MapReduceBase implements Mapper<LongWritable, Text, DoubleWritable, Text> {
    
    public void map(LongWritable key, Text value, OutputCollector<DoubleWritable, Text> output, Reporter arg3) throws IOException {
    	int tabUserIndex = value.find("\t");
        int tabAuthIndex = value.find("\t", tabUserIndex + 1);
        
        String user = Text.decode(value.getBytes(), 0, tabUserIndex);
        String auth = Text.decode(value.getBytes(), tabUserIndex+1, tabAuthIndex-(tabUserIndex+1));
        
        output.collect(new DoubleWritable(Double.parseDouble(auth)), new Text(user));
    }
    
}
