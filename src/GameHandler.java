public class GameHandler {
    Game game;

    public GameState queryGameState() {
        if (game == null) {
            return null;
        }
        return game.queryGameState();
    }

    public void updateGame(InputState inputState) {
        switch(inputState.inputType) {
            case GAME_START:
                game = new Game();
                break;
            case GAME_TURN:
                game.updateGame(inputState);
                break;
            case GAME_END:
                System.exit(0);
                break;
        }
    }
}
