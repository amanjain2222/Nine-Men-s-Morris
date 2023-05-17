public enum ExecutionCode {
    SUCCESS(false),
    INVALID_NULL(true),
    INVALID_NOT_EMPTY(true),
    INVALID_NOT_OWNER(true),
    INVALID_NOT_ADJACENT(true),
    INVALID_POSITION(true),
    INVALID_MILL_POSITION(true),
    INVALID_NOT_OPPONENT_PIECE(true),
    INVALID_OPPONENT_PIECE_IN_MILL_POSITION(true),
    MILL_FORMED(false),
    QUIT(false),
    UNDO(false),
    REMOVED(false),
    UNKNOWN(false);

    public final boolean IS_INVALID;

    private ExecutionCode(boolean IS_INVALID) {
        this.IS_INVALID = IS_INVALID;
    }
}