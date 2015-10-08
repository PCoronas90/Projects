package Main;



import java.util.ArrayList;

import Generatore.GeneratoreElementi;
import Grafico.GeneratoreGrafici;

import InterfacciaGrafica.Panel;
import InterfacciaGrafica.TabbedPane;
import Operazioni.CalcoloMomenti;
import Segnali.Pr;
import Segnali.Segnale;


public class BaseClass {

	
	
	
	public static  void avviamento(ArrayList<String> asd,Panel panel) throws Exception {

		
		int lunghezza=Integer.parseInt(asd.get(0));
		int prove= Integer.parseInt(asd.get(1));
		int inf= Integer.parseInt(asd.get(2));
		int sup= Integer.parseInt(asd.get(3));
		
		
		Segnale s = new Segnale(lunghezza);
              
		//Momenti di segnale+rumore//
        ArrayList<CalcoloMomenti> Calc = GeneratoreElementi.generatoreMomenti(s,lunghezza,s.CalcoloPotenza(),prove,inf,sup);
       
    	//Momenti di solo rumore//
		ArrayList<CalcoloMomenti> Calc2 = GeneratoreElementi.generatoreMomenti(null,lunghezza,s.CalcoloPotenza(),prove,inf,sup);
		
        //Potenza del segnale+rumore//
		ArrayList<ArrayList<Double> > potenza1= GeneratoreElementi.generatorePotenza(Calc);
		
        //Potenza del solo rumore//
		ArrayList<ArrayList<Double> > potenza2= GeneratoreElementi.generatorePotenza(Calc2);
		
        //Potenza con incertezza di segnale+rumore//
		ArrayList<ArrayList<Double> > POTENZA1= GeneratoreElementi.generatorePotenzaIncertezza(s,lunghezza,s.CalcoloPotenza(),1,prove,inf,sup);
		
        //Potenza con incertezza del solo rumore//
		ArrayList<ArrayList<Double> > POTENZA2= GeneratoreElementi.generatorePotenzaIncertezza(null,lunghezza,s.CalcoloPotenza(),1,prove,inf,sup);
		

		ArrayList<Pr> Pr1 = GeneratoreElementi.generatorePr(Calc);
		

		ArrayList<Pr> Pr2 = GeneratoreElementi.generatorePr(Calc2);
		
		
	
	panel.setVisible(false);
	GeneratoreGrafici.graficoMetodoImplSim(Pr1,Pr2);
    GeneratoreGrafici.graficoMetodoImpTeorico(Pr1,Pr2);
	GeneratoreGrafici.graficiAconfronto(POTENZA1,POTENZA2,Pr1,Pr2);
    GeneratoreGrafici.graficoPD(potenza1,potenza2,Pr1);
 

	
    new TabbedPane(Pr1,Pr2,potenza1,potenza2,POTENZA1,POTENZA2);

		}
		}
