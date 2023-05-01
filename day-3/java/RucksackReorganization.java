import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class RucksackReorganization {
    public static void main(String[] args) {
        String path = "day-3/resources/input.txt";

        long startTime = System.currentTimeMillis();
        System.out.println("getPrioritiesSum: " + getPrioritiesSum(path));
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        System.out.println("Elapsed time: " + elapsedTime + " ms");
        //getPrioritiesSum: 7581

        startTime = System.currentTimeMillis();
        System.out.println("getPrioritiesSum2: " + getPrioritiesSumV2(path));
        endTime = System.currentTimeMillis();
        elapsedTime = endTime - startTime;
        System.out.println("Elapsed time: " + elapsedTime + " ms");
        //getPrioritiesSum: 7581

        // Part-Two
        System.out.println("getGroupsPrioritiesSum: " + getGroupsPrioritiesSum(path));
        //getGroupsPrioritiesSum: 2525
    }

    public static int getPrioritiesSum(String fileName) {
        int prioritiesSum = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                if (findCommonItem(line).isPresent()) {
                    prioritiesSum += priorityConverter(findCommonItem(line).get());
                    line = bufferedReader.readLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prioritiesSum;
    }

    public static int getGroupsPrioritiesSum(String fileName) {
        int prioritiesSum = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                String rucksack2 = bufferedReader.readLine();
                String commonItemsAmongTwoRucksacks = findCommonItemsAmongTwoRucksacks(line, rucksack2);
                String rucksack3 = bufferedReader.readLine();
                String commonItemsAmongThreeRucksacks = findCommonItemsAmongTwoRucksacks(commonItemsAmongTwoRucksacks, rucksack3);
                prioritiesSum += commonItemsAmongThreeRucksacks.chars().distinct().map(operand -> operand > 96 ? operand - 96 : operand - 38).sum();
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prioritiesSum;
    }

    public static int getPrioritiesSumV2(String fileName) {
        int prioritiesSum = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                if (findCommonItemV2(line).isPresent()) {
                    prioritiesSum += priorityConverter(findCommonItemV2(line).get());
                }
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prioritiesSum;
    }


    public static Optional<Character> findCommonItem(String rucksack) {
        int itemsAmount = rucksack.length();
        int halfSize = itemsAmount / 2;
        String leftHalf = rucksack.substring(0, halfSize);
        String rightHalf = rucksack.substring(halfSize, itemsAmount);
        char[] leftItems = leftHalf.toCharArray();
        for (char item : leftItems) {
            if (rightHalf.contains(String.valueOf(item))) {
                return Optional.of(item);
            }
        }
        return Optional.empty();
    }

    public static Optional<Character> findCommonItemV2(String rucksack) {
        int itemsAmount = rucksack.length();
        int halfSize = itemsAmount / 2;
        String leftHalf = rucksack.substring(0, halfSize);
        String rightHalf = rucksack.substring(halfSize, itemsAmount);
        Set<Character> rightItems = rightHalf.chars().mapToObj(value -> (char) value).collect(Collectors.toSet());
        return leftHalf.chars().mapToObj(value -> (char) value).filter(rightItems::contains).findFirst();
    }

    public static String findCommonItemsAmongTwoRucksacks(String rucksack1, String rucksack2) {
        StringBuilder commonItems = new StringBuilder();
        char[] leftItems = rucksack1.toCharArray();
        for (char item : leftItems) {
            if (rucksack2.contains(String.valueOf(item))) {
                commonItems.append(item);
            }
        }
        return commonItems.toString();
    }

    public static int priorityConverter(char item) {
        return (int) item > 96 ? (int) item - 96 : (int) item - 38;
    }
}
