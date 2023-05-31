package blokus;

import blokus.LegumeModele.Legumes;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

public class LegumeCase extends JLabel implements Observer {
    private Legumes legume;
    private String label;

    public LegumeCase(Legumes legume) {
        this.legume = legume;
        this.label = legume.getLabel();
        add(new JLabel(label));
    }

    public String getLabel() {
        return label;
    }

    public Legumes getLegume() {
        return legume;
    }
    @Override
    public void update(Observable o, Object arg) {

    }
}
