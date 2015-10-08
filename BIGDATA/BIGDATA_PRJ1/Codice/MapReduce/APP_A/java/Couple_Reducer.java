package EsercizioA;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class Couple_Reducer extends
		Reducer<Coppie, IntWritable, Coppie, DoubleWritable> {

	private PriorityQueue<final_res> coda;
	private HashMap<String, Integer> prodotto2occorrenze = new HashMap<String, Integer>();

	private class final_res {
		public Integer conteggio;
		public String sx;
		public String dx;

		public final_res(String sx, String dx, Integer conteggio) {
			this.sx = sx;
			this.dx = dx;
			this.conteggio = conteggio;
		}
	};

	protected double percent(int occ, final_res res) {
		return Math.floor(((double) res.conteggio / (double) occ) * 100) / 100;
	}
	
	protected void compare() {
	coda = new PriorityQueue<final_res>(10, new Comparator<final_res>() {
		public int compare(final_res f1, final_res f2) {
			return f1.conteggio.compareTo(f2.conteggio);
		}
	});}
	
	protected void order(Context contex) throws IOException, InterruptedException{
		List<final_res> ress = new ArrayList<final_res>();
		while (!coda.isEmpty()) {
			ress.add(coda.remove());
		}
		for (int i = ress.size() - 1; i >= 0; i--) {
			final_res res = ress.get(i);
			int occ = prodotto2occorrenze.get(res.sx);
			contex.write(new Coppie(new Text(res.sx), new Text(res.dx)),
					new DoubleWritable(percent(occ, res)));
		}
		
	}
	
	@Override
	protected void setup(Context contex) {
		compare();
	}
	
	@Override
	protected void cleanup(Context contex) throws IOException, InterruptedException  {
		order(contex);
	}

	
	@Override
	protected void reduce(Coppie couple, Iterable<IntWritable> values, Context ctx)
			throws IOException, InterruptedException {

		int somma = 0;
		for (IntWritable val : values) {
			somma += val.get();
		}
		if (!couple.getDx().toString().equals("NULL"))
			coda.add(new final_res(couple.getSx().toString(), couple
					.getDx().toString(), somma));
		else
			prodotto2occorrenze.put(couple.getSx().toString(), somma);
	}

	
}