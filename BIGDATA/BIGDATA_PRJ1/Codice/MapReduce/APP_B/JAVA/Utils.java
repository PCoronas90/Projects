package EsercizioB;

import java.util.ArrayList;
import java.util.HashSet;
import org.apache.hadoop.io.Text;

public abstract class Utils {

	static HashSet<HashSet<Text>> createSet(HashSet<Text> starter, int max) {
		
		HashSet<HashSet<Text>> finalSet = new HashSet<HashSet<Text>>();
		
		if ( starter.isEmpty() ) {
			finalSet.add( new HashSet<Text>() );
			return finalSet;
		}
		
		ArrayList<Text> starterlist = new ArrayList<Text>(starter);
		Text head = starterlist.get(0);
		int sizeList= starterlist.size();
		
		HashSet<Text> body = new HashSet<Text>( starterlist.subList(1, sizeList) );
		for ( HashSet<Text> Offset :createSet(body, max) )
			if ( Offset.size() < max ) {
				HashSet<Text> newOffSet = new HashSet<Text>();
				newOffSet.add(head);
				newOffSet.addAll(Offset);				
				finalSet.add(newOffSet);
				finalSet.add(Offset);
			}
		return finalSet;
	}

}
