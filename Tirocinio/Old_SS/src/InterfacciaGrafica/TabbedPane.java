package InterfacciaGrafica;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;


import Operazioni.CalcoloDelleDetection;
import Operazioni.CalcoloSoglie;
import Segnali.Pr;

import java.util.ArrayList;

/**
 * Visualizza dei componenti in un JTabbedPane
 */
public class TabbedPane extends BaseFrame implements ItemListener{
	


   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public TabbedPane(ArrayList<Pr> Pr1, ArrayList<Pr>  Pr2,ArrayList<ArrayList<Double>> potenza1,ArrayList<ArrayList<Double>> potenza2,ArrayList<ArrayList<Double>> POTENZA1,ArrayList<ArrayList<Double>> POTENZA2) throws Exception  {
	  
      JTabbedPane tab = new JTabbedPane();
      tab.setPreferredSize(new Dimension(900, 700));
       
      Icon icon= new ImageIcon("C://Grafico1.png");
      Icon icon2= new ImageIcon("C://Grafico2.png");
      Icon icon3= new ImageIcon("C://Grafico3.png");
      Icon icon4= new ImageIcon("C://Grafico4.png");
      Icon icon5= new ImageIcon("BaseClass.jpg");
   
      
     
      
      
      JPanel panel = new JPanel();
      JLabel lbl = new JLabel();
      lbl.setIcon(icon);
      panel.add(lbl);
      tab.addTab("Metodo implementato(simulazione)", panel);

      JPanel second = new JPanel();
      JLabel lbl2=new JLabel();
      lbl2.setIcon(icon2);
      second.add(lbl2);
      tab.addTab("Metodo implementato", second);
      
      JPanel fifth = new JPanel();
      JLabel lbl3=new JLabel();
      lbl3.setIcon(icon3);
      fifth.add(lbl3);
      tab.addTab("Grafici a confronto", fifth);
      
      
      JPanel third = new JPanel();
      JLabel lbl5=new JLabel();
      lbl5.setIcon(icon4);
      third.add(lbl5);
      tab.addTab("Power Detection", third);
      
          
     JPanel fourth = new JPanel(new GridLayout(Pr1.size()*6, 1));
     fourth.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
    
     for(int i=0;i<Pr1.size();i++){
    		 		
      JLabel message= new JLabel("Con SNR uguale a "+Pr1.get(i).getMomenti().getSnr()+" Db");
      JLabel message2= new JLabel("Detection nel metodo implementato con incertezza (1db) pari a " + CalcoloDelleDetection.calcoloDetection(CalcoloSoglie.calcoloSoglia(0.01,Pr2.get(i)), Pr1.get(i))+" %");
      JLabel message3= new JLabel("Detection Teorica nel metodo implementato con incertezza (1db) pari a " +CalcoloDelleDetection.calcoloDetectionTeorica(CalcoloSoglie.calcoloSoglia(0.01,Pr2.get(i)), Pr1.get(i))+" %");
      JLabel message4= new JLabel("Power Detection senza incertezza pari a "+CalcoloDelleDetection.calcoloPowerDetection(CalcoloSoglie.calcoloSogliaPD(0.01,potenza2.get(i)), potenza1.get(i))+ "%");     
      JLabel message5= new JLabel("UPower Detection con incertezza (1DB) pari a "+CalcoloDelleDetection.calcoloPowerDetection(CalcoloSoglie.calcoloSogliaPD(0.01,POTENZA2.get(i)), POTENZA1.get(i))+ "%");  
      JLabel message6= new JLabel("");
    
     fourth.add(message);
     fourth.add(message2);
     fourth.add(message3);
     fourth.add(message4);
     fourth.add(message5);
     fourth.add(message6);
     }
     
     JScrollPane pane = new JScrollPane(fourth);
     tab.addTab("Valori Numerici",pane);
     
     JPanel six = new JPanel();
     JLabel lbl6=new JLabel();
     lbl6.setIcon(icon5);
     six.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
     six.add(lbl6);
     JScrollPane pane2 = new JScrollPane(six);



     tab.addTab("DettegliImplementativi",pane2);
     
     
     
     
 

  
    
    
  

      this.add(tab);
      this.pack();
   }

@Override
public void itemStateChanged(ItemEvent e) {
	switch(e.getStateChange()) {
    case(ItemEvent.SELECTED):
        dispose();
    	new Panel();
    case(ItemEvent.DESELECTED):
       break;
    default:
  
}

	
}}