

import com.kiriost.game.gameobject.map.TileBasedMap;
import com.kiriost.game.mechanic.pathfinding.AStarPathFinder;
import com.kiriost.game.mechanic.pathfinding.Mover;
import com.kiriost.game.mechanic.pathfinding.Path;
import com.kiriost.game.mechanic.pathfinding.PathFinder;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

class Map implements TileBasedMap {
    private int length = 5;
    private boolean map[][];

    public Map() {
        map = new boolean[length][length];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                map[i][j] = false;
            }
        }

        map[2][1] = true;
        map[3][1] = true;
        map[3][2] = true;
        map[3][3] = true;
        map[2][3] = true;
    }

    @Override
    public int getWidthInTiles() {
        return length;
    }

    @Override
    public int getHeightInTiles() {
        return length;
    }

    @Override
    public void pathFinderVisited(int x, int y) {
    }

    @Override
    public boolean blocked(Mover mover, int x, int y) {
        return map[x][y];
    }

    @Override
    public float getCost(Mover mover, int sx, int sy, int tx, int ty) {
        return 1;
    }
}

public class PathFindingTest {
    private Map map;
    private PathFinder pathFinder;
    private Path path;

    @Before
    public void getPath() {
        map = new Map();
        pathFinder = new AStarPathFinder(map, 10, false);
        path = pathFinder.findPath(null, 0, 2, 4, 2);
    }


    @Test
    public void pathNotNull() {
        assertTrue(path.getLength() > 0);
    }

    @Test
    public void srcAndDstStep() {
        assertTrue(path.getX(0) == 0 && path.getY(0) == 2);
        assertTrue(path.getX(path.getLength() - 1) == 4 && path.getY(path.getLength() - 1) == 2);
    }

    @Test
    @Ignore
    public void optimalPath() {
        for (int i = 0; i < path.getLength(); i++) {
            System.out.println("X: " + path.getX(i) + " - " + "Y: " + path.getY(i));
        }
    }
}