import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class RockPaperScissors {
    public static void main(String[] args) {
        String path = "day-2/resources/input.txt";

        System.out.println("calculate part-1: " + calculateScore(path, 1));
        // 9241

        long startTime = System.currentTimeMillis();
        System.out.println("calculate part-2 V1: " + calculateScore(path, 2));
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        System.out.println("Elapsed time: " + elapsedTime + " ms");
        // 14610

        startTime = System.currentTimeMillis();
        System.out.println("calculate part-2 V2: " + calculateScore2(path, 2));
        endTime = System.currentTimeMillis();
        elapsedTime = endTime - startTime;
        System.out.println("Elapsed time: " + elapsedTime + " ms");
        // 14610

        startTime = System.currentTimeMillis();
        System.out.println("calculate part-2 V3: " + calculateScore3(path, 2));
        endTime = System.currentTimeMillis();
        elapsedTime = endTime - startTime;
        System.out.println("Elapsed time: " + elapsedTime + " ms");
        // 14610
    }

    public static int calculateScore(String fileName, int strategyVariant) {
        int score = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                String gameResult = line.replaceAll(" ", "").toUpperCase();
                GameStrategy gameVariant = switch (strategyVariant) {
                    case 1 -> GameVariant1.valueOf(gameResult);
                    case 2 -> GameVariant2.valueOf(gameResult);
                    default -> throw new IllegalArgumentException("Invalid strategy variant: " + strategyVariant);
                };
                score += gameVariant.getFinalScore();
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return score;
    }

    public static int calculateScore2(String fileName, int strategyVariant) {
        int score = 0;
        try {
            List<String> allLines = Files.readAllLines(Path.of(fileName));
            score = allLines.stream().map(line -> line.replaceAll(" ", "").toUpperCase()).map(gameResult -> switch (strategyVariant) {
                case 1 -> GameVariant1.valueOf(gameResult);
                case 2 -> GameVariant2.valueOf(gameResult);
                default -> throw new IllegalArgumentException("Invalid strategy variant: " + strategyVariant);
            }).mapToInt(GameStrategy::getFinalScore).sum();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return score;
    }

    public static int calculateScore3(String fileName, int strategyVariant) {
        int score = 0;
        try (Stream<String> lines = Files.lines(Path.of(fileName))) {
            Function<String, String> preprocess = line -> line.replaceAll(" ", "").toUpperCase();
            switch (strategyVariant) {
                case 1 -> score = lines.map(preprocess).map(GameVariant1::valueOf).mapToInt(GameStrategy::getFinalScore).sum();
                case 2 -> score = lines.map(preprocess).map(GameVariant2::valueOf).mapToInt(GameStrategy::getFinalScore).sum();
                default -> throw new IllegalArgumentException("Invalid strategy variant: " + strategyVariant);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return score;
    }
}
