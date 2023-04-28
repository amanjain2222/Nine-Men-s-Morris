public class Program {
    public static void main(String[] args) {
        // Main Entry Point for 9MM Program
        runGame();
    }

    private static void runGame() {
        DisplayInterface displayInterface = new DisplayInterface();
        Game game = new Game();

        while(true) {
            // Game Updates By Processing Any Pending Input and Resolving Any Game Constraints
            // game.updateGame();
            // Query GameState and use it to update the display.
            //GameState currentGameState = game.queryGameState(); 
            displayInterface.updateDisplay(game);
            // Query InputState and queue it to be processed in Game object.
            InputState currentInputState = displayInterface.queryInputState(game);
            //game.queueInputProcess(currentInputState);
            game.updateGame(currentInputState);
        }
    }
}
