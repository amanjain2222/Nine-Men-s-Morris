public class RemovePiece extends Move {
    public RemovePiece(Board board, Player player, Position targetPosition) {
        super(board, player, targetPosition);
    }


    @Override
    public MoveStatus execute() {
        MoveStatus superMoveStatus = super.execute();
        if (superMoveStatus != MoveStatus.SUCCESS) return superMoveStatus;
        if (targetPosition.isEmpty()) return MoveStatus.INVALID_CANNOT_REMOVE_NO_PIECE_FOUND;
        if (targetPosition.getPieceOccupying().getOwner() == player) return MoveStatus.INVALID_CANNOT_REMOVE_YOUR_PIECE;
        if (targetPosition.isMill()) return MoveStatus.INVALID_OPPONENT_PIECE_IN_MILL_POSITION;

        targetPosition.getPieceOccupying().getOwner().decreaseNumOfPiecesOnBoard();
        board.removePlayerPositionOnBoard(targetPosition);
        targetPosition.setEmpty();

        return MoveStatus.SUCCESS;
    }
}

