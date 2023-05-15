public class MovePlace extends Move{
    protected Board board;
    public MovePlace(Board board, Player player, Position targetPosition) {
        super(player, targetPosition);
        this.board = board;
    }

    @Override
    public boolean execute() {
        if (!super.execute()) return false;
        if (!targetPosition.isEmpty()) return false;
        
        targetPosition.setPieceOccupying(player.popPiecesRemaining());
        return true;
    }
}
