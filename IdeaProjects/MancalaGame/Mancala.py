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


   return currentIndex if returnVariation == False else currentRow, currentIndex

def stealFromOther(stoneRow, stoneIndex):
    tempStoneRow = 1-stoneRow
    temp = board[tempStoneRow][stoneIndex]
    board[stoneRow][stoneIndex] = 0
    board[stoneRow, 6] += temp

def winQuestionMark():
    rowTotal = 0
    for i in board:
        for j in i:
            rowTotal += j
        if rowTotal == 0:
            return True
    return False


def turnPlayer(player, bonus):
    playerInput = int(input("What hole number would you like to move? "))

    if not bonus:
        player = 1 - player
    match player:
        case 0:
            if playerInput > 6 or playerInput < 1:
                print("Please enter a number between 1 and 6")
                turnPlayer(player, True)
            elif addToPocketsForEachStone(player, int(playerInput-1), False) == 7:
                print("Bonus round!")
                turnPlayer(player, True)
            else:
                return
        case 1:
            if playerInput > 13 or playerInput < 8:
                print("Please enter a number between 8 and 13")
                turnPlayer(player, True)
            elif addToPocketsForEachStone(player, int(playerInput-8), False) == 7:
                print("Bonus round!")
                turnPlayer(player, True)
            else:
                return

def startGame():
    player = 1
    printBoard(board)
    while winQuestionMark() != True:
        turnPlayer(player, False)
        printBoard(board)
    print(f"Congrats! Player {player} Won! It’s over!")

startGame()