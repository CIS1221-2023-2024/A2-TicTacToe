import java.util.Random;
import java.util.List;
import java.util.ArrayList;
public class AIPlayer extends Player{
    private char symbol;

    public enum Strategy{
        RANDOM, // AI places its token in a random empty cell
        MINIMAX // AI uses the Minimax algorithm 
    }
    private Strategy strategy;

    // constructor for AIPlayer
    public AIPlayer(char symbol, Strategy strategy){
        super("AI", symbol); 
        this.symbol = symbol;
        this.strategy = strategy;
    }

    public void makeMove(Board board){
        // if the trategy is selected to be random, then the AI will use the random strategy of play
        if(this.strategy == strategy.RANDOM){
            randomMove(board);
        }
        // if the strategy chosen is Miniax
        else if (this.strategy == Strategy.MINIMAX){
            long startTime = System.nanoTime();
            // then the Ai will determine the best move possible and place its symbol
            Move bestMove = getBestMove(board);
            long endTime = System.nanoTime();
            long duration = endTime - startTime;
            System.out.println("AI move calculation time (nanoseconds): " + duration);
            board.setCellSymbol(bestMove.getRow(), bestMove.getColumn(), this.symbol);
        }
    }
    // symbol getter
    public char getSymbol(){
        return symbol;
    }

    // strategy getter
    public Strategy getStrategy(){
        return this.strategy;
    }

    // method to get the best move possible
    public Move getBestMove(Board board){

        int bestScore = Integer.MIN_VALUE;
        Move bestMove = null;

        //Iteration through all the available moves
        for(int i = 0; i < 3; i++){
            for(int j = 0; j <3; j++){
                if(board.isCellEmpty(i,j)){
                    //Make the move
                    board.setCellSymbol(i,j,symbol);

                    //Evaluate the score for the current move
                    int score = minimax(board,0,false, Integer.MIN_VALUE, Integer.MAX_VALUE); // Assume it's the opponent's turn

                    //Undo the move
                    board.setCellSymbol(i,j,' ');

                    //Update the best move if the current move has a higher score
                    if(score > bestScore){
                        bestScore = score;
                        bestMove = new Move(i,j);
                    
                    }
                }
            }
        }
        return bestMove;
    }
    public void randomMove(Board board){
        List<Move> availableMoves = new ArrayList<>();
        // find all available (empty) cells
        // Rows
        for(int i = 0; i < 3; i++){
            // Columns
            for(int j = 0; j < 3; j ++){
                if(board.isCellEmpty(i,j)){
                    // if the cell is empty, then it is added to the list of available moves
                    availableMoves.add(new Move(i,j));  
                }
            }
        }
        // choose a random empty cell to place AI token in
        if(!availableMoves.isEmpty()){
            Random random = new Random();
            Move move = availableMoves.get(random.nextInt(availableMoves.size()));
            board.setCellSymbol(move.getRow(), move.getColumn(), this.symbol);
        }

    }

    private int minimax(Board board, int depth, boolean isMaximising, int alpha, int beta){
        //Base cases (if game is already won, or drawn)
        if(board.checkForWin(symbol)){
            return 10 - depth; //AI wins
        }
        else if (board.checkForWin(getOpponentSymbol())){
            return depth -10; // Opponent Wins
        }
        else if (board.isBoardFull()){
            return 0; //It's a draw
        }

        // Recursive Case
        if(isMaximising){
            int maxScore = Integer.MIN_VALUE;
            
            for(int i = 0; i < 3; i++){
                for(int j = 0; j < 3; j++){
                    if(board.isCellEmpty(i,j)){
                        board.setCellSymbol(i,j,symbol);
                        int score = minimax(board, depth + 1, false, alpha, beta);
                        board.setCellSymbol(i,j,' ');
                        
                        maxScore = Math.max(maxScore,score);
                        alpha = Math.max(alpha, score);
                        if(beta <= alpha){
                            return maxScore;
                        }
                    }
                }
            }
            return maxScore;
        }else{
            int minScore = Integer.MAX_VALUE;

            for(int i = 0; i < 3; i++){
                for(int j = 0; j < 3; j++){
                    if(board.isCellEmpty(i,j)){
                        board.setCellSymbol(i,j, getOpponentSymbol());
                        int score = minimax(board,depth + 1, true, alpha, beta);
                        board.setCellSymbol(i,j, ' ');

                        minScore = Math.min(minScore, score);
                        beta = Math.min(beta, score);
                        if(beta <= alpha){
                            return minScore;// pruning
                        }
                    } 
                }
            }
            return minScore;
        }
    }
    private char getOpponentSymbol(){
        return symbol == 'X' ? 'O' : 'X';
    } 
}