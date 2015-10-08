package EsercizioA;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;

public class Coppie implements WritableComparable<Coppie> {

	private Text sx;
	private Text dx;
	

	public Coppie(){
	}
	
	public Coppie(Text sx,Text right){
		this.sx = sx;
		this.dx = right;
	}
	
	public void write(DataOutput output) throws IOException {
		output.writeUTF(sx.toString());
		output.writeUTF(dx.toString());
	}

	
	public void readFields(DataInput input) throws IOException {
		sx = new Text(input.readUTF());
		dx = new Text(input.readUTF());
	}

	public void set(String Sx,String Dx){
		sx = new Text(Sx);
		dx = new Text(Dx);
	}
		
	public int compareTo(Coppie couple) {
		int compare = sx.compareTo(couple.sx);
		if(compare==0)
			return dx.compareTo(couple.dx);
		else{
			return compare;
		}
	}
	
	@Override
	public String toString() {
		return sx.toString()+","+dx.toString();
	}

	public Text getSx() {
		return sx;
	}

	public Text getDx() {
		return dx;
	}

}
