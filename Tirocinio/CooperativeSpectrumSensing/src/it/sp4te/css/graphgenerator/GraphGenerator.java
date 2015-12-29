package it.sp4te.css.graphgenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * <p>Titolo:GraphGenerator</p>
 * <p>Descrizione: Interfaccia per le classi di creazione dei grafici</p>
 * @author Pietro Coronas**/

public interface GraphGenerator {

	/**
	 * Metodo per la creazione del grafico SNR-Detection
	 * 
	 * @param title Titolo del grafico
	 * @param detection Mappa che ha come chiave il nome della curva da visualizzare e come valore una lista con le percentuali di 
	 * Detection al variare dell'SNR.
	 * @param inf Estremo inferiore di SNR su cui � stata effettuata la simulazione
	 * @param sup Estremo superiore di SNR su cui � stata effettuata la simulazione
	 * @throws IOException 
	 **/
	
	public  void drawSNRtoDetectionGraph(String title,HashMap<String, ArrayList<Double>> detection, int inf, int sup) throws IOException;

	/**
	 * Metodo per la creazione del grafico SNR-Detection e il salvataggio al path specificato
	 * 
	 * @param title Titolo del grafico
	 * @param detection Mappa che ha come chiave il nome della curva da visualizzare e come valore una lista con le percentuali di 
	 * Detection al variare dell'SNR.
	 * @param inf Estremo inferiore di SNR su cui � stata effettuata la simulazione
	 * @param sup Estremo superiore di SNR su cui � stata effettuata la simulazione
	 * @param path Destinazione in cui salvare l'immagine
	 * @throws IOException 
	 **/
	
	public  void drawAndSaveSNRtoDetectionGraph(String title,HashMap<String, ArrayList<Double>> detection, int inf, int sup,String path) throws IOException;
	
	/**
	 * Metodo per la creazione del grafico % Utenti Malevoli-Detection
	 * 
	 * @param title Titolo del grafico
	 * @param detection Mappa che ha come chiave il nome della curva da visualizzare e come valore una lista con le percentuali di 
	 * Detection al variare dell'SNR.
	 * @param inf Estremo inferiore di SNR su cui � stata effettuata la simulazione
	 * @param sup Estremo superiore di SNR su cui � stata effettuata la simulazione
	 * @throws IOException 
	 **/
	
	public  void drawMaliciousUsersToDetectionGraph(String title,HashMap<String, ArrayList<Double>> detection, int inf, int sup) throws IOException;
	
	/**
	 * Metodo per la creazione del grafico % Utenti Malevoli-Detection e salvataggio su path specificata
	 * 
	 * @param title Titolo del grafico
	 * @param detection Mappa che ha come chiave il nome della curva da visualizzare e come valore una lista con le percentuali di 
	 * Detection al variare dell'SNR.
	 * @param inf Estremo inferiore di SNR su cui � stata effettuata la simulazione
	 * @param sup Estremo superiore di SNR su cui � stata effettuata la simulazione
	 * @param path Destinazione in cui salvare l'immagine
	 * @throws IOException 
	 **/
	
	public  void drawAndSaveMaliciousUsersToDetectionGraph(String title,HashMap<String, ArrayList<Double>> detection, int inf, int sup,String path) throws IOException;

	/**
	 * Metodo per la creazione del grafico MDT-SNR e salvataggio su path specificata
	 * 
	 * @param title Titolo del grafico
	 * @param detection Mappa che ha come chiave il nome della curva da visualizzare e come valore una lista con le percentuali di 
	 * Detection al variare dell'SNR.
	 * @param inf Estremo inferiore di SNR su cui � stata effettuata la simulazione
	 * @param sup Estremo superiore di SNR su cui � stata effettuata la simulazione
	 * @param path Destinazione in cui salvare l'immagine
	 * @throws IOException 
	 **/
	
	public  void drawAndSaveMDTtoSNRGraph(String title,HashMap<String, ArrayList<Double>> detection, int inf, int sup,String path) throws IOException;


	/**
	 * Metodo per la creazione del grafico SNR-MDT
	 * 
	 * @param title Titolo del grafico
	 * @param detection Mappa che ha come chiave il nome della curva da visualizzare e come valore una lista con le percentuali di 
	 * Detection al variare dell'SNR.
	 * @param inf Estremo inferiore di SNR su cui � stata effettuata la simulazione
	 * @param sup Estremo superiore di SNR su cui � stata effettuata la simulazione
	 * @throws IOException 
	 **/
	public void drawMDTtoSNRGraph(String title, HashMap<String, ArrayList<Double>> detection, int inf, int sup) throws IOException;

	public  void drawAndSaveMDTtoSNRRatioGraph(String title,HashMap<String, ArrayList<Double>> detection, int inf, int sup,String path)throws IOException;
	public  void drawMDTtoSNRRatioGraph(String title,HashMap<String, ArrayList<Double>> detection, int inf, int sup,String path)throws IOException;
}
