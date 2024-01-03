public class AIPlayer extends Player{
    private char symbol;

    public AIPlayer(char symbol){
        super("AI", symbol); 
        this.symbol = symbol;
    }
    public char getSymbol(){
        return symbol;
    }

    public Move getBestMove(Board board){

        int bestScore = Integer.MIN_VALUE;
        Move bestMove = null;

        //Iteration through all the available moves
        for(int i = 0; i < 3; i++){
            for(int j = 0; j <3; j++){
                if(board.isCellEmpty(i,j)){
                    //Make the move
                    board.setCellSymbol(i,j,symbol);

                    //Eveluate the score for the current move
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
                            break;
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
                            break;
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