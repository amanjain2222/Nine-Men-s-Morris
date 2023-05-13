public class MoveAdjacent extends Move {
    private Position startPosition;

    public MoveAdjacent(Player player, Position startPosition, Position targetPosition) {
        super(player, targetPosition);
        this.startPosition = startPosition;
    }

    @Override
    public ErrorCode execute() {
        if (super.execute() != ErrorCode.SUCCESS) return super.execute();

        if (!startPosition.isAdjacentToThisPosition(targetPosition)) {
            return ErrorCode.NOT_ADJACENT;
        }

        // Move the piece if it belongs to the player whose trying to move it.
        Piece selectedPiece = startPosition.getPieceOccupying();
        if (selectedPiece.getOwner() != player) {return ErrorCode.NOT_OWNER;}

        // Perform piece move.
        startPosition.setEmpty();
        targetPosition.setPieceOccupying(selectedPiece);
        return ErrorCode.SUCCESS;
    }
}
