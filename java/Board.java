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
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                System.out.print(cells[i][j].getSymbol());
                if(j < 2){
                    System.out.print(" | ");
                }
            }
            System.out.println();
            if(i < 2) {
                System.out.println("---------");
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
