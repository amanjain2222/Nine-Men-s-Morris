import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class GameHistory {
    List<GameState> history;

    public GameHistory() {
        history = new ArrayList<>();
    }

    public GameHistory(GameState[] gameStates) {
        history = new ArrayList<>(Arrays.asList(gameStates));
    }

    public GameState popPreviousGameState() {
        if (history.size() < 2) {
            return null;
        }
        history.remove(history.size() - 1);
        return queryPreviousGameState();
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
