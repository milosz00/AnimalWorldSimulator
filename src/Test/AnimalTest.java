import agh.cs.lab8.AnimalWorld.GrassField;
import agh.cs.lab8.Classes.Animal;
import agh.cs.lab8.Classes.Vector2d;
import agh.cs.lab8.Enums.MapDirection;
import agh.cs.lab8.Enums.MoveDirection;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class AnimalTest {
    @Test
    public void worldTest(){
        GrassField map = new GrassField(5,10,2,2,2,2,2,1);

        Animal animal = new Animal(map,new Vector2d(5,9));
        Animal animal1 = new Animal(map,new Vector2d(5,9));

        map.placeAnimal(animal);
        map.placeAnimal(animal1);

        assertEquals(animal.copulate(animal1).getPosition(),new Vector2d(0,8));
        assertEquals(animal.copulate(animal1).getEnergy(),4);

        assertEquals(animal.getEnergy(),6);


        assertEquals(map.getAnimals().get(new Vector2d(0,9)).size(),2);

        assertEquals(animal.getPosition(),new Vector2d(0,9));

        animal.move(MoveDirection.FORWARD);
        assertEquals(animal.getPosition(),new Vector2d(0,0));

        animal.move(MoveDirection.LEFT);
        animal.move(MoveDirection.FORWARD);
        assertEquals(animal.getPosition(),new Vector2d(4,1));



        ArrayList<Animal> list = map.getAnimalList();

        assertEquals(list.size(),2);

        assertEquals(list.get(1).getPosition(),new Vector2d(0,9));
        assertEquals(list.get(0).getPosition(),new Vector2d(4,1));
        assertEquals(list.get(0).getDirection(), MapDirection.NORTHWEST);
        assertEquals(list.get(1).getDirection(),MapDirection.NORTH);



    }

}
