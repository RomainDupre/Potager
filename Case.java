/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blokus;
import blokus.LegumeModele.Legumes;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;

/**
 *
 * @author frederic
 */
public class Case extends JLabel implements Observer {
    int x;
    int y;
    public Legumes legume;

    float humidity = 0;


    public Case(int x, int y) {
        this.x = x;
        this.y = y;
        setOpaque(true);
    }

    public Legumes getLegume() {
        return this.legume;
    }

    public Case(int x, int y, Legumes legume) {
        //this.x = x;
        //this.y = y;
        this(x, y);
        this.legume = legume;

    }

    public boolean hasLegume() {
        return legume != null;
    }

    public void setLabel(String s) {
        this.add(new JLabel(s));
    }
    public void plantLegume(Legumes legume) {
        this.legume = legume;
    }

    public void harvestLegume() {
        this.legume = null;
    }

    public void grow(float water, float sun) {
        if (legume != null) {
            legume.pousser(water, sun);
        }
    }
    @Override
    public void update(Observable o, Object arg) {

    }
}
