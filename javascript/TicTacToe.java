import java.util.Scanner;
public class TicTacToe {
    private Board board;
    private Player player1, player2, currentPlayer;
    private Scanner s = new Scanner(System.in);

    public TicTacToe(String player1Name, String player2Name){
        board = new Board();
        player1 = new Player(player1Name, 'X');
        player2 = new Player(player2Name, 'O');
        currentPlayer = player1;
    }
    public void playGame(){
        int row, column;

        do{
            board.display();
            System.out.println(currentPlayer.getName() + ", enter your move (row, column, seperated by a space): ");
            row = s.nextInt();
            column = s.nextInt();

            if(isValidMove(row, column)){
                board.setCellSymbol(row, column, currentPlayer.getSymbol());
                if(checkForWin()){
                    board.display();
                    System.out.println(currentPlayer.getName() + " wins!");
                    return;
                } else if (isBoardFull()){
                    board.display();
                    System.out.println("It's a draw!");
                    return;
                }else{
                    switchPlayer();
                }
                }else{
                    System.out.println("Invalid move. Try again");
                }
            }while(true);
        }
        private boolean isValidMove(int row, int column){
            return row>= 0 && row < 3 && column >= 0 && column < 3 && board.isCellEmpty(row,column);
        }
        private void switchPlayer(){
            // if player 1, then switch to player 2
            // if false, then switch to player 1
            currentPlayer = (currentPlayer == player1)? player2 : player1;
        }
        private boolean checkForWin(){
            //check rows
            for(int i = 0; i < 3; i++){
                if(board.getSymbol(i,0)== currentPlayer.getSymbol() &&
                    board.getSymbol(i,1) == currentPlayer.getSymbol() &&
                    board.getSymbol(i,2) == currentPlayer.getSymbol()){
                        return true;
                    }
            }
            //check columns
            for(int j = 0; j < 3; j++){
                if(board.getSymbol(0,j) == currentPlayer.getSymbol() &&
                    board.getSymbol(1,j) == currentPlayer.getSymbol() &&
                    board.getSymbol(2,j) == currentPlayer.getSymbol()){
                        return true;
                    }
            //check diagonal (from left to right)
            if(board.getSymbol(0,0) == currentPlayer.getSymbol() &&
                board.getSymbol(1,1) == currentPlayer.getSymbol() &&
                board.getSymbol(2,2) == currentPlayer.getSymbol()){
                    return true;
                }
            //check diagonal (from right to left)
            if(board.getSymbol(0,2) == currentPlayer.getSymbol() &&
                board.getSymbol(1,1) == currentPlayer.getSymbol() &&
                board.getSymbol(2,0) == currentPlayer.getSymbol()){
                    return true;
                }
            }
            return false;
        }
        private boolean isBoardFull(){
            return board.isBoardFull();
        }

        public static void main(String args[]){
            TicTacToe game = new TicTacToe("Player1", "Player2");
            game.playGame();
        }
    }
            

     

