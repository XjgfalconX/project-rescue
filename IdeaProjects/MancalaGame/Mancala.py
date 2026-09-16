board = [[4, 4, 4, 4, 4, 4, 0], [4, 4, 4, 4, 4, 4, 0]]
# All bugs stem from win check // Sorry :P
# Fix winCheck to exclude the store, this breaks the game
# Add a sweep-remaining-stones-to-store step when the game ends.
# Compare board[0][6] vs board[1][6] to determine the actual winner (including ties).

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
    tempRow = 1 - stoneRow                  #save the row
    temp = board[tempRow][5 - stoneIndex]   #the stones in the opponents pit, it's 5 - SI because the rows go in opposite directions
    board[tempRow][5 - stoneIndex] = 0      #set opponents index to 0
    board[stoneRow][stoneIndex] = 0         #set your own index to 0
    board[stoneRow][6] += temp + 1
def winCheck(player):
    boardIsEmpty = True

    for i in range(len(board[player])):
        if board[player][i] != 0:
            boardIsEmpty = False
    return true

def turnPlayer(player):
    playerInput = int(input("What hole number would you like to move? "))

    match player:
        case 0:
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
            if playerInput > 13 or playerInput < 8:
                print("Please enter a number between 8 and 13")
                return turnPlayer(player)

            moveRow, moveIndex = addToPocketsForEachStone(player, int(playerInput - 8), True)

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

def startGame():
    player = 1
    printBoard(board)
    winCondition = False
    winChecker = False
    while True:
        winChecker = winCheck(player)
        if(winChecker):
            break
        player = turnPlayer(player)
        printBoard(board)
    print(f"Congrats! Player {player} Won! It's over!")

startGame()