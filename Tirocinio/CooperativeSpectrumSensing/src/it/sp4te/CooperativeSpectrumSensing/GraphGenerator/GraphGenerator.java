package it.sp4te.CooperativeSpectrumSensing.GraphGenerator;

import static com.googlecode.charts4j.Color.*;

import java.awt.BorderLayout;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.googlecode.charts4j.AxisLabels;
import com.googlecode.charts4j.AxisLabelsFactory;
import com.googlecode.charts4j.AxisStyle;
import com.googlecode.charts4j.AxisTextAlignment;
import com.googlecode.charts4j.Color;
import com.googlecode.charts4j.Data;
import com.googlecode.charts4j.Fills;
import com.googlecode.charts4j.GCharts;
import com.googlecode.charts4j.Line;
import com.googlecode.charts4j.LineChart;
import com.googlecode.charts4j.LineStyle;
import com.googlecode.charts4j.LinearGradientFill;
import com.googlecode.charts4j.Plots;
import com.googlecode.charts4j.Shape;

/** Classe per la creazione dei grafici **/

public class GraphGenerator {

	/**
	 * Il metodo prende in input una mappa (nomeDetection->ValoriDiDetection) e
	 * gli estremi di SNR su cui � stata effettuata la simulazione. In output
	 * produce un grafico SNR->% di Detection con tante linee quanti sono gli
	 * elementi nella mappa
	 **/

	public static void drawGraph(HashMap<String, ArrayList<Double>> detection, int inf, int sup) throws IOException {

		ArrayList<Line> lines = new ArrayList<Line>();
		for (String graphName : detection.keySet()) {
			Color lineColor = generateColor();
			Line line = Plots.newLine(Data.newData(detection.get(graphName)), lineColor, graphName);
			line.setLineStyle(LineStyle.newLineStyle(2, 1, 0));
			line.addShapeMarkers(Shape.CIRCLE, lineColor, 8);
			lines.add(line);
		}

		// Definisco il chart
		LineChart chart = GCharts.newLineChart(lines);
		chart.setSize(665, 450); // Massima dimensione
		chart.setTitle("Detection Methods", WHITE, 14);

		// Definisco lo stile
		AxisStyle axisStyle = AxisStyle.newAxisStyle(WHITE, 12, AxisTextAlignment.CENTER);

		// Etichetta asse y(% di detection)
		AxisLabels yAxis = AxisLabelsFactory.newNumericRangeAxisLabels(0, 100);
		yAxis.setAxisStyle(axisStyle);

		// Etichetta asse x(SNR in DB)
		AxisLabels xAxis1 = AxisLabelsFactory.newNumericRangeAxisLabels(inf, sup);
		xAxis1.setAxisStyle(axisStyle);

		// Etichetta asse x
		AxisLabels xAxis3 = AxisLabelsFactory.newAxisLabels("SNR (Decibel)", 50.0);
		xAxis3.setAxisStyle(AxisStyle.newAxisStyle(WHITE, 14, AxisTextAlignment.CENTER));

		// Etichetta asse y
		AxisLabels yAxis3 = AxisLabelsFactory.newAxisLabels("% of Detection", 50.0);
		yAxis3.setAxisStyle(AxisStyle.newAxisStyle(WHITE, 14, AxisTextAlignment.CENTER));

		// Aggiungo al chart
		chart.addXAxisLabels(xAxis1);
		chart.addYAxisLabels(yAxis);
		chart.addXAxisLabels(xAxis3);
		chart.addYAxisLabels(yAxis3);

		// Parametri generali su aspetto
		chart.setBackgroundFill(Fills.newSolidFill(Color.newColor("1F1D1D")));
		chart.setAreaFill(Fills.newSolidFill(Color.newColor("708090")));
		LinearGradientFill fill = Fills.newLinearGradientFill(0, Color.newColor("363433"), 100);
		fill.addColorAndOffset(Color.newColor("2E2B2A"), 0);
		chart.setAreaFill(fill);

		// Mostro il grafico tramite java swing
		displayUrlString(chart.toURLString());
	}

	/** Metodo per visualizzare il grafico in una finestra Java Swing **/
	private static void displayUrlString(final String urlString) throws IOException {
		JFrame frame = new JFrame();
		JLabel label = new JLabel(new ImageIcon(ImageIO.read(new URL(urlString))));
		frame.getContentPane().add(label, BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);

	}

	/** Metodo per la generazione del colore Random. **/
	private static Color generateColor() {
		Double Random = Math.random() * 100;
		if (Random < 10) {
			return Color.WHITE;
		} else if (Random > 10 & Random < 20) {
			return Color.INDIGO;
		} else if (Random > 20 & Random < 30) {
			return Color.BLUE;
		} else if (Random > 30 & Random < 40) {
			return Color.YELLOW;
		} else if (Random > 40 & Random < 50) {
			return Color.GREEN;
		} else if (Random > 50 & Random < 60) {
			return Color.AQUA;
		} else if (Random > 60 & Random < 70) {
			return Color.AZURE;
		} else if (Random > 70 & Random < 80) {
			return Color.VIOLET;
		} else if (Random > 80 & Random < 90) {
			return Color.CYAN;
		} else
			return Color.PURPLE;
	}

}
