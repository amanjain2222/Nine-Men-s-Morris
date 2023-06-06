public class InputState {
    public InputType inputType;
    public int[] inputValues;
    public String inputFilepath;

    public static enum InputType {
        GAME_START,
        GAME_END,
        GAME_TURN,
        GAME_SAVE,
        GAME_LOAD,
        GAME_UNDO,
        GAME_LOAD_FAILED_EMPTY_FILE,
        GAME_LOAD_CANCELLED,
        GAME_SAVE_CANCELLED,
        GAME_SAVE_FAILED_EMPTY_GAME;
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
