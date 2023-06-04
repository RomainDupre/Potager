package blokus;

import blokus.LegumeModele.Legumes;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

public class LegumeCase extends JLabel implements Observer {
    private Legumes legume;

    public LegumeCase(Legumes legume) {
        this.legume = legume;
    }

    public Legumes getLegume() {
        return legume;
    }
    @Override
    public void update(Observable o, Object arg) {

    }
}
