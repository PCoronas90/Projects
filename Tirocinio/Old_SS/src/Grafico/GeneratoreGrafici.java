package Grafico;

import java.util.ArrayList;

import org.jfree.ui.RefineryUtilities;

import Segnali.Pr;

public class GeneratoreGrafici {

	
	
	
	
public static void graficoMetodoImplSim(ArrayList<Pr> pr1, ArrayList<Pr>  pr2) throws Exception{
	GraficoMedotoImpSim grafico = new GraficoMedotoImpSim("Grafico Metodo Implementato(simulazione)",pr1,pr2);
	RefineryUtilities.centerFrameOnScreen(grafico);
	grafico.setVisible(false);
    grafico.pack();
	
}

public static void graficoMetodoImpTeorico(ArrayList<Pr> pr1, ArrayList<Pr>  pr2) throws Exception{
	GraficoMetodoImpTeorico grafico = new GraficoMetodoImpTeorico("Grafico Metodo Implementato(teorico)",pr1,pr2);
	RefineryUtilities.centerFrameOnScreen(grafico);
	grafico.setVisible(false);
	grafico.pack();

}

public static void graficiAconfronto(ArrayList<ArrayList<Double>> p1,ArrayList<ArrayList<Double>> p2,
		ArrayList<Pr> prs, ArrayList<Pr> prr) throws Exception{
	
	GraficiAconfronto grafico= new GraficiAconfronto("Grafici a confronto",p1,p2,prs,prr);
	RefineryUtilities.centerFrameOnScreen(grafico);
	grafico.setVisible(false);
	grafico.pack();
}

public static void graficoPD(ArrayList<ArrayList<Double>> P1,ArrayList<ArrayList<Double>> P2,
		ArrayList<Pr> prs) throws Exception{
	GraficoPowerDetection grafico = new GraficoPowerDetection("Grafico Della Power Detection senza incertezza",P1,P2,prs);
	RefineryUtilities.centerFrameOnScreen(grafico);
	grafico.setVisible(false);
	grafico.pack();}}


