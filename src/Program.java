public class Program {
    public static void main(String[] args) {
        // Main Entry Point for 9MM Program
        runGame();
    }

    private static void runGame() {
        Display currentDisplay = new Display();
        Game currentGame = new Game();

        while(true) {
            Input currentInput = currentDisplay.updateDisplay(currentGame);
            currentGame.updateGame(currentInput);
        }
    }
}
