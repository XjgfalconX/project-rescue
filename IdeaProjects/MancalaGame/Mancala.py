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



   # functionality for adding the stones
   # WE COULD DO IT LIKE THIS OR MAKE IT RECURSIVE WHICH WOULD BE COOL(and maybe better)




def addToPocketsForEachStone(pocketRow, pocketIndex, returnVariation):
   boardSize = 7
   initialRow = pocketRow


   # Pick up the stones from the selected pit
   stones = board[pocketRow][pocketIndex]
   board[pocketRow][pocketIndex] = 0


   # Track our current position as we move around the board
   currentRow = pocketRow
   currentIndex = pocketIndex


   while stones > 0:
       currentIndex += 1


       if currentIndex >= boardSize:
           currentRow = 1 - currentRow  # Absolute difference: 1 becomes 0 0 becomes 1
           currentIndex = 0


       # Check if the current index is a store and if it's the opponent's row
       ifIndexIsStore = (currentIndex == 6)
       isOpponentRow = (currentRow != initialRow)


       # If it's the opponent's store skip it
       if ifIndexIsStore and isOpponentRow:
           continue


       # Drop a stone in the current pit/store
       board[currentRow][currentIndex] += 1
       stones -= 1


   return (currentRow, currentIndex) if returnVariation else currentIndex

def stealFromOther(stoneRow, stoneIndex):
    print("DYNHFDJYHGFYKUFHKUF")
    tempStoneRow = 1-stoneRow
    temp = board[tempStoneRow][stoneIndex]
    board[stoneRow][stoneIndex] = 0
    board[stoneRow][6] += temp

def winCheck(player):
    boardIsEmpty = True

    for i in range(len(board[player])):
        if board[player][i] != 0:
            boardIsEmpty = False
    return boardIsEmpty;


def turnPlayer(player):
    playerInput = int(input("What hole number would you like to move? "))

    match player:
        case 0:
            if playerInput > 6 or playerInput < 1:
                print("Please enter a number between 1 and 6")
                return turnPlayer(player)

            stonesAtIndex = board[player][playerInput-1]
            landing = stonesAtIndex + playerInput - 2
            landingValue = board[player][landing]
            moveIndex = addToPocketsForEachStone(player, int(playerInput-1), False)

            if moveIndex == 6:
                print("Bonus round!")
                printBoard(board)
                return turnPlayer(player)
            elif landingValue == 0:
                stealFromOther(player, moveIndex)

            else:
                return 1 - player
        case 1:
            if playerInput > 13 or playerInput < 8:
                print("Please enter a number between 8 and 13")
                return turnPlayer(player)

            stonesAtIndex = board[player][playerInput-8]
            landing = stonesAtIndex + playerInput - 9
            landingValue = board[player][landing]
            moveIndex = addToPocketsForEachStone(player, int(playerInput - 8), False)

            if moveIndex == 6:
                print("Bonus round!")
                printBoard(board)
                return turnPlayer(player)
            elif landingValue == 0:
                stealFromOther(player, moveIndex)

            else:
                return 1 - player

def startGame():
    player = 1
    printBoard(board)
    winCondition = False
    winChecker = False
    while not winChecker:
        winChecker = winCheck(player)
        player = turnPlayer(player)
        printBoard(board)
    print(f"Congrats! Player {player} Won! It's over!")

startGame()