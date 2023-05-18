public class MovePlace extends Move{
    public MovePlace(Board board, Player player, Position targetPosition) {
        super(board, player, targetPosition);
    }

    @Override
    public MoveStatus execute() {
        // Ensure super reqs are met.
        MoveStatus superMoveStatus = super.execute();
        if (superMoveStatus != MoveStatus.SUCCESS) return superMoveStatus;

        // Ensure other reqs are met.
        if (!targetPosition.isEmpty()) return MoveStatus.INVALID_NOT_EMPTY;

        // Perform Placement
        targetPosition.setPieceOccupying(board.popPieceFromStartPosition(player));
        board.addPlayerPositionOnBoard(targetPosition);   

        // Return successful move status.
        return targetPosition.isMill() ? MoveStatus.MILL_FORMED : MoveStatus.SUCCESS;
    }
}
