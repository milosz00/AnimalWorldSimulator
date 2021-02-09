package agh.cs.lab8.Classes;

import agh.cs.lab8.Enums.MapDirection;
import agh.cs.lab8.Enums.MoveDirection;
import agh.cs.lab8.Interfaces.IPositionChangeObserver;
import agh.cs.lab8.Interfaces.IWorldMap;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Animal{

    private MapDirection direction;
    private Vector2d position;
    private IWorldMap map;
    private Genes genes;
    private int energy;
    private int startEnergy;
    private int age;
    private int howManyChildren;
    public List<IPositionChangeObserver> observers = new ArrayList<>();

    public Animal(){
        this.direction = MapDirection.NORTH;
        this.position = new Vector2d(2,2);
        this.genes = new Genes();
        this.energy  = 10;
        this.age = 0;
        this.howManyChildren = 0;
    }

    public Animal(IWorldMap map){
        this();
        this.map = map;
    }

    public Animal(IWorldMap map,Vector2d position){
        this(map);
        this.position = position;
    }

    public Animal(IWorldMap map,Vector2d initialPosition,int energy){
        this(map,initialPosition);
        this.energy = energy;
        this.startEnergy = energy;
    }

    public Animal(IWorldMap map,Vector2d initialPosition,int energy,Genes genes){
        this(map,initialPosition,energy);
        this.genes = genes;
    }

    public String toString(){
        return direction.toString();
    }

    public void move(MoveDirection dir){
        switch(dir){
            case RIGHT:
                direction=direction.next();
                break;
            case LEFT:
                direction=direction.previous();
                break;
            case FORWARD:
                Vector2d newPosition = position.add(direction.toUnitVector());

                Vector2d old = new Vector2d(this.getPosition().x, this.getPosition().y);
                position = map.toRoundPosition(newPosition);
                this.positionChanged(old,position,this);
                break;

            case BACKWARD:
                Vector2d newPosition2 = position.subtract(direction.toUnitVector());

                Vector2d old2 = new Vector2d(this.getPosition().x, this.getPosition().y);
                position = map.toRoundPosition(newPosition2);
                this.positionChanged(old2,position,this);
                break;
        }
    }

    public MapDirection getDirection(){
        return direction;
    }

    public Vector2d getPosition(){
        return position;
    }

    public void setPosition(Vector2d newPosition){
        this.position = newPosition;
    }

    public void rotate(){
        int randomGen = genes.getRandomGen();
        for(int i=0;i<randomGen;i++){
            this.move(MoveDirection.RIGHT);
        }
    }

    public Animal copulate(Animal partner){
        int childEnergy = (int) (0.25 * (this.getEnergy() + partner.getEnergy()));
        this.setEnergy((int) -(this.getEnergy()*0.25));
        partner.setEnergy((int) -(partner.getEnergy()*0.25));
        this.howManyChildren+=1;
        partner.howManyChildren+=1;


        int[] x = new int[]{0,1,1,1,0,-1,-1,-1};
        int[] y = new int[]{-1,-1,0,1,1,1,0,-1};


        Vector2d childPosition = this.getPosition();
        for(int i = 0; i<8; i++){
            Vector2d newPosition = map.toRoundPosition(childPosition.add(new Vector2d(x[i],y[i])));
            if(!map.isOccupied(childPosition)){
                childPosition = newPosition;
                break;
            }

        }
        //jezeli wszytskie miejsca w około są zajęte to umieszczamy na pierwszym dowolnym miejscu
        if(childPosition.equals(this.getPosition())){
            childPosition = childPosition.add(new Vector2d(x[0],y[0]));
        }

        Animal child = new Animal(map,childPosition,childEnergy);
        child.genes = new Genes(this.genes, partner.genes);

        return child;

    }

    public void addObserver(IPositionChangeObserver observer){
        observers.add(observer);
    }
    public void removeObserver(IPositionChangeObserver observer){
        observers.remove(observer);
    }

    private void positionChanged(Vector2d oldPosition,Vector2d newPosition,Object o){
        for (IPositionChangeObserver observer : observers) {
            observer.positionChanged(oldPosition,newPosition,o);
        }
    }

    public Genes getGenes() {
        return genes;
    }

    public int getEnergy(){
        return this.energy;
    }

    public void setEnergy(int value){
        this.energy+=value;
    }

    public int getHowManyChildren() {
        return howManyChildren;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int value) {
        this.age += value;
    }

    public boolean isDead(){
        return this.energy<=0;
    }

    public Color toColor(){
        if(this.getEnergy() <= 0)
            return new Color(255,255,255);
        else if(this.energy > 0 && this.energy <= 0.4 * this.startEnergy)
            return new Color(255,229,204);
        else if(this.energy > 0.4 * this.startEnergy && this.energy <= 0.8 * this.startEnergy)
            return new Color(255,153,51);
        else if(this.energy > 0.8 * this.startEnergy && this.energy <= 1.4 * this.startEnergy)
            return new Color(204,102,0);
        else if(this.energy > 1.4 * this.startEnergy && this.energy <= 2 * this.startEnergy)
            return new Color(102,51,0);
        else if(this.energy > 2 * this.startEnergy && this.energy <= 3 * this.startEnergy)
            return new Color(51,25,0);
        else
            return new Color(0,0,0);

    }
}
