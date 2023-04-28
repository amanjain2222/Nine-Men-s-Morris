public class MovePlace extends Move{
    public MovePlace(Player player, Position targetPosition) {
        super(player, targetPosition);
    }

    @Override
    public boolean execute() {
        if (!super.execute()) return false;
        
        targetPosition.setPieceOccupying(player.popPiecesRemaining());
        return true;
    }
}
