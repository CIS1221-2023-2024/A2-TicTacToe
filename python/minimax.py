import utils

board = utils.getBoard()


def evaluate(depth): #this function is used to evaluate the move done by minimax
    if utils.checkForWin(board):
        winner = utils.getWinner()
        if winner == "X": # if ai wins
            return True,(10-depth)
        elif winner == "O": # if user wins
            return True,(depth-10)
    if utils.checkTie(board) == True:
        return True,0
    else:
        return False, None # if game has not ended, so no score can be given

def minimax(brd,depth,alpha,beta,maximizingPlayer):
    gameEnded,score = evaluate(depth) #checking if game has ended and getting the score

    if gameEnded or depth == 0: # stopping condition for recursion
        return score

    else:
        if maximizingPlayer: # if its the ai's turn to play when checking all possibilities
            maxEval = float('-inf')
            empSpcs = utils.empSpaces()
            if len(empSpcs) > 0:
                for i in empSpcs:
                    brd[i] = "X"
                    eval = minimax(brd,depth -1,alpha,beta,False)
                    brd[i] = str(i+1)
                    maxEval = max(maxEval,eval)
                    alpha = max(alpha,eval)
                    if beta <= alpha:
                        break
                return maxEval
        else: # if it's the user's turn when checking all possibilities
            minEval = float('inf')
            empSpcs = utils.empSpaces()
            if len(empSpcs) >0:
                for i in empSpcs:
                    brd[i] = "O"
                    eval = minimax(brd,depth-1,alpha,beta,True)
                    brd[i] = str(i+1)
                    minEval = min(minEval,eval)
                    beta = min(beta,eval)
                    if beta <= alpha:
                        break
                return minEval

def bestMove(brd): # determing the best path to be taken by ai
    bestScore = float('-inf')
    bestLoc = 0

    empScp = utils.empSpaces() # getting a list of all the empty spaces on the board
    alpha = float('-inf')
    beta = float('inf')
    if len(empScp) > 0:
        if len(empScp) == 9: # if first move
            import random
            return random.randint(0,8) #returns a random value for the token to be placed
        else:
            depth = len(empScp)

            for i in empScp:

                brd[i] ="X"
                moveVal = minimax(brd,depth,alpha,beta,False)
                brd[i] = str(i+1)

                if moveVal > bestScore:
                    bestScore = moveVal
                    bestLoc = i
            return bestLoc # returning location which will be used for player input