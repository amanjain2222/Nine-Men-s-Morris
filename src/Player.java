import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Player {
    private final String name;
    private final char displayChar;
    private ArrayList<Piece> piecesRemaining;
    private int numOfPiecesOnBoard;

    public Player(String name, char displayChar) {
        this.name = name;
        this.displayChar = displayChar;
        this.numOfPiecesOnBoard = 0;
        this.piecesRemaining = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            this.piecesRemaining.add(new Piece(displayChar, this));
        }
    }

    public String getName() {
        // return the name of the player
        return this.name;
    }

    public char getDisplayChar() {
        // return the display character of the player
        return this.displayChar;
    }

    public ArrayList<Piece> getPiecesRemaining() {
        // return the pieces remaining
        return this.piecesRemaining;
    }

    public int getNumOfPiecesRemaining() {
        //  return the number of pieces remaining
        return this.piecesRemaining.size();
    }

    public int getNumOfPiecesOnBoard() {
        // return the number of pieces on the board
        return this.numOfPiecesOnBoard;
    }

    public void increaseNumOfPiecesOnBoard() {
        // increase the number of pieces on the board
        this.numOfPiecesOnBoard++;
    }

    public void decreaseNumOfPiecesOnBoard() {
        // decrease the number of pieces on the board
        this.numOfPiecesOnBoard--;
    }

    public boolean isAllPiecesOnBoard() {
        // check if all pieces are on the board
        return this.piecesRemaining.size() == 0;
    }

    public Piece popPiecesRemaining() {
        // pop a piece from the pieces remaining
        return this.piecesRemaining.remove(this.piecesRemaining.size() - 1);
    }

    public void addPiecesRemaining(Piece piece) {
        // add a piece to the pieces remaining
        this.piecesRemaining.add(piece);
    }

    public Move getAdjacentMove(Board board, Piece piece) {
        //TODO: write code for getting move from player, this is where the game input is taken

        // create new move objects for each type of move
        Move adjacentMove = new AdjacentMove(this, board, piece);

        // ask user input for start position
        Scanner startPiece = new Scanner(System.in);
        System.out.print("Please enter the position of the piece that you want to move: ");
        String startPieceInput = startPiece.nextLine();
        // ask user inout for end position
        Scanner endPiece = new Scanner(System.in);
        System.out.print("Please enter the position you want to move the piece to: ");
        String endPieceInput = endPiece.nextLine();

        // execute the move
        if (adjacentMove.isValidStartPosition(Integer.parseInt(startPieceInput)) && adjacentMove.isValidEndPosition(Integer.parseInt(endPieceInput))){
            // make the move
            adjacentMove.execute(Integer.parseInt(startPieceInput), Integer.parseInt(endPieceInput));
        } else {
            // fail to make the move and ask user to try again
            System.out.println("Please try again!");
            getAdjacentMove(board, piece);
        }
        return null;
    }

    public int getRemovePosition() {
        // TODO: write code for prompts the player to select a position to remove a piece from the board after
        return 0;
    }

    public void undoRemovePieceFromBoard() {
        //TODO: write code for undoing the removal of a piece from the board
    }
}
