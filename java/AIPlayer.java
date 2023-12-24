public class AIPlayer{
    private char symbol;

    public AIPlayer(char symbol){
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
                    int score = minimax(board,0,false); // Assume it's the opponent's turn

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

    private int minimax(Board board, int depth, boolean isMaximising){
        //Base cases (if game is already won, or drawn)
        if(board.checkForWin(symbol) && symbol == getSymbol()){
            return 1; //AI wins
        }
        else if (board.checkForWin(symbol) && symbol != getSymbol()){
            return -1; // Opponent Wins
        }
        else if (board.isBoardFull()){
            return 0; //It's a draw
        }

        // Recursive Case
        if(isMaximising){
            int maxScore = Integer.MIN_VALUE;
            int bestMove = -1;

            for(int i = 0; i < 3; i++){
                for(int j = 0; j < 3; j++){
                    if(board.isCellEmpty(i,j)){
                        board.setCellSymbol(i,j,symbol);
                        int score = minimax(board, depth + 1, false);
                        board.setCellSymbol(i,j,' ');
                        
                        if(score > maxScore){
                            maxScore = score;
                            bestMove = i * 3 + j; //Convert 2D coordinates to 1D index
                        }
                    }
                }
            }
            return (depth == 0) ? bestMove : maxScore; // Return the best move only at the root level
        }else{
            int minScore = Integer.MAX_VALUE;

            for(int i = 0; i < 3; i++){
                for(int j = 0; j < 3; j++){
                    if(board.isCellEmpty(i,j)){
                        board.setCellSymbol(i,j, getSymbol());
                        int score = minimax(board,depth + 1, true);
                        board.setCellSymbol(i,j, ' ');
                        minScore = Math.min(minScore, score);
                    } 
                }
            }
            return minScore;
        }
    }
}