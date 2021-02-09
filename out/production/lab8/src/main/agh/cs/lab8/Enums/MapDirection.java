package agh.cs.lab8.Enums;

import agh.cs.lab8.Classes.Vector2d;

public enum MapDirection {
    NORTH, SOUTH, WEST, EAST,NORTHEAST,NORTHWEST,SOUTHEAST,SOUTHWEST;

    public String toString(){
        switch(this){
            case NORTH:
                return "N";
            case SOUTH:
                return "S";
            case WEST:
                return "W";
            case EAST:
                return "E";
            case NORTHEAST:
                return "NE";
            case NORTHWEST:
                return "NW";
            case SOUTHEAST:
                return "SE";
            case SOUTHWEST:
                return "SW";
            default:
                return "Błędny kierunek";
        }
    }

    public MapDirection next(){
        switch(this){
            case NORTH:
                return NORTHEAST;
            case SOUTH:
                return SOUTHWEST;
            case WEST:
                return NORTHWEST;
            case EAST:
                return SOUTHEAST;
            case NORTHWEST:
                return NORTH;
            case SOUTHWEST:
                return WEST;
            case NORTHEAST:
                return EAST;
            case SOUTHEAST:
                return SOUTH;
            default:
                return null;
        }
    }

    public MapDirection previous(){
        switch(this) {
            case NORTH:
                return NORTHWEST;
            case NORTHWEST:
                return WEST;
            case WEST:
                return SOUTHWEST;
            case SOUTHWEST:
                return SOUTH;
            case SOUTH:
                return SOUTHEAST;
            case SOUTHEAST:
                return EAST;
            case EAST:
                return NORTHEAST;
            case NORTHEAST:
                return NORTH;
            default:
                return null;
        }
    }

    public Vector2d toUnitVector(){

        switch(this){
            case NORTH:
                return new Vector2d(0,1);
            case WEST:
                return new Vector2d(-1,0);
            case SOUTH:
                return new Vector2d(0,-1);
            case EAST:
                return new Vector2d(1,0);
            case NORTHEAST:
                return new Vector2d(1,1);
            case NORTHWEST:
                return new Vector2d(-1,1);
            case SOUTHEAST:
                return new Vector2d(1,-1);
            case SOUTHWEST:
                return new Vector2d(-1,-1);
            default:
                return null;
        }
    }
}
