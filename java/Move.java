public class Move {
    private int row, column;

    public Move(int row, int column){
        this.row = row;
        this.column = column;
    }

    public int getRow(){
        return row;
    }

    public int getColumn(){
        return column;
    }

    public static int[] getRowCol(int number){
        int row = (number -1)/ 3;
        int column = (number -1) % 3;
        return new int[] {row, column};
    }
}