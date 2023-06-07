/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package blokus;

import blokus.LegumeModele.Legumes;
import blokus.ListeLegumes.*;
import blokus.RecolteLegume.LegumesRecolte;
import blokus.RecolteLegume.Stock;
import blokus.Tools.*;

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

/**
 *
 * @author frederic
 */
public class Vue extends JFrame implements Observer {

    private Modele modele;
    private Case[][] plateau = new Case[Modele.TAILLE][Modele.TAILLE];

    public static Legumes[] legumes;

    public  LegumesRecolte[] tableauRecolte = new LegumesRecolte[legumes.length];


    private BufferedImage imageTerre;
    private BufferedImage imageSceau;
    private BufferedImage imagePelle;

    private BufferedImage imageSpray;

    public static Tools[] tools = {
        new Pelle(),
        new Seau(),
        new Spray(),
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


        ChargerLesImages();

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

        //panel on the right
        JComponent panelRight = new JPanel();
        //panel for plant legumes
        JComponent pan = new JPanel (new GridLayout(10,10));
        //panel to select each legume to plant
        JComponent panelPlantation = new JPanel(new GridLayout(4,10));
        panelPlantation.setSize(20,400);
        //panel to see all recolted legume
        JComponent panelRecolte = new JPanel(new GridLayout(3,10));
        panelRight.add(panelPlantation);
        panelRight.add(panelRecolte,"span");

        //add split bar
        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pan, panelRight);
        split.setDividerLocation(1100);

        //style
        Border blackline = BorderFactory.createLineBorder(Color.black,1);
        panelRight.setBorder(blackline);
        panelRecolte.setBorder(blackline);


        /// SHOW IMAGE ///

        //Create recolte legume panel with all legume we have
        for (int j = 0; j < legumes.length; j++) {
            LegumesRecolte label = new LegumesRecolte(legumes[j]);
            modele.monStock.mesLegumesEnStocks[j] = label.getLegume();
            this.tableauRecolte[j]= label;
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setVerticalAlignment(JLabel.CENTER);
            BufferedImage im = attribuerImage(legumes[j]);
            Image iconeLegume = im.getScaledInstance(100, 100, Image.SCALE_SMOOTH); // icône redimentionnée
            label.setIcon(new ImageIcon(iconeLegume));
            panelRecolte.add(label);
            panelRecolte.add(label.getNbrLegumeRecolte());
        }



        //create tool panel
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

            if(toolCase.getLabel() =="Spray")
            {
                Image iconeLegume = imageSpray.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
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

        //Garden
        for(int i = 0; i<modele.plateau.length; i++){
            for (int j = 0; j<modele.plateau[i].length;j++){
                Case uneCase = new Case(i, j, null);
                uneCase.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if(modele.isToolsSelected()) {
                            if(modele.getToolsSelected().getLabel()== "Pelle") modele.harverstLegumeInCase(((Case) e.getSource()).x, ((Case) e.getSource()).y);
                            else if (modele.getToolsSelected().getLabel() == "Spray") modele.healLegumeInCase(((Case) e.getSource()).x, ((Case) e.getSource()).y);
                            else if (modele.getToolsSelected().getLabel() == "Sceau") modele.arroser(((Case) e.getSource()).x, ((Case) e.getSource()).y);
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

    public BufferedImage filtreVert(BufferedImage image)
    {
        int width = image.getWidth();
        int height = image.getHeight();

        BufferedImage filteredImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = image.getRGB(x, y);

                // Extraire les composantes rouge, verte et bleue du pixel
                int red = (rgb >> 16) & 0xFF;
                int green = (rgb >> 8) & 0xFF;
                int blue = rgb & 0xFF;

                // Appliquer le filtre vert
                green = Math.min(255, green + 50); // Ajustez la valeur pour obtenir l'effet souhaité

                // Recréer le pixel avec les nouvelles composantes
                int newRgb = (red << 16) | (green << 8) | blue;
                filteredImage.setRGB(x, y, newRgb);
            }
        }

        return filteredImage;

    }

    public BufferedImage filtreRouge(BufferedImage image)
    {
        int width = image.getWidth();
        int height = image.getHeight();

        BufferedImage filteredImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // Parcourir tous les pixels de l'image
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = image.getRGB(x, y);

                // Extraire les composantes rouge, verte et bleue du pixel
                int red = (rgb >> 16) & 0xFF;
                int green = (rgb >> 8) & 0xFF;
                int blue = rgb & 0xFF;

                // Appliquer le filtre rouge
                red = Math.min(255, red + 50); // Ajustez la valeur pour obtenir l'effet souhaité

                // Recréer le pixel avec les nouvelles composantes
                int newRgb = (red << 16) | (green << 8) | blue;
                filteredImage.setRGB(x, y, newRgb);
            }
        }

        return filteredImage;
    }


    public void ChargerLesImages()
    {
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

        this.imageSpray = null; // chargement de l'image de la terre
        try {
            this.imageSpray = ImageIO.read(new File("LegumeModele/spray.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.hmap.put("Salade",image.getSubimage( 0,0, 148, 159));
        this.hmap.put("Champignon",image.getSubimage( 387,0, 148, 159));
        this.hmap.put("Orange",image.getSubimage( 779,0, 148, 159));
        this.hmap.put("Citron",image.getSubimage( 1150,0, 160, 159));
        this.hmap.put("Betterave",image.getSubimage( 1550,0, 160, 159));

    }

    @Override
    public void update(Observable o, Object arg) {

        for (int i = 0; i < modele.plateau.length; i++) {
            for (int j = 0; j < modele.plateau[i].length; j++) {
                if (modele.plateau[i][j].hasLegume()) {
                    BufferedImage im;
                    if(modele.plateau[i][j].getLegume().getCroissance() >= 100 && modele.plateau[i][j].getLegume().getMaladie()==null)
                    {
                         im =  filtreVert(attribuerImage(modele.plateau[i][j].getLegume()));
                    } else if (modele.plateau[i][j].getLegume().getMaladie() != null) {
                        im = filtreRouge(attribuerImage(modele.plateau[i][j].getLegume()));
                    } else
                    {
                         im = attribuerImage(modele.plateau[i][j].getLegume());
                    }
                    int croissance = modele.plateau[i][j].getLegume().getCroissance();
                    Image iconeLegume = im.getScaledInstance(30 + 70 * croissance / 100, 30 + 60 * croissance / 100, Image.SCALE_SMOOTH); // icône redimentionnée
                    plateau[i][j].setIcon(new ImageIcon(iconeLegume));

                    if(modele.plateau[i][j].humidity < 25)
                    {
                        plateau[i][j].setBackground(new Color(Integer.parseInt("#F7750F".substring(1), 16)));
                    }
                    else if(modele.plateau[i][j].humidity < 50)
                    {
                        plateau[i][j].setBackground(new Color(Integer.parseInt("#C85D09".substring(1), 16)));
                    }
                    else if (modele.plateau[i][j].humidity < 75)
                    {
                        plateau[i][j].setBackground(new Color(Integer.parseInt("#6d3305".substring(1), 16)));
                    }
                    else
                    {
                        plateau[i][j].setBackground(new Color(Integer.parseInt("#482306".substring(1), 16)));
                    }

                } else {
                    Image iconeLegume = imageTerre.getScaledInstance(200, 100, Image.SCALE_SMOOTH);
                    plateau[i][j].setIcon(new ImageIcon(iconeLegume));
                    plateau[i][j].setVisible(true);
                }
            }
        }

        for(int i = 0; i <tableauRecolte.length; i++) {
            tableauRecolte[i].setNbrLegumeRecolte(modele.monStock.GetNbrLegumeEnStock(tableauRecolte[i].getLegume()));
        }
    }
}
