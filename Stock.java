package blokus;

import blokus.LegumeModele.Legumes;

public class Stock {

    public Legumes[] mesLegumesEnStocks = new Legumes [10];

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
