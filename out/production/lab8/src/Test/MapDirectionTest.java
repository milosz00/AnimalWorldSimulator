import agh.cs.lab8.Enums.MapDirection;
import agh.cs.lab8.Classes.Vector2d;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MapDirectionTest {
    @Test
    public void testNext(){
        assertEquals(MapDirection.WEST.next(),MapDirection.NORTHWEST);
        assertEquals(MapDirection.NORTH.next(),MapDirection.NORTHEAST);
        assertEquals(MapDirection.EAST.next(),MapDirection.SOUTHEAST);
        assertEquals(MapDirection.SOUTH.next(),MapDirection.SOUTHWEST);
    }

    @Test
    public  void testPrevious(){
        assertEquals(MapDirection.EAST.previous(),MapDirection.NORTHEAST);
        assertEquals(MapDirection.SOUTH.previous(),MapDirection.SOUTHEAST);
        assertEquals(MapDirection.WEST.previous(),MapDirection.SOUTHWEST);
        assertEquals(MapDirection.NORTH.previous(),MapDirection.NORTHWEST);
    }

    @Test
    public void testToUnitVector(){
        assertEquals(MapDirection.NORTHEAST.toUnitVector(),new Vector2d(1,1));
        assertEquals(MapDirection.NORTHWEST.toUnitVector(),new Vector2d(-1,1));
        assertEquals(MapDirection.NORTH.toUnitVector(),new Vector2d(0,1));
        assertEquals(MapDirection.SOUTHEAST.toUnitVector(),new Vector2d(1,-1));
        assertEquals(MapDirection.SOUTHWEST.toUnitVector(),new Vector2d(-1,-1));
        assertEquals(MapDirection.SOUTH.toUnitVector(),new Vector2d(0,-1));
        assertEquals(MapDirection.EAST.toUnitVector(),new Vector2d(1,0));
        assertEquals(MapDirection.WEST.toUnitVector(),new Vector2d(-1,0));
    }
}
