public class MovePlace extends Move{
    public MovePlace(Board board, Player player, Position targetPosition) {
        super(board, player, targetPosition);
    }

    @Override
    public MoveStatus execute() {
        MoveStatus superMoveStatus = super.execute();
        if (superMoveStatus != MoveStatus.SUCCESS) return superMoveStatus;

        if (!targetPosition.isEmpty()) return MoveStatus.INVALID_NOT_EMPTY;

        targetPosition.setPieceOccupying(board.popPieceFromStartPosition(player));
        board.addPlayerPositionOnBoard(targetPosition);
        return MoveStatus.SUCCESS;
    }
}
