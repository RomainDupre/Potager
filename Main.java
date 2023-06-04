/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package blokus;

import blokus.ListeLegumes.Tomates;

import javax.swing.SwingUtilities;
import java.io.IOException;

/**
 *
 * @author frederic
 */
public class Main {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){

				Modele m = new Modele();
				Vue fenetre = new Vue(m);
				m.addObserver(fenetre);
				fenetre.setVisible(true);//On la rend visible
			}
		});
	}

}
