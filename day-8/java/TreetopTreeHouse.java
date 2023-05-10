import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TreetopTreeHouse {
    public static void main(String[] args) {
        String path = "day-8/resources/input.txt";

        int[][] trees = readInput(path);
        boolean[][] visible = isVisible(trees);
        int visibleTrees = 0;
        for (int i = 0; i < visible.length; i++) {
            for (int j = 0; j < visible[0].length; j++) {
                if (visible[i][j]) {
                    visibleTrees++;
                }
            }
        }
        System.out.println("Number of trees that are visible from outside the grid: " + visibleTrees);
        // 1717

        //Part Two
        int[][] scenicScores = scenicScores(trees);
        int highestScenicScore = 0;
        for (int i = 0; i < scenicScores.length; i++) {
            for (int j = 0; j < scenicScores[0].length; j++) {
                if (scenicScores[i][j] > highestScenicScore) {
                    highestScenicScore = scenicScores[i][j];
                }
            }
        }
        System.out.println("Highest scenic score: " + highestScenicScore);
        // 321975
    }

    private static int[][] scenicScores(int[][] trees) {
        System.out.println("Calculating scenicScores[][].");
        int rowsNumber = trees.length;
        int columnsNumber = trees[0].length;
        int[][] treesT = new int[columnsNumber][rowsNumber];//5x7
        for (int k = 0; k < rowsNumber; k++) {
            for (int j = 0; j < columnsNumber; j++) {
                treesT[j][k] = trees[k][j];
            }
        }

        int[][] scenicScoresLeftRight = new int[rowsNumber][columnsNumber];
        System.out.println("...scenicScoresLeftRight...");
        for (int i = 0; i < rowsNumber; i++) {
            int[] a = scenicScoreInBothDirections(trees[i]);
            System.out.println("row_" + Arrays.toString(trees[i]) + "___" + Arrays.toString(a));
            scenicScoresLeftRight[i] = a;
        }

        int[][] scenicScoresUpDownT = new int[columnsNumber][rowsNumber];
        System.out.println("...scenicScoresUpDownT...");
        for (int i = 0; i < columnsNumber; i++) {
            int[] b = scenicScoreInBothDirections(treesT[i]);
            System.out.println("row_" + Arrays.toString(treesT[i]) + "___" + Arrays.toString(b));
            scenicScoresUpDownT[i] = b;
        }

        int[][] scores = new int[rowsNumber][columnsNumber];
        for (int r = 0; r < rowsNumber; r++) {
            for (int c = 0; c < columnsNumber; c++) {
                scores[r][c] = scenicScoresLeftRight[r][c] * scenicScoresUpDownT[c][r];
            }
        }
        return scores;
    }

    private static int[] scenicScoreInBothDirections(int[] row) {
        int length = row.length;
        int[] scenicScore = new int[length];
//        System.out.println("ROW: " + Arrays.toString(row));
        for (int i = 1; i < length; i++) {
            //from the left
            int lScore = 0;
            for (int l = i - 1; l >= 0; l--) {
                if (row[i] > row[l]) {
                    lScore++;
                } else if (row[i] <= row[l]) {
                    lScore++;
                    break;
                }
            }
            //from the right
            int rScore = 0;
            for (int r = i + 1; r < length; r++) {
                if (row[i] > row[r]) {
                    rScore++;
                } else if (row[i] <= row[r]) {
                    rScore++;
                    break;
                }
            }
            scenicScore[i] = lScore * rScore;
        }
        scenicScore[0] = 0;
        scenicScore[length - 1] = 0;
        return scenicScore;
    }


    private static boolean[][] isVisible(int[][] trees) {
        int rowsNumber = trees.length;
        int columnsNumber = trees[0].length;

        boolean[][] visibleLeftRight = new boolean[rowsNumber][columnsNumber];
        for (int i = 0; i < rowsNumber; i++) {
            System.out.println("row_" + Arrays.toString(trees[i]));
            visibleLeftRight[i] = isVisibleFromBothDirections(trees[i]);
        }
        System.out.println("111111111111111111111111111");
        for (int i = 0; i < rowsNumber; i++) {
            System.out.println(Arrays.toString(trees[i]));
        }
        System.out.println("---------------------------");
        for (int i = 0; i < rowsNumber; i++) {
            System.out.println(Arrays.toString(visibleLeftRight[i]));
        }
        System.out.println("111111111111111111111111111");

        System.out.println("!!!! matrix transposition");
        //matrix transposition (7x5 -> 5x7)
        int[][] treesT = new int[columnsNumber][rowsNumber];//5x7
        for (int k = 0; k < rowsNumber; k++) {
            for (int j = 0; j < columnsNumber; j++) {
                treesT[j][k] = trees[k][j];
            }
        }
        System.out.println("2222222222222222222222222222");
        for (int i = 0; i < columnsNumber; i++) {
            System.out.println(Arrays.toString(treesT[i]));
        }
        System.out.println("++++++++++++++++++++++++++++");

        //5x7
        boolean[][] visibleUpDownT = new boolean[columnsNumber][rowsNumber];
        for (int i = 0; i < columnsNumber; i++) {
            System.out.println("row_" + Arrays.toString(treesT[i]));
            visibleUpDownT[i] = isVisibleFromBothDirections(treesT[i]);
        }

        for (int i = 0; i < columnsNumber; i++) {
            System.out.println(Arrays.toString(visibleUpDownT[i]));
        }
        System.out.println("2222222222222222222222222222");
        boolean[][] visibleUpDown = new boolean[rowsNumber][columnsNumber];//(7x5)
        for (int k = 0; k < columnsNumber; k++) {
            for (int j = 0; j < rowsNumber; j++) {
                visibleUpDown[j][k] = visibleUpDownT[k][j];
            }
        }

        for (int i = 0; i < rowsNumber; i++) {
            for (int j = 0; j < columnsNumber; j++) {
                if (!visibleLeftRight[i][j] && visibleUpDown[i][j]) {
                    visibleLeftRight[i][j] = true;
                }
            }
        }
        return visibleLeftRight;
    }

    private static boolean[] isVisibleFromBothDirections(int[] row) {
        int length = row.length;
        boolean[] visible = new boolean[length];
        int leftHighestTreePosition = 0;
        int leftHighestTree = row[leftHighestTreePosition];
        //left check
        for (int i = 1; i < length; i++) {
            if (row[i] > leftHighestTree) {
                visible[i] = true;
                leftHighestTree = row[i];
                leftHighestTreePosition = i;
            }
            if (leftHighestTree == 9) {
                leftHighestTreePosition = i;
                break;
            }
        }
        visible[0] = true;
//        System.out.println("LEFTCHECK completed: leftHighestTreePosition_" + leftHighestTreePosition + ", highestTree_" + leftHighestTree);
//        System.out.println(Arrays.toString(visible));

        //right check
        boolean[] visibleFromRight = new boolean[length];
        if (leftHighestTreePosition != length - 1) {
            visibleFromRight = isVisibleFromRight(row, leftHighestTreePosition);
        }

        for (int i = 0; i < length; i++) {
            if (!visible[i] && visibleFromRight[i]) {
                visible[i] = true;
            }
        }

//        System.out.println("LEFTCHECK + RIGHTCHECK combined");
//        System.out.println(Arrays.toString(visible));
        return visible;
    }

    private static boolean[] isVisibleFromRight(int[] row, int leftHighestTreePosition) {
        int length = row.length;
        boolean[] visible = new boolean[length];
        int rightHighestTreePosition = length - 1;
        int rightHighestTree = row[rightHighestTreePosition];
        for (int i = length - 1; i > 0; i--) {
            if (row[i - 1] > rightHighestTree) {
                visible[i - 1] = true;
                rightHighestTree = row[i - 1];
            }
            if (rightHighestTree == 9 | leftHighestTreePosition == i) {
                break;
            }
        }
        visible[length - 1] = true;
//        System.out.println("RIGHTCHECK completed:");
//        System.out.println(Arrays.toString(visible));
        return visible;
    }

    public static int[][] readInput(String fileName) {
        int[][] trees = null;
        int rowLength = 0;
        List<char[]> rows = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line = bufferedReader.readLine();
            rowLength = line.trim().length();
            while (line != null) {
//                System.out.print("line: *" + line + "*. ");
                char[] treeLine = line.toCharArray();
                rows.add(treeLine);

                line = bufferedReader.readLine();
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        trees = new int[rows.size()][rowLength];
        System.out.println();
        for (int i = 0; i < rows.size(); i++) {
            for (int j = 0; j < rowLength; j++) {
                trees[i][j] = Integer.parseInt(String.valueOf(rows.get(i)[j]));
            }
        }
        return trees;
    }
}