import java.util.Scanner;
import java.util.InputMismatchException;
public class TicTacToe {
    //creating instances of the board, players 1 and 2, AI player, and the playing mode
    private Board board;
    private Player player1, player2, currentPlayer;
    private Player aiPlayer; 
    private PlayingMode playingMode;
    private Scanner s = new Scanner(System.in); // for integers
    private Scanner sc = new Scanner(System.in);// for Strings

    public enum PlayingMode{
        // game mode where two players play against each other
        USER_VS_USER,
        //game mode where a user plays against AI
        USER_VS_AI
    }

    public enum gameDifficulty{
        EASY, // AI chooses a random free cell to enter its token
        HARD // AI uses the Minimax algorithm
    }

    // TicTacToe class constructor
    public TicTacToe(){
        board = new Board();
        chooseGameMode();
        setupPlayers();
    }
    
    // this method asks the user which game mode is preferred
    private void chooseGameMode(){
        int choice;
        while(true){
            try{
            System.out.println("Choose game mode: \n1. User vs User \n2. User vs AI");
            choice = s.nextInt();
            if((choice == 1) || (choice == 2)){
                break;
            }
            else{
                System.out.println("Invalid input. Enter 1 or 2");
            }
            }catch (InputMismatchException e){
                System.out.println("Invalid input. Enter an integer");
                s.next();
            }
        };
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
    // this method asks the user which difficulty is preferred (in the case of user vs AI)
    private gameDifficulty chooseGameDifficulty(){
        int choice;
        while(true){
            try{
                System.out.println("\nChoose game difficulty: \n1. Easy \n2. Hard");
                choice = s.nextInt();
                if((choice == 1)||(choice == 2)){
                    break;
                }
                else{
                    System.out.println("Invalid input. Enter 1 or 2");
                }
            }catch(InputMismatchException e){
            System.out.println("Invalid input. Enter an integer");
            s.next();
            }

        }
    
        switch(choice){
            case 1:
            // sets game difficulty to Easy (random AI logic)
            return gameDifficulty.EASY;
            case 2:
            // sets game difficulty to Hard (Minimax algorithm)
            return gameDifficulty.HARD;
            default: System.out.println("Input entered is invalid. Selecting Easy Mode as default");
            return gameDifficulty.EASY;
        }
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
            System.out.print("\nEnter your name: ");
            String playerName = sc.nextLine();
            // new player is created
            player1 = new Player(playerName, 'X');
            // new AI player is created
            aiPlayer = new AIPlayer('O', null);

            gameDifficulty level = chooseGameDifficulty();
            if(level == gameDifficulty.EASY){
                // if the difficulty level chosen is Easy, then the Random Strategy is used
                aiPlayer = new AIPlayer('O', AIPlayer.Strategy.RANDOM);
            
            }else if (level == gameDifficulty.HARD){
                //if the difficulty level chosen is Hard, then the Minimax algorithm is used
                aiPlayer = new AIPlayer('O', AIPlayer.Strategy.MINIMAX);
                }
        }
        // player 1 starts the game (the user in the case of AI vs User)
        currentPlayer = player1;
    }

    public void playGame(){
        int row, column;

        do{
            // the board is displayed
            board.display();

            if(playingMode == PlayingMode.USER_VS_USER){
                handleUserMove(s);
            
            // if user vs AI
            }else{
                if(currentPlayer instanceof AIPlayer){
                    //AI's turn
                    AIPlayer ai = (AIPlayer) currentPlayer;
                    ai.makeMove(board);
                } 
                
                else{
                    //User's turn
                    handleUserMove(s);
            }
        }
            // if the game is won
            if(board.checkForWin(currentPlayer.getSymbol())){
                board.display();
                // winning player is shown
                System.out.println(currentPlayer.getName() + " wins!");
                return;
            // if board is full and no one won
            } else if (board.isBoardFull()){
                board.display();
                // message is shown
                System.out.println("It's a draw!");
                return;
            }
            // switch the player for the next turn
            switchPlayer();
        }while(true);
    }

    public void makeMove(int row, int column){
        if(board.isCellEmpty(row, column)){
            board.setCellSymbol(row, column, currentPlayer.getSymbol());
        }else{
            System.out.println("Invalid move, cell is already occupied. Try again");
            handleUserMove(s);

        }
    }
    private void handleUserMove(Scanner s){
        int number;
        boolean validMoveMade = false;
        while(!validMoveMade){
            try{
            System.out.println(currentPlayer.getName() + ", enter your move (number 1-9): ");
            number = s.nextInt();

            if(isValidMove(number)){
                int[] rowCol = Move.getRowCol(number);
                makeMove(rowCol[0], rowCol[1]);
                validMoveMade = true;
            }else{
                System.out.println("Invalid move. Try again.");
            }
                }catch(InputMismatchException e){
                    System.out.println("Input is invalid. Enter an integer between 1 and 9\n");
                    s.next();
                }
         }
    }
            
        private boolean isValidMove(int number){
            if((number>= 1)  && (number <= 9)){
                int[] rowCol = Move.getRowCol(number);
                return board.isCellEmpty(rowCol[0], rowCol[1]);
            }
            return false;
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
        public static void main(String args[]){
            TicTacToe game = new TicTacToe();
            game.playGame();
        }
    }