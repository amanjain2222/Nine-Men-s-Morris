public class MoveJump extends MoveAdjacent{
    public MoveJump(Board board, Player player, Position startPosition, Position targetPosition) {
        super(board, player, startPosition, targetPosition);
    }

    @Override
    public ExecutionCode execute() {
        ExecutionCode superExecutionCode = super.execute();
        if (superExecutionCode != ExecutionCode.SUCCESS && superExecutionCode != ExecutionCode.NOT_ADJACENT) return superExecutionCode;
        if (!targetPosition.isEmpty()) return ExecutionCode.NOT_EMPTY;
        board.removePlayerPositionOnBoard(startPosition);
        startPosition.setEmpty();
        targetPosition.setPieceOccupying(startPosition.getPieceOccupying());
        board.addPlayerPositionOnBoard(targetPosition);
        return ExecutionCode.SUCCESS;
    }
    // TODO: Implement Jump Move
}
