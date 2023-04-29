import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CalorieCounting {
    public static void main(String[] args) {
        String path = "day-1/resources/input.txt";

        System.out.println("getMaxCalorieAmount: " + getMaxCalorieAmount(path));
        System.out.println("getCalorieAmountOfTopNthElf(1): " + getCalorieAmountOfTopNthElf(path, 1));
        //getMaxCalorieAmount: 69310

        // Part-Two
        System.out.println("getSumOfTop3CalorieAmount: " + getCalorieAmountOfTopNthElf(path, 3));
        //getSumOfTop3CalorieAmount: 206104
    }

    public static int getMaxCalorieAmount(String fileName) {
        int maxCalorieAmount = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line = bufferedReader.readLine();
            int elfsCalorieAmount = 0;
            while (line != null) {
                if (!line.equals("")) {
                    elfsCalorieAmount += Integer.parseInt(line);
                } else {
                    maxCalorieAmount = Math.max(elfsCalorieAmount, maxCalorieAmount);
                    elfsCalorieAmount = 0;
                }
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return maxCalorieAmount;
    }

    public static int getCalorieAmountOfTopNthElf(String fileName, Integer numberOfElf) {
        List<Integer> calorieAmounts = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line = bufferedReader.readLine();
            int elfsCalorieAmount = 0;
            while (line != null) {
                if (!line.equals("")) {
                    elfsCalorieAmount += Integer.parseInt(line);
                } else {
                    calorieAmounts.add(elfsCalorieAmount);
                    elfsCalorieAmount = 0;
                }
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return calorieAmounts.stream().sorted(Comparator.reverseOrder()).limit(numberOfElf).mapToInt(Integer::intValue).sum();
    }
}
