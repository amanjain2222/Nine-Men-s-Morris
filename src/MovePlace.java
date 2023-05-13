public class MovePlace extends Move{
    protected Board board;
    public MovePlace(Board board, Player player, Position targetPosition) {
        super(player, targetPosition);
        this.board = board;
    }

    @Override
    public ExecutionCode execute() {
        ExecutionCode superExecutionCode = super.execute();
        if (superExecutionCode != ExecutionCode.SUCCESS) return superExecutionCode;
        targetPosition.setPieceOccupying(board.popPieceFromStartPosition(player));
        return ExecutionCode.SUCCESS;
    }
}
