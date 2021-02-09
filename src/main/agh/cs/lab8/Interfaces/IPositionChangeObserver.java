package agh.cs.lab8.Interfaces;

import agh.cs.lab8.Classes.Vector2d;

public interface IPositionChangeObserver {
    public boolean positionChanged(Vector2d oldPosition, Vector2d newPosition, Object o);
}
