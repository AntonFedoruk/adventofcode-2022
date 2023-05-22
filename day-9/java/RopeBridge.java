import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class RopeBridge {
    public static void main(String[] args) {
        String path = "day-9/resources/input.txt";

//        int countOfTLocations = countOfTLocations(path);
//        System.out.println("countOfTLocations: " + countOfTLocations);
        // 5878

        //Part Two
        int countOfTLocations9 = countOfTLocations9Knots(path);
        System.out.println("countOfTLocations9: " + countOfTLocations9);
        // 2560 - wrong
    }
    public static int countOfTLocations(String fileName) {
        Marker tail = new Marker();
        Marker head = new Marker();
        RopeSystem ropeSystem = new RopeSystem(head, tail);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String command = bufferedReader.readLine();
            while (command != null) {
                String[] commandByParts = command.split(" ");
                MoveCommand move = new MoveCommand(commandByParts[0], Integer.parseInt(commandByParts[1]));
                ropeSystem.move(move);
                command = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tail.visitedLocationsCount();
    }
    public static int countOfTLocations9Knots(String fileName) {
        Marker knotH = new Marker();
        Marker knot1 = new Marker();
        Marker knot2 = new Marker();
        Marker knot3 = new Marker();
        Marker knot4 = new Marker();
        Marker knot5 = new Marker();
        Marker knot6 = new Marker();
        Marker knot7 = new Marker();
        Marker knot8 = new Marker();
        Marker knot9 = new Marker();
        RopeSystem9 ropeSystem = new RopeSystem9(knotH, knot1, knot2, knot3, knot4, knot5, knot6, knot7, knot8, knot9);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String command = bufferedReader.readLine();
            while (command != null) {
                String[] commandByParts = command.split(" ");
                MoveCommand move = new MoveCommand(commandByParts[0], Integer.parseInt(commandByParts[1]));
                ropeSystem.move(move);
                command = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("visitedLocations: " + Arrays.toString(ropeSystem.getTail().visitedLocations.toArray()));

        return knot9.visitedLocationsCount();
    }
}