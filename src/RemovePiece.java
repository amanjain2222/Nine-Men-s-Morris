public class RemovePiece extends Move {
    public RemovePiece(Player player, Position targetPosition) {
        super(player, targetPosition);
    }

    @Override
    public boolean execute() {
        if (!super.execute()) return false;
        if (targetPosition.getPieceOccupying().getOwner() == player) return false;
        targetPosition.setEmpty();
        return true;
    }
}
