import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class TuningTrouble {
    public static void main(String[] args) {
        String path = "day-6/resources/input.txt";

        System.out.println("charAmountBeforeMarker(4): " + charAmountBeforeMarker(path, 4));
        // 1987

        //Part Two
        System.out.println("charAmountBeforeMarker(14): " + charAmountBeforeMarker(path, 14));
        // 3059
    }

    public static int charAmountBeforeMarker(String fileName, int markerSize) {
        int amountOfChar = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            int value;
            List<Character> marker = new ArrayList<>();
            while ((value = bufferedReader.read()) != -1) {
                char val = (char) value;
                System.out.println("INPUT char: " + val);
                if (marker.isEmpty()) {
                    marker.add(val);
                    System.out.println("marker: " + marker);
                } else {
                    if (marker.contains(val)) {
                        System.out.println("such char already exists");
                        int index = marker.indexOf(val);
                        if (index >= 0) {
                            marker.subList(0, index + 1).clear();
                        }
                        marker.add(val);
                        System.out.println("marker: " + marker);
                    } else {
                        System.out.println("new char added");
                        marker.add(val);
                        System.out.println("marker: " + marker);
                        if (marker.size() == markerSize) {
                            return amountOfChar + 1;
                        }
                    }

                }
                amountOfChar++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return amountOfChar;
    }
}