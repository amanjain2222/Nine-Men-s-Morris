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

    public void getAdjacentMove(Board board) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Please enter the position of the piece that you want to move: ");
            int startPosition = scanner.nextInt();

            System.out.print("Please enter the position you want to move the piece to: ");
            int endPosition = scanner.nextInt();

            Move adjacentMove = new AdjacentMove(this, board, board.getPlayerAtPosition(startPosition));

            if (adjacentMove.execute(startPosition, endPosition)) {
                //TODO: write code for checking if a mill is formed after moving the piece and ask to remove a piece
                break;
            } else {
                System.out.println("Invalid move. Please try again.");
            }
        }
    }

    public void getPlaceMove(Board board, Piece piece) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            // ask for the position to place the piece
            System.out.print("Please enter the position on board you want to place the piece to: ");
            int endPosition = scanner.nextInt();

            Move placeMove = new PlaceMove(this, board, this.getPiecesRemaining().get(0));

            // execute the move
            if (placeMove.execute(piece.getPosition(), endPosition)) {
                //TODO: write code for checking if a mill is formed after moving the piece and ask to remove a piece
                break;
            } else {
                System.out.println("Invalid move. Please try again.");
            }
        }
    }

    public int getRemovePosition() {
        // TODO: write code for prompts the player to select a position to remove a piece from the board after
        return 0;
    }

    public void undoRemovePieceFromBoard() {
        //TODO: write code for undoing the removal of a piece from the board
    }
}
