package Operazioni;

import java.util.ArrayList;

import FunzioniErrore.InvErf;
import FunzioniMat.FunzioniMatematiche;
import Segnali.Pr;

public class CalcoloSoglie {

	//Calcola la soglia 
	public static  double calcoloSoglia(double Pfa,Pr pr) throws Exception{
		double M = FunzioniMatematiche.CalcoloMedia(pr.getPr());
		double V= FunzioniMatematiche.CalcoloVarianza(pr.getPr());

		double soglia=M+ Math.sqrt(2*V)*InvErf.InvErf(1-2*Pfa);
		return soglia;
	}
	

    public static double calcoloSogliaPD(double Pfa,ArrayList<Double> potenza) throws Exception{

    	    double M1 = FunzioniMatematiche.CalcoloMedia(potenza);
    		double V1= FunzioniMatematiche.CalcoloVarianza(potenza);

    		double soglia1=M1+ Math.sqrt(2*V1)*InvErf.InvErf(1-2*Pfa);
    		return soglia1;
    }}
