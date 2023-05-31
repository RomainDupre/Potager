package blokus.ListeLegumes;

import blokus.Croissance.Croissance;
import blokus.LegumeModele.Legumes;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Salades extends Legumes {


    public Croissance croissance = new Croissance(75, 50, 25, 10, 5);
    public Salades() throws IOException {


        super(0, 0);
        BufferedImage image = ImageIO.read(new File("LegumeModele/data.png")); // chargement de l'image globale

        BufferedImage tomate = image.getSubimage(0, 0, 160, 160); // image du légume le légume (x, y : coin supérieur gauche, w, h : largeur et hauteur)

        Image iconeTomate = tomate.getScaledInstance(100, 100, Image.SCALE_SMOOTH); // icône redimentionnée
        this.image = iconeTomate;
    }
    public Salades(float masse, float prix) throws IOException {
        super(masse, prix);
        BufferedImage image = ImageIO.read(new File("LegumeModele/data.png")); // chargement de l'image globale

        BufferedImage tomate = image.getSubimage(365, 0, 160, 160); // image du légume le légume (x, y : coin supérieur gauche, w, h : largeur et hauteur)

        Image iconeTomate = tomate.getScaledInstance(100, 100, Image.SCALE_SMOOTH); // icône redimentionnée
        this.image = iconeTomate;
        System.out.println("Tomate créée");
        System.out.println("Masse : " + masse);
        System.out.println("Prix : " + prix);
        System.out.println(image);
    }

    @Override
    public void pousser(float water, float sun) {
        croissance.updateCroissance(water, sun);
    }

    public int getCroissance() {
        return croissance.getCroissance();
    }

    @Override
    public String getLabel() {
        return "Salade";
    }


}
