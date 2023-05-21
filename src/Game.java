public class Game {
    private static final String FIRST_PLAYER_NAME = "Ice";
    private static final Character FIRST_PLAYER_CHAR = 'I';
    private static final String SECOND_PLAYER_NAME = "Fire";
    private static final Character SECOND_PLAYER_CHAR = 'F';

    private final Player[] PLAYERS = { new Player(FIRST_PLAYER_NAME, FIRST_PLAYER_CHAR),
            new Player(SECOND_PLAYER_NAME, SECOND_PLAYER_CHAR) };

    private final Board BOARD = new Board();

    private final int TOTAL_PLACEMENT_TURNS = Player.STARTING_PIECES * PLAYERS.length;

    private int gameTurn = 0;
    private GameStatus gameStatus = GameStatus.AWAITING_PLACEMENT;
    private MoveStatus moveStatus = MoveStatus.GAME_START;

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

        // First ensure input didn't fail to process, else we can stop processing.
        if (moveStatus.IS_INVALID) {
            return;
        }

        // Check if a removal move is required from the player before game continuation.
        if (moveStatus == MoveStatus.SUCCESS_MILL_FORMED) {
            // Handle edge case where no piece is able to be removed.
            System.out.println(BOARD.findUnprotectedPieces(getOpponentPlayer().getDisplayChar()));
            if (BOARD.findUnprotectedPieces(getOpponentPlayer().getDisplayChar())) {
                gameStatus = GameStatus.AWAITING_REMOVAL;
                return;
            }
            moveStatus = MoveStatus.SUCCESS_MILL_FORMED_NO_REMOVAL_NEEDED;
        }

        // Check for game over conditions.
        if (isGameOver()) {
            gameStatus = GameStatus.GAME_OVER;
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

    public boolean isGameOver() {
        // Case where player can't make a mill.
        if (getOpponentPlayer().getTotalPieces() < 3) {
            return true;
        }

        // Case where player has no valid moves.
        if (getOpponentPlayer().getTotalPieces() > 3
                && getOpponentPlayer().getPiecesLeftToPlace() < 1
                && BOARD.isNoValidPieceMoves(getOpponentPlayer().getDisplayChar())) {
            return true;
        }

        return false;
    }
}
