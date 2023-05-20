public class InputState {
    public InputType inputType;
    public int[] inputValues;

    public static enum InputType {
        GAME_START,
        GAME_END,
        GAME_TURN;
    }

    public InputState(InputType inputType) {
        this.inputType = inputType;
    }

    public InputState(InputType inputType, int[] inputValues) {
        this.inputType = inputType;
        this.inputValues = inputValues;
    }
}
