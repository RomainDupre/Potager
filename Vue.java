/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package blokus;

import blokus.LegumeModele.Legumes;
import blokus.ListeLegumes.Tomates;

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

    public int listeImage[][];

    public int indicesX = 0;
    public int indicesY = 0;

    public Image iconeLegume;


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

    /*public void AttribuerImage(Case uneCase)
    {
        switch(uneCase.legume.getClass()) {

            case Salade.class:
                indicesX = listeImage[SALADE][0];
                indicesY = listeImage[SALADE][1];
                break;
            case Chapignon.class:
                indicesX = listeImage[CHAMPIGNON][0];
                indicesY = listeImage[CHAMPIGNON][1];
                break;
            case Orange.class:
                indicesX = listeImage[ORANGE][0];
                indicesY = listeImage[ORANGE][1];
                break;
            case Citron.class:
                indicesX = listeImage[CITRON][0];
                indicesY = listeImage[CITRON][1];
                break;
            case Betterave.class:
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

     */
    @Override
    public void update(Observable o, Object arg) {
        System.out.println("update done");
        int indicesX = 0;
        int indicesY = 0;
        for (int i = 0; i < modele.plateau.length; i++) {
            for (int j = 0; j < modele.plateau[i].length; j++) {
                if (modele.plateau[i][j].hasLegume()) {
                    // AttribuerImage(modele.plateau[i][j]);

                    this.setVisible(true);
                } else {
                    plateau[i][j].setBackground(Color.WHITE);
                }
            }
        }
    }
}
