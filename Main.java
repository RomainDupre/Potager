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
				try {
					Tomates tomates = new Tomates(10.0F, 10.0f);
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
				fenetre.setVisible(true);//On la rend visible
			}
		});
	}

}
