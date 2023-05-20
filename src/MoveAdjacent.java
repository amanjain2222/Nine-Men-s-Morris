public class MoveAdjacent extends Move {
    protected Position startPosition;

    public MoveAdjacent(Player player, Position startPosition, Position targetPosition) {
        super(player, targetPosition);
        this.startPosition = startPosition;
    }

    @Override
    public MoveStatus execute() {
        MoveStatus superMoveStatus = super.execute();
        if (superMoveStatus != MoveStatus.SUCCESS)
            return superMoveStatus;

        if (startPosition == null)
            return MoveStatus.INVALID_OUT_OF_BOUNDS_POSITION;
        if (startPosition.getPieceOccupying() == null)
            return MoveStatus.INVALID_NO_STARTING_PIECE;
        if (!targetPosition.isEmpty())
            return MoveStatus.INVALID_TARGET_POSITION_NOT_EMPTY;

        // Move the piece if it belongs to the player whose trying to move it.
        Piece selectedPiece = startPosition.getPieceOccupying();
        if (selectedPiece.getOwner() != player) {
            return MoveStatus.INVALID_NOT_PIECE_OWNER;
        }

        // Checking adjacency must happen after other cases.
        if (!startPosition.isAdjacentToThisPosition(targetPosition))
            return MoveStatus.INVALID_NOT_ADJACENT_POSITION;

        // Perform piece move.
        targetPosition.setPieceOccupying(selectedPiece);
        startPosition.setEmpty();

        return targetPosition.isMill() ? MoveStatus.SUCCESS_MILL_FORMED : MoveStatus.SUCCESS;
    }
}
