package main.TicTacToe;

/*
STEP1: DISCUSS REQUIREMENTS
1. It is a 2 player game on a 3*3 board [9 cells]
2. Usually 1st player is given 'X' as marker and 2nd player is given 'O' respectively: How to decide?: can be several ways like
    a. Toss a coin
    b. Rock paper scissor
    c. Pick a number, etc.;
3. The first player who has 3 of their symbols either in a row, column or diagonal will be the winner.
4. If no player could match the line 3 criteria then it is a draw.
5. A player should only mark the empty cells with their symbol respectively

 */

/*==========================================================================*/

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
STEP2: IDENTIFY ENTITIES
1. Player
2. Board
3. Symbol
4. Status
4. TicTacToeGame
5. TicTacToeGameController

 */
/*==========================================================================*/
/*
STEP3: CLASS DESIGN[ UML IF REQUIRED ]
player{
    -String playerName;
    -Symbol playerSymbol;

    +getPlayerNamer();
    +setPlayerNamer();

}
Board{
    -int size;
    -Symbol[][] board;
    Board{
        size = 3;
        board = new char[3][3];
    }

    +setSymbolOnboard(int position, Symbol symbol){

    }



}

enum Symbol{
    'X', 'O'
}

Game{
    Queue<Player> playerQueue;
    Board board;

    +isGameOver(int position, Symbol symbol){

    }

    +printBoard(){

    }

}

TicTacToeController{
    Game game;


    +createPlayers();
    +playGame();



}

* */
enum Symbol {
    X('X'),
    O('O'),
    EMPTY('_');

    private final char symbol;

    Symbol(char x) {
        this.symbol = x;
    }

    public char getChar() {
        return symbol;
    }
}

class Player {
    private final String playerName;
    private final Symbol playerSymbol;

    Player(String playerName, Symbol symbol) {
        this.playerName = playerName;
        playerSymbol = symbol;
    }

    public String getPlayerName() {
        return playerName;
    }


    public Symbol getPlayerSymbol() {
        return playerSymbol;
    }

}

class Board {
    int size;
    Symbol[][] board;
    int numberOfMoves;

    Board() {
        size = 3;
        board = new Symbol[size][size];
        Arrays.stream(board).forEach(row -> Arrays.fill(row, Symbol.EMPTY));
    }

    public void setSymbolAtPosition(int row, int column, Symbol symbol) {
        board[row - 1][column - 1] = symbol;
        numberOfMoves++;
    }

    public boolean isPositionValid(int row, int column){
        if (row < 1 || row > size || column < 1 || column > size) {
            System.out.println("The row/column is out of the board grids");
            return false;
        }
        if (!Symbol.EMPTY.equals(board[row - 1][column - 1])) {
            System.out.println("The row: " + row + "column: " + column + " is already occupied with: " + board[row - 1][column - 1]);
            return false;
        }
        return true;
    }

    public void printBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(board[i][j].getChar() + " ");
            }
            System.out.println();
        }
    }

    public boolean isBoardFull() {
        return numberOfMoves >= size * size;
    }

    public Symbol getCell(int row, int col) {
        return board[row - 1][col - 1];
    }

    public int getSize() {
        return size;
    }
}

class Game {
    private Queue<Player> playerQueue; // use two individualPLayers as well[preferred here]
    private final Board board;

    Game(Player p1, Player p2) {
        playerQueue = new LinkedList<>();
        playerQueue.offer(p1);
        playerQueue.offer(p2);
        board = new Board();

    }

    public void play() {
        Scanner sc = new Scanner(System.in);
        boolean isWinnerFound = false;
        while (!isGameOver()) {
            Player player = playerQueue.poll();
            System.out.println(player.getPlayerName() + ", Please choose a position to mark: " + player.getPlayerSymbol() + "in the empty cells");
            board.printBoard();
            int row = sc.nextInt();
            int col = sc.nextInt();
            if(!board.isPositionValid(row, col)){
                System.out.println("The row and col positions are invalid. Please provide valid input");
                playerQueue.offer(player);
                continue;
            }
            board.setSymbolAtPosition(row, col, player.getPlayerSymbol());
            if (isRowComplete(player.getPlayerSymbol(), row) || isColumnComplete(player.getPlayerSymbol(), col) || isDiagonalComplete(player.getPlayerSymbol(), row, col)){
                System.out.println("Player: " + player.getPlayerName() + " is the winner");
                isWinnerFound = true;
                board.printBoard();
                break;
            }
            playerQueue.offer(player);
        }
        if(!isWinnerFound && isGameOver()){
            System.out.println("The game is a draw. Thanks for playing!!");
            board.printBoard();
        }
    }

    public boolean isRowComplete(Symbol symbol, int row) {
        for (int i = 1; i <= board.getSize(); i++) {
            if (board.getCell(row, i) != symbol) {
                return false;
            }
        }
        return true;
    }

    public boolean isColumnComplete(Symbol symbol, int col) {
        for (int i = 1; i <= board.getSize(); i++) {
            if (board.getCell(i, col) != symbol) {
                return false;
            }
        }
        return true;
    }

    public boolean isDiagonalComplete(Symbol symbol, int row, int col) {
        int size = board.getSize();

        // Main diagonal check
        if (row == col) {
            boolean ok = true;
            for (int i = 1; i <= size; i++) {
                if (board.getCell(i, i) != symbol) {
                    ok = false;
                    break;
                }
            }
            if (ok) return true;
        }

        // Anti-diagonal check
        if (row + col == size + 1) {
            for (int i = 1; i <= size; i++) {
                if (board.getCell(i, size - i + 1) != symbol) {
                    return false;
                }
            }
            return true;
        }

        return false;
    }

    private boolean isGameOver() {
        return board.isBoardFull();
    }

}
public class TicToeController1{
    private final Game game;

    TicToeController(Game game) {
        this.game = game;
    }

    // "Driver" method to start the game
    public void start() {
        game.play();
    }

    public static void main(String[] args) {
        Player p1 = new Player("Player-1", Symbol.X);
        Player p2 = new Player("Player-2", Symbol.O);

        Board board = new Board();

        Game game = new Game(p1, p2);
        TicToeController controller = new TicToeController(game);

        controller.start();
    }

}

