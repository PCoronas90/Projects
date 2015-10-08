package Grafico;



import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.ui.ApplicationFrame;

public abstract class Grafico extends ApplicationFrame  {

	
	
	public Grafico(String titolo) throws Exception {
		super(titolo);
		
		
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected abstract XYDataset createDataset() throws Exception;
	protected abstract XYSeries generaSerie(String label) throws Exception;
	protected abstract JFreeChart createChart(final XYDataset dataset) throws Exception;
}
