package agh.cs.lab8.Properties;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class LoadJson {

    int mapWidth;
    int mapHeight;
    int jungleWidth;
    int jungleHeight;
    int grassEnergyProfit;
    int dayCost;
    int animalsStartEnergy;
    int numOfSpawnedAnimalsInJungle ;
    int numOfSpawnedAnimalsInStep;
    int delay;


    public static LoadJson loadJsonFromFile() throws FileNotFoundException,IllegalArgumentException{
        Gson gson = new Gson();

        LoadJson properties = (LoadJson) gson.fromJson(new FileReader("src\\main\\agh\\cs\\lab8\\Properties\\config.json"),LoadJson.class);
        properties.validate();
        return properties;
    }

    public void validate(){
        if(this.mapWidth <= 0) throw new IllegalArgumentException("Invalid map width");
        if(this.mapHeight <= 0) throw new IllegalArgumentException("Invalid map height");
        if(this.delay <= 0) throw new IllegalArgumentException("Invalid delay");
        if(this.animalsStartEnergy <= 0) throw new IllegalArgumentException("Invalid animal start energy");
        if(this.dayCost <= 0) throw new IllegalArgumentException("Invalid day Cost");
        if(this.numOfSpawnedAnimalsInStep <= 0) throw new IllegalArgumentException("Invalid number of spawned animals in step");
        if(this.numOfSpawnedAnimalsInJungle <= 0) throw new IllegalArgumentException("Invalid number of spawned animals in jungle");
        if(this.grassEnergyProfit <= 0) throw new IllegalArgumentException("Invalid profit of eating grass");
        if(this.jungleWidth <= 0) throw new IllegalArgumentException("Invalid jungle width");
        if(this.jungleHeight <= 0) throw new IllegalArgumentException("Invalid jungle height");
    }

    public int getMapWidth() {
        return mapWidth;
    }

    public int getMapHeight() {
        return mapHeight;
    }

    public int getJungleWidth() {
        return jungleWidth;
    }

    public int getJungleHeight() {
        return jungleHeight;
    }

    public int getGrassEnergyProfit() {
        return grassEnergyProfit;
    }

    public int getDayCost() {
        return dayCost;
    }

    public int getAnimalsStartEnergy() {
        return animalsStartEnergy;
    }

    public int getNumOfSpawnedAnimalsInJungle() {
        return numOfSpawnedAnimalsInJungle;
    }

    public int getNumOfSpawnedAnimalsInStep() {
        return numOfSpawnedAnimalsInStep;
    }

    public int getDelay() {
        return delay;
    }

}
