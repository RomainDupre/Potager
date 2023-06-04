package blokus.RecolteLegume;

import blokus.LegumeModele.Legumes;

import java.util.Observable;

public class Stock extends Observable {

    public Legumes[] mesLegumesEnStocks ;

    private int[] QuanitéDeLegumeEnStock = new int [10];

    public Stock(Legumes[] mesLegumes)
    {
        this.mesLegumesEnStocks = mesLegumes;
    }

    public void RecolterUnLegume(Legumes legume)
    {
        for(int i =0;i<mesLegumesEnStocks.length;i++)
        {
            if(mesLegumesEnStocks[i].getLabel() == legume.getLabel())
            {
                QuanitéDeLegumeEnStock[i]++;
                this.setChanged();
                this.notifyObservers();
            }
        }

    }

    public int GetNbrLegumeEnStock(Legumes legume)
    {
        for (int i = 0; i <mesLegumesEnStocks.length; i++)
        {
            if(mesLegumesEnStocks[i].getLabel() == legume.getLabel())  return QuanitéDeLegumeEnStock[i];
        }
        return 0;
    }
}
