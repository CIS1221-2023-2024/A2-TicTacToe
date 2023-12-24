import java.util.Scanner;
public class TicTacToe {
    private Board board;
    private Player player1, player2, currentPlayer;
    private AIPlayer aiPlayer; 
    private PlayingMode playingMode;
    private Scanner s = new Scanner(System.in);

    public enum PlayingMode{
        USER_VS_USER,
        USER_VS_AI
    }

    public TicTacToe(String player1Name, String player2Name){
        board = new Board();
        player1 = new Player(player1Name, 'X');
        player2 = new Player(player2Name, 'O');
        aiPlayer = new AIPlayer('O');
        currentPlayer = player1;
        this.playingMode = playingMode;
    }

    public void playGame(){
        int row, column;

        do{
            board.display();

            if(playingMode == PlayingMode.USER_VS_USER){
                handleUserMove(s);
            }else{
                if(currentPlayer == player1){
                    handleUserMove(s);
                }else{
                    //AI's turn
                    Move aiMove = aiPlayer.getBestMove(board);
                    row = aiMove.getRow();
                    column = aiMove.getColumn();
                    makeMove(row, column);
                }
            }

            if(checkForWin()){
                board.display();
                System.out.println(currentPlayer.getName() + "wins!");
                return;
            } else if (isBoardFull()){
                board.display();
                System.out.println("It's a draw!");
                return;
            }

            switchPlayer();
        }while(true);
    }

    public void makeMove(int row, int column){
        if(isValidMove(row, column)){
            board.setCellSymbol(row, column, currentPlayer.getSymbol());
        }
    }
    private void handleUserMove(Scanner s){
        int row, column;
        System.out.println(currentPlayer.getName() + ", enter your move (row and column, seperated by space): ");
        row = s.nextInt();
        column = s.nextInt();

        if(isValidMove(row, column)){
            makeMove(row, column);
        }else{
            System.out.println("Invalid move. Try again.");
            handleUserMove(s);
        }
    }
            
        private boolean isValidMove(int row, int column){
            return row>= 0 && row < 3 && column >= 0 && column < 3 && board.isCellEmpty(row,column);
        }
        private void switchPlayer(){
            // if player 1, then switch to player 2
            // if false, then switch to player 1
            currentPlayer = (currentPlayer == player1)? player2 : player1;
        }

        private boolean isBoardFull(){
            return board.isBoardFull();
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

        public static void main(String args[]){
            TicTacToe game = new TicTacToe("Player1", "Player2");
            game.playGame();
        }
    }

    
            

     

