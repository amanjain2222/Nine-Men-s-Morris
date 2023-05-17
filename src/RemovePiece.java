public class RemovePiece extends Move {
    public RemovePiece(Board board, Player player, Position targetPosition) {
        super(board, player, targetPosition);
    }


    @Override
    public ExecutionCode execute() {
        ExecutionCode superExecutionCode = super.execute();
        if (superExecutionCode != ExecutionCode.SUCCESS) return superExecutionCode;
        if (targetPosition.getPieceOccupying().getOwner() == player) return ExecutionCode.INVALID_CANNOT_REMOVE_YOUR_PIECE;
        board.removePlayerPositionOnBoard(targetPosition);
        targetPosition.setEmpty();
        return ExecutionCode.SUCCESS;
    }
}

