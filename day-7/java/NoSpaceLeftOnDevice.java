import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NoSpaceLeftOnDevice {
    public static void main(String[] args) {
        String path = "day-7/resources/input.txt";

        Directory root = readRootDir(path);

        System.out.println("sumOfTheTotalSizesOfDirectoriesWithLessThen100000: " + sumOfTheTotalSizesOfDirectoriesWithLessThen100000(root));
        // 1306611

        //Part Two
        int totalDiskSpace = 70_000_000;
        int neededUnusedSpace = 30_000_000;

        int usedSize = root.getSize();
        int freeSpace = totalDiskSpace - usedSize;
        int neededSpace = neededUnusedSpace - freeSpace;

        System.out.println("should be deleted folder: "+dirSizes(root).stream().sorted().filter(size -> size >= neededSpace).findFirst().get());
        // 13210366
    }

    public static Directory readRootDir(String fileName) {
        String commandTemplate = "^\\$ (?<command>\\w+)(?<param>.*)";
        Pattern pattern_commandTemplate = Pattern.compile(commandTemplate);

        String dirNote = "^dir (?<name>\\w+)";
        Pattern pattern_dirNote = Pattern.compile(dirNote);

        String fileNote = "(?<size>^\\d+) (?<name>.+)";
        Pattern pattern_fileNote = Pattern.compile(fileNote);


        Directory root = new Directory("root");

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line = bufferedReader.readLine();
            Directory currentDir = root;
            while (line != null) {
                System.out.print("line: *" + line + "*. ");
                System.out.println("Current dir: '" + currentDir.getName() + "'(father: " + currentDir.getFatherDir().getName() + ")");
                Matcher matcherOf_commandTemplate = pattern_commandTemplate.matcher(line);
                if (matcherOf_commandTemplate.find()) {
                    String commandName = matcherOf_commandTemplate.group("command");
                    System.out.println("Command name: '" + commandName + "'");
                    if (commandName.equals("cd")) {
                        String dirName = matcherOf_commandTemplate.group("param").trim();
                        if (dirName.equals("/")) {
                            currentDir = root;
                            System.out.println("__ currentDir changed to: "+ root.getName());
                        } else if (dirName.equals("..")) {
                            currentDir = currentDir.getFatherDir();
                            System.out.println("__ currentDir(" + currentDir.getName() + ") changed to: " + currentDir.getFatherDir().getName() + ".");
                        } else {
                            currentDir = currentDir.getInnerDirectories().stream().filter(directory -> directory.getName().equals(dirName)).findAny().get();
                            System.out.println("__ currentDir changed to: " + currentDir.getName());
                        }
                    }
                }
                Matcher matcherOf_dirNote = pattern_dirNote.matcher(line);
                Matcher matcherOf_fileNote = pattern_fileNote.matcher(line);
                if (matcherOf_dirNote.find()) {
                    System.out.print("<-- it is a DIRECTORY. ");
                    currentDir.addInnerDirectory(new Directory(matcherOf_dirNote.group("name"), currentDir));
                    System.out.println(" Added to '" + currentDir.getName() + "' directory.");
                }
                if (matcherOf_fileNote.find()) {
                    System.out.print("<-- it is a FILE. ");
                    int size = Integer.parseInt(matcherOf_fileNote.group("size"));
                    currentDir.addInnerFile(new File(matcherOf_fileNote.group("name"), size));
                    System.out.println(" Added to '" + currentDir.getName() + "' directory.");
                }

                System.out.println(" go to next line ===> ");
                line = bufferedReader.readLine();
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        return root;
    }

    public static int sumOfTheTotalSizesOfDirectoriesWithLessThen100000(Directory directory) {
        int maxSize = 100_000;
        int sum = 0;
        if (directory.getSize() <= maxSize) {
            sum += directory.getSize();
        }

        for (Directory d : directory.getInnerDirectories()) {
            sum += sumOfTheTotalSizesOfDirectoriesWithLessThen100000(d);
        }
        return sum;
    }

    public static List<Integer> dirSizes(Directory dir) {
        List<Integer> dirSizes = new ArrayList<>();
        dirSizes.add(dir.getSize());
        for (Directory d : dir.getInnerDirectories()) {
            dirSizes.addAll(dirSizes(d));
        }
        return dirSizes;
    }
}

class Directory {
    boolean isRoot = false;
    private Directory fatherDir;

    private final String name;

    private List<Directory> directories = new ArrayList<>();
    private List<File> files = new ArrayList<>();

    public Directory(String name, Directory fatherDir) {
        this.name = name;
        this.fatherDir = fatherDir;
    }

    public Directory(String name) {
        this.name = name;
        isRoot = true;
    }

    public Directory getFatherDir() {
        if (isRoot) {
            return this;
        }
        return fatherDir;
    }

    public int getSize() {
        int totalSize = 0;
        if (!directories.isEmpty()) {
            totalSize += directories.stream().mapToInt(Directory::getSize).sum();
        }
        if (!files.isEmpty()) {
            totalSize += files.stream().mapToInt(File::getSize).sum();
        }
        return totalSize;
    }

    public String getName() {
        return name;
    }

    public void addInnerDirectory(Directory directory) {
        this.directories.add(directory);
    }

    public void addInnerFile(File file) {
        this.files.add(file);
    }

    public List<Directory> getInnerDirectories() {
        return this.directories;
    }
}

class File {
    String name;
    private final int size;

    public File(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}