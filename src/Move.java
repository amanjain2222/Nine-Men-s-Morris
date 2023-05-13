public abstract class Move extends Action {
    protected Player player;
    protected Position targetPosition;

    public Move(Player player, Position targetPosition) {
        this.player = player;
        this.targetPosition = targetPosition;
    }

    @Override
    public ExecutionCode execute() {
        // validate target position
        if (targetPosition == null) return ExecutionCode.NULL;
        if (!targetPosition.isEmpty()) return ExecutionCode.NOT_EMPTY;
        return ExecutionCode.SUCCESS;
    }
}
