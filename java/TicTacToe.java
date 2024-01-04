import java.util.Scanner;
public class TicTacToe {
    //creating instances of the board, players 1 and 2, AI player, and the playing mode
    private Board board;
    private Player player1, player2, currentPlayer;
    private Player aiPlayer; 
    private PlayingMode playingMode;
    private Scanner s = new Scanner(System.in); //For integer inputs
    private Scanner sc = new Scanner(System.in); //For String inputs

    public enum PlayingMode{
        // game mode where two players play against each other
        USER_VS_USER,
        //game mode where a user plays against AI
        USER_VS_AI
    }

    public enum gameDifficulty{
        EASY, HARD
    }

    // TicTacToe class constructor
    public TicTacToe(){
        board = new Board();
        chooseGameMode();
        setupPlayers();
    }
    
    // this method asks the user which game mode is preferred
    private void chooseGameMode(){
        System.out.println("Choose game mode: \n1. User vs User \n2. User vs AI");
        int choice = s.nextInt();
        do{
            switch(choice){
                case 1:
                // sets playing mode to user vs user
                playingMode = PlayingMode.USER_VS_USER;
                break;

                case 2:
                // sets playing mode to user vs AI
                playingMode = PlayingMode.USER_VS_AI;
                break; 

                default:
                System.out.println("Input entered is invalid. Please enter a valid value");
            }
        }while((choice != 1) && (choice != 2));
    }

    private gameDifficulty chooseGameDifficulty(){
        System.out.println("Choose game difficulty: \n1. Easy \n2. Hard");
        int choice = s.nextInt();
        do{
        switch(choice){
            case 1:
            return gameDifficulty.EASY;
            case 2:
            return gameDifficulty.HARD;
            default: System.out.println("Input entered is invalid. Selecting Easy Mode as default");
            return gameDifficulty.EASY;
        }
        }while((choice != 1) && (choice != 2));

    }

    private void setupPlayers(){
        if(playingMode == PlayingMode.USER_VS_USER){
            // name for player 1 is entered
            System.out.print("Enter name for Player 1: ");
            String player1Name = sc.nextLine();
            // a new player is created
            player1 = new Player(player1Name, 'X');

            // name for player 2 is entered
            System.out.print("Enter name for Player 2: ");
            String player2Name = sc.nextLine();
            // a new player is created
            player2 = new Player(player2Name, 'O');
        }

        else if (playingMode == PlayingMode.USER_VS_AI){
            // player name is entered
            System.out.print("Enter your name: ");
            String playerName = sc.nextLine();
            // new player is created
            player1 = new Player(playerName, 'X');
            // new AI player is created
            aiPlayer = new AIPlayer('O', null);

            gameDifficulty level = chooseGameDifficulty();
            if(level == gameDifficulty.EASY){
                aiPlayer = new AIPlayer('O', AIPlayer.Strategy.RANDOM);
            }else{
                aiPlayer = new AIPlayer('O', AIPlayer.Strategy.MINIMAX);
                }
        }
        // player 1 starts the game
        currentPlayer = player1;
    }

    public void playGame(){
        int row, column;

        do{
            // the board is displayed
            board.display();

            if(playingMode == PlayingMode.USER_VS_USER){
                handleUserMove(s);
            }else{
                if(currentPlayer instanceof AIPlayer){
                    //AI's turn
                    AIPlayer ai = (AIPlayer) currentPlayer;
                    Move aiMove = ai.getBestMove(board);
                    row = aiMove.getRow();
                    column = aiMove.getColumn();
                    makeMove(row, column);
                }
                else{
                    //User's turn
                    handleUserMove(s);
            }
        }

            if(board.checkForWin(currentPlayer.getSymbol())){
                board.display();
                System.out.println(currentPlayer.getName() + " wins!");
                return;
            } else if (board.isBoardFull()){
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
            if (playingMode == PlayingMode.USER_VS_USER){
            // if player 1, then switch to player 2
            // if false, then switch to player 1
            currentPlayer = (currentPlayer == player1)? player2 : player1;
            }
            else if (playingMode == PlayingMode.USER_VS_AI){
                currentPlayer = (currentPlayer == player1) ? aiPlayer : player1;
            }
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
            TicTacToe game = new TicTacToe();
            game.playGame();
        }
    }

    
            

     

