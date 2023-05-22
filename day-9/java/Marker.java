import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class Marker {
    Set<String> visitedLocations = new LinkedHashSet<>();
    private int x, y;

    public Marker() {
        x = 0;
        y = 0;
        visitedLocations.add(location(x, y));
    }

    public Marker(int x, int y) {
        this.x = x;
        this.y = y;
        visitedLocations.add(location(x, y));
    }

    private static String location(int x, int y) {
        return String.valueOf(x).concat(";").concat(String.valueOf(y));
    }

    public void move(String direction) {
        switch (direction) {
            case "U" -> y++;
            case "D" -> y--;
            case "R" -> x++;
            case "L" -> x--;
        }
        visitedLocations.add(location(x, y));
    }

    public void move(int x, int y) {
        this.x = x;
        this.y = y;
        visitedLocations.add(location(x, y));
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int visitedLocationsCount() {
        return visitedLocations.size();
    }
}
