/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package blokus;

import blokus.LegumeModele.Tomates;

import java.awt.Color;
import java.awt.GridLayout;

import java.awt.event.*;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;

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
                Case uneCase = new Case(i, j, null);
                uneCase.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        try {
                            modele.plantLegumeInCase(((Case)e.getSource()).x, ((Case)e.getSource()).y, new Tomates(10.0F, 10.0f));

                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        ((Case)e.getSource()).setLabel("salade");
                                //(ImageIcon iconeSalade = salade.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));
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
        System.out.println("update done");
        for (int i = 0; i < modele.plateau.length; i++) {
            for (int j = 0; j < modele.plateau[i].length; j++) {
                if (modele.plateau[i][j].hasLegume() && !modele.plateau[i][j].hasIcone()) {
                    JLabel label = new JLabel(new ImageIcon(modele.plateau[i][j].legume.image));
                    plateau[i][j].add(label);
                    modele.plateau[i][j].isIconeSet(true);
                } else {
                    plateau[i][j].setBackground(Color.WHITE);
                }
            }
        }
    }
}
