import minimax
import utils

board = utils.getBoard()
winner = utils.getWinner()


currentPlayer = "X"
gameRunning = True

player1 = ""
player2 = ""
gamemode = 0



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
            if inpVal == False:
                print("AI's move:" )
        elif gamemode == 2.2 and currentPlayer == "X":
            inp = minimax.bestMove(brd)+1
            inpStr = str(inp)
            inpVal = placeToken(brd,inpStr,inp)
            if inpVal == False:
                print("AI's move:" )

def setWinnerName(winner):
    if winner == "X":
        return player1
    elif winner == "O":
        return player2

def outWinner(brd):
    global gameRunning
    if utils.checkForWin(brd):
        gameRunning = False
        winner = utils.getWinner()
        if winner == "X":
            print(f"\nThe Winner is {setWinnerName(winner)}")
            utils.printBoard()
        elif winner == "O":
            print(f"\nThe Winner is {setWinnerName(winner)}")
            utils.printBoard()
    elif utils.checkTie(brd):
        gameRunning = False
        print("It's a tie!")
        utils.printBoard()



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
    print()
    utils.printBoard()

    while gameRunning == True:
        board = utils.getBoard()
        playerInp(board)
        utils.printBoard()
        outWinner(board)
        switchPlayer()

gamePlay()
