package PageRank.Twitter.job2;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

@SuppressWarnings("deprecation")
public class RankReducer extends MapReduceBase implements Reducer<Text, Text, Text, Text> {

    private static final float damping = 0.85F;
    
    public void reduce(Text user, Iterator<Text> values, OutputCollector<Text, Text> out, Reporter reporter) throws IOException {
        boolean isExistingUser = false;
        String[] split;
        float sumShareOtherUserRanks = 0;
        String links = "";
        String userWithRank;
        
   
        while(values.hasNext()){
           userWithRank = values.next().toString();
            
            if(userWithRank.equals("!")) {
                isExistingUser = true;
                continue;
            }
            
            if(userWithRank.startsWith("|")){
                links = "\t"+userWithRank.substring(1);
                continue;
            }

            split = userWithRank.split("\\t");
            
            float pageRank = Float.valueOf(split[1]);
            int countOutLinks = Integer.valueOf(split[2]);
            
            sumShareOtherUserRanks += (pageRank/countOutLinks);
        }

        if(!isExistingUser) return;
        float newRank = damping * sumShareOtherUserRanks + (1-damping);

        out.collect(user, new Text(newRank + links));
    }
}
