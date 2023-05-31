package blokus.Meteo;

import java.util.HashMap;

public class Meteo {
    private float temperature;
    private float humidite;
    private float ensoleillement;
    private int clockTick;
    private float currentTemperature;
    private float currentHumidite;
    private float currentEnsoleillement;
    private Boolean isRaining = false;
    private Boolean isCurrentlyRaining = false;

    private int precipitation = 0;

    private static final int CLOCK = 1;

    private int time = 0;

    private final HashMap<Number, Float> correspondance = new HashMap<Number, Float>() {{
        put(0,30.0f); put(1,30.0f); put(2,30.0f); put(3,30.0f); put(4,30.0f); put(5,30.0f); put(6,30.0f); put(7,40.0f); put(8,50.0f); put(9,60.0f); put(10,70.0f); put(11,85.0f); put(12,100.0f);
        put(13,95.0f); put(14,90.0f); put(15,85.0f); put(16,80.0f); put(17,75.0f); put(18,70.0f); put(19,60.0f); put(20,50.0f); put(21,40.0f); put(22,22.0f); put(23,30.0f); put(24,30.0f);
    }};

    public Meteo(float temperature, float humidite, float ensoleillement) {
        this.temperature = temperature;
        this.humidite = humidite;
        this.ensoleillement = ensoleillement;
        this.clockTick = 0;
        this.currentTemperature = temperature;
        this.currentHumidite = humidite;
        this.currentEnsoleillement = ensoleillement;

    }



    public void afficher() {
        System.out.println("Temperature: " + this.currentTemperature);
        System.out.println("Humidite: " + this.currentHumidite);
        System.out.println("Ensoleillement: " + this.currentEnsoleillement);
        System.out.println("Time: " + this.time);
    }
    public void updateClockTick(){
        if(this.clockTick == CLOCK){
            this.clockTick = 0;
            updateTime();
        }
        this.clockTick++;

    }
    public void updateTime(){
        if(this.time == 24){
            this.time = 0;
            generateMeteo();
        }
        this.time++;
        updateMeteo();
    }

    public void updateMeteo() {
        int random = (int) (Math.random() * 4);
        if(this.isRaining){
            int precipitation = (int) (Math.random() * this.precipitation) - 50;
            if(precipitation > 0){
                this.isCurrentlyRaining = false;
            }
            else{
                this.isCurrentlyRaining = true;
            }
        }
        this.currentTemperature = correspondance.get(this.time) / 100.0f * this.temperature;
        this.currentHumidite = (100.0f - correspondance.get(this.time)) / 100.0f * this.humidite;
        this.currentEnsoleillement =  correspondance.get(this.time) / 100.0f * this.ensoleillement;
        afficher();
    }

    public void generateMeteo(){
        int random = (int) (Math.random() * 10) - 5;
        if(random < 0){
            this.isRaining = true;
            this.precipitation = (random + 5) * 10;
            this.temperature = (float) (Math.random() * 20) + 5.0f;
            this.humidite = (float) (Math.random() * 20) + 80.0f;
            this.ensoleillement = (float) (Math.random() * 40) + 20.0f;
        }
        else{
            this.isRaining = false;
            this.precipitation = 0;
            this.temperature = (float) (Math.random() * 30)+10.0f;
            this.humidite = (float) (Math.random() * 70);
            this.ensoleillement = (float) (Math.random() * 80) +20.0f;
        }

        afficher();
    }

}
