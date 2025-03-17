public class Point {
    private int row;
    private int column;
    private boolean fork = false;
    private boolean visited = true;

    public Point(int row,int column){
        this.row = row;
        this.column = column;
    }
    public Point(int row,int column,boolean fork){
        this.row = row;
        this.column = column;
        this.fork = fork;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public boolean isFork() {
        return fork;
    }

    public void setFork(boolean fork) {
        this.fork = fork;
    }

    @Override
    public String toString() {
        return "("+row+","+column+")";
    }
    public boolean compare(Point other){
        if(other.getRow() == this.getRow() && other.getColumn() == this.getColumn())
            return true;
        return false;
    }
}
