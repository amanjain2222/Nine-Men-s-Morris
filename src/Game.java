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

    private GameState.NextInput nextInput;
    private ExecutionCode executionCode;
    private String currentGamePhase;
    private boolean previousMoveInvalid = false;
    private boolean previousRemoveInvalid = true;
    private boolean undoMove = false;
    private boolean gameOver = false;
    private boolean gameRestarted = false;
    private Position inputStartPosition;
    private Position inputTargetPosition;

    public Game() {
        gameTurn = 0;
        gameBoard = new Board(players[0], players[1]);
        currentGamePhase = "PLACEMENT";
    }

    public void updateGame(InputState input) {
        ExecutionCode moveStatusCode = ExecutionCode.UNKNOWN;
        Player currentPlayer = players[gameTurn % players.length];

        switch (getCurrentGamePhase()) {
            case "PLACEMENT":
                moveStatusCode = handlePlacementPhase(input, currentPlayer);
                break;
            case "MOVEMENT":
                moveStatusCode = handleMovementPhase(input, currentPlayer);
                break;
            case "REMOVAL":
                moveStatusCode =handleRemovalPhase(input, currentPlayer);
                break;
        }


        if (moveStatusCode == ExecutionCode.SUCCESS) {
            gameTurn++;
            updateCurrentGamePhase();
        }
    }

    public GameState queryGameState() {
        return GameState.getGameStateBuilder()
                .setNextInput(nextInput)
                .setExecutionCode(executionCode)
                // Current Player GameState Variables
                .setCurrentPlayerName(getCurrentPlayer().getName())
                .setCurrentPlayerPiecesRemaining(getCurrentPlayer().getNumOfPiecesRemaining())
                .setCurrentPlayerPiecesOnBoard(getCurrentPlayer().getNumOfPiecesOnBoard())
                .setCurrentPlayerPiecesCaptured(getCurrentPlayer().getNumOfPiecesCaptured())
                .setCurrentPlayerMillsCreated(getCurrentPlayer().getNumOfMillsMade())
                // Opponent Player GameState Variables
                .setOpponentPlayerName(getOpponentPlayer().getName())
                .setOpponentPlayerPiecesOnBoard(getOpponentPlayer().getNumOfPiecesOnBoard())
                .setOpponentPlayerPiecesCaptured(getOpponentPlayer().getNumOfPiecesCaptured())
                .setOpponentPlayerMillsCreated(getOpponentPlayer().getNumOfMillsMade())
                // Meta GameState Variables
                .setGameTurn(gameTurn)
                .setBoard(getGameBoard())
                .build();
    }

    private void setGamePhase(String gamePhase) {
        currentGamePhase = gamePhase;
    }

    private ExecutionCode handlePlacementPhase(InputState input, Player currentPlayer) {
        if (input.inputValues.get(0) == -1)
            return handleUndo();

    private void updateCurrentGamePhase() {
        if (PreviousMovePositions.size() > 0 && gameBoard.isMillFormed(players[(gameTurn - 1) % players.length] , PreviousMovePositions.get(PreviousMovePositions.size() - 1))) {
            setGamePhase("REMOVAL");
            gameTurn--;
        } else if (gameTurn < players.length * PLAYER_STARTING_PIECES) {
            setGamePhase("PLACEMENT");
        } else {
            setGamePhase("MOVEMENT");
        }
    }

    private ExecutionCode handleRemovalPhase(InputState input, Player currentPlayer) {

        ExecutionCode statusCode;
        Position targetPosition = gameBoard.getPosition(input.inputValues.get(0));

        if (gameBoard.isMillFormed(players[(gameTurn+1) % players.length], targetPosition)) {
            statusCode = ExecutionCode.OPPONENT_PIECE_IN_MILL_POSITION;
        }else{
            statusCode = players[gameTurn % players.length].removePiece(gameBoard, targetPosition);
        }

        if (statusCode == ExecutionCode.SUCCESS){
            PreviousMovePositions.clear();
        }


        updatePlayerCanJump(); // update opponent player's canJump status
        updateIsGameOver();
        return statusCode;
    }

    private ExecutionCode handlePlacementPhase(InputState input, Player currentPlayer) {
        Position targetPosition = gameBoard.getPosition(input.inputValues.get(0));
        ExecutionCode statusCode = currentPlayer.makePlaceMove(gameBoard, targetPosition);
        setPreviousMoveInvalid(statusCode != ExecutionCode.SUCCESS);
        setExecutionCode(statusCode);
        this.inputTargetPosition = targetPosition;
        if (statusCode == ExecutionCode.SUCCESS){
            PreviousMovePositions.add(targetPosition);
        }

        return statusCode;
    }

    private ExecutionCode handleMovementPhase(InputState input, Player currentPlayer) {

        if (input.inputValues.get(0) == -1 || input.inputValues.get(1) == -1)
            return handleUndo();

        ExecutionCode statusCode;
        Position startingPosition = gameBoard.getPosition(input.inputValues.get(0));
        Position targetPosition = gameBoard.getPosition(input.inputValues.get(1));

        if (!currentPlayer.getCanJump()) {
            statusCode = currentPlayer.makeAdjacentMove(gameBoard, startingPosition, targetPosition);
        } else {
            statusCode = currentPlayer.makeJumpMove(gameBoard, startingPosition, targetPosition);
        }

        setPreviousMoveInvalid(statusCode != ExecutionCode.SUCCESS);
        setExecutionCode(statusCode);
        this.inputStartPosition = startingPosition;
        this.inputTargetPosition = targetPosition;

        if (statusCode == ExecutionCode.SUCCESS){
            PreviousMovePositions.add(targetPosition);
        }
        return statusCode;
    }

    private ExecutionCode handleRemovalPhase(InputState input, Player currentPlayer) {

        if (input.inputValues.get(2) == -1)
            return handleUndo();

        // TODO: Handle Removal Phase
        updatePlayerCanJump(); // update opponent player's canJump status
        updateIsGameOver();
        return ExecutionCode.REMOVED;

    private void setGamePhase(String gamePhase) {
        currentGamePhase = gamePhase;

    }

    private ExecutionCode handleUndo() {
        // TODO: implement code to handle undo move
        return ExecutionCode.UNDO;
    }

    private void updatePlayerCanJump() {
        if (this.getOpponentPlayer().getNumOfPiecesOnBoard() == 3) {
            this.getOpponentPlayer().setCanJump(true);
        }
    }


    public boolean wasPreviousMoveInvalid() {
        // Returns whether the Previous Game Move was Valid
        return previousMoveInvalid;
    }

    private void setPreviousMoveInvalid(boolean wasInvalid) {
        previousMoveInvalid = wasInvalid;
    }

    private void setExecutionCode(ExecutionCode executionCode) {
        this.executionCode = executionCode;
    }

    public ExecutionCode getExecutionCode() {
        return executionCode;
    }

    public Board getGameBoard() {
        return gameBoard;
    }

    public String getCurrentGamePhase() {
        return currentGamePhase;
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

    public boolean isPreviousRemoveValid() {
        return previousRemoveInvalid;
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
        currentGamePhase = "PLACEMENT";
        gameOver = false;
        gameRestarted = true;
    }
}
