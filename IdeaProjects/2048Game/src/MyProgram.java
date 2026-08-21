import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

public class MyProgram {
    private int[][] board;

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void main(String[] args) {
        boolean win = false;
        int[][] board = new int[4][4];
        MyProgram game = new MyProgram(board);
        game.start();

        while (!win) {
            clearScreen();
            System.out.println(game);
            game.move();
            game.spawn();

            if (game.isGameOver()) {
                clearScreen();
                System.out.println(game);
                System.out.println("Game Over! You cant move anymore");
                break;
            }
        }
    }


    public MyProgram(int[][] boardx) {
        board = boardx;
    }

    public void start() {
        int count = 0;
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (count < 2 && Math.random() > 0.875) {
                    count++;
                    if (Math.random() > 0.5) {
                        board[row][col] = 2;
                    } else {
                        board[row][col] = 4;
                    }
                }
            }
        }
        if (count == 1) {
            if (board[0][0] == 0) {
                board[0][0] = 2;
            } else {
                board[0][1] = 2;
            }
        } else if (count == 0) {
            board[0][0] = 2;
            board[0][1] = 2;
        }
    }

    public void spawn() {
        ArrayList<Point> points = new ArrayList<>();
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col] == 0) {
                    points.add(new Point(row, col));
                }
            }
        }
        if (!points.isEmpty()) {
            int idx = (int) (Math.random() * points.size());
            if (Math.random() > 0.5) {
                board[points.get(idx).getRow()][points.get(idx).getCol()] = 2;
            } else {
                board[points.get(idx).getRow()][points.get(idx).getCol()] = 4;
            }
        }
    }

    public void move() {
        Scanner input = new Scanner(System.in);
        String key = input.nextLine();
        switch (key) {
            case "w":
                for (int col = 0; col < board[0].length; col++) {
                    for (int row = 1; row < board.length; row++) {
                        if (board[row][col] != 0) {
                            int r = row;
                            while (r > 0 && board[r - 1][col] == 0) {
                                board[r - 1][col] = board[r][col];
                                board[r][col] = 0;
                                r--;
                            }
                            if (r > 0 && board[r - 1][col] == board[r][col]) {
                                board[r - 1][col] *= 2;
                                board[r][col] = 0;
                            }
                        }
                    }
                }
                break;
            case "a":
                for (int row = 0; row < board.length; row++) {
                    for (int col = 1; col < board[row].length; col++) {
                        if (board[row][col] != 0) {
                            int c = col;
                            while (c > 0 && board[row][c - 1] == 0) {
                                board[row][c - 1] = board[row][c];
                                board[row][c] = 0;
                                c--;
                            }
                            if (c > 0 && board[row][c - 1] == board[row][c]) {
                                board[row][c - 1] *= 2;
                                board[row][c] = 0;
                            }
                        }
                    }
                }
                break;
            case "s":
                for (int col = 0; col < board[0].length; col++) {
                    for (int row = board.length - 2; row >= 0; row--) {
                        if (board[row][col] != 0) {
                            int r = row;
                            while (r < board.length - 1 && board[r + 1][col] == 0) {
                                board[r + 1][col] = board[r][col];
                                board[r][col] = 0;
                                r++;
                            }
                            if (r < board.length - 1 && board[r + 1][col] == board[r][col]) {
                                board[r + 1][col] *= 2;
                                board[r][col] = 0;
                            }
                        }
                    }
                }
                break;
            case "d":
                for (int row = 0; row < board.length; row++) {
                    for (int col = board[row].length - 2; col >= 0; col--) {
                        if (board[row][col] != 0) {
                            int c = col;
                            while (c < board[row].length - 1 && board[row][c + 1] == 0) {
                                board[row][c + 1] = board[row][c];
                                board[row][c] = 0;
                                c++;
                            }
                            if (c < board[row].length - 1 && board[row][c + 1] == board[row][c]) {
                                board[row][c + 1] *= 2;
                                board[row][c] = 0;
                            }
                        }
                    }
                }
                break;
            default:
                System.out.println("Please enter a valid character");
        }


    }

    public String toString() {
        String boardStr = "";
        for (int row = 0; row < board.length; row++) {
            if (row > 0) {
                boardStr += "\n";
            }
            for (int col = 0; col < board[row].length; col++) {
                boardStr += "[" + board[row][col] + "]";
            }
        }
        return boardStr;
    }
    public boolean isGameOver() {
        // Check for any empty tile
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col] == 0) {
                    return false; // There's still room to spawn
                }
            }
        }

        // Check for any combinable adjacent horizontal or vertical tiles
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                int current = board[row][col];

                // Check right
                if (col < board[row].length - 1 && current == board[row][col + 1]) {
                    return false;
                }
                // Check down
                if (row < board.length - 1 && current == board[row + 1][col]) {
                    return false;
                }
            }
        }

        // No moves left
        return true;
    }

}