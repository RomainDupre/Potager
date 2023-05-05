/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package blokus;

import java.awt.Color;
import java.awt.GridLayout;

import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JMenu;

import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicComboBoxUI.ItemHandler;

/**
 *
 * @author frederic
 */
public class Vue extends JFrame implements Observer {

    private Modele modele;
    private Case[][] plateau = new Case[Modele.TAILLE][Modele.TAILLE];


    Vue(Modele modele) {
        super();
        this.modele = modele;
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent arg0) {
                super.windowClosing(arg0);
                System.exit(0);
            }
        });
        build();

    }

    public void build() {
        // Menu Bar
        JMenuBar jm = new JMenuBar();
        JMenu m = new JMenu("Jeu");
        JMenuItem mi = new JMenuItem("Partie");
        m.add(mi);
        jm.add(m);
        setJMenuBar(jm);

        // Window
        setTitle("Ma première fenêtre");
        setSize(400, 400);
        JComponent pan = new JPanel (new GridLayout(10,10));

        Border blackline = BorderFactory.createLineBorder(Color.black,1);


        for(int i = 0; i<modele.plateau.length;i++){
            for (int j = 0; j<modele.plateau[i].length;j++){
                Case uneCase = new Case(i, j);
                uneCase.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        modele.changeColor(((Case)e.getSource()).x, ((Case)e.getSource()).y);
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {

                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {

                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {

                    }

                    @Override
                    public void mouseExited(MouseEvent e) {

                    }
                });
                uneCase.setBorder(blackline);
                plateau[i][j] = uneCase;
                pan.add(uneCase);
            }
        }
        pan.setBorder(blackline);
        add(pan);
    }


    @Override
    public void update(Observable o, Object arg) {
        for (int i = 0; i < modele.plateau.length; i++) {
            for (int j = 0; j < modele.plateau[i].length; j++) {
                if (modele.plateau[i][j]) {
                    plateau[i][j].setBackground(Color.BLACK);
                } else {
                    plateau[i][j].setBackground(Color.WHITE);
                }
            }
        }
    }
}
