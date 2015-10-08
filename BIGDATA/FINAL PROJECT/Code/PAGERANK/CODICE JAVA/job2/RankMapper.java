package PageRank.Twitter.job2;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

@SuppressWarnings("deprecation")
public class RankMapper extends MapReduceBase implements Mapper<LongWritable, Text, Text, Text>{

    public void map(LongWritable key, Text value, OutputCollector<Text, Text> output, Reporter reporter) throws IOException {
        
    	int userTabIndex = value.find("\t");
        int rankTabIndex = value.find("\t", userTabIndex+1);

        String user = Text.decode(value.getBytes(), 0, userTabIndex);
        String userWithRank = Text.decode(value.getBytes(), 0, rankTabIndex+1);
        
        //Controllo
        output.collect(new Text(user), new Text("!"));

        // Salta gli utenti senza collegamenti
        if(rankTabIndex == -1) return;
        
        String list = Text.decode(value.getBytes(), rankTabIndex+1, value.getLength()-(rankTabIndex+1));
        String[] allOtherUser = list.split(",");
        int totalLinks = allOtherUser.length;
        
        for (String otherPage : allOtherUser){
            Text userRankTotalLinks = new Text(userWithRank + totalLinks);
            output.collect(new Text(otherPage), userRankTotalLinks);
        }
        
        
        output.collect(new Text(user), new Text("|"+list));
    }
}
