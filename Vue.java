/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package blokus;

import blokus.LegumeModele.Legumes;
import blokus.ListeLegumes.*;

import java.awt.*;

import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import javax.imageio.ImageIO;
import javax.swing.*;

import javax.swing.border.Border;

import static blokus.ListeIndiceLegume.*;

/**
 *
 * @author frederic
 */
public class Vue extends JFrame implements Observer {

    private Modele modele;
    private Case[][] plateau = new Case[Modele.TAILLE][Modele.TAILLE];

    public static Legumes[] legumes;

    static {
        try {
            legumes = new Legumes[]{
                new Tomates()
            };
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public int listeImage[][] = new int[10][2];

    public int indicesX = 0;
    public int indicesY = 0;


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
        int x = 0;
        int y = 0;
        for(int i =0;i<10;i++)
        {
            listeImage[i][0] = x;
            listeImage[i][1] = y;
            x += 365;
        }

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
        setTitle("Potager");
        setSize(400, 400);
        JComponent pan = new JPanel (new GridLayout(10,10));
        JComponent rightPanel = new JPanel(new GridLayout(5,10));
        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pan, rightPanel);

        split.setDividerLocation(300);
        Border blackline = BorderFactory.createLineBorder(Color.black,1);
        rightPanel.setBorder(blackline);

        // rightPanel should have each legume in a jlabel
        for (int i = 0; i < legumes.length; i++) {
            LegumeCase label = new LegumeCase(legumes[i]);

            label.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                   modele.setLegumeSelected(true);
                   modele.setLegumeSelected(((LegumeCase)e.getSource()).getLegume());
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
            rightPanel.add(label);
        }

        for(int i = 0; i<modele.plateau.length; i++){
            for (int j = 0; j<modele.plateau[i].length;j++){
                Case uneCase = new Case(i, j, null);
                uneCase.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {

                        modele.plantLegumeInCase(((Case)e.getSource()).x, ((Case)e.getSource()).y, modele.getLegumeSelected());
                        //((Case)e.getSource()).setLabel("salade");
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
        add(split);
    }

    public void AttribuerImage(Case uneCase)
    {
        switch(uneCase.getLegume().getLabel()) {

            case "Salade":
                indicesX = listeImage[SALADE][0];
                indicesY = listeImage[SALADE][1];
                break;
            case "Champignon":
                indicesX = listeImage[CHAMPIGNON][0];
                indicesY = listeImage[CHAMPIGNON][1];
                break;
            case "Orange":
                indicesX = listeImage[ORANGE][0];
                indicesY = listeImage[ORANGE][1];
                break;
            case "Citron":
                indicesX = listeImage[CITRON][0];
                indicesY = listeImage[CITRON][1];
                break;
            case "Betterave":
                indicesX = listeImage[BETTERAVE][0];
                indicesY = listeImage[BETTERAVE][1];
                break;
        }


        BufferedImage image = null; // chargement de l'image globale
        try {
            image = ImageIO.read(new File("LegumeModele/data.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        BufferedImage legume = image.getSubimage(indicesX ,indicesY, 160, 160); // image du légume le légume (x, y : coin supérieur gauche, w, h : largeur et hauteur)

        Image iconeLegume = legume.getScaledInstance(100, 100, Image.SCALE_SMOOTH); // icône redimentionnée

        JLabel label = new JLabel(new ImageIcon(iconeLegume));
        uneCase.add(label);
    }


    @Override
    public void update(Observable o, Object arg) {
        System.out.println("update done");
        int indicesX = 0;
        int indicesY = 0;
        for (int i = 0; i < modele.plateau.length; i++) {
            for (int j = 0; j < modele.plateau[i].length; j++) {
                if (modele.plateau[i][j].hasLegume()) {
                    AttribuerImage(modele.plateau[i][j]);
                    this.setVisible(true);
                } else {
                    plateau[i][j].setBackground(Color.WHITE);
                }
            }
        }
    }
}
