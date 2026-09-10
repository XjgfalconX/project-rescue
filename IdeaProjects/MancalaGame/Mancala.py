board = [[4, 4, 4, 4, 4, 4, 0], [4, 4, 4, 4, 4, 4, 0]]
player = 0


def printBoard():
   print(" 13   12   11   10    9    8")
   print(" ----------------------------")
   for i in range(6, 0, -1):
       print(f" {board[0][i]:2} ", end=" ")
   print();
   print(board[1][7], "                          ", board[0][7]
   print(f" {board[1][i]:2} ", end=" ")
   print();
   print(" ----------------------------")
   print("  1    2    3    4    5    6")


   # functionality for adding the stones
   # WE COULD DO IT LIKE THIS OR MAKE IT RECURSIVE WHICH WOULD BE COOL(and maybe better)




def addToPocketsForEachStone(pocketRow, pocketIndex):
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


   return currentIndex


def winQuestionMark():
   rowTotal = 0
   for i in board:
       for j in i:
           rowTotal += j
	 if rowTotal == 0:
	     return True
   Return False


def turnPlayer(player):
   playerInput = input("What hole number would you like to move? ")
   match player:
       case 0:
            if playerInput > 6 or playerInput < 1:
               print("Please enter a number between 1 and 6")
               turnPlayer(player)
	        elif addtoPocketsForEachStone(player, playerInput) == 7:
		        print("Bonus round!")
                turnPlayer(player)
       case 1:
           if playerInput > 13 or playerInput < 8:
               print("Please enter a number between 8 and 13")
           elif addtoPocketsForEachStone(player, int(playerInput)) == 14:
                print("Bonus round!")
                turnPlayer(player)
   printBoard()
   return 1 - player


def startGame():
   printBoard()
   while winQuestionMark() != True:
       player = turnPlayer(player)
   print("Congrats! It’s over!")
