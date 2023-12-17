board = ["1","2","3",
         "4","5","6",
         "7","8","9"]

currentPlayer = "X"
gameRunning = True
winner = None

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
#switch player
#check for win or tie again

def gamePlay():
    while gameRunning == True:
        printBoard(board)
        playerInp(board)
        
gamePlay