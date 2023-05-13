public class Game {
    private final int PLAYER_STARTING_PIECES = 9;
    private final String FIRST_PLAYER_NAME = "Ice";
    private final Character FIRST_PLAYER_CHAR = 'I';
    private final String SECOND_PLAYER_NAME = "Fire";
    private final Character SECOND_PLAYER_CHAR = 'F';
    private final Player[] players = {new Player(FIRST_PLAYER_NAME, FIRST_PLAYER_CHAR), new Player(SECOND_PLAYER_NAME, SECOND_PLAYER_CHAR)};

    private final Board gameBoard;

    private int gameTurn;

    private String currentGamePhase;
    // Keeps Track of whether Previous Game Move was Valid
    private boolean previousMoveInvalid = false;
    private ErrorCode errorCode;
    private String moveConsoleDescription = "";

    public Game() {
        gameTurn = 0;
        gameBoard = new Board(players[0], players[1]);
        currentGamePhase = "PLACEMENT";

    }

    public void updateGame(InputState input) {
        // Take Player Turn or Determine if Player Move was Invalid
        ErrorCode moveStatusCode = ErrorCode.UNKNOWN;
        String moveDescription = "";

        if (getCurrentGamePhase().equals("PLACEMENT")) {
            Position targetPosition = gameBoard.getPosition(input.inputValues.get(0));
            moveStatusCode = players[gameTurn % players.length].makePlaceMove(gameBoard,targetPosition);
            moveDescription = players[gameTurn % players.length].getName() + " Player placed a piece at Position "
                    + targetPosition.getPositionNumber() + ".";

        } else if (getCurrentGamePhase().equals("MOVEMENT")) {
            Position startingPosition = gameBoard.getPosition(input.inputValues.get(0));
            Position targetPosition =  gameBoard.getPosition(input.inputValues.get(1));
            moveStatusCode = players[gameTurn % players.length].makeAdjacentMove(startingPosition, targetPosition);
            moveDescription = players[gameTurn % players.length].getName() + " Player moved a piece from Position "
                    + startingPosition.getPositionNumber() + " to Position " + targetPosition.getPositionNumber() + ".";
        }

        setPreviousMoveInvalid(moveStatusCode != ErrorCode.SUCCESS);
        setErrorCode(moveStatusCode);
        setMoveConsoleDescription(moveDescription);

        if (moveStatusCode != ErrorCode.SUCCESS) return;

        // Only if Move Was Valid Update Game Globals
        gameTurn++;
        updateCurrentGamePhase();
    }

    private void updateCurrentGamePhase() {
        // Set the current game phase
        if (gameTurn < players.length * PLAYER_STARTING_PIECES) {
            this.currentGamePhase = "PLACEMENT";
        }
        else {
            this.currentGamePhase = "MOVEMENT";
        }
    }

    public boolean wasPreviousMoveInvalid() {
        // Returns whether or not the Previous Game Move was Valid
        return previousMoveInvalid;
    }

    private void setPreviousMoveInvalid(boolean wasInvalid) {
        previousMoveInvalid = wasInvalid;
    }

    private void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public String getMoveConsoleDescription() {
        return moveConsoleDescription;
    }

    public void setMoveConsoleDescription(String moveConsoleDescription) {
        this.moveConsoleDescription = moveConsoleDescription;
    }

    public Board getGameBoard() {
        return gameBoard;
    }

    public String getCurrentGamePhase() {
        return currentGamePhase;
    }

    public int getGameTurn() {
        return gameTurn;
    }

    public Player getCurrentPlayer() {
        return players[gameTurn % players.length];
    }
}
