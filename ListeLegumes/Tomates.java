package blokus.ListeLegumes;

import blokus.Croissance.Croissance;
import blokus.LegumeModele.Legumes;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Tomates extends Legumes {
    public Tomates() throws IOException {
        super(new Croissance(75, 40, 75, 50, 10));

    }

    @Override
    public void pousser(float water, float sun) {
        croissance.updateCroissance(water, sun);
    }

    @Override
    public String getLabel() {
        return "Tomate";
    }

    public int getCroissance() {
        return croissance.getCroissance();
    }
}
