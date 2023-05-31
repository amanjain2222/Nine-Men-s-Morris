public class GameHandler {
    GameHandlerStatus gameHandlerStatus = GameHandlerStatus.INACTIVE;
    Game game;
    GameHistory gameHistory;
    FileHandler fileHandler;

    public GameHandler(FileHandler fileHandler) {
        this.fileHandler = fileHandler;
    }

    public GameState queryGameState() {
        if (game == null) {
            return new GameState(gameHandlerStatus);
        }
        GameState gameState = game.queryGameState();
        gameState.setGameHandlerStatus(gameHandlerStatus);
        gameHandlerStatus = GameHandlerStatus.RUNNING;
        return gameState;
    }

    public void updateGame(InputState inputState) {
        switch(inputState.inputType) {
            case GAME_START:
                gameHistory = new GameHistory();
                game = new Game();
                gameHandlerStatus = GameHandlerStatus.RUNNING;
                break;
            case GAME_TURN:
                game.updateGame(inputState);
                break;
            case GAME_END:
                System.exit(0);
                break;
            case GAME_SAVE:
                fileSaveGame(inputState.inputFilepath);
                break;
            case GAME_LOAD:
                fileLoadGame(inputState.inputFilepath);
                break;
        }
    }

    public void fileSaveGame(String filepath) {
        if (!fileHandler.saveFileGameStates(filepath, gameHistory.queryAllGameStates())) {
            gameHandlerStatus = GameHandlerStatus.GAME_SAVE_FAILED;
            return;
        }
        gameHandlerStatus = GameHandlerStatus.GAME_SAVE_SUCCESS;
    }

    public void fileLoadGame(String filepath) {
        GameState[] loadedGameStates;
        if ((loadedGameStates = fileHandler.getFileGameStates(filepath)) == null) { 
            gameHandlerStatus = game == null ? GameHandlerStatus.INACTIVE_LOAD_FAILED : GameHandlerStatus.GAME_LOAD_FAILED;
            return;
        }
        gameHistory = new GameHistory(loadedGameStates);
        if (game == null) {
            game = new Game();
        }
        game.loadGameState(gameHistory.queryPreviousGameState());
        gameHandlerStatus = GameHandlerStatus.GAME_LOAD_SUCCESS;
    }
}
