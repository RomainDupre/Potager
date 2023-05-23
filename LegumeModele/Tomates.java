package blokus.LegumeModele;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Tomates extends Legumes {


    public Tomates() throws IOException {


        super();
        BufferedImage image = ImageIO.read(new File("/LegumeModele/data.png")); // chargement de l'image globale

        BufferedImage tomate = image.getSubimage(0, 0, 20, 20); // image du légume le légume (x, y : coin supérieur gauche, w, h : largeur et hauteur)

        //Image iconeTomate = tomate.getScaledInstance(20, 20, Image.SCALE_SMOOTH); // icône redimentionnée
    }
    public Tomates(float masse, float prix) throws IOException {
        super(masse, prix);
        BufferedImage image = ImageIO.read(new File("LegumeModele/data.png")); // chargement de l'image globale

        BufferedImage tomate = image.getSubimage(0, 0, 20, 20); // image du légume le légume (x, y : coin supérieur gauche, w, h : largeur et hauteur)

        Image iconeTomate = tomate.getScaledInstance(20, 20, Image.SCALE_SMOOTH); // icône redimentionnée
        this.image = iconeTomate;
        System.out.println("Tomate créée");
        System.out.println("Masse : " + masse);
        System.out.println("Prix : " + prix);
        System.out.println(image);
    }
    public void pousser() {
        System.out.println("JE POUUUUUSSE!!!!!!!!!!!!!!");
    }


}
