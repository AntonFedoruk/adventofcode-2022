import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class SupplyStacks {
    public static void main(String[] args) {
        String path = "day-5/resources/input.txt";

        System.out.println("getTopOfCrateStack9000: " + getTopOfCrateStack9000(path));
        // SHMSDGZVC

        //Part Two
        System.out.println("getTopOfCrateStack9001: " + getTopOfCrateStack9001(path));
        // VRZGHDFBQ

    }

    public static String getTopOfCrateStack9000(String fileName) {
        Map<Integer, Deque<String>> stacksOfCratesInReverseOrder = new HashMap<>();

        String regexStacksOfCrates = "\\[(\\w)\\]\\s?|(\\s{3})\\s?";
        Pattern patternOfStacksOfCrates = Pattern.compile(regexStacksOfCrates);

        String regexRearrangementProcedure = "move \\d+ from \\d+ to \\d+";
        Pattern patternOfRearrangementProcedure = Pattern.compile(regexRearrangementProcedure);

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                System.out.println("line: *" + line + "*");
                //Reading-creating stacks of crates
                String lineWithSpaceAtTheEnd = line + " ";
                Matcher matcherOfStacksOfCrates = patternOfStacksOfCrates.matcher(lineWithSpaceAtTheEnd);
                if (stacksOfCratesInReverseOrder.isEmpty()) {
                    System.out.println("**Creating stacksOfCratesInReverseOrder**");
                    int stacksAmount = lineWithSpaceAtTheEnd.length() / 4;
                    for (int i = 1; i <= stacksAmount; i++) {
                        Deque<String> dq = new ArrayDeque<>();
                        stacksOfCratesInReverseOrder.put(i, dq);
                    }
                }
                int stackNumber = 1;
                while (matcherOfStacksOfCrates.find()) {
                    int cratesPosition = matcherOfStacksOfCrates.start() + 1;
                    String crateOrEmpty = String.valueOf(line.charAt(cratesPosition));
                    if (!crateOrEmpty.equals(" ")) {
                        stacksOfCratesInReverseOrder.get(stackNumber).add(crateOrEmpty);
                    }
                    if (stackNumber >= stacksOfCratesInReverseOrder.size()) {
                        stackNumber = 1;
                    } else {
                        stackNumber++;
                    }
                }
                //Reading-creating rearrangement procedure
                Matcher matcherOfRearrangementProcedure = patternOfRearrangementProcedure.matcher(line);
                if (matcherOfRearrangementProcedure.find()) {
                    String[] command = line.split(" ");
                    for (int i = 0; i < Integer.parseInt(command[1]); i++) {
                        stacksOfCratesInReverseOrder.get(Integer.parseInt(command[5])).addFirst(stacksOfCratesInReverseOrder.get(Integer.parseInt(command[3])).pollFirst());
                    }
                }

                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Final Stacks Of Crates");
        stacksOfCratesInReverseOrder.forEach((integer, strings) -> {
            System.out.print(integer + " has: ");
            strings.forEach(System.out::print);
            System.out.println();
        });
        return stacksOfCratesInReverseOrder.values().stream().map(Deque::getFirst).collect(Collectors.joining());
    }

    public static String getTopOfCrateStack9001(String fileName) {
        Map<Integer, Deque<String>> stacksOfCratesInReverseOrder = new HashMap<>();

        String regexStacksOfCrates = "\\[(\\w)\\]\\s?|(\\s{3})\\s?";
        Pattern patternOfStacksOfCrates = Pattern.compile(regexStacksOfCrates);

        String regexRearrangementProcedure = "move \\d+ from \\d+ to \\d+";
        Pattern patternOfRearrangementProcedure = Pattern.compile(regexRearrangementProcedure);

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                System.out.println("line: *" + line + "*");
                //Reading-creating stacks of crates
                String lineWithSpaceAtTheEnd = line + " ";
                Matcher matcherOfStacksOfCrates = patternOfStacksOfCrates.matcher(lineWithSpaceAtTheEnd);
                if (stacksOfCratesInReverseOrder.isEmpty()) {
                    System.out.println("**Creating stacksOfCratesInReverseOrder**");
                    int stacksAmount = lineWithSpaceAtTheEnd.length() / 4;
                    for (int i = 1; i <= stacksAmount; i++) {
                        Deque<String> dq = new ArrayDeque<>();
                        stacksOfCratesInReverseOrder.put(i, dq);
                    }
                }
                int stackNumber = 1;
                while (matcherOfStacksOfCrates.find()) {
                    int cratesPosition = matcherOfStacksOfCrates.start() + 1;
                    String crateOrEmpty = String.valueOf(line.charAt(cratesPosition));
                    if (!crateOrEmpty.equals(" ")) {
                        stacksOfCratesInReverseOrder.get(stackNumber).add(crateOrEmpty);
                    }
                    if (stackNumber >= stacksOfCratesInReverseOrder.size()) {
                        stackNumber = 1;
                    } else {
                        stackNumber++;
                    }
                }
                //Reading-creating rearrangement procedure
                Matcher matcherOfRearrangementProcedure = patternOfRearrangementProcedure.matcher(line);
                if (matcherOfRearrangementProcedure.find()) {
                    String[] command = line.split(" ");
                    int amountOfCrates = Integer.parseInt(command[1]);
                    if (amountOfCrates == 1) {
                        stacksOfCratesInReverseOrder.get(Integer.parseInt(command[5])).addFirst(stacksOfCratesInReverseOrder.get(Integer.parseInt(command[3])).pollFirst());
                    } else {
                        String[] crates = new String[amountOfCrates];
                        for (int i = 0; i < amountOfCrates; i++) {
                            crates[i] = stacksOfCratesInReverseOrder.get(Integer.parseInt(command[3])).pollFirst();
                        }
                        for (int i = amountOfCrates-1; i >= 0; i--) {
                            stacksOfCratesInReverseOrder.get(Integer.parseInt(command[5])).addFirst(crates[i]);
                        }
                    }
                }

                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Final Stacks Of Crates");
        stacksOfCratesInReverseOrder.forEach((integer, strings) -> {
            System.out.print(integer + " has: ");
            strings.forEach(System.out::print);
            System.out.println();
        });
        return stacksOfCratesInReverseOrder.values().stream().map(Deque::getFirst).collect(Collectors.joining());
    }
}