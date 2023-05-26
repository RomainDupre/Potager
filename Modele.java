package blokus;

import blokus.LegumeModele.Legumes;
import blokus.ListeLegumes.Tomates;

import javax.swing.*;
import java.io.IOException;
import java.util.Observable;

public class Modele extends Observable implements Runnable{

    public static final int TAILLE = 10;
    private boolean isLegumeSelected = false;
    private Legumes legumeSelected = null;
    private boolean isToolsSelected = false;
    private Object toolsSelected = null;

    public boolean isLegumeSelected() {
        return isLegumeSelected;
    }

    public void setLegumeSelected(boolean legumeSelected) {
        isLegumeSelected = legumeSelected;
    }

    public Legumes getLegumeSelected() {
        return legumeSelected;
    }

    public void setLegumeSelected(Legumes legumeSelected) {
        this.legumeSelected = legumeSelected;
        System.out.println("Legume selected: " + legumeSelected.getLabel());
    }

    public boolean isToolsSelected() {
        return isToolsSelected;
    }

    public void setToolsSelected(boolean toolsSelected) {
        isToolsSelected = toolsSelected;
    }

    public Object getToolsSelected() {
        return toolsSelected;
    }

    public void setToolsSelected(Object toolsSelected) {
        this.toolsSelected = toolsSelected;
    }

    Case[][] plateau = new Case[TAILLE][TAILLE];


    public Modele() {
        super();
        Thread t = new Thread(this);
        t.start();
        // Random value between 0 and 1
    }


    @Override
    public void run() {
        try {
            Init();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        while(true) {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            setChanged();
            notifyObservers();
        }
    }

    /**
     * Modify the plateau to random values
     */
    public void Init() throws IOException {
        for (int i = 0; i < TAILLE; i++) {
            for (int j = 0; j < TAILLE; j++) {
                plateau[i][j] = new Case(10, 10, null);
            }
        }
    }

    public void plantLegumeInCase(int x, int y, Legumes legume){
        if(!plateau[x][y].hasLegume()) {
            plateau[x][y].plantLegume(legume);
            setChanged();
            notifyObservers();
        }
    }

}
