public enum MoveStatus {
    SUCCESS(false),
    INVALID_OUT_OF_BOUNDS_POSITION(true),
    INVALID_NOT_EMPTY(true),
    INVALID_NOT_OWNER(true),
    INVALID_NOT_ADJACENT(true),
    INVALID_POSITION(true),
    INVALID_MILL_POSITION(true),
    INVALID_NOT_OPPONENT_PIECE(true),
    INVALID_OPPONENT_PIECE_IN_MILL_POSITION(true),
    INVALID_CANNOT_REMOVE_YOUR_PIECE(true),
    INVALID_CANNOT_REMOVE_NO_PIECE_FOUND(true),
    MILL_FORMED(false),
    QUIT(false),
    UNDO(false),
    REMOVED(false),
    GAME_START(false),
    UNKNOWN(false);

    public final boolean IS_INVALID;

    private MoveStatus(boolean IS_INVALID) {
        this.IS_INVALID = IS_INVALID;
    }
}