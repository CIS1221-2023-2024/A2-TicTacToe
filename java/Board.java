public class Board {
    private Cell[][] cells;

    public Board(){
        cells = new Cell[3][3];
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                cells[i][j] = new Cell();
            }
        }
    }

    public void display(){
        int number = 1;
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(cells[i][j].getSymbol() == ' '){
                    // Cell is empty, then show it's corresponding number
                    System.out.print(number);
                }else{
                    // Cell is occupied, print the symbol
                    System.out.print(cells[i][j].getSymbol());
                }
                number++;
                if(j < 2){ 
                    System.out.print(" | ");
                }
            }
            System.out.println();
            if(i < 2) {
                System.out.println("-------------");
            }
        }
        System.out.println();
    }

    public boolean isCellEmpty(int row, int column){
        return cells[row][column].getSymbol() == ' ';
    }

    public void setCellSymbol(int row, int column, char symbol){
        cells[row][column].setSymbol(symbol);
    }
    
    public int getSymbol(int row, int column){
        return cells[row][column].getSymbol();
    }

    public boolean checkForWin(char symbol){
        //Check rows, column,. and diagonals for a winning combination
        return checkRows(symbol) || checkColumns(symbol) || checkDiagonals(symbol);
    }
    private boolean checkRows(char symbol){
        for(int i = 0; i < 3; i ++){
            if(cells[i][0].getSymbol() == symbol && cells[i][1].getSymbol() == symbol && cells[i][2].getSymbol() == symbol){
            return true; // Found a winning row
        }
    }
    return false;
}

private boolean checkColumns(char symbol){
    for(int j = 0; j < 3; j++){
        if(cells[0][j].getSymbol() == symbol && cells[1][j].getSymbol() == symbol && cells[2][j].getSymbol() == symbol){
            return true; // Found a winning column
        }
    }
    return false;
}
private boolean checkDiagonals(char symbol){
    //Check the two diagonals
    if((cells[0][0].getSymbol() == symbol && cells[1][1].getSymbol() == symbol && cells[2][2].getSymbol() == symbol)||
    (cells[0][2].getSymbol() == symbol && cells[1][1].getSymbol() == symbol && cells[2][0].getSymbol() == symbol)){
        return true; // Found a winning diagonal
    }
    return false;

}

    public boolean isBoardFull(){
        for(int i = 0; i < 3; i ++){
            for(int j = 0; j < 3; j++){
                if (cells[i][j].getSymbol() == ' '){
                    return false;
                }
            }
        }
        return true;
    }
}
