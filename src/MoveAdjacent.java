public class MoveAdjacent extends Move {
    private Position startPosition;

    public MoveAdjacent(Player player, Position startPosition, Position targetPosition) {
        super(player, targetPosition);
        this.startPosition = startPosition;
    }

    @Override
    public boolean execute() {
        if (!super.execute()) return false;
        if (startPosition == null) return false;
        if (!startPosition.isAdjacentToThisPosition(targetPosition)) return false;
        if (startPosition.isEmpty()) return false;

        // Move the piece if it belongs to the player whose trying to move it.
        Piece selectedPiece = startPosition.getPieceOccupying();
        if (selectedPiece.getOwner() != player) return false;

        // Perform piece move.
        startPosition.setEmpty();
        targetPosition.setPieceOccupying(selectedPiece);
        return true;
    }
}
