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
    public MoveStatus execute() {
        if (targetPosition == null) return MoveStatus.INVALID_OUT_OF_BOUNDS_POSITION;
        return MoveStatus.SUCCESS;
    }


}
