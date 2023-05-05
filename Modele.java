package blokus;

import java.util.Observable;

public class Modele extends Observable implements Runnable{

    public static final int TAILLE = 10;


    boolean[][] plateau = new boolean[TAILLE][TAILLE];


    public Modele() {
        super();
        Thread t = new Thread(this);
        t.start();
        // Random value between 0 and 1
    }


    @Override
    public void run() {
        randomize();


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
    public void randomize() {
        for (int i = 0; i < TAILLE; i++) {
            for (int j = 0; j < TAILLE; j++) {
                plateau[i][j] = Math.random() < 0.5;
            }
        }
    }

    public void changeColor(int x, int y) {
        plateau[x][y] = !plateau[x][y];
        setChanged();
        notifyObservers();
    }
}
