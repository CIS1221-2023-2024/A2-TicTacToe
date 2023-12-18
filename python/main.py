board = ["1","2","3",
         "4","5","6",
         "7","8","9"]

currentPlayer = "X"
gameRunning = True
winner = None

#print board
def printBoard(brd):
    print(brd[0] + " | " + brd[1] + " | " + brd[2])
    print("-" * 10)
    print(brd[3] + " | " + brd[4] + " | " + brd[5])
    print("-" * 10)
    print(brd[6] + " | " + brd[7] + " | " + brd[8])

#player input
def playerInp(brd):
    inpVal = True
    while inpVal:
        inp = input("Enter the location to place marker in ")
        if(inp.isdigit()):
            inpInt = int(inp)
            if (inpInt >= 1 and inpInt<=9):
                if brd[inpInt - 1] == inp:
                    brd[(inpInt - 1)] = currentPlayer
                    inpVal = False
                else:
                    print("Location already taken!")
            else:
                print("Integer entered is not a location on the board!")
        else:
            print("Opps the input entered in not an integer!")
            
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
    global gameRunning
    cnt = 0
    for i in range(9):
        if brd[i] != str(i+1):
            cnt += 1
    if cnt == 9:
        print("It's a tie!")
        printBoard(brd)
        gameRunning = False

def checkForWin(brd):
    global gameRunning
    if checkDiagonal(brd) or checkHorizontal(brd) or checkVertical(brd):
        print(f"The winner is {winner}!")
        printBoard(brd)
        gameRunning = False
        
#switch player
def switchPlayer():
    global currentPlayer
    if currentPlayer == "X":
        currentPlayer = "O"
    else:
        currentPlayer = "X"
        

def gamePlay():
    while gameRunning == True:
        printBoard(board)
        playerInp(board)
        checkForWin(board)
        checkTie(board)
        switchPlayer()
        
        
gamePlay()