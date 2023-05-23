package blokus;

import blokus.LegumeModele.Legumes;
import blokus.LegumeModele.Tomates;

import java.io.IOException;
import java.util.Observable;

public class Modele extends Observable implements Runnable{

    public static final int TAILLE = 10;


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


        //while(true) {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            setChanged();
            notifyObservers();
        //}
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
        plateau[x][y].plantLegume(legume);
        setChanged();
        notifyObservers();
    }



    /*public void changeColor(int x, int y) {
        plateau[x][y] = !plateau[x][y];
        setChanged();
        notifyObservers();
    }*/



}
