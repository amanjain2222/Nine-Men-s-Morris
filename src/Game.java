public class Game {
    private final String FIRST_PLAYER_NAME = "Ice";
    private final Character FIRST_PLAYER_CHAR = 'I';
    private final String SECOND_PLAYER_NAME = "Fire";
    private final Character SECOND_PLAYER_CHAR = 'F';

    private final Player[] PLAYERS = { new Player(FIRST_PLAYER_NAME, FIRST_PLAYER_CHAR),
            new Player(SECOND_PLAYER_NAME, SECOND_PLAYER_CHAR) };

    private final Board BOARD = new Board(PLAYERS[0], PLAYERS[1]);

    private final int PLAYER_STARTING_PIECES = 9;
    private final int TOTAL_PLACEMENT_TURNS = PLAYER_STARTING_PIECES * PLAYERS.length;

    private int gameTurn = 0;
    private GameStatus gameStatus = GameStatus.AWAITING_PLACEMENT;
    private MoveStatus moveStatus = MoveStatus.GAME_START;
    private boolean gameOver = false;

    public void updateGame(InputState input) {
        // Get current player.
        Player currentPlayer = PLAYERS[gameTurn % PLAYERS.length];

        // Pass input as current player's input.
        switch (gameStatus) {
            case AWAITING_PLACEMENT:
                moveStatus = handlePlacementPhase(input, currentPlayer);
                break;
            case AWAITING_MOVEMENT:
                moveStatus = handleMovementPhase(input, currentPlayer);
                break;
            case AWAITING_REMOVAL:
                moveStatus = handleRemovalPhase(input, currentPlayer);
                updateIsGameOver();
                break;
            default:
                break;
        }

        // First ensure input didn't fail to process, else we can stop processing.
        if (moveStatus.IS_INVALID) {
            return;
        }

        // Check if a removal move is required from the player before game continuation.
        if (moveStatus == MoveStatus.MILL_FORMED) {
            gameStatus = GameStatus.AWAITING_REMOVAL;
            return;
        }

        // Move on to next game turn if no additional steps are required from player.
        gameTurn++;
        gameStatus = gameTurn < TOTAL_PLACEMENT_TURNS ? GameStatus.AWAITING_PLACEMENT : GameStatus.AWAITING_MOVEMENT;
        // TODO: This is temp fix to make the code work change as necessary
        //gameStatus = getCurrentPlayer().getNumOfPiecesRemaining() <= 0 ? GameStatus.AWAITING_MOVEMENT : GameStatus.AWAITING_PLACEMENT;
    }

    public GameState queryGameState() {
        return new GameState(this);
    }

    private MoveStatus handlePlacementPhase(InputState input, Player currentPlayer) {
        Position targetPosition = BOARD.getPosition(input.inputValues.get(0));
        return currentPlayer.makePlaceMove(targetPosition);
    }

    private MoveStatus handleMovementPhase(InputState input, Player currentPlayer) {
        Position startingPosition = BOARD.getPosition(input.inputValues.get(0));
        Position targetPosition = BOARD.getPosition(input.inputValues.get(1));
        return currentPlayer.movePiece(gameBoard, startPosition, targetPosition);
    }

    private MoveStatus handleRemovalPhase(InputState input, Player currentPlayer) {
        Position targetPosition = BOARD.getPosition(input.inputValues.get(0));
        return currentPlayer.removePiece(targetPosition);
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
        if (getOpponentPlayer().getNumOfPiecesRemaining() > 0) {
            gameOver = false;
        } else if (getOpponentPlayer().getNumOfPiecesOnBoard() < 3)
            gameOver = true;
    }

    private void updatePlayerCanJump() {
        if (this.getOpponentPlayer().getNumOfPiecesOnBoard() == 3) {
            this.getOpponentPlayer().setCanJump(true);
        }
    }
}
