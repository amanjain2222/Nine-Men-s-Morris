public class MoveAdjacent extends Move {
    protected Position startPosition;

    public MoveAdjacent(Board board, Player player, Position startPosition, Position targetPosition) {
        super(board, player, targetPosition);
        this.startPosition = startPosition;
    }

    @Override
    public MoveStatus execute() {
        MoveStatus superMoveStatus = super.execute();
        if (superMoveStatus != MoveStatus.SUCCESS) return superMoveStatus;
        if (startPosition.getPieceOccupying() == null) return MoveStatus.INVALID_OUT_OF_BOUNDS_POSITION;

        // Move the piece if it belongs to the player whose trying to move it.
        Piece selectedPiece = startPosition.getPieceOccupying();
        if (selectedPiece.getOwner() != player) {return MoveStatus.INVALID_NOT_OWNER;}

        if (!startPosition.isAdjacentToThisPosition(targetPosition)) return MoveStatus.INVALID_NOT_ADJACENT;

        // Perform piece move.
        board.removePlayerPositionOnBoard(startPosition);
        startPosition.setEmpty();
        targetPosition.setPieceOccupying(selectedPiece);
        board.addPlayerPositionOnBoard(targetPosition);
        
        return targetPosition.isMill() ? MoveStatus.MILL_FORMED : MoveStatus.SUCCESS;
    }
}
