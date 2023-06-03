/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package blokus;

import blokus.LegumeModele.Legumes;
import blokus.ListeLegumes.*;
import blokus.Tools.Pelle;
import blokus.Tools.Seau;
import blokus.Tools.ToolCase;
import blokus.Tools.Tools;

import java.awt.*;

import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import javax.imageio.ImageIO;
import javax.swing.*;

import javax.swing.border.Border;

import static blokus.ListeIndiceLegume.*;
import static java.awt.Color.blue;

/**
 *
 * @author frederic
 */
public class Vue extends JFrame implements Observer {

    private Modele modele;
    private Case[][] plateau = new Case[Modele.TAILLE][Modele.TAILLE];

    public static Legumes[] legumes;


    private BufferedImage imageTerre;
    private BufferedImage imageSceau;
    private BufferedImage imagePelle;

    public static Tools[] tools = {
        new Pelle(),
        new Seau(),
    };
    static {
        try {
            legumes = new Legumes[]{
                new Salades(),
                new Champignons(),
                new Oranges(),
                new Citrons(),
                new Betteraves(),
            };
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public int listeImage[][] = new int[10][2];


    public Map hmap = new HashMap();

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
            x += 365 ;
        }

        build();

    }

    public void build() {

        BufferedImage image = null; // chargement de l'image globale
        try {
            image = ImageIO.read(new File("LegumeModele/data.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.imageTerre = null; // chargement de l'image de la terre
        try {
            this.imageTerre = ImageIO.read(new File("LegumeModele/TerreSec.jpg"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.imageSceau = null; // chargement de l'image de la terre
        try {
            this.imageSceau = ImageIO.read(new File("LegumeModele/sceau.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.imagePelle = null; // chargement de l'image de la terre
        try {
            this.imagePelle = ImageIO.read(new File("LegumeModele/pelle.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.hmap.put("Salade",image.getSubimage( 0,0, 148, 159));
        this.hmap.put("Champignon",image.getSubimage( 387,0, 148, 159));
        this.hmap.put("Orange",image.getSubimage( 779,0, 148, 159));
        this.hmap.put("Citron",image.getSubimage( 1150,0, 160, 159));
        this.hmap.put("Betterave",image.getSubimage( 1550,0, 160, 159));

        modele.monStock = new Stock(legumes);

        // Menu Bar
        JMenuBar jm = new JMenuBar();
        JMenu m = new JMenu("Jeu");
        JMenuItem mi = new JMenuItem("Partie");
        m.add(mi);
        jm.add(m);
        setJMenuBar(jm);

        // Window
        setTitle("Potager");
        setSize(1920, 1080);
        JComponent panelRight = new JPanel();
        JComponent pan = new JPanel (new GridLayout(10,10));
        JComponent panelPlantation = new JPanel(new GridLayout(2,10));
        panelPlantation.setSize(20,400);
       // panelPlantation.setLayout(new BorderLayout());
       JComponent panelRecolte = new JPanel(new GridLayout(3,10));
        panelRight.add(panelPlantation);
        panelRight.add(panelRecolte,"span");

        Border blackline = BorderFactory.createLineBorder(Color.black,1);
        panelRight.setBorder(blackline);
        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pan, panelRight);

        split.setDividerLocation(1100);
        panelPlantation.setBorder(blackline);
        panelRecolte.setBorder(blackline);


        for (int j = 0; j < legumes.length; j++) {
            LegumeCase label = new LegumeCase(legumes[j]);
            modele.monStock.mesLegumesEnStocks[j] = label.getLegume();
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setVerticalAlignment(JLabel.CENTER);
            BufferedImage im = attribuerImage(legumes[j]);
            Image iconeLegume = im.getScaledInstance(100, 100, Image.SCALE_SMOOTH); // icône redimentionnée
            label.setIcon(new ImageIcon(iconeLegume));
            System.out.print(label);
            panelRecolte.add(label);
            panelRecolte.add(new Label ("x" + Integer.toString(modele.monStock.GetNbrLegumeEnStock(label.getLegume()))));

        }


        for (int i = 0; i < tools.length; i++){
            ToolCase toolCase = new ToolCase(tools[i]);
            if(toolCase.getLabel() =="Sceau")
            {
                Image iconeLegume = imageSceau.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                toolCase.setIcon(new ImageIcon(iconeLegume));
                toolCase.setHorizontalAlignment(JLabel.CENTER);
                toolCase.setVerticalAlignment(JLabel.CENTER);
            }
            if(toolCase.getLabel() =="Pelle")
            {
                Image iconeLegume = imagePelle.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                toolCase.setIcon(new ImageIcon(iconeLegume));
                toolCase.setHorizontalAlignment(JLabel.CENTER);
                toolCase.setVerticalAlignment(JLabel.CENTER);
            }

            toolCase.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    modele.setToolsSelected(true);
                    modele.setLegumeSelected(false);
                    modele.setToolsSelected(((ToolCase)e.getSource()).getTool());
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
            panelPlantation.add(toolCase);
        };
        // panelPlantation should have each legume in a jlabel
        for (int i = 0; i < legumes.length; i++) {
            LegumeCase label = new LegumeCase(legumes[i]);
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setVerticalAlignment(JLabel.CENTER);
            BufferedImage im = attribuerImage(legumes[i]);
            Image iconeLegume = im.getScaledInstance(100, 100, Image.SCALE_SMOOTH); // icône redimentionnée
            label.setIcon(new ImageIcon(iconeLegume));


            label.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                   modele.setLegumeSelected(true);
                   modele.setToolsSelected(false);
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
            panelPlantation.add(label);
        }

        for(int i = 0; i<modele.plateau.length; i++){
            for (int j = 0; j<modele.plateau[i].length;j++){
                Case uneCase = new Case(i, j, null);
                uneCase.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if(modele.isToolsSelected()) {
                            modele.harverstLegumeInCase(((Case) e.getSource()).x, ((Case) e.getSource()).y);


                        } else {
                            String legumeLabel = modele.getLegumeSelected().getLabel();
                            Legumes monLegumeClique = null;
                            switch(legumeLabel){
                                case "Orange":
                                    try {
                                        monLegumeClique = new Oranges();
                                    } catch (IOException ex) {
                                        throw new RuntimeException(ex);
                                    }
                                    break;
                                case "Champignon":
                                    try {
                                        monLegumeClique = new Champignons();
                                    } catch (IOException ex) {
                                        throw new RuntimeException(ex);
                                    }
                                    break;
                                case "Citron":
                                    try {
                                        monLegumeClique = new Citrons();
                                    } catch (IOException ex) {
                                        throw new RuntimeException(ex);
                                    }
                                    break;
                                case "Salade":
                                    try {
                                        monLegumeClique = new Salades();
                                    } catch (IOException ex) {
                                        throw new RuntimeException(ex);
                                    }break;
                                case "Tomate":
                                    try {
                                        monLegumeClique = new Tomates();
                                    } catch (IOException ex) {
                                        throw new RuntimeException(ex);
                                    }break;
                                case "Betterave":
                                    try {
                                        monLegumeClique = new Betteraves();
                                    } catch (IOException ex) {
                                        throw new RuntimeException(ex);
                                    }break;
                            }
                                modele.plantLegumeInCase(((Case)e.getSource()).x, ((Case)e.getSource()).y, monLegumeClique);

                        }
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
                uneCase.setHorizontalAlignment(JLabel.CENTER);
                uneCase.setVerticalAlignment(JLabel.CENTER);
                plateau[i][j] = uneCase;
                pan.add(uneCase);
            }
        }
        pan.setBorder(blackline);
       add(split);
    }

    public BufferedImage attribuerImage(Legumes unLegume)
    {
        BufferedImage bf_legume = switch (unLegume.getLabel()) {
            case "Salade" -> (BufferedImage) this.hmap.get("Salade");
            case "Champignon" -> (BufferedImage) this.hmap.get("Champignon");
            case "Orange" -> (BufferedImage) this.hmap.get("Orange");
            case "Citron" -> (BufferedImage) this.hmap.get("Citron");
            case "Betterave" -> (BufferedImage) this.hmap.get("Betterave");
            default -> null;
        };
        return bf_legume;
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("update done");
       /* if(modele.getLegumeRecolte() != null) {


        }

        */
        for (int i = 0; i < modele.plateau.length; i++) {
            for (int j = 0; j < modele.plateau[i].length; j++) {
                if (modele.plateau[i][j].hasLegume()) {
                    BufferedImage im = attribuerImage(modele.plateau[i][j].getLegume());
                    int croissance = modele.plateau[i][j].getLegume().getCroissance();
                    Image iconeLegume = im.getScaledInstance(30 + 70 * croissance / 100, 30 + 60 * croissance / 100, Image.SCALE_SMOOTH); // icône redimentionnée
                    plateau[i][j].setIcon(new ImageIcon(iconeLegume));
                    plateau[i][j].setBackground(new Color(Integer.parseInt("#6d3305".substring(1), 16)));
                } else {
                    Image iconeLegume = imageTerre.getScaledInstance(200, 100, Image.SCALE_SMOOTH);
                    plateau[i][j].setIcon(new ImageIcon(iconeLegume));

                    plateau[i][j].setVisible(true);
                }
            }
        }
    }
}
