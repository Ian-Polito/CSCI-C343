import java.util.*;

public class FloodFunction {

    private Driver driver;
    // here floodList is declared as a LinkedList
    // it is declared as public (a bad practice), but it is needed by the Driver class
    public List<Coord> floodList = new LinkedList<Coord>();

    // constructor
    public FloodFunction(Driver newDriver) {
        driver = newDriver;
        // when the game starts, only the cell at the left/top corner is filled
        floodList.add(new Coord(0, 0));
    }

    // flood function is to be implemented by students
    public void flood(int colorToFlood) {
        //change all tiles in floodList to colorToFlood
        //check if all tiles adjecent to any floodList are colorToFlood color
        //if they are add them to floodList if they arent in there already
        ListIterator<Coord> it = floodList.listIterator();
        while(it.hasNext()) {
            Coord c = it.next();
            if (inbound(up(c))) {
                if (colorOfCoord(up(c)) == colorToFlood) {
                    //add this tile to list if not already in it
                    if (!(floodList.contains(up(c)))) {
                        this.driver.colorOfCoord[up(c).x][up(c).y] = colorToFlood;
                        it.add(up(c));
                    }
                }
            }
            if (inbound(down(c))) {
                if (colorOfCoord(down(c)) == colorToFlood) {
                    //add this tile to list if not already in it
                    if (!(floodList.contains(down(c)))) {
                        this.driver.colorOfCoord[down(c).x][down(c).y] = colorToFlood;
                        it.add(down(c));
                    }
                }
            }
            if (inbound(left(c))) {
                if (colorOfCoord(left(c)) == colorToFlood) {
                    //add this tile to list if not already in it
                    if (!(floodList.contains(left(c)))) {
                        this.driver.colorOfCoord[left(c).x][left(c).y] = colorToFlood;
                        it.add(left(c));
                    }
                }
            }
            if (inbound(right(c))) {
                if (colorOfCoord(right(c)) == colorToFlood) {
                    //add this tile to list if not already in it
                    if (!(floodList.contains(right(c)))) {
                        this.driver.colorOfCoord[right(c).x][right(c).y] = colorToFlood;
                        it.add(right(c));
                    }
                }
            }
        }
    }

    // is input cell (specified by coord) on the board?
    public boolean inbound(final Coord coord) {
        final int x = coord.x;
        final int y = coord.y;
        final int size = this.driver.size;
        return x > -1 && x < size && y > -1 && y < size;
    }
    // return the coordinate of the cell on the top of the given cell (coord)
    public Coord up(final Coord coord) {
        return new Coord(coord.x, coord.y-1);
    }
    // return the coordinate of the cell below the given cell (coord)
    public Coord down(final Coord coord) {
        return new Coord(coord.x, coord.y+1);
    }
    // return the coordinate of the cell to the left of the given cell (coord)
    public Coord left(final Coord coord) {
        return new Coord(coord.x-1, coord.y);
    }
    // return the coordinate of the cell to the right of the given cell (coord)
    public Coord right(final Coord coord) {
        return new Coord(coord.x + 1, coord.y);
    }
    // get the color of a cell (coord)
    public int colorOfCoord(final Coord coord) {
        return this.driver.getColor(coord);
    }
}