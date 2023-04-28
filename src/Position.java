import java.util.ArrayList;

public class Position {
    private final ArrayList<Position> adjacentPositions; // adjacent positions stored as ArrayList because of variable number of adjacent positions
    private Piece pieceOccupying;

    public Position() {
        adjacentPositions = new ArrayList<>();
        pieceOccupying = null;
    }

    public Piece getPieceOccupying() {
        // return the piece occupying the position
        return pieceOccupying;
    }

    public void setPieceOccupying(Piece piece) {
        // set the piece occupying the position
        this.pieceOccupying = piece;
    }

    public boolean isEmpty() {
        // check if position is empty
        return (pieceOccupying == null);
    }

    public void setEmpty() {
        // set the position to empty
        this.pieceOccupying = null;
    }

    public void addAdjacentPositions(Position adjacentPostion1, Position adjacentPostion2) {
        // add the adjacent indexes to the list for position with two adjacent positions
        this.adjacentPositions.add(adjacentPostion1);
        this.adjacentPositions.add(adjacentPostion2);
    }

    public void addAdjacentPositions(Position adjacentPostion1, Position adjacentPostion2, Position adjacentPostion3) {
        // add the adjacent indexes to the list for position with three adjacent positions
        this.adjacentPositions.add(adjacentPostion1);
        this.adjacentPositions.add(adjacentPostion2);
        this.adjacentPositions.add(adjacentPostion3);
    }

    public void addAdjacentPositions(Position adjacentPostion1, Position adjacentPostion2, Position adjacentPostion3, Position adjacentPostion4) {
        // add the adjacent indexes to the list for position with four adjacent positions
        this.adjacentPositions.add(adjacentPostion1);
        this.adjacentPositions.add(adjacentPostion2);
        this.adjacentPositions.add(adjacentPostion3);
        this.adjacentPositions.add(adjacentPostion4);
    }

    public boolean isAdjacentToThisPosition(Position queryPosition) {
        // Check if the given index is adjacent to this position
        return this.adjacentPositions.contains(queryPosition);
    }
}
