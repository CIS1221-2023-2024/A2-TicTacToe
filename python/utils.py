#global variables for board and winner
board = ["1","2","3",
         "4","5","6",
         "7","8","9"]
winner = None

#get functions for board and winner so they can be accessed form other scripts
def getBoard():
    return board

def getWinner():
    return winner

#function to check the number of empty spaces in the board and storing their index in a list
def empSpaces():
    lst = []
    for i in range(9):
        if board[i] == str(i+1):
            lst.append(i)
    return lst

#print board
def printBoard():
    print(board[0] + " | " + board[1] + " | " + board[2])
    print("-" * 10)
    print(board[3] + " | " + board[4] + " | " + board[5])
    print("-" * 10)
    print(board[6] + " | " + board[7] + " | " + board[8])
    print()

#check for win or tie
def checkHorizontal(brd):
    global winner
    if brd[0] == brd[1] == brd[2] and (brd[0] == "X" or brd[0] == "O"):
        winner = brd[0]
        return True
    elif brd[3] == brd[4] == brd[5]and (brd[3] == "X" or brd[3] == "O"):
       winner = brd[3]
       return True
    elif brd[6] == brd[7] == brd[8] and (brd[6] == "X" or brd[6] == "O"):
        winner = brd[6]
        return True
    else:
        return False

def checkVertical(brd):
    global winner
    if brd[0] == brd[3] == brd[6]and (brd[0] == "X" or brd[0] == "O"):
        winner = brd[0]
        return True
    elif brd[1] == brd[4] == brd[7]and (brd[1] == "X" or brd[1] == "O"):
       winner = brd[1]
       return True
    elif brd[2] == brd[5] == brd[8]and (brd[2] == "X" or brd[2] == "O"):
        winner = brd[2]
        return True
    else:
        return False

def checkDiagonal(brd):
    global winner
    if brd[0] == brd[4] == brd[8] and (brd[0] == "X" or brd[0] == "O"):
        winner = brd[0]
        return True
    elif brd[2] == brd[4] == brd[6]and (brd[2] == "X" or brd[2] == "O"):
        winner = brd[2]
        return True
    else:
        return False

def checkTie(brd):
    cnt = 0
    for i in range(9):
        if brd[i] != str(i+1):
            cnt += 1
    if cnt == 9:
        return True
    else:
        return False


def checkForWin(brd):
    if checkDiagonal(brd) or checkHorizontal(brd) or checkVertical(brd):
        return True
    else:
        return False

