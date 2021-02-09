package agh.cs.lab8.AnimalWorld;

import agh.cs.lab8.Properties.LoadJson;
import agh.cs.lab8.Visualization.MenuSettings;

import java.io.FileNotFoundException;

public class World {
    public static void main(String[] args)  {


        try {
            LoadJson properties = LoadJson.loadJsonFromFile();


            Integer[] jsonParameters = {
                    properties.getMapWidth(),
                    properties.getMapHeight(),
                    properties.getJungleWidth(),
                    properties.getJungleHeight(),
                    properties.getGrassEnergyProfit(),
                    properties.getDayCost(),
                    properties.getAnimalsStartEnergy(),
                    properties.getNumOfSpawnedAnimalsInJungle(),
                    properties.getNumOfSpawnedAnimalsInStep(),
                    properties.getDelay()
            };


            new MenuSettings(jsonParameters);
        }
        catch (IllegalArgumentException e){
            System.out.println(e);
            return;
        }
        catch (FileNotFoundException e){
            System.out.println(e);
            return;
        }


    }


}
