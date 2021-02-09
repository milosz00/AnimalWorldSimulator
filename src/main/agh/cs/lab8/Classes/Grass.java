package agh.cs.lab8.Classes;

import java.awt.*;

public class Grass {


    private Vector2d position;


    public Grass(Vector2d position){
        this.position=position;
    }

    @Override
    public String toString() {
        return "*";
    }

    public Vector2d getPosition(){
        return position;
    }

    public Color toColor(){
        return new Color(76,153,0);
    }

}
