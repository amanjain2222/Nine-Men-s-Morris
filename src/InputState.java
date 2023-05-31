public class InputState {
    public InputType inputType;
    public int[] inputValues;
    public String inputFilepath;

    public static enum InputType {
        GAME_START,
        GAME_END,
        GAME_TURN,
        GAME_SAVE,
        GAME_LOAD;
    }

    public InputState(InputType inputType) {
        this.inputType = inputType;
    }

    public InputState(InputType inputType, int[] inputValues) {
        this.inputType = inputType;
        this.inputValues = inputValues;
    }

    public InputState(InputType inputType, String inputFilepath) {
        this.inputType = inputType;
        this.inputFilepath = inputFilepath;
    }
}
