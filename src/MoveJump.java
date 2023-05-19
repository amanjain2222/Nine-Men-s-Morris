public class MoveJump extends MoveAdjacent {
    public MoveJump(Board board, Player player, Position startPosition, Position targetPosition) {
        super(board, player, startPosition, targetPosition);
    }

    @Override
    public MoveStatus execute() {
        MoveStatus superMoveStatus = super.execute();
        if (superMoveStatus != MoveStatus.SUCCESS && superMoveStatus != MoveStatus.INVALID_NOT_ADJACENT) return superMoveStatus;
        if (!targetPosition.isEmpty()) return MoveStatus.INVALID_NOT_EMPTY;

        targetPosition.setPieceOccupying(startPosition.getPieceOccupying());
        startPosition.setEmpty();

        return targetPosition.isMill() ? MoveStatus.MILL_FORMED : MoveStatus.SUCCESS;
    }
}
