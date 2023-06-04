package blokus.ListeLegumes;

import blokus.Croissance.Croissance;
import blokus.LegumeModele.Legumes;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Oranges extends Legumes {


    public Croissance croissance = new Croissance(75, 50, 25, 10, 5);
    public Oranges() throws IOException {
        super(new Croissance(75, 50, 25, 10, 5));
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
        return "Orange";
    }
}
