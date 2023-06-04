package blokus.LegumeModele;

import blokus.Croissance.Croissance;
import blokus.Maladie;

import java.awt.*;

public abstract class Legumes {
    private float masse = 0;
    private float prix = 0;
    private Maladie maladie = null;
    public Image image = null;

    public Croissance croissance;

    public Legumes() {
        super();
    }

    public Legumes(Croissance croissance) {
        super();
        this.croissance = croissance;

    }

    public abstract void pousser(float water, float sun);

    public abstract int getCroissance();
    public abstract String getLabel();

    //getter and setter for this classe

    public float getMasse() {
        return masse;
    }

    public float getPrix() {
        return prix;
    }

    public Maladie getMaladie() {
        return maladie;
    }

    public void setMasse(float masse) {
        this.masse = masse;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public void setMaladie(Maladie maladie) {
        this.maladie = maladie;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    private Image getImage() {
        return image;
    }


}
