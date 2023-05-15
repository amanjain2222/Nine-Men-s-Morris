public abstract class Move extends Action {
    protected Player player;
    protected Position targetPosition;

    public Move(Player player, Position targetPosition) {
        this.player = player;
        this.targetPosition = targetPosition;
    }

    @Override
    public boolean execute() {
        // validate target position
        return targetPosition != null;
    }


}
