package blokus;

public class Maladie {
    public int probabilite;
    public String nom;
    public String description;
    public int propagation;
    public Maladie(int probabilite, String nom, String description, int propagation) {
        super();
        this.probabilite = probabilite;
        this.nom = nom;
        this.description = description;
        this.propagation = propagation;
    }

    public int getProbabilite() {
        return probabilite;
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public int getPropagation() {
        return propagation;
    }
}
