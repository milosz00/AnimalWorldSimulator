package agh.cs.lab8.Simulation;

import agh.cs.lab8.AnimalWorld.EnergyComparator;
import agh.cs.lab8.AnimalWorld.GrassField;
import agh.cs.lab8.Classes.Animal;
import agh.cs.lab8.Classes.Grass;
import agh.cs.lab8.Classes.Vector2d;
import agh.cs.lab8.Enums.MoveDirection;
import agh.cs.lab8.Interfaces.IEngine;

import java.util.LinkedList;
import java.util.Random;

public class Engine implements IEngine {

    GrassField map;


    public Engine(GrassField map){
        this.map = map;
    }

    @Override
    public void eating(){
        LinkedList<Grass> grassToRemove = new LinkedList<>();

        for (Grass grass : map.getGrassList()) {
            LinkedList<Animal> listAnimal = map.getAnimals().get(grass.getPosition());
            if(listAnimal != null && listAnimal.size()!=0){
                LinkedList<Animal> strongestAnimals = map.getStrongestAnimals(listAnimal);
                int countStrongest = strongestAnimals.size();

                for (Animal strongestAnimal : strongestAnimals) {
                    strongestAnimal.setEnergy(map.getGrassProfit()/countStrongest);
                }
                grassToRemove.add(grass);
            }
        }

        for (Grass grass : grassToRemove) {
            map.getGrasses().remove(grass.getPosition());
            map.getGrassList().remove(grass);
        }
    }

    @Override
    public void spawnGrass(){

        int jungleSize = map.getWidthJungle()*map.getHeightJungle();
        int mapSize = map.getWidth()*map.getHeight();
        int stepSize = mapSize - jungleSize;

        spawnGrassJungle(jungleSize);
        spawnGrassStep(stepSize);
    }

    private void spawnGrassJungle(int jungleSize){

        int timesToRandom = 0;
        Random generator = new Random();

        while(timesToRandom < jungleSize){
            Vector2d positionNew = new Vector2d(generator.nextInt(map.getWidthJungle())+map.getLowerLeftJungle().x,
                    generator.nextInt(map.getHeightJungle())+map.getLowerLeftJungle().y);

            if(!map.isOccupied(positionNew) && map.getGrasses().get(positionNew)==null){
                map.getGrasses().put(positionNew,new Grass(positionNew));
                map.getGrassList().add(new Grass(positionNew));
                break;
            }
            timesToRandom++;
        }
    }

    private void spawnGrassStep(int stepSize){
        int timesToRandom = 0;
        Random generator = new Random();

        while (timesToRandom < stepSize){
            Vector2d positionNew = new Vector2d(generator.nextInt(map.getWidth()),generator.nextInt(map.getHeight()));

            if(!map.isOccupied(positionNew)  && !(positionNew.follows(map.getLowerLeftJungle()) && positionNew.precedes(map.getUpperRightJungle()))
                    && map.getGrasses().get(positionNew)==null){
                map.getGrasses().put(positionNew,new Grass(positionNew));
                map.getGrassList().add(new Grass(positionNew));
                break;
            }
            timesToRandom++;
        }
    }

    @Override
    public void removeDeadAnimals(){
        LinkedList<Animal> animalToRemove = new LinkedList<>();
        for (Animal animal : map.getAnimalList()) {
            if(animal.getEnergy()<=0){
                map.setSumLifeLength(animal.getAge());
                map.removeAnimal(animal,animal.getPosition());
                animal.removeObserver(map);
                animalToRemove.add(animal);
            }
        }

        for (Animal animal : animalToRemove) {
            map.getAnimalList().remove(animal);
        }

    }

    @Override
    public void moveAnimals(){
        for (Animal animal : map.getAnimalList()) {
            animal.rotate();
            animal.move(MoveDirection.FORWARD);
        }
    }

    @Override
    public void copulation(){

        LinkedList<Animal> animalToAdd = new LinkedList<>();

        for(LinkedList<Animal> listAnimal : map.getAnimals().values()) {
            if (listAnimal != null) {
                if (listAnimal.size() >= 2) {
                    listAnimal.sort(new EnergyComparator());
                    Animal father = listAnimal.get(0);
                    Animal mother = listAnimal.get(1);


                    if (father.getEnergy() >= 0.5 * map.getStartAnimalEnergy() && mother.getEnergy() >= 0.5 * map.getStartAnimalEnergy()) {
                        Animal child = father.copulate(mother);
                        animalToAdd.add(child);
                    }
                }
            }
        }

        for (Animal animal : animalToAdd) {
            map.placeAnimal(animal);
        }

    }

    @Override
    public void nextDay(){
        for (Animal animal : map.getAnimalList()) {
            animal.setEnergy(map.getDayCost());
            animal.setAge(1);
        }
    }


    public void daySimulation(){
        this.removeDeadAnimals();
        this.moveAnimals();
        this.eating();
        this.copulation();
        this.nextDay();
        this.spawnGrass();
    }
}
