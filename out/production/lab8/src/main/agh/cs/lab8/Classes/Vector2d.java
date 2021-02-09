package agh.cs.lab8.Classes;

import java.util.Objects;

public class Vector2d {
    public final int x;
    public final int y;

    public Vector2d(int x,int y){
        this.x = x;
        this.y = y;
    }

    public String toString(){
        return "(" + x + "," + y + ")";
    }

    public boolean precedes(Vector2d other){
        if(x<=other.x && y<= other.y)
            return true;
        else
            return false;
    }

    public boolean follows(Vector2d other){
        if(x>=other.x && y>= other.y)
            return true;
        else
            return false;
    }

    public Vector2d upperRight(Vector2d other){
        int rightX,rightY;

        if(this.x>other.x)
            rightX=this.x;
        else
            rightX=other.x;

        if(this.y>other.y)
            rightY=this.y;
        else
            rightY= other.y;

        return new Vector2d(rightX,rightY);
    }

    public Vector2d lowerLeft(Vector2d other){
        int leftY,leftX;

        if(this.x<other.x)
            leftX=this.x;
        else
            leftX= other.x;

        if(this.y< other.y)
            leftY=this.y;
        else
            leftY= other.y;

        return new Vector2d(leftX,leftY);
    }

    public Vector2d add(Vector2d other){
        return new Vector2d(this.x+other.x,this.y+other.y);
    }

    public Vector2d subtract(Vector2d other){
        return new Vector2d(this.x-other.x,this.y-other.y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector2d vector2D = (Vector2d) o;
        return x == vector2D.x &&
                y == vector2D.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.x, this.y);
    }

    public Vector2d opposite(){
        return new Vector2d(-x,-y);
    }
}
