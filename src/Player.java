import java.util.ArrayList;

public class Player {
    private final String name;
    private ArrayList<Piece> piecesRemaining;
    private int numOfPiecesOnBoard;

    public Player(String name, char displayChar) {
        this.name = name;
        numOfPiecesOnBoard = 0;
        piecesRemaining = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            piecesRemaining.add(new Piece(displayChar, this));
        }
    }

    public boolean makeAdjacentMove(Position startPosition, Position targetPosition) {
        return new MoveAdjacent(this, startPosition, targetPosition).execute();
    }

    public boolean makePlaceMove(Position targetPosition) {
        // Return any potential invalid status from the move so it can be used to update the GameState.
        return new MovePlace(this, targetPosition).execute();
    }

    public String getName() {
        // return the name of the player
        return name;
    }

    public int getNumOfPiecesRemaining() {
        //  return the number of pieces remaining
        return piecesRemaining.size();
    }

    public int getNumOfPiecesOnBoard() {
        // return the number of pieces on the board
        return numOfPiecesOnBoard;
    }

    public Piece popPiecesRemaining() {
        // pop a piece from the pieces remaining
        return piecesRemaining.remove(piecesRemaining.size() - 1);
    }
}
