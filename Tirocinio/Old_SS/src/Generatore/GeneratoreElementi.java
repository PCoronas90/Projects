package Generatore;

import java.util.ArrayList;

import Operazioni.CalcoloMomenti;
import Operazioni.CalcoloPotenzaIncertezza;
import Segnali.Pr;
import Segnali.Segnale;

public class GeneratoreElementi {

	//Questa classe genera i momenti, la potenza con e senza icnertezza ed i vettori Pr

	public static ArrayList<CalcoloMomenti> generatoreMomenti(Segnale s,int lunghezza,double potenza,int prove,int inf,int sup){
		ArrayList<CalcoloMomenti> Calc = new ArrayList<CalcoloMomenti>();	
		for(double i=inf;i<sup;i++){
			CalcoloMomenti m= new CalcoloMomenti(s,prove);
			m.calcolo(potenza,i,lunghezza);
			Calc.add(m);}
		return Calc;
	}

	public static  ArrayList<ArrayList<Double> > generatorePotenza(ArrayList<CalcoloMomenti> Calc){
		ArrayList<ArrayList<Double> > potenza = new ArrayList<ArrayList<Double> >();
		for(int i=0;i<Calc.size();i++){
			potenza.add(Calc.get(i).getPotenza());
		}
		return potenza;
	}

	public static ArrayList<ArrayList<Double> > generatorePotenzaIncertezza(Segnale s,int lunghezza,double potenza,double incertezza,int prove,int inf,int sup){
		ArrayList<ArrayList<Double> > POTENZA= new ArrayList<ArrayList<Double> >();
		for(int i=inf;i<sup;i++){
			CalcoloPotenzaIncertezza c= new CalcoloPotenzaIncertezza(s,potenza,incertezza,prove);
			POTENZA.add(c.CalcoloPotenza(i,lunghezza));
		}
		return POTENZA;
	}
	public static ArrayList<Pr>  generatorePr(ArrayList<CalcoloMomenti> Calc){
		ArrayList<Pr> Pr1 = new ArrayList<Pr>();
		for(int i=0;i<Calc.size();i++){
			Pr pr = new Pr(Calc.get(i));
			Pr1.add(pr);

		}
		return Pr1;
	}
}