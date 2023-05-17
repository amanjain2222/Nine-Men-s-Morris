public abstract class Move extends Action {
    protected Player player;
    protected Position targetPosition;
    protected Board board;

    public Move(Board board, Player player, Position targetPosition) {
        this.board = board;
        this.player = player;
        this.targetPosition = targetPosition;
    }

    @Override
    public ExecutionCode execute() {
        // validate target position
        if (targetPosition == null) return ExecutionCode.INVALID_NULL;
        return ExecutionCode.SUCCESS;
    }


}
