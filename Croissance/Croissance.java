package blokus.Croissance;

public class Croissance {
    public final int croissanceQota = 100;
    public int waterQota = 0;
    public int sunQota = 0;
    public int currentWater = 0;
    public int currentSun = 0;
    public float sunNeed = 0;
    public float waterNeed = 0;
    public float waterConsumption = 0;

    public int croissance = 0;

    public Croissance(int waterQota, int waterNeed, int sunQota, int sunNeed, float waterConsumption) {
        this.waterQota = waterQota;
        this.waterNeed = waterNeed;
        this.sunQota = sunQota;
        this.sunNeed = sunNeed;
        this.waterConsumption = waterConsumption;
    }

    public int getCroissance(){
        return croissance;
    }

    public void updateCroissance(float water, float sun){
        if(croissance >= croissanceQota){
            return;
        }

        if (water >= waterNeed && currentWater < waterQota){
            currentWater++;
            croissance++;
        }

        if (sun >= sunNeed){
            currentSun++;
            croissance++;
        }
    }

}
