package blokus;

import blokus.LegumeModele.Legumes;
import blokus.Meteo.Meteo;
import blokus.RecolteLegume.Stock;
import blokus.Tools.Tools;

import java.io.IOException;
import java.util.Observable;

public class Modele extends Observable implements Runnable{

    public static final int TAILLE = 10;
    private boolean isLegumeSelected = false;
    private Legumes legumeSelected = null;
    private boolean isToolsSelected = false;
    private Tools toolsSelected = null;
    private Maladie[] maladies = new Maladie[1];

    public Stock monStock;

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

    public Tools getToolsSelected() {
        return toolsSelected;
    }



    public void setToolsSelected(Tools toolsSelected) {
        this.toolsSelected = toolsSelected;
    }


    Case[][] plateau = new Case[TAILLE][TAILLE];

    public Meteo meteo = new Meteo(25, 80, 40);


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
                Thread.sleep(100);
                meteo.updateClockTick();
                refreshPlateau();
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
        this.maladies[0] = new Maladie(1, "Maladie de plante", "La plante arrête sa croissance et peut contaminer els autres palntes", 3);
        for (int i = 0; i < TAILLE; i++) {
            for (int j = 0; j < TAILLE; j++) {
                plateau[i][j] = new Case(10, 10, null);
            }
        }
    }

    private void refreshPlateau(){
        for(int i = 0; i < plateau.length; i++){
            for(int j = 0; j < plateau[i].length; j++){
                if(plateau[i][j].hasLegume()) {
                    for(int k = 0; k < maladies.length; k++){
                        int random = (int)(Math.random() * 100);
                        if(random < maladies[k].getProbabilite()){
                            plateau[i][j].getLegume().setMaladie(maladies[k]);
                        }
                    }

                    if(plateau[i][j].legume.getMaladie() != null){
                        int random2 = (int)(Math.random() * 100);
                        if(random2 > plateau[i][j].legume.getMaladie().getPropagation()){
                            int random3 = (int)(Math.random() * 3);
                            switch (random3){
                                case 0:
                                    if(plateau[i-1][j].legume != null)
                                    plateau[i-1][j].legume.setMaladie(plateau[i][j].legume.getMaladie());
                                case 1:
                                    if(plateau[i+1][j].legume != null)
                                    plateau[i+1][j].legume.setMaladie(plateau[i][j].legume.getMaladie());
                                case 2:
                                    if(plateau[i][j-1].legume != null)
                                    plateau[i][j-1].legume.setMaladie(plateau[i][j].legume.getMaladie());
                                case 3:
                                    if(plateau[i][j+1].legume != null)
                                    plateau[i][j+1].legume.setMaladie(plateau[i][j].legume.getMaladie());
                            }
                        }
                    }

                    if(plateau[i][j].humidity < meteo.getCurrentHumidite()) {
                        plateau[i][j].humidity = meteo.getCurrentHumidite();
                    }
                    float sun = meteo.getCurrentTemperature();

                    if(plateau[i][j].legume.getMaladie() == null) {
                        plateau[i][j].grow(sun);
                    }
                }

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

    public void arroser(int x, int y) {
        if(plateau[x][y].hasLegume()) {
            plateau[x][y].arroser();
            setChanged();
            notifyObservers();
        }
    }

    public void harverstLegumeInCase(int x, int y){
        if(plateau[x][y].hasLegume()) {
            if(plateau[x][y].getCroissance() >= 100 && plateau[x][y].getLegume().getMaladie() == null){
                monStock.RecolterUnLegume(plateau[x][y].getLegume());
            }
            plateau[x][y].harvestLegume();
            setChanged();
            notifyObservers();
        }
    }

}
