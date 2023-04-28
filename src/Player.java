import java.util.ArrayList;

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

    public boolean makeAdjacentMove(Board board, Position startPosition, Position targetPosition) {
        Move adjacentMove = new AdjacentMove(this, board, board.getPlayerAtPosition(startPosition.getIndex()));

        return adjacentMove.execute(startPosition.getIndex(), targetPosition.getIndex());
    }

    public boolean makePlaceMove(Board board, Position targetPosition) {
        Piece piece = getPiecesRemaining().get(0);
        Move placeMove = new PlaceMove(this, board, piece);

        return placeMove.execute(piece.getPosition(), targetPosition.getIndex());
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

    public int getRemovePosition() {
        // TODO: write code for prompts the player to select a position to remove a piece from the board after
        return 0;
    }

    public void undoRemovePieceFromBoard() {
        //TODO: write code for undoing the removal of a piece from the board
    }
}
