import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class RockPaperScissors {
    public static void main(String[] args) {
        String path = "day-2/resources/input.txt";

        System.out.println("calculate part-1: " + calculateScore(path, 1));
        // 9241

        System.out.println("calculate part-2: " + calculateScore(path, 2));
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
}
