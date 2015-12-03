package it.sp4te.css.agents;

import it.sp4te.css.model.Signal;

/** <p>Titolo:PrimaryUser</p>
 * <p>Descrizione: Classe che modella utenti secondari fidati  </p>
 * @author Pietro Coronas
 * **/

public class PrimaryUser {



	public Signal createAndSend(int length){
		Signal s = new Signal(length);
		return s;
	}
}
