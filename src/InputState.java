import java.util.ArrayList;

public class InputState {
    public InputType inputType;
    public ArrayList<Integer> inputValues;

    public static enum InputType {
        GAME_START,
        GAME_END,
        GAME_TURN;
    }

    public InputState(InputType inputType) {
        this.inputType = inputType;
    }

    public InputState(InputType inputType, ArrayList<Integer> inputValues) {
        this.inputType = inputType;
        this.inputValues = inputValues;
    }
}
