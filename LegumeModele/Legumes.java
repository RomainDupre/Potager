package blokus.LegumeModele;

import blokus.Maladie;

import java.awt.*;

public abstract class Legumes {
    private float masse;
    private float prix;
    private Maladie maladie;
    public Image image;
    public Legumes() {
        super();
    }

    public Legumes(float masse, float prix) {
        super();
        this.masse = masse;
        this.prix = prix;
        this.maladie = new Maladie();
        this.image = null;
    }

    public abstract void pousser();

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
