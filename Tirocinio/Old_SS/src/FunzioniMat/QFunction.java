package FunzioniMat;

import FunzioniErrore.InvErf;

public class QFunction {

	
	
public static double calcolaQinv(double pfa) throws Exception{
	return Math.sqrt(2)*InvErf.InvErf(1-2*pfa);
}
}
