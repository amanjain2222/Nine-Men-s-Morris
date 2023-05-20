public class MoveJump extends MoveAdjacent {
    public MoveJump(Player player, Position startPosition, Position targetPosition) {
        super(player, startPosition, targetPosition);
    }

    @Override
    public MoveStatus execute() {
        MoveStatus superMoveStatus = super.execute();
        if (superMoveStatus != MoveStatus.INVALID_NOT_ADJACENT_POSITION)
            return superMoveStatus;

        targetPosition.setPieceOccupying(startPosition.getPieceOccupying());
        startPosition.setEmpty();

        return targetPosition.isMill() ? MoveStatus.SUCCESS_MILL_FORMED : MoveStatus.SUCCESS;
    }
}
