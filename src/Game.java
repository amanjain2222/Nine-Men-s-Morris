public class Game {
    private static final String FIRST_PLAYER_NAME = "Ice";
    private static final Character FIRST_PLAYER_CHAR = 'I';
    private static final String SECOND_PLAYER_NAME = "Fire";
    private static final Character SECOND_PLAYER_CHAR = 'F';

    private final Player[] PLAYERS = { new Player(FIRST_PLAYER_NAME, FIRST_PLAYER_CHAR),
            new Player(SECOND_PLAYER_NAME, SECOND_PLAYER_CHAR) };

    private final Board BOARD = new Board(PLAYERS[0], PLAYERS[1]);

    private final int TOTAL_PLACEMENT_TURNS = Player.STARTING_PIECES * PLAYERS.length;

    private int gameTurn = 0;
    private GameStatus gameStatus = GameStatus.AWAITING_PLACEMENT;
    private MoveStatus moveStatus = MoveStatus.GAME_START;
    private boolean gameOver = false;

    public void updateGame(InputState input) {
        // Get current player.
        Player currentPlayer = PLAYERS[gameTurn % PLAYERS.length];
        Position targetPosition = BOARD.getPosition(input.inputValues[0]);
        Position startPosition = BOARD.getPosition(input.inputValues[1]);

        // Pass input as current player's input.
        switch (gameStatus) {
            case AWAITING_PLACEMENT ->
                moveStatus = currentPlayer.takePlaceTurn(targetPosition);
            case AWAITING_MOVEMENT ->
                moveStatus = currentPlayer.takeMovementTurn(startPosition, targetPosition);
            case AWAITING_REMOVAL ->
                moveStatus = currentPlayer.takeRemoveTurn(targetPosition);
            case GAME_OVER ->
                moveStatus = MoveStatus.GAME_OVER;
        }

        updateIsGameOver();

        // First ensure input didn't fail to process, else we can stop processing.
        if (moveStatus.IS_INVALID) {
            return;
        }

        // Check if a removal move is required from the player before game continuation.
        if (moveStatus == MoveStatus.SUCCESS_MILL_FORMED) {
            gameStatus = GameStatus.AWAITING_REMOVAL;
            return;
        }

        // Move on to next game turn if no additional steps are required from player.
        gameTurn++;
        gameStatus = gameTurn < TOTAL_PLACEMENT_TURNS ? GameStatus.AWAITING_PLACEMENT : GameStatus.AWAITING_MOVEMENT;
    }

    public GameState queryGameState() {
        return new GameState(this);
    }

    public Board getBoard() {
        return BOARD;
    }

    public int getGameTurn() {
        return gameTurn;
    }

    public Player getCurrentPlayer() {
        return PLAYERS[gameTurn % PLAYERS.length];
    }

    public Player getOpponentPlayer() {
        return PLAYERS[(gameTurn + 1) % PLAYERS.length];
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public MoveStatus getMoveStatus() {
        return moveStatus;
    }

    public boolean getIsGameOver() {
        // return (players[0].getNumOfPiecesOnBoard() < 3 ||
        // players[1].getNumOfPiecesOnBoard() < 3);
        return gameOver;
    }

    private void updateIsGameOver() {
        if (getOpponentPlayer().getTotalPieces() > 0) {
            gameOver = false;
        } else if (getOpponentPlayer().getTotalPieces() < 3)
            gameOver = true;
    }
}
