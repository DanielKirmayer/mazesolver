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

        ArrayList<String> finalPath = new ArrayList<String>();

        ArrayList<String> intersectionPoints = new ArrayList<String>();
        finalPath.add("(0,0)");
        for (int i = 0; i < maze.length; i++) {
            System.out.println(Arrays.toString(maze[i]));
        }
        int count = 0;

        int currentRow = 0;
        int currentColumn = 0;



        while(!finalPath.contains("("+(maze.length-1)+","+(maze[0].length-1)+")")){

            boolean goingUp;
            boolean goingDown;
            boolean goingLeft;
            boolean goingRight;


            if(checkUp(currentRow,currentColumn,maze,finalPath)) {
                currentRow --;
            }
            else if(checkDown(currentRow,currentColumn,maze,finalPath)) {
                currentRow ++;
            }
            else if(checkLeft(currentRow,currentColumn,maze,finalPath)) {
                currentColumn --;
            }
            else if(checkRight(currentRow,currentColumn,maze,finalPath)) {
                currentColumn ++;
            }

            finalPath.add("("+currentRow+","+currentColumn+")");
            count++;


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


    public static boolean checkUp(int row,int column,String [][] givenMaze,ArrayList<String> places)
    {

        if(row != 0 && !places.contains("("+(row-1)+","+column+")") && Objects.equals(givenMaze[row - 1][column], "."))
            return true;

        return false;
    }
    public static boolean checkDown(int row,int column,String [][] givenMaze,ArrayList<String> places)
    {
        if(row!= givenMaze.length-1 && !places.contains("("+(row+1)+","+column+")") &&  Objects.equals(givenMaze[row + 1][column], "."))
            return true;

        return false;
    }
    public static boolean checkLeft(int row,int column,String [][] givenMaze,ArrayList<String> places)
    {
        if(column != 0 && !places.contains("("+(row)+","+(column-1)+")") && Objects.equals(givenMaze[row][column - 1], "."))
            return true;

        return false;
    }
    public static boolean checkRight(int row,int column,String [][] givenMaze,ArrayList<String> places)
    {
        if(column != givenMaze[0].length-1 && !places.contains("("+(row)+","+(column+1)+")") && Objects.equals(givenMaze[row][column + 1], "."))
            return true;

        return false;
    }
}