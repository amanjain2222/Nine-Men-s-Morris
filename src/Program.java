public class Program {

    public static void main(String[] args) {
        // Main Entry Point for 9MM Program
        runProgram();
    }

    private static void runProgram() {
        DisplayInterface displayInterface = new DisplayInterface();
        GameHandler gameHandler = new GameHandler();

        while (true) {
            // Query GameState from Game Object.
            GameState currentGameState = gameHandler.queryGameState();
            // Use GameState to update the display.
            displayInterface.updateDisplay(currentGameState);
            // Query InputState and queue it to be processed in Game object.
            InputState currentInputState = displayInterface.queryInputState();
            // game.queueInputProcess(currentInputState);
            gameHandler.updateGame(currentInputState);
        }
    }
}
