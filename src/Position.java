import java.util.ArrayList;

public class Position {
    private final int index;
    private Piece pieceOccupying;
    private boolean isEmpty;
    private final ArrayList<Integer> adjacentIndexes; // adjacent positions stored as ArrayList because of variable number of adjacent positions

    public Position(int index) {
        this.index = index;
        this.pieceOccupying = null;
        this.isEmpty = true;
        this.adjacentIndexes = new ArrayList<>();
    }

    public int getIndex() {
        // return the index of the position
        return index;
    }

    public Piece getPieceOccupying() {
        // return the piece occupying the position
        return pieceOccupying;
    }

    public void setPieceOccupying(Piece piece) {
        // set the piece occupying the position
        this.pieceOccupying = piece;
        this.isEmpty = false;
    }

    public boolean isEmpty() {
        // check if position is empty
        return isEmpty;
    }

    public void setEmpty() {
        // set the position to empty
        this.pieceOccupying = null;
        this.isEmpty = true;
    }

    public void addAdjacentIndexes(int adjIndex1, int adjIndex2) {
        // add the adjacent indexes to the list for position with two adjacent positions
        this.adjacentIndexes.add(adjIndex1);
        this.adjacentIndexes.add(adjIndex2);
    }

    public void addAdjacentIndexes(int adjIndex1, int adjIndex2, int adjIndex3) {
        // add the adjacent indexes to the list for position with three adjacent positions
        this.adjacentIndexes.add(adjIndex1);
        this.adjacentIndexes.add(adjIndex2);
        this.adjacentIndexes.add(adjIndex3);
    }

    public void addAdjacentIndexes(int adjIndex1, int adjIndex2, int adjIndex3, int adjIndex4) {
        // add the adjacent indexes to the list for position with four adjacent positions
        this.adjacentIndexes.add(adjIndex1);
        this.adjacentIndexes.add(adjIndex2);
        this.adjacentIndexes.add(adjIndex3);
        this.adjacentIndexes.add(adjIndex4);
    }

    public int[] getAdjacentIndexes() {
        // return the adjacent indexes as an array
        int[] adjIndexes = new int[this.adjacentIndexes.size()];
        for (int i = 0; i < this.adjacentIndexes.size(); i++) {
            adjIndexes[i] = this.adjacentIndexes.get(i);
        }
        return adjIndexes;
    }

    public boolean isAdjacentToThisIndex(int index) {
        // Check if the given index is adjacent to this position
        return this.adjacentIndexes.contains(index);
    }
}
