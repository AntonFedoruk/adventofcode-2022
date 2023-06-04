import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CathodeRayTube {
    public static void main(String[] args) {
        String path = "day-10/resources/input.txt";

        signalStrength(path);
        System.out.println(signalStrength(path));
        // 12740

        //Part Two
        System.out.println();
        drawsCRT(path);
        // RBPARGF
    }

    public static int signalStrength(String fileName) {
        List<Integer> signalStrengths = new ArrayList<>();
        signalStrengths.add(0, 1);
        int sumOfStrengths20s = 1;

        int registerX = 1, cycle = 1;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line = bufferedReader.readLine();
            while (line != null) {
//                System.out.println("LINE: " + line);
                String[] inputs = line.split(" ");
                //noop takes one cycle to complete. It has no other effect.
                if (inputs[0].equals("noop")) {
                    signalStrengths.add(cycle, registerX);
//                    System.out.println("registerX(" + registerX + ")*cycle(" + cycle + "):" + registerX);
                } else {
//                    System.out.println("registerX(" + registerX + ")*cycle(" + cycle + "):" + registerX);
                    signalStrengths.add(cycle, registerX);
                    cycle++;
                    registerX += Integer.parseInt(inputs[1]);
                    signalStrengths.add(cycle, registerX);
//                    System.out.println("registerX(" + registerX + ")*cycle(" + cycle + "):" + registerX);
                }
                cycle++;
                line = bufferedReader.readLine();
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        sumOfStrengths20s =
                signalStrengths.get(19) * 20 +
                        signalStrengths.get(59) * 60 +
                        signalStrengths.get(99) * 100 +
                        signalStrengths.get(139) * 140 +
                        signalStrengths.get(179) * 180 +
                        signalStrengths.get(219) * 220;
        return sumOfStrengths20s;
    }

    public static void drawsCRT(String fileName) {
        List<Integer> signalStrengths = new ArrayList<>();
        signalStrengths.add(0, 1);

        int registerX = 1, cycle = 1;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] inputs = line.split(" ");
                //noop takes one cycle to complete. It has no other effect.
                if (inputs[0].equals("noop")) {
                    signalStrengths.add(cycle, registerX);
                } else {
                    signalStrengths.add(cycle, registerX);
                    cycle++;
                    registerX += Integer.parseInt(inputs[1]);
                    signalStrengths.add(cycle, registerX);
                }
                cycle++;
                line = bufferedReader.readLine();
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }

        int rowCRTpos = 1, spriteLeftPosition;
        for (int z = 0; z < 6; z++) {
            System.out.print("");
            for (int i = 40 * z; i < 40 * (z + 1); i++) {
                spriteLeftPosition = signalStrengths.get(i);
                if (rowCRTpos == spriteLeftPosition | rowCRTpos == (spriteLeftPosition + 1) | rowCRTpos == (spriteLeftPosition + 2)) {
                    System.out.print("#");
                } else {
                    System.out.print(".");
                }
                rowCRTpos++;
            }
            rowCRTpos = 1;
            System.out.println();
        }

//        int rowCRTpos = 1, spriteLeftPosition;
//        System.out.print("Cycle   1 -> ");
//        for (int i = 0; i < 40; i++) {
//            spriteLeftPosition = signalStrengths.get(i);
//            if (rowCRTpos == spriteLeftPosition | rowCRTpos == (spriteLeftPosition + 1) | rowCRTpos == (spriteLeftPosition + 2)) {
//                System.out.print("#");
//            } else {
//                System.out.print(".");
//            }
//            rowCRTpos++;
//        }
//        System.out.println(" <- Cycle  40");
//
//        rowCRTpos = 1;
//        System.out.print("Cycle  41 -> ");
//        for (int i = 40; i < 80; i++) {
//            spriteLeftPosition = signalStrengths.get(i);
//            if (rowCRTpos == spriteLeftPosition | rowCRTpos == (spriteLeftPosition + 1) | rowCRTpos == (spriteLeftPosition + 2)) {
//                System.out.print("#");
//            } else {
//                System.out.print(".");
//            }
//            rowCRTpos++;
//        }
//        System.out.println(" <- Cycle  80");
//
//        rowCRTpos = 1;
//        System.out.print("Cycle  81 -> ");
//        for (int i = 80; i < 120; i++) {
//            spriteLeftPosition = signalStrengths.get(i);
//            if (rowCRTpos == spriteLeftPosition | rowCRTpos == (spriteLeftPosition + 1) | rowCRTpos == (spriteLeftPosition + 2)) {
//                System.out.print("#");
//            } else {
//                System.out.print(".");
//            }
//            rowCRTpos++;
//        }
//        System.out.println(" <- Cycle 120");
    }
}