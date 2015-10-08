package Grafico;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardXYItemLabelGenerator;
import org.jfree.chart.labels.XYItemLabelGenerator;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


import Operazioni.CalcoloDelleDetection;
import Operazioni.CalcoloSoglie;
import Segnali.Pr;



	public class GraficoPowerDetection extends Grafico {
	


		private static final long serialVersionUID = 1L;
		 ArrayList<ArrayList<Double> > Potenza1;
	     ArrayList<ArrayList<Double> > Potenza2;
	     ArrayList<Pr> PRSegnale;

		String titolo;
		

//Genera il grafico della power detection senza incertezza. Rappresenta il caso ideale
		public GraficoPowerDetection(String titolo, ArrayList<ArrayList<Double> > Potenza1,  ArrayList<ArrayList<Double> > Potenza2,ArrayList<Pr> prSegnale) throws Exception {


			super(titolo);
			this.titolo=titolo;
			this.Potenza1=Potenza1;
			this.Potenza2=Potenza2;
			this.PRSegnale=prSegnale;
			


			final XYDataset dataset = createDataset();

			final JFreeChart chart = createChart(dataset);

			final ChartPanel chartPanel = new ChartPanel(chart);

			chartPanel.setPreferredSize(new java.awt.Dimension(900, 470));

			setContentPane(chartPanel);

			
			
				try {


					ChartUtilities.saveChartAsPNG(new File("C:\\Grafico4.png"), chart, 650, 450);

					} catch (IOException ex) {

					System.out.println(ex.getLocalizedMessage());

					}}
		
			
				
			

		



		/**

		 * Creazione del dataset da utilizzare per la generazione del grafi

		 * Ogni grafico ha un suo dataset specifico

		 * @return un dataset di default.
		 * @throws Exception 

		 */

 public XYDataset createDataset() throws Exception {

			XYSeriesCollection dataset = new XYSeriesCollection();
            
		
			final XYSeries serie1 = generaSerie("Power Detection");
			dataset.addSeries(serie1);
			
			


			return dataset;

		}

		protected XYSeries generaSerie(String label) throws Exception {

			XYSeries serie = new XYSeries(label);
	        
			  
		      
		        	for(int i=0;i<this.Potenza1.size();i++){
						serie.add(this.PRSegnale.get(i).getMomenti().getSnr(),CalcoloDelleDetection.calcoloPowerDetection(CalcoloSoglie.calcoloSogliaPD(0.01,this.Potenza2.get(i)), this.Potenza1.get(i)));	
						}
		        
	
		        
			
			return serie;
		}

			
		/**

		 * Creazione del grafico.

		 * @param dataset  il dataset creato dal metodo createDataset

		 * @return il grafico.

		 */

public JFreeChart createChart(final XYDataset dataset) {
			
			final JFreeChart chart = ChartFactory.createXYLineChart(this.titolo, //titolo
				
					"SNR (Decibel)", //label asse delle X

					"Detection (%) ", //label asse dell Y

					dataset, // sorgente dei dati

					PlotOrientation.VERTICAL, //orientamento del grafico

					true, // mostra la legenda

					true, //usa i tooltip

					false
					

					);
			



			XYPlot plot = (XYPlot) chart.getPlot();
	        
			XYLineAndShapeRenderer renderer =  new XYLineAndShapeRenderer(true, true);
	         
			plot.setRenderer(renderer);
		
				plot.getRenderer().setSeriesPaint(0, Color.GREEN);	
			
			renderer.setBaseShapesVisible(true);

			renderer.setBaseShapesFilled(true);

			NumberFormat format = NumberFormat.getNumberInstance();

			format.setMaximumFractionDigits(2);
			

			XYItemLabelGenerator generator =

					new StandardXYItemLabelGenerator(

							StandardXYItemLabelGenerator.DEFAULT_ITEM_LABEL_FORMAT,

							format, format);

			renderer.setBaseItemLabelGenerator(generator);

			renderer.setBaseItemLabelsVisible(true);

			return chart;

		}

		



	}