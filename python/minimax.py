import utils

board = utils.getBoard()


def evaluate(depth):
    winner = utils.getWinner()
    if utils.checkForWin(board):
        if winner == "X":
            #score = 10 - depth
            return True,(10-depth)
        elif winner == "O":
            #score = depth-10
            return True,(depth-10)
    if utils.checkTie(board) == True:
        #score = 0
        return True,0
    else:
        return False,-11

def minimax(brd,depth,alpha,beta,maximizingPlayer):
    gameStatus,score = evaluate(depth)

    if gameStatus or depth == 0:
        print(score)
        return score

    else:
        if maximizingPlayer:
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
        else:
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

def bestMove(brd):
    bestScore = float('-inf')
    bestLoc = 0

    empScp = utils.empSpaces()
    alpha = float('-inf')
    beta = float('inf')
    if len(empScp) > 0:
        depth = len(empScp)

        for i in empScp:

            brd[i] ="X"
            moveVal = minimax(brd,depth,alpha,beta,False)
            brd[i] = str(i+1)

            if moveVal > bestScore:
                bestScore = moveVal
                bestLoc = i
        return bestLoc