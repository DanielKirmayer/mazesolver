import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        String[][] maze = getMaze("src/InputData");

        ArrayList<Point> finalPath = new ArrayList<Point>();
        finalPath.add(new Point(0, 0));
        maze[0][0] = "V";

        ArrayList<Point> intersectionPoints = new ArrayList<Point>();
        for (int i = 0; i < maze.length; i++) {
            System.out.println(Arrays.toString(maze[i]));
        }

        int currentRow = 0;
        int currentColumn = 0;

        while (!(finalPath.getLast().getRow() == maze.length - 1 && finalPath.getLast().getColumn() == maze[0].length - 1)) {

            int placesToGo = 0;

            boolean goingUp = false;
            boolean goingDown = false;
            boolean goingLeft = false;
            boolean goingRight = false;

            if (checkUp(currentRow, currentColumn, maze)) {
                goingUp = true;
                placesToGo++;
            }
            if (checkDown(currentRow, currentColumn, maze)) {
                goingDown = true;
                placesToGo++;
            }
            if (checkLeft(currentRow, currentColumn, maze)) {
                goingLeft = true;
                placesToGo++;
            }
            if (checkRight(currentRow, currentColumn, maze)) {
                goingRight = true;
                placesToGo++;
            }

            if (placesToGo > 1) {
                finalPath.getLast().setFork(true);
                maze[finalPath.getLast().getRow()][finalPath.getLast().getColumn()] = "I";
                intersectionPoints.add(finalPath.getLast());
            }

            if (goingUp) {
                currentRow--;
            } else if (goingDown) {
                currentRow++;
            } else if (goingLeft) {
                currentColumn--;
            } else if (goingRight) {
                currentColumn++;
            } else {
                fillDeadEnd(finalPath, maze);
                if (!intersectionPoints.isEmpty()) {
                    Point lastFork = intersectionPoints.removeLast();
                    currentRow = lastFork.getRow();
                    currentColumn = lastFork.getColumn();
                }
            }

            finalPath.add(new Point(currentRow, currentColumn));
            maze[currentRow][currentColumn] = "V";

            for (int i = 0; i < maze.length; i++) {
                System.out.println(Arrays.toString(maze[i]));
            }

            System.out.println(finalPath);
            System.out.println(intersectionPoints);
        }
        System.out.println("Solution Path: " + finalPath);
    }

    public static String[][] getMaze(String fileName) {
        File f = new File(fileName);
        Scanner s = null;
        try {
            s = new Scanner(f);
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            System.exit(1);
        }

        ArrayList<String> fileData = new ArrayList<String>();
        while (s.hasNextLine())
            fileData.add(s.nextLine());

        int rows = fileData.size();
        int cols = fileData.get(0).length();

        String[][] maze = new String[rows][cols];

        for (int i = 0; i < fileData.size(); i++) {
            String d = fileData.get(i);
            for (int j = 0; j < d.length(); j++) {
                maze[i][j] = d.charAt(j) + "";
            }
        }
        return maze;
    }

    public static boolean checkUp(int row, int column, String[][] givenMaze) {
        return row != 0 && Objects.equals(givenMaze[row - 1][column], ".");
    }

    public static boolean checkDown(int row, int column, String[][] givenMaze) {
        return row != givenMaze.length - 1 && Objects.equals(givenMaze[row + 1][column], ".");
    }

    public static boolean checkLeft(int row, int column, String[][] givenMaze) {
        return column != 0 && Objects.equals(givenMaze[row][column - 1], ".");
    }

    public static boolean checkRight(int row, int column, String[][] givenMaze) {
        return column != givenMaze[0].length - 1 && Objects.equals(givenMaze[row][column + 1], ".");
    }

    public static void fillDeadEnd(ArrayList<Point> path, String[][] maze) {
        while (!path.isEmpty() && !path.getLast().isFork()) {
            Point p = path.removeLast();
            maze[p.getRow()][p.getColumn()] = "#";
        }
    }
}
