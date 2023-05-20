public enum GameStatus {
    AWAITING_PLACEMENT("PLACEMENT"),
    AWAITING_MOVEMENT("MOVEMENT"),
    AWAITING_REMOVAL("REMOVAL"),
    
    GAME_OVER("GAME OVER");

    public final String INPUT_DESCRIPTOR;

    private GameStatus(String inputDescriptor) {
        this.INPUT_DESCRIPTOR = inputDescriptor;
    }
}
