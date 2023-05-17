public class MovePlace extends Move{
    public MovePlace(Board board, Player player, Position targetPosition) {
        super(board, player, targetPosition);
    }

    @Override
    public ExecutionCode execute() {
        ExecutionCode superExecutionCode = super.execute();
        if (superExecutionCode != ExecutionCode.SUCCESS) return superExecutionCode;

        targetPosition.setPieceOccupying(board.popPieceFromStartPosition(player));
        board.addPlayerPositionOnBoard(targetPosition);
        return ExecutionCode.SUCCESS;
    }
}
