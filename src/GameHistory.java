import java.util.ArrayList;
import java.util.Arrays;

public class GameHistory {
    ArrayList<GameState> history;

    public GameHistory() {
        history = new ArrayList<>();
    }

    public GameHistory(GameState[] gameStates) {
        history = new ArrayList<>(Arrays.asList(gameStates));
    }

    public GameState[] queryAllGameStates() {
        GameState[] allGameStates = new GameState[0];
        return history.toArray(allGameStates);
    }

    public GameState queryPreviousGameState() {
        return history.get(history.size() - 1);
    }

    public void recordNextGameState(GameState gameState) {
        history.add(gameState);
    }
}
