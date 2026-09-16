board = [[4, 4, 4, 4, 4, 4, 0], [4, 4, 4, 4, 4, 4, 0]]
player = 1
bonus = False


def printBoard(board):
    print(" 13  12  11  10   9   8")
    print(" --------------------------")
    for i in range(5, -1, -1):
        print(f" {board[1][i]:2} ", end="")
    print()
    print(f"{board[1][6]:2}                     {board[0][6]:2}")
    for i in range(0, 6):
        print(f" {board[0][i]:2} ", end="")
    print()
    print(" --------------------------")
    print("  1   2   3   4   5   6")

def addToPocketsForEachStone(pocketRow, pocketIndex, returnVariation):
   boardSize = 7
   initialRow = pocketRow

   stones = board[pocketRow][pocketIndex]
   board[pocketRow][pocketIndex] = 0

   currentRow = pocketRow
   currentIndex = pocketIndex


   while stones > 0:
       currentIndex += 1


       if currentIndex >= boardSize:
           currentRow = 1 - currentRow  # Absolute difference: 1 becomes 0 0 becomes 1
           currentIndex = 0

       ifIndexIsStore = (currentIndex == 6)
       isOpponentRow = (currentRow != initialRow)

       if ifIndexIsStore and isOpponentRow:
           continue

       board[currentRow][currentIndex] += 1
       stones -= 1

   return (currentRow, currentIndex) if returnVariation else currentIndex

def stealFromOther(stoneRow, stoneIndex):

    tempRow = 1 - stoneRow                  #save the row
    if(board[tempRow][5 - stoneIndex] == 0):
        return
    temp = board[tempRow][5 - stoneIndex]   #the stones in the opponents pit, it's 5 - SI because the rows go in opposite directions
    board[tempRow][5 - stoneIndex] = 0      #set opponents index to 0
    board[stoneRow][stoneIndex] = 0         #set your own index to 0
    board[stoneRow][6] += temp + 1


def winCheck(playerOrWin):
    oneIsEmpty = all(pit == 0 for pit in board[0][:7])
    twoIsEmpty = all(pit == 0 for pit in board[1][:7])

    gameOver = oneIsEmpty or twoIsEmpty

    if not gameOver:
        return False

    # Return game status if True, otherwise return the winner
    if playerOrWin:
        return True
    if(oneIsEmpty):
        boardSweep(0)
    elif (twoIsEmpty):
        boardSweep(1)

    if board[0][6] > board[1][6]:
        return "Player 1"
    elif board[1][6] > board[0][6]:
        return "Player 2"
    else:
        return "Tie"

def boardSweep(player):
    for i in range(len(board[player])-1):
        temp = board[player][i]
        board[player][i] = 0
        board[player][6] += temp

def turnPlayer(player):
    playerInput = int(input("What hole number would you like to move? "))

    match player:
        case 0:
            if (board[player][playerInput] == 0):
                return turnPlayer(player)
            if playerInput > 6 or playerInput < 1:
                print("Please enter a number between 1 and 6")
                return turnPlayer(player)

            moveRow, moveIndex = addToPocketsForEachStone(player, int(playerInput - 1), True)

            if moveIndex == 6:
                print("Bonus round!")
                printBoard(board)
                return turnPlayer(player)

            landingValue = board[moveRow][moveIndex]  # will be 1 if the pit was empty before
            if landingValue == 1 and moveRow == player:
                stealFromOther(moveRow, moveIndex)
                return 1 - player

            else:
                return 1 - player
        case 1:
            if (board[player][playerInput-8] == 0):
                return turnPlayer(player)
            if playerInput > 13 or playerInput < 8:
                print("Please enter a number between 8 and 13")
                return turnPlayer(player)

            moveRow, moveIndex = addToPocketsForEachStone(player, int(playerInput - 8), True)

            if moveIndex == 6:
                print("Bonus round!")
                printBoard(board)
                return turnPlayer(player)

            landingValue = board[moveRow][moveIndex]
            if landingValue == 1 and moveRow == player:
                stealFromOther(moveRow, moveIndex)
                return 1 - player

            else:
                return 1 - player

def startGame():
    player = 1
    printBoard(board)
    while True:
        winChecker = winCheck(True)
        if winChecker:
            printBoard(board)
            break
        player = turnPlayer(player)
        printBoard(board)


    print(f"Congrats! Player {winCheck(False)} Won! It's over!")

startGame()