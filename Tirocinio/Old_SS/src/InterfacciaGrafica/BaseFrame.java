package InterfacciaGrafica;

import javax.swing.*;


/**
 * Implementazione di base JFrame che definisce delle impostazioni
 */
public class BaseFrame extends JFrame {

   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

BaseFrame() {
      super();

      this.setTitle("Programma Cognitive Radio-Pietro Coronas");
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setSize(350, 100);

      this.setVisible(true);
   }
}
