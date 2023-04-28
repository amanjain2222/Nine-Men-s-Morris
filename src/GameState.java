public class GameState {
    private final InvalidMoveStatus invalidMoveStatus = null;

    public enum InvalidMoveStatus {
        START_POSITION_OUT_OF_BOUNDS("The start position you entered is not valid!"),
        END_POSITION_OUT_OF_BOUNDS("The end position you entered is not valid!"),
        END_POSITION_NOT_ADJACENT("The end position is not adjacent to the start position!");

        public String statusMessage;

        private InvalidMoveStatus(String statusMessage) {
            this.statusMessage = statusMessage;
        }
    }
}
