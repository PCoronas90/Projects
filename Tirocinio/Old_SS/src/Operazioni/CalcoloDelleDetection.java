package Operazioni;

import java.util.ArrayList;

import FunzioniErrore.Derf;
import FunzioniMat.FunzioniMatematiche;
import Segnali.Pr;

public class CalcoloDelleDetection {

	//Calcola la detection tramite simulazione
	public static double calcoloDetection(double soglia,Pr pr){
		double cont=0;
		for(int i=0;i<pr.getPr().size();i++){
			if (pr.getPr().get(i)>=soglia){
				cont++;
			}}


		return (double)100/(double)(pr.getPr().size()/cont);

	}

	//Calcola la detection teorica
	public static double calcoloDetectionTeorica(double soglia,Pr pr){

		double M = FunzioniMatematiche.CalcoloMedia(pr.getPr());
		double V= FunzioniMatematiche.CalcoloVarianza(pr.getPr());
		double Z= ((soglia-M)/(Math.sqrt(2*V)));
		return (Derf.derfc(Z)/2)*100;

	}
	
	//Calcola la power detection
    public static double calcoloPowerDetection(double soglia2, ArrayList<Double> potenza){
    	double cont1=0;
		for(int i=0;i<potenza.size();i++){
			if (potenza.get(i)>soglia2){
				cont1++;
			}}


		return (double)100/(double)(potenza.size()/cont1);
    }


}
