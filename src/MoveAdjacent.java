public class MoveAdjacent extends Move {
    private Position startPosition;

    public MoveAdjacent(Player player, Position startPosition, Position targetPosition) {
        super(player, targetPosition);
        this.startPosition = startPosition;
    }

    @Override
    public ExecutionCode execute() {
        ExecutionCode superExecutionCode = super.execute();
        if (superExecutionCode != ExecutionCode.SUCCESS) return superExecutionCode;

        if (!startPosition.isAdjacentToThisPosition(targetPosition)) {
            return ExecutionCode.NOT_ADJACENT;
        }

        // Move the piece if it belongs to the player whose trying to move it.
        Piece selectedPiece = startPosition.getPieceOccupying();
        if (selectedPiece.getOwner() != player) {return ExecutionCode.NOT_OWNER;}

        // Perform piece move.
        startPosition.setEmpty();
        targetPosition.setPieceOccupying(selectedPiece);
        return ExecutionCode.SUCCESS;
    }
}
