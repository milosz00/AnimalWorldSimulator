package agh.cs.lab8.AnimalWorld;

import agh.cs.lab8.Classes.Animal;
import agh.cs.lab8.Classes.Genes;
import agh.cs.lab8.Classes.Grass;
import agh.cs.lab8.Classes.Vector2d;
import agh.cs.lab8.Interfaces.IPositionChangeObserver;
import agh.cs.lab8.Interfaces.IWorldMap;

import java.util.*;

public class GrassField  implements IWorldMap, IPositionChangeObserver {


    private int width;
    private int height;
    private int widthJungle;
    private int heightJungle;
    private Vector2d upperRightJungle;
    private Vector2d lowerLeftJungle;
    private Vector2d upperRight;
    private Vector2d lowerLeft;
    private int sumLifeLength;
    private int countAnimals;

    private int grassProfit;
    private int startAnimalEnergy;
    private int dayCost;
    private int id;

    Map<Vector2d, LinkedList<Animal>> animals = new LinkedHashMap<>();
    ArrayList<Animal> animalList = new ArrayList<>();
    Map<Vector2d, Grass> grasses = new LinkedHashMap<>();
    List<Grass> grassList = new ArrayList<>();
    int[] countGenes = new int[8];




    public GrassField(int width,int height,int widthJungle,int heightJungle,int grassProfit,int startAnimalEnergy,int dayCost,int id){
        this.width = width;
        this.height = height;
        this.widthJungle = widthJungle;
        this.heightJungle = heightJungle;
        this.grassProfit = grassProfit;
        this.startAnimalEnergy = startAnimalEnergy;
        this.dayCost = (-1)*dayCost;
        this.id = id;

        lowerLeft = new Vector2d(0,0);
        upperRight = new Vector2d(width-1,height-1);

        sumLifeLength = 0;
        countAnimals = 0;

        calculateJungleCorners();

    }


    private void calculateJungleCorners(){

        int lljx = 0; //lower left jungle x
        int lljy = 0; //lower left jungle y
        int urjx = width - 1; //upper right jungle x
        int urjy = height - 1; //upper right jungle y

        for (int i = 0; i < (width - widthJungle); i++) {
            if (i % 2 == 0) {
                lljx++;
            } else {
                urjx--;
            }
        }

        for (int i = 0; i < (height - heightJungle); i++) {

            if (i % 2 == 0) {
                lljy++;
            } else {
                urjy--;
            }
        }

        this.lowerLeftJungle = new Vector2d(lljx, lljy);
        this.upperRightJungle = new Vector2d(urjx, urjy);
    }

    @Override
    public Vector2d toRoundPosition(Vector2d oldPosition){

        int newX;
        int newY;

        //jeżeli wyjdziemy na pozycje minusową, to musimy przejść na koniec mapy, wpp po prostu zaokrąglamy naszego x dla pewności
        if(oldPosition.x < lowerLeft.x){
            newX = width + oldPosition.x;
        }
        else{
            newX = oldPosition.x%width;
        }

        // podobnie jak dla x robimy dla y
        if(oldPosition.y< lowerLeft.y){
            newY = height + oldPosition.y;
        }
        else{
            newY = oldPosition.y%height;
        }

        return new Vector2d(newX,newY);


    }



    @Override
    public void placeAnimal(Animal animal) {

        Vector2d newPosition = toRoundPosition(animal.getPosition());
        animal.setPosition(newPosition);


        countAnimals+=1;
        countGenes[animal.getGenes().getMaxGen()]+=1;
        addAnimal(animal, newPosition);
        animalList.add(animal);
        animal.addObserver(this);
    }


    @Override
    public boolean isOccupied(Vector2d position) {
        if(animals.get(position)!=null && animals.get(position).size()!=0)
            return true;
        return false;
    }



    public void addAnimal(Animal animal,Vector2d newPosition){
        LinkedList<Animal> list = animals.get(newPosition);
        if(list==null){
            LinkedList<Animal> tmp = new LinkedList<>();
            tmp.add(animal);
            animals.put(newPosition,tmp);
        }
        else {
            list.add(animal);
        }

    }

    public void removeAnimal(Animal animal,Vector2d oldPosition){
        LinkedList<Animal> list = animals.get(oldPosition);

        if(list == null){
            throw new IllegalArgumentException("Animal" + animal.getPosition() + "already not exists on the map");
        }
        else if(list.size()==0){
            throw new IllegalArgumentException("Animal" + animal.getPosition() + "already not exists on the map,empty list");
        }
        else{
            list.remove(animal);
            if(list.size()==0) {
                animals.remove(oldPosition);
            }
        }
    }



    public void placeAnimalInJungle(){
        int jungleSize = widthJungle*heightJungle;
        Vector2d animalPosition;

        int timesNeedToPlace = 0;
        Random generator = new Random();
        while(timesNeedToPlace<2*jungleSize){
            animalPosition = new Vector2d(generator.nextInt(widthJungle) + lowerLeftJungle.x,generator.nextInt(heightJungle) + lowerLeftJungle.y);
            if(!isOccupied(animalPosition)){
                this.placeAnimal(new Animal(this,animalPosition,startAnimalEnergy,new Genes()));
                break;
            }
            timesNeedToPlace++;
        }
    }

    public void placeAnimalInStep(){
        int jungleSize = widthJungle*heightJungle;
        int mapSize = width*height;
        int stepSize = mapSize - jungleSize;

        Vector2d animalPosition;

        int timesNeedToPlace = 0;
        Random generator = new Random();
        while(timesNeedToPlace < 2*stepSize){
            animalPosition = new Vector2d(generator.nextInt(width),generator.nextInt(height));
            if(!isOccupied(animalPosition) && !(animalPosition.follows(lowerLeftJungle) && animalPosition.precedes(upperRightJungle))){
                this.placeAnimal(new Animal(this,animalPosition,startAnimalEnergy,new Genes()));
                break;
            }
            timesNeedToPlace++;
        }
    }

    @Override
    public boolean positionChanged(Vector2d oldPosition, Vector2d newPosition, Object o ){

        oldPosition = toRoundPosition(oldPosition);
        newPosition = toRoundPosition(newPosition);


        removeAnimal((Animal) o,oldPosition);
        addAnimal((Animal) o,newPosition);
        return true;

    }

    public LinkedList<Animal> getStrongestAnimals(LinkedList<Animal> list){
        LinkedList<Animal> strongestAnimals = new LinkedList<>();
        list.sort(new EnergyComparator());

        int maxEnergy = list.getFirst().getEnergy();

        for (Animal animal : list) {
            if(animal.getEnergy() == maxEnergy)
                strongestAnimals.add(animal);
            else
                break;
        }
        return strongestAnimals;
    }


    public Map<Vector2d, LinkedList<Animal>> getAnimals() {
        return animals;
    }

    public ArrayList<Animal> getAnimalList() {
        return animalList;
    }

    public Vector2d getUpperRightJungle() {
        return upperRightJungle;
    }

    public Vector2d getLowerLeftJungle() {
        return lowerLeftJungle;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getWidthJungle() {
        return widthJungle;
    }

    public int getHeightJungle() {
        return heightJungle;
    }

    public void setSumLifeLength(int value) {
        this.sumLifeLength += value;
    }

    public int getStartAnimalEnergy() {
        return startAnimalEnergy;
    }

    public int getDayCost() {
        return dayCost;
    }

    public List<Grass> getGrassList() {
        return grassList;
    }

    public Map<Vector2d, Grass> getGrasses() {
        return grasses;
    }

    public int getGrassProfit() {
        return grassProfit;
    }

    public int getId() {
        return id;
    }

    public double getAvgEnergy(){
        int avg = 0;
        for (Animal animal : animalList) {
            avg+=animal.getEnergy();
        }
        if(animalList.size()==0)
            return 0;
        else
            return avg/animalList.size();
    }

    public double getAvgLifeLength(){
        if(countAnimals == 0)
            return 0;
        else
            return sumLifeLength/countAnimals;
    }

    public double getAvgNumOfChildren(){
        int sum = 0;
        for (Animal animal : animalList) {
            sum+=animal.getHowManyChildren();
        }
        if(animalList.size()==0)
            return 0;
        else
            return sum/animalList.size();
    }

    public int getDominantGen(){
        int max = 0;
        int index = 0;
        for(int i = 0; i<8 ; i++){
            if(countGenes[i]>max){
                max = countGenes[i];
                index = i;
            }
        }
        return index;
    }

}
