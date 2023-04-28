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

    public Game() {
        gameTurn = 0;
        gameBoard = new Board(players[0], players[1]);
        currentGamePhase = "PLACEMENT";
    }

    public void updateGame(InputState input) {
        // Process input by using it to interpret the current player's next move.
        if (!isGameOver()) {
            // Take Player Turn or Determine if Player Move was Invalid
            boolean moveStatus = false;
            if (getCurrentGamePhase().equals("PLACEMENT")) {
                Position targetPosition = gameBoard.getPosition(input.inputValues.get(0));
                moveStatus = players[gameTurn % players.length].makePlaceMove(gameBoard, targetPosition);
            } else if (getCurrentGamePhase().equals("MOVEMENT")) {
                Position startingPosition = gameBoard.getPosition(input.inputValues.get(0));
                Position targetPosition =  gameBoard.getPosition(input.inputValues.get(1));
                moveStatus = players[gameTurn % players.length].makeAdjacentMove(gameBoard, startingPosition, targetPosition); 
            }
            setPreviousMoveInvalid(!moveStatus);
            if (!moveStatus) return;

            // Only if Move Was Valid Update Game Globals
            gameTurn++;
            updateCurrentGamePhase();
        }
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
        this.previousMoveInvalid = wasInvalid;
    }

    public Board getGameBoard() {
        // Return the game board
        return this.gameBoard;
    }

    public String getCurrentGamePhase() {
        // Return the current game phase
        return this.currentGamePhase;
    }

    private boolean isGameOver() {
        //TODO: Implement Code for checking if the game is over
        return false;
    }

    public int getGameTurn() {
        return gameTurn;
    }

    public Player getCurrentPlayer() {
        return players[gameTurn % players.length];
    }
}
