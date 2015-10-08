package PageRank.Twitter.job3;

import java.io.IOException;
import java.nio.charset.CharacterCodingException;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

@SuppressWarnings("deprecation")
public class FinalRankingMapper extends MapReduceBase implements Mapper<LongWritable, Text, FloatWritable, Text> {
    
    public void map(LongWritable key, Text value, OutputCollector<FloatWritable, Text> output, Reporter arg3) throws IOException {
        String[] userAndRank = getPageAndRank(key, value);
        
        float parseFloat = Float.parseFloat(userAndRank[1]);
        
        Text user = new Text(userAndRank[0]);
        FloatWritable rank = new FloatWritable(parseFloat);
        
        output.collect(rank, user);
    }
    
    private String[] getPageAndRank(LongWritable key, Text value) throws CharacterCodingException {
        String[] UserAndRank = new String[2];
        int tabUserIndex = value.find("\t");
        int tabRankIndex = value.find("\t", tabUserIndex + 1);
        
        // senza tab quando non ci sono collegamenti
        int end;
        if (tabRankIndex == -1) {
            end = value.getLength() - (tabUserIndex + 1);
        } else {
            end = tabRankIndex - (tabUserIndex + 1);
        }
        
        UserAndRank[0] = Text.decode(value.getBytes(), 0, tabUserIndex);
        UserAndRank[1] = Text.decode(value.getBytes(), tabUserIndex + 1, end);
        
        return UserAndRank;
    }
    
}
