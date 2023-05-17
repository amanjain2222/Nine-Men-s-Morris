public class MoveAdjacent extends Move {
    protected Position startPosition;

    public MoveAdjacent(Board board, Player player, Position startPosition, Position targetPosition) {
        super(board, player, targetPosition);
        this.startPosition = startPosition;
    }

    @Override
    public ExecutionCode execute() {
        ExecutionCode superExecutionCode = super.execute();
        if (superExecutionCode != ExecutionCode.SUCCESS) return superExecutionCode;
        if (startPosition.getPieceOccupying() == null) return ExecutionCode.NULL;
        if (!startPosition.isAdjacentToThisPosition(targetPosition)) return ExecutionCode.NOT_ADJACENT;

        // Move the piece if it belongs to the player whose trying to move it.
        Piece selectedPiece = startPosition.getPieceOccupying();
        if (selectedPiece.getOwner() != player) {return ExecutionCode.NOT_OWNER;}

        // Perform piece move.
        board.removePlayerPositionOnBoard(startPosition);
        startPosition.setEmpty();
        targetPosition.setPieceOccupying(selectedPiece);
        board.addPlayerPositionOnBoard(targetPosition);
        return ExecutionCode.SUCCESS;
    }
}
