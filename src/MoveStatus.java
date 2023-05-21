public enum MoveStatus {
    INVALID_OUT_OF_BOUNDS_POSITION(MoveStatus.INVALID),
    INVALID_NO_STARTING_PIECE(MoveStatus.INVALID),
    INVALID_TARGET_POSITION_NOT_EMPTY(MoveStatus.INVALID),
    INVALID_NOT_PIECE_OWNER(MoveStatus.INVALID),
    INVALID_NOT_ADJACENT_POSITION(MoveStatus.INVALID),
    INVALID_OPPONENT_PIECE_IN_MILL_POSITION(MoveStatus.INVALID),
    INVALID_CANNOT_REMOVE_YOUR_PIECE(MoveStatus.INVALID),
    INVALID_CANNOT_REMOVE_NO_PIECE_FOUND(MoveStatus.INVALID),
    
    SUCCESS(MoveStatus.VALID),
    SUCCESS_MILL_FORMED(MoveStatus.VALID),
    
    GAME_START(MoveStatus.VALID),
    GAME_OVER(MoveStatus.VALID);

    private static final boolean INVALID = true;
    private static final boolean VALID = false;

    public final boolean IS_INVALID;

    private MoveStatus(boolean IS_INVALID) {
        this.IS_INVALID = IS_INVALID;
    }
}