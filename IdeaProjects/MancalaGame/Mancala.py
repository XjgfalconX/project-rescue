board = [[4, 4, 4, 4, 4, 4, 0], [4, 4, 4, 4, 4, 4, 0]]


def printBoard():
    print(" 13   12   11   10    9    8")
    print(" ----------------------------")
    for i in range(6, 0, -1):
        print(f" {board[0][i]:2} ", end=" ")
    print()
    print(board[1][7], "                          ", board[0][7])
    print(f" {board[1][i]:2} ", end=" ")
    print()
    print(" ----------------------------")
    print("  1    2    3    4    5    6")

    # functionality for adding the stones
    # WE COULD DO IT LIKE THIS OR MAKE IT RECURSIVE WHICH WOULD BE COOL(and maybe better)


def addToPocketsForEachStone(pocketRow, pocketIndex):
    boardSize = 7  # important for checking if it changes y level

    # if the index + the amount of stones is larger than the board size, change the y level
    if (pocketIndex + board[pocketRow][pocketIndex] > boardSize):
        remainder = pocketIndex + board[pocketRow][pocketIndex] % boardSize

        # do this after adding all the stones to the initial row
        pocketRow = 1 - pocketRow  # absolute difference formula, 1 - x(1) = 0,    1 - x(0) = 1
    # starting index0  x where the pocket is
    for i in range(0, board[pocketRow][pocketIndex]):


printBoard()

