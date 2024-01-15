public class Move {
    private int row, column;

    // move constructor
    public Move(int row, int column){
        this.row = row;
        this.column = column;
    }

    // row getter
    public int getRow(){
        return row;
    }

    // column getter
    public int getColumn(){
        return column;
    }

    // method that converts the number inputted by the user (1-9) to the corresponding row and column index
    public static int[] getRowCol(int number){
        int row = (number -1)/ 3;
        int column = (number -1) % 3;
        return new int[] {row, column};
    }
}