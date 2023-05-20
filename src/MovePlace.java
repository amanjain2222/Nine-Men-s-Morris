public class MovePlace extends Move {
    public MovePlace(Player player, Position targetPosition) {
        super(player, targetPosition);
    }

    @Override
    public MoveStatus execute() {
        // Ensure super reqs are met.
        MoveStatus superMoveStatus = super.execute();
        if (superMoveStatus != MoveStatus.SUCCESS)
            return superMoveStatus;

        // Ensure other reqs are met.
        if (!targetPosition.isEmpty())
            return MoveStatus.INVALID_TARGET_POSITION_NOT_EMPTY;

        // Perform Placement
        targetPosition.setPieceOccupying(new Piece(player));

        // Return successful move status.
        return targetPosition.isMill() ? MoveStatus.SUCCESS_MILL_FORMED : MoveStatus.SUCCESS;
    }
}
