board = ["1","2","3",
         "4","5","6",
         "7","8","9"]

currentPlayer = "X"
gameRunning = True
winner = None
player1 = ""
player2 = ""
gamemode = 0


#print board
def printBoard(brd):
    print(brd[0] + " | " + brd[1] + " | " + brd[2])
    print("-" * 10)
    print(brd[3] + " | " + brd[4] + " | " + brd[5])
    print("-" * 10)
    print(brd[6] + " | " + brd[7] + " | " + brd[8])
    print()
    
#menu to choose game mode
def menu():
    global gamemode
    print("Welcome to Tic Tac Toe!")
    while True:
        userinp = input("Please select game mode: \n1. User vs User \n2. User vs AI \n")
        if userinp.isdigit():
            userinp = int(userinp)
            if userinp == 1:
                gamemode = 1
                break
            elif userinp == 2:
                while True:
                    plymode = input("Please select game difficulty: \n1.easy \n2.Hard \n")
                    if plymode.isdigit():
                        plymode = int(plymode)
                        if plymode ==1 or plymode == 2:
                            gamemode = 2 + plymode/10
                            break
                        else:
                            print("Invalid input! Must be an integer between 1-2")
                    else:
                        print("Invalid input! Must be an integer.")
                break
            else:
                print("Invalid input! Must be an integer between 1-2")
        else:
            print("Invalid input! Must be an integer.")
                
#input player name   
def inpPlayerName(gm):
    global player1
    global player2
    if gm == 1:
        player1 = input("Please enter name for player 1 (X):")
        player2 = input("Please enter name for player 2 (O):")
    elif int(gm) == 2:
        player1 = "AI"
        print("Player 1 (X) is AI")
        player2 = input("Please enter name for player 2 (O):")
    
def placeToken(brd,inp,inpInt):
    val = True
    if (inpInt >= 1 and inpInt<=9):
        if brd[inpInt - 1] == inp:
            brd[(inpInt - 1)] = currentPlayer
            val = False
        elif gamemode == 1 or currentPlayer == "0": 
            #this condition is added in order to not have the message printed everytime the ai generates a random number already taken
                print("Location already taken!")
    else:
        print("Integer entered is not a location on the board!")
    return val
    
#player input
def playerInp(brd):
    inpVal = True
    while inpVal:
        if gamemode == 1 or (int(gamemode) == 2 and  currentPlayer == "O"):
            inp = input(f"Enter the location to place your {currentPlayer}:")
            if(inp.isdigit()):
                inpInt = int(inp)
                inpVal = placeToken(brd, inp, inpInt)
            else:
                print("Opps the input entered in not an integer!")
        
        elif gamemode == 2.1 and currentPlayer == "X":
            import random
            inp = random.randint(1,9)
            inpStr = str(inp)
            inpVal = placeToken(brd, inpStr,inp)
                  
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

def outWinName(winner):
    if winner == "X":
        return player1
    elif winner == "O":
        return player2

def checkForWin(brd):
    global gameRunning
    if checkDiagonal(brd) or checkHorizontal(brd) or checkVertical(brd):
        print(f"\nThe winner is {outWinName(winner)}!")
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
    menu()
    inpPlayerName(gamemode)
    
    while gameRunning == True:
        printBoard(board)
        playerInp(board)
        checkForWin(board)
        checkTie(board)
        switchPlayer()
        
        
gamePlay()
