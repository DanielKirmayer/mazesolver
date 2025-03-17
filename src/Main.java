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
        finalPath.add(new Point(0,0));
        maze[0][0] = "V";

        ArrayList<Point> intersectionPoints = new ArrayList<Point>();
        for (int i = 0; i < maze.length; i++) {
            System.out.println(Arrays.toString(maze[i]));
        }
        int count = 0;

        int currentRow = 0;
        int currentColumn = 0;



        while(!(finalPath.getLast().getRow()==(maze.length-1)) && !(finalPath.getLast().getRow()==maze[0].length-1)){

            int placesToGo = 0;

            boolean goingUp = false;
            boolean goingDown = false;
            boolean goingLeft = false;
            boolean goingRight = false;


            if(checkUp(currentRow,currentColumn,maze,finalPath)) {
                goingUp = true;
                placesToGo++;
            }
            if(checkDown(currentRow,currentColumn,maze,finalPath)) {
                goingDown = true;
                placesToGo++;
            }
            if(checkLeft(currentRow,currentColumn,maze,finalPath)) {
                goingLeft = true;
                placesToGo++;
            }
            if(checkRight(currentRow,currentColumn,maze,finalPath)) {
                goingRight = true;
                placesToGo++;
            }





            if(goingUp) {
                currentRow --;
            }
            else if(goingDown) {
                currentRow ++;
            }
            else if(goingLeft) {
                currentColumn --;
            }
            else if(goingRight) {
                currentColumn ++;
            }


            if(placesToGo > 1) {
                finalPath.getLast().setFork(true);
                maze[finalPath.getLast().getRow()][finalPath.getLast().getColumn()] = "I";
            }
                finalPath.add(new Point(currentRow, currentColumn));
                maze[currentRow][currentColumn] = "V";







            if(placesToGo == 0) {
                fillDeadEnd(finalPath, maze);
                currentRow = finalPath.getLast().getRow();
                currentColumn = finalPath.getLast().getColumn();
            }


            for (int i = 0; i < maze.length; i++) {
                System.out.println(Arrays.toString(maze[i]));
            }


            System.out.println(finalPath);
            System.out.println(intersectionPoints);



        }
        System.out.println(finalPath);


    }
    public static String[][] getMaze(String fileName) {
        File f = new File(fileName);
        Scanner s = null;
        try {
            s = new Scanner(f);
        }
        catch (FileNotFoundException e) {
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


    public static boolean checkUp(int row,int column,String [][] givenMaze,ArrayList<Point> places)
    {

        if(row != 0 && Objects.equals(givenMaze[row - 1][column], "."))
            return true;

        return false;
    }
    public static boolean checkDown(int row,int column,String [][] givenMaze,ArrayList<Point> places)
    {
        if(row!= givenMaze.length-1 && Objects.equals(givenMaze[row + 1][column], "."))
            return true;

        return false;
    }
    public static boolean checkLeft(int row,int column,String [][] givenMaze,ArrayList<Point> places)
    {
        if(column != 0 && Objects.equals(givenMaze[row][column - 1], "."))
            return true;

        return false;
    }
    public static boolean checkRight(int row,int column,String [][] givenMaze,ArrayList<Point> places)
    {
        if(column != givenMaze[0].length-1 && Objects.equals(givenMaze[row][column + 1], "."))
            return true;

        return false;
    }

    public static void fillDeadEnd(ArrayList<Point> path,String[][] maze){

        for (int i = path.size()-1; i > 0; i--) {
            if (path.get(i).isFork())
                break;
            maze[path.get(i).getRow()][path.get(i).getColumn()] = "#";
            path.removeLast();
        }


    }
}