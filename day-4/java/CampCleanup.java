import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CampCleanup {
    public static void main(String[] args) {
        String path = "day-4/resources/input.txt";

        System.out.println("howManyAssignmentPairsDoesOneRangeFullyContainTheOther: " + howManyAssignmentPairsDoesOneRangeFullyContainTheOther(path));
        // 459

        System.out.println("howManyAssignmentPairsOverlapEachOther: " + howManyAssignmentPairsOverlapEachOther(path));
        // 779
    }

    public static int howManyAssignmentPairsDoesOneRangeFullyContainTheOther(String fileName) {
        int oneRangeFullyContainTheOtherCount = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String pair = bufferedReader.readLine();
            while (pair != null) {
                String[] ranges = pair.split(",");
                Range r1 = new Range(ranges[0]);
                Range r2 = new Range(ranges[1]);
                if (r1.contain(r2) | r2.contain(r1)) {
                    oneRangeFullyContainTheOtherCount++;
                }
                pair = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return oneRangeFullyContainTheOtherCount;
    }

    public static int howManyAssignmentPairsOverlapEachOther(String fileName) {
        int overlapPairCount = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String pair = bufferedReader.readLine();
            while (pair != null) {
                String[] ranges = pair.split(",");
                Range r1 = new Range(ranges[0]);
                Range r2 = new Range(ranges[1]);
                if (r1.overlap(r2) | r2.overlap(r1)) {
                    overlapPairCount++;
                }
                pair = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return overlapPairCount;
    }
}

class Range {
    int start;
    int end;

    boolean is1Range;

    public Range(String rangeWithHyphen) {
        start = Integer.parseInt(rangeWithHyphen.split("-")[0]);
        end = Integer.parseInt(rangeWithHyphen.split("-")[1]);
        is1Range = start == end;
    }

    boolean contain(Range other) {
        return start <= other.start && other.end <= end;
    }

    boolean overlap(Range other) {
        return start <= other.end && other.start <= end;
    }
}
