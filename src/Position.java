import java.util.ArrayList;

public class Position {
    // adjacent positions stored as ArrayList because of variable number of adjacent positions
    private final ArrayList<Position> adjacentPositions;
    // mill lines stored as ArrayList of ArrayLists because of variable number of mill lines
    private final ArrayList<ArrayList<Position>> millLines;
    private Piece pieceOccupying;
    private int positionNumber;

    public Position(int positionNumber) {
        adjacentPositions = new ArrayList<>();
        millLines = new ArrayList<>();
        pieceOccupying = null;
        this.positionNumber = positionNumber;

    }

    public Piece getPieceOccupying() {
        // return the piece occupying the position
        return pieceOccupying;
    }

    public void setPieceOccupying(Piece piece) {
        pieceOccupying = piece;
    }

    public Piece popPieceOccupying() {
        Piece piece = pieceOccupying;
        pieceOccupying = null;
        return piece;
    }

    public boolean isEmpty() {
        return (pieceOccupying == null);
    }

    public void setEmpty() {
        pieceOccupying = null;
    }

    public void addAdjacentPositions(Position adjacentPostion1, Position adjacentPostion2) {
        adjacentPositions.add(adjacentPostion1);
        adjacentPositions.add(adjacentPostion2);
    }

    public void addAdjacentPositions(Position adjacentPostion1, Position adjacentPostion2, Position adjacentPostion3) {
        adjacentPositions.add(adjacentPostion1);
        adjacentPositions.add(adjacentPostion2);
        adjacentPositions.add(adjacentPostion3);
    }

    public void addAdjacentPositions(Position adjacentPostion1, Position adjacentPostion2, Position adjacentPostion3, Position adjacentPostion4) {
        adjacentPositions.add(adjacentPostion1);
        adjacentPositions.add(adjacentPostion2);
        adjacentPositions.add(adjacentPostion3);
        adjacentPositions.add(adjacentPostion4);
    }

    public boolean isAdjacentToThisPosition(Position queryPosition) {
        return adjacentPositions.contains(queryPosition);
    }

    public int getPositionNumber() {
        return positionNumber;
    }

    public void addMillLine(ArrayList<Position> millLine) {
        millLines.add(millLine);
    }

    public boolean isPartOfMill() {
        for (ArrayList<Position> line : millLines) {
            if (line.stream().allMatch(pos -> !pos.isEmpty() &&
                    pos.getPieceOccupying().getOwner() == this.getPieceOccupying().getOwner())) {
                return true;
            }
        }
        return false;
    }
}
