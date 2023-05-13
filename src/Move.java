public abstract class Move extends Action {
    protected Player player;
    protected Position targetPosition;

    public Move(Player player, Position targetPosition) {
        this.player = player;
        this.targetPosition = targetPosition;
    }

    @Override
    public ErrorCode execute() {
        // validate target position
        if (targetPosition == null) return ErrorCode.NULL;
        if (!targetPosition.isEmpty()) return ErrorCode.NOT_EMPTY;
        return ErrorCode.SUCCESS;
    }
}
