import java.util.Scanner;

public class Player {
    private final String name;
    private final char displayChar;
    private int piecesRemaining;
    private int piecesOnBoard;

    public Player(String name, char displayChar, int piecesRemaining) {
        this.name = name;
        this.displayChar = displayChar;
        this.piecesRemaining = piecesRemaining;
        this.piecesOnBoard = 0;
    }

    public String getName() {
        // return the name of the player
        return name;
    }

    public char getDisplayChar() {
        // return the display character of the player
        return displayChar;
    }

    public int getPiecesRemaining() {
        //  return the number of pieces remaining
        return piecesRemaining;
    }

    public int getPiecesOnBoard() {
        // return the number of pieces on the board
        return piecesOnBoard;
    }

    public void increasePiecesOnBoard() {
        // increase the number of pieces on the board
        piecesOnBoard++;
    }

    public void decreasePiecesOnBoard() {
        // decrease the number of pieces on the board
        piecesOnBoard--;
    }

    public void decreasePiecesRemaining() {
        // decrease the number of pieces remaining
        piecesRemaining--;
    }

    public boolean isAllPiecesOnBoard() {
        // check if all pieces are on the board
        return piecesRemaining == 0;
    }

    public Move getMove() {
        //ToDo: write code for getting move from player, this is where the game input is taken
        return null;
    }

    public int getRemovePosition() {
        // ToDo: write code for prompts the player to select a position to remove a piece from the board
        return 0;
    }
}
