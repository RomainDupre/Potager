package blokus.ListeLegumes;

import blokus.Croissance.Croissance;
import blokus.LegumeModele.Legumes;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Citrons extends Legumes {
    public Citrons() throws IOException {
        super(new Croissance(75, 50, 25, 10, 5));
    }

    @Override
    public void pousser(float water, float sun) {
        croissance.updateCroissance(water, sun);
    }

    @Override
    public String getLabel() {
        return "Citron";
    }

    public int getCroissance() {
        return croissance.getCroissance();
    }
}
