package Segnali;



import java.util.ArrayList;


import Operazioni.CalcoloMomenti;


/**
 * @author  Pietro
 */
public class Pr {
	/**
	 * @uml.property  name="momenti"
	 * @uml.associationEnd  
	 */
	CalcoloMomenti momenti;
	ArrayList<Double> m;
	ArrayList<Double> q;
	/**
	 * @uml.property  name="pr"
	 */
	ArrayList<Double> pr;

	public Pr( CalcoloMomenti momenti){
		this.momenti=momenti;
		this.pr= new ArrayList<Double>();
		this.m=this.momenti.getMedia();
		this.q=this.momenti.getQ();
		for(int i=0;i<m.size();i++){
			this.pr.add(i,(double) ((2*(Math.pow(m.get(i),2))-q.get(i))));
		}
	}


	/**
	 * @return
	 * @uml.property  name="momenti"
	 */
	public CalcoloMomenti getMomenti(){
		return this.momenti;
	}
    
	/**
	 * @return
	 * @uml.property  name="pr"
	 */
	public ArrayList<Double> getPr(){
		return this.pr;
	}
	
}
