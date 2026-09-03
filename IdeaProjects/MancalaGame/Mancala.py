board = [[4, 4, 4, 4, 4, 4, 0], [4, 4, 4, 4, 4, 4, 0]]


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

    return currentRow, currentIndex


printBoard()

