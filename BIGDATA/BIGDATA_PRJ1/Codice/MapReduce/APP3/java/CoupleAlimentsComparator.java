package App3;

import java.nio.ByteBuffer;

import org.apache.hadoop.io.WritableComparator;
import org.apache.hadoop.io.IntWritable;

public class CoupleAlimentsComparator extends WritableComparator {
    protected CoupleAlimentsComparator() {
        super (IntWritable.class);
    }

   
    //Identico alla versione precedente. Trovato online
    @Override
    public int compare(byte[] b1, int j1, int k1, byte[] b2, int j2, int k2) {
        Integer a = ByteBuffer.wrap(b1, j1, k1).getInt();
        Integer b = ByteBuffer.wrap(b2, j2, k2).getInt();
        return a.compareTo(b) * -1;
    }
}
