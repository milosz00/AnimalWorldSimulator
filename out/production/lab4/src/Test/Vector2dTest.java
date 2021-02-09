import agh.cs.lab8.Classes.Vector2d;
import org.junit.Test;

import static org.junit.Assert.*;

public class Vector2dTest {
    @Test
    public void equalsTest(){
        Vector2d position1 = new Vector2d(1,2);
        Vector2d position2 = new Vector2d(1,2);
        Vector2d position3 = new Vector2d(-1,2);


        assertTrue(position1.equals(position2));
        assertFalse(position1.equals(position3));
    }

    @Test
    public void toStringTest(){
        Vector2d abc = new Vector2d(-3,-5);
        Vector2d xyz = new Vector2d(0,2);
        Vector2d fgh = new Vector2d(-9,2);

        assertEquals(abc.toString(),"(-3,-5)");
        assertEquals(xyz.toString(),"(0,2)");
        assertEquals(fgh.toString(),"(-9,2)");
    }

    @Test
    public void precedesTest(){
        Vector2d abc = new Vector2d(-3,-5);
        Vector2d xyz = new Vector2d(0,2);
        Vector2d fgh = new Vector2d(-9,2);

        assertTrue(abc.precedes(xyz));
        assertFalse(abc.precedes(fgh));
        assertFalse(xyz.precedes(fgh));
    }

    @Test
    public void followsTest(){
        Vector2d abc = new Vector2d(-3,-5);
        Vector2d xyz = new Vector2d(0,2);
        Vector2d fgh = new Vector2d(-9,2);

        assertTrue(xyz.follows(abc));
        assertFalse(fgh.follows(abc));
        assertTrue(xyz.follows(fgh));
    }

    @Test
    public void upperRightTest(){
        Vector2d abc = new Vector2d(-3,-5);
        Vector2d xyz = new Vector2d(0,2);
        Vector2d fgh = new Vector2d(-9,2);

        assertEquals(abc.upperRight(xyz),new Vector2d(0,2));
        assertEquals(abc.upperRight(fgh),new Vector2d(-3,2));
        assertEquals(xyz.upperRight(fgh),new Vector2d(0,2));
    }

    @Test
    public void lowerLeftTEst(){
        Vector2d abc = new Vector2d(-3,-5);
        Vector2d xyz = new Vector2d(0,2);
        Vector2d fgh = new Vector2d(-9,2);

        assertEquals(abc.lowerLeft(xyz),new Vector2d(-3,-5));
        assertEquals(abc.lowerLeft(fgh),new Vector2d(-9,-5));
        assertEquals(xyz.lowerLeft(fgh),new Vector2d(-9,2));
    }

    @Test
    public void addTest(){
        Vector2d abc = new Vector2d(-3,-5);
        Vector2d xyz = new Vector2d(0,2);
        Vector2d fgh = new Vector2d(-9,2);

        assertEquals(abc.add(xyz),new Vector2d(-3,-3));
        assertEquals(xyz.add(abc),new Vector2d(-3,-3));
        assertEquals(fgh.add(abc),new Vector2d(-12,-3));
        assertEquals(fgh.add(xyz),new Vector2d(-9,4));
    }

    @Test
    public void subtractTest(){
        Vector2d abc = new Vector2d(-3,-5);
        Vector2d xyz = new Vector2d(0,2);
        Vector2d fgh = new Vector2d(-9,2);

        assertEquals(abc.subtract(xyz),new Vector2d(-3,-7));
        assertEquals(abc.subtract(fgh),new Vector2d(6,-7));
        assertEquals(xyz.subtract(abc),new Vector2d(3,7));
        assertEquals(xyz.subtract(fgh),new Vector2d(9,0));
    }

    @Test
    public void oppositeTest(){
        Vector2d abc = new Vector2d(-3,-5);
        Vector2d xyz = new Vector2d(0,2);
        Vector2d fgh = new Vector2d(-9,2);

        assertEquals(abc.opposite(),new Vector2d(3,5));
        assertEquals(xyz.opposite(),new Vector2d(0,-2));
        assertEquals(fgh.opposite(),new Vector2d(9,-2));
    }
}
