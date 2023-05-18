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
    private boolean undoMove = false;
    private boolean gameOver = false;
    private boolean gameRestarted = false;
    private Position inputStartPosition;
    private Position inputTargetPosition;

    public Game() {
        gameTurn = 0;
        gameBoard = new Board(players[0], players[1]);
        gameStatus = GameState.GameStatus.AWAITING_PLACEMENT;
    }

    public void updateGame(InputState input) {
        MoveStatus moveStatusCode = MoveStatus.UNKNOWN;
        Player currentPlayer = players[gameTurn % players.length];

        switch (gameStatus) {
            case AWAITING_PLACEMENT:
                moveStatusCode = handlePlacementPhase(input, currentPlayer);
                break;
            case AWAITING_MOVEMENT:
                moveStatusCode = handleMovementPhase(input, currentPlayer);
                break;
            case AWAITING_REMOVAL:
                moveStatusCode = handleRemovalPhase(input, currentPlayer);
                break;
            default:
                break;
        }


        if (moveStatusCode == MoveStatus.SUCCESS) {
            gameTurn++;
            updateCurrentGamePhase();
        }
    }

    public GameState queryGameState() {
        return GameState.getGameStateBuilder()
                // Current Player GameState Variables
                .setCurrentPlayerName(getCurrentPlayer().getName())
                .setCurrentPlayerPiecesRemaining(getCurrentPlayer().getNumOfPiecesRemaining())
                .setCurrentPlayerPiecesOnBoard(getCurrentPlayer().getNumOfPiecesOnBoard())
                .setCurrentPlayerPiecesCaptured(getCurrentPlayer().getNumOfPiecesCaptured())
                .setCurrentPlayerMillsCreated(getCurrentPlayer().getNumOfMillsMade())
                // Opponent Player GameState Variables
                .setOpponentPlayerName(getOpponentPlayer().getName())
                .setOpponentPlayerPiecesRemaining(getOpponentPlayer().getNumOfPiecesRemaining())
                .setOpponentPlayerPiecesOnBoard(getOpponentPlayer().getNumOfPiecesOnBoard())
                .setOpponentPlayerPiecesCaptured(getOpponentPlayer().getNumOfPiecesCaptured())
                .setOpponentPlayerMillsCreated(getOpponentPlayer().getNumOfMillsMade())
                // Meta GameState Variables
                .setGameStatus(gameStatus)
                .setMoveStatus(moveStatus)
                .setGameTurn(gameTurn)
                .setBoard(getGameBoard().toCharArray())
                .build();
    }
    
    private MoveStatus handlePlacementPhase(InputState input, Player currentPlayer) {
        Position targetPosition = gameBoard.getPosition(input.inputValues.get(0));
        MoveStatus statusCode = currentPlayer.makePlaceMove(gameBoard, targetPosition);
        setMoveStatus(statusCode);
        this.inputTargetPosition = targetPosition;
        if (statusCode == MoveStatus.SUCCESS){
            PreviousMovePositions.add(targetPosition);
        }

        return statusCode;
    }

    private void updateCurrentGamePhase() {
        if (PreviousMovePositions.size() > 0 && gameBoard.isMillFormed(players[(gameTurn - 1) % players.length] , PreviousMovePositions.get(PreviousMovePositions.size() - 1))) {
            gameStatus = GameState.GameStatus.AWAITING_REMOVAL;
            gameTurn--;
        } else if (gameTurn < players.length * PLAYER_STARTING_PIECES) {
            gameStatus = GameState.GameStatus.AWAITING_PLACEMENT;
        } else {
            gameStatus = GameState.GameStatus.AWAITING_MOVEMENT;
        }
    }

    private MoveStatus handleRemovalPhase(InputState input, Player currentPlayer) {

        MoveStatus statusCode;
        Position targetPosition = gameBoard.getPosition(input.inputValues.get(0));

        if (gameBoard.isMillFormed(players[(gameTurn+1) % players.length], targetPosition)) {
            statusCode = MoveStatus.INVALID_OPPONENT_PIECE_IN_MILL_POSITION;
        }else{
            statusCode = players[gameTurn % players.length].removePiece(gameBoard, targetPosition);
        }

        if (statusCode == MoveStatus.SUCCESS){
            PreviousMovePositions.clear();
        }


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

    public boolean isPreviousMoveUndo() {
        return undoMove;
    }

    public void resetGameRestarted() {
        this.gameRestarted = false;
    }

    public boolean isGameRestarted() {
        return gameRestarted;
    }

    public void restartGame() {
        gameTurn = 0;
        gameBoard = new Board(players[0], players[1]);
        gameStatus = GameState.GameStatus.AWAITING_PLACEMENT;
        gameOver = false;
        gameRestarted = true;
    }
}
