public class MovePlace extends Move{
    protected Board board;
    public MovePlace(Board board, Player player, Position targetPosition) {
        super(player, targetPosition);
        this.board = board;
    }

    @Override
    public ErrorCode execute() {
        if (super.execute() != ErrorCode.SUCCESS) return super.execute();
        targetPosition.setPieceOccupying(board.popPieceFromStartPosition(player));
        return ErrorCode.SUCCESS;
    }
}
