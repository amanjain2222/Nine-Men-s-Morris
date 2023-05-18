import java.util.ArrayList;

public class Game {
    private final int PLAYER_STARTING_PIECES = 9;
    private final String FIRST_PLAYER_NAME = "Ice";
    private final Character FIRST_PLAYER_CHAR = 'I';
    private final String SECOND_PLAYER_NAME = "Fire";
    private final Character SECOND_PLAYER_CHAR = 'F';
    private final Player[] players = { new Player(FIRST_PLAYER_NAME, FIRST_PLAYER_CHAR),
            new Player(SECOND_PLAYER_NAME, SECOND_PLAYER_CHAR) };

    private Board gameBoard;

    private int gameTurn;

    private ArrayList<Position> PreviousMovePositions = new ArrayList<>();

    private GameState.GameStatus gameStatus;
    private MoveStatus moveStatus = MoveStatus.GAME_START;
    private boolean gameOver = false;
    private Position inputStartPosition;
    private Position inputTargetPosition;

    public Game() {
        gameTurn = 0;
        gameBoard = new Board(players[0], players[1]);
        gameStatus = GameState.GameStatus.AWAITING_PLACEMENT;
    }

    public void updateGame(InputState input) {
        moveStatus = MoveStatus.UNKNOWN;
        Player currentPlayer = players[gameTurn % players.length];

        switch (gameStatus) {
            case AWAITING_PLACEMENT:
                moveStatus = handlePlacementPhase(input, currentPlayer);
                break;
            case AWAITING_MOVEMENT:
                moveStatus = handleMovementPhase(input, currentPlayer);
                break;
            case AWAITING_REMOVAL:
                moveStatus = handleRemovalPhase(input, currentPlayer);
                break;
            default:
                break;
        }

        // Don't update game globals if move failed to process.
        if (moveStatus.IS_INVALID) {
            return;
        }

        if (moveStatus == MoveStatus.MILL_FORMED) {
            gameStatus = GameState.GameStatus.AWAITING_REMOVAL;
        } else if (gameTurn < players.length * PLAYER_STARTING_PIECES) {
            gameStatus = GameState.GameStatus.AWAITING_PLACEMENT;
        } else {
            gameStatus = GameState.GameStatus.AWAITING_MOVEMENT;
        }
        if (moveStatus == MoveStatus.SUCCESS) {
            gameTurn++;
        }
    }

    public GameState queryGameState() {
        return new GameState(this);
    }
    
    private MoveStatus handlePlacementPhase(InputState input, Player currentPlayer) {
        Position targetPosition = gameBoard.getPosition(input.inputValues.get(0));
        MoveStatus statusCode = currentPlayer.makePlaceMove(gameBoard, targetPosition);
        setMoveStatus(statusCode);
        this.inputTargetPosition = targetPosition;
        if (!statusCode.IS_INVALID){
            PreviousMovePositions.add(targetPosition);
        }

        return statusCode;
    }

    private MoveStatus handleRemovalPhase(InputState input, Player currentPlayer) {
        Position targetPosition = gameBoard.getPosition(input.inputValues.get(0));
        MoveStatus statusCode = currentPlayer.removePiece(gameBoard, targetPosition);
        updatePlayerCanJump(); // update opponent player's canJump status
        updateIsGameOver();
        return statusCode;
    }

    private MoveStatus handleMovementPhase(InputState input, Player currentPlayer) {
        MoveStatus statusCode;
        Position startingPosition = gameBoard.getPosition(input.inputValues.get(0));
        Position targetPosition = gameBoard.getPosition(input.inputValues.get(1));

        if (!currentPlayer.getCanJump()) {
            statusCode = currentPlayer.makeAdjacentMove(gameBoard, startingPosition, targetPosition);
        } else {
            statusCode = currentPlayer.makeJumpMove(gameBoard, startingPosition, targetPosition);
        }

        setMoveStatus(statusCode);
        this.inputStartPosition = startingPosition;
        this.inputTargetPosition = targetPosition;

        if (statusCode == MoveStatus.SUCCESS){
            PreviousMovePositions.add(targetPosition);
        }
        return statusCode;
    }

    private void updatePlayerCanJump() {
        if (this.getOpponentPlayer().getNumOfPiecesOnBoard() == 3) {
            this.getOpponentPlayer().setCanJump(true);
        }
    }

    private void setMoveStatus(MoveStatus moveStatus) {
        this.moveStatus = moveStatus;
    }

    public MoveStatus getMoveStatus() {
        return moveStatus;
    }

    public Board getGameBoard() {
        return gameBoard;
    }

    public Player getCurrentPlayer() {
        return players[gameTurn % players.length];
    }

    public Player getOpponentPlayer() {
        return players[(gameTurn + 1) % players.length];
    }

    public Position getInputStartPosition() {
        return inputStartPosition;
    }

    public Position getInputTargetPosition() {
        return inputTargetPosition;
    }

    public int getGameTurn() {
        return gameTurn;
    }

    public GameState.GameStatus getGameStatus() {
        return gameStatus;
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
}
