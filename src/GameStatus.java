public enum GameStatus {
    AWAITING_PLACEMENT("PLACEMENT"),
    AWAITING_MOVEMENT("MOVEMENT"),
    AWAITING_REMOVAL("REMOVAL"),
    AWAITING_JUMPING("JUMPING"),
    
    GAME_OVER("GAME OVER");

    public final String INPUT_DESCRIPTOR;

    private GameStatus(String inputDescriptor) {
        this.INPUT_DESCRIPTOR = inputDescriptor;
    }
}
