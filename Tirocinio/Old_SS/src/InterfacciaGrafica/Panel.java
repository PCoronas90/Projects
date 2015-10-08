package InterfacciaGrafica;





import javax.swing.*;

import Main.BaseClass;




import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.util.ArrayList;

/**
 * Cattura il clic su un JButton
 */
public class Panel extends BaseFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton button;
	JLabel label;
	int click=0;
	int add=0;
	ArrayList<String> valori = new ArrayList<String>();
	/**
	 * @uml.property  name="bar"
	 * @uml.associationEnd  
	 */

	JLabel result;


	/**
	 * Crea un pulsante e un'etichetta,
	 * registra il metodo actionPerformed con il pulsante
	 */
	public Panel() {
	



		JPanel panel = new JPanel(new GridLayout(11, 3));
		panel.setBorder(
				BorderFactory.createEmptyBorder(10, 10, 10, 10));

		Image i=java.awt.Toolkit.getDefaultToolkit().getImage("images.png");
		i=i.getScaledInstance(580,40,Image.SCALE_DEFAULT);
		Icon icon= new ImageIcon(i);
		JLabel lbl = new JLabel();
		lbl.setIcon(icon);
		panel.add(lbl,JLabel.CENTER);

		Font font = new Font("Verdana", Font.BOLD, 12);
		panel.setFont(font);



		this.add(panel);




		JLabel label = new JLabel("Lunghezza del segnale [Campioni] (consigliati 400 campioni)",
				SwingConstants.CENTER);
		panel.add(label);
        String[] values = new String[]
				{"Scegli un valore","1000","600","400","200", "100"} ;
		JComboBox box = new JComboBox(values);
		box.addActionListener(this);
		
		box.setSize(60, 50);
		box.setEditable(true);
		box.addActionListener(this);
		panel.add(box);

		
		
		JLabel label2 = new JLabel("Numero di prove  (consigliate 500 prove)",
				SwingConstants.CENTER);
		panel.add(label2);
        String[] values2 = new String[]
				{"Scegli un valore","10000","1000","500","300","100","50"} ;
		JComboBox box2 = new JComboBox(values2);
		
		box2.setSize(60, 50);
		box2.setEditable(true);
		box2.addActionListener(this);
		panel.add(box2);



		JLabel label3 = new JLabel("Estremo inferiore intervallo SNR [DB] (consigliati -30DB)",
				SwingConstants.CENTER);
		panel.add(label3);
		String[] values3 = new String[]
				{"Scegli un valore","-40","-35","-30","-20","-10"} ;
		JComboBox box3 = new JComboBox(values3);
		
		box3.setSize(60, 50);
		box3.setEditable(true);
		box3.addActionListener(this);
		panel.add(box3);

		
		
		JLabel label4 = new JLabel("Estremo superiore intervallo SNR [DB] (consigliati 5 DB)",
				SwingConstants.CENTER);
		panel.add(label4);
		String[] values4 = new String[]
				{"Scegli un valore","0","5","10","15"} ;
		JComboBox box4 = new JComboBox(values4);
		box4.setSize(60, 50);
		
		box4.setEditable(true);
		box4.addActionListener(this);
		panel.add(box4);


		JLabel label5 = new JLabel("",
				SwingConstants.CENTER);
		panel.add(label5);
		this.pack();


        button = new JButton("Avvia Simulazione");
		button.setMnemonic(KeyEvent.VK_C );
		button.setSize(200,80);
		button.addActionListener(this);
		button.setLocation(700,700);

		panel.add(button);
        setPreferredSize(new Dimension(600,400));
		this.pack();
	
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JComboBox) {
	         JComboBox item = (JComboBox)e.getSource();
	         String value =(String)item.getSelectedItem();
	           
	     
	            
	         
	         if (!this.valori.contains(value))
	        	
	   	         this.valori.add(value);
	        
		}

		this.click++;	
		if(this.click>5){
			
		button.setEnabled(false);

		
			try {
				BaseClass.avviamento(this.valori,this);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
	
		
		}}
		
		
		
		

	       
     
	   

			
		
	
	public ArrayList<String> getValori(){
		return this.valori;
	}      

	

	/**
	 * @return
	 * @uml.property  name="bar"
	 */
	
	
	}