package blokus.ListeLegumes;

import blokus.Croissance.Croissance;
import blokus.LegumeModele.Legumes;
import java.io.IOException;

public class Champignons extends Legumes {

    public Champignons() throws IOException {
        super(new Croissance(120, 60, 10, 5, 15));
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
        return "Champignon";
    }
}
