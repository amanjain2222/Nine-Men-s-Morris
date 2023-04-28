public class Game {
    private final Board gameBoard;
    private final Player player1;
    private final Player player2;
    private String currentGamePhase;
    private Player thisPlayerTurn;
    // Keeps Track of whether Previous Game Move was Valid
    private boolean previousMoveInvalid = false;

    public Game() {
        player1 = new Player("Ice", 'I');
        player2 = new Player("Fire", 'F');
        gameBoard = new Board(player1, player2);
        currentGamePhase = "PLACEMENT";
        thisPlayerTurn = player1;
    }

    public void updateGame(Input input) {
        // Process input by using it to interpret the current player's next move.
        if (!isGameOver()) {
            // Take Player Turn or Determine if Player Move was Invalid
            boolean moveStatus = false;
            if (getCurrentGamePhase().equals("PLACEMENT")) {
                Position targetPosition = gameBoard.getPosition(input.inputValues.get(0));
                moveStatus = thisPlayerTurn.makePlaceMove(gameBoard, targetPosition);
                updateCurrentGamePhase();
            } else if (getCurrentGamePhase().equals("MOVEMENT")) {
                Position startingPosition = gameBoard.getPosition(input.inputValues.get(0));
                Position targetPosition =  gameBoard.getPosition(input.inputValues.get(1));
                moveStatus = thisPlayerTurn.makeAdjacentMove(gameBoard, startingPosition, targetPosition);
            }
            setPreviousMoveInvalid(!moveStatus);
            if (!moveStatus) return;

            // Only if Move Was Valid Update Game Globals
            switchTurn();
            updateCurrentGamePhase();
        }
    }

    public void switchTurn() {
        // Switch the player whose turn it is
        if (this.thisPlayerTurn == this.player1) {
            this.thisPlayerTurn = this.player2;
        } else {
            this.thisPlayerTurn = this.player1;
        }
    }

    private void updateCurrentGamePhase() {
        // Set the current game phase
        if (this.player1.getNumOfPiecesRemaining() > 0 || this.player2.getNumOfPiecesRemaining() > 0) {
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

    public Player getPlayer1() {
        // Return player 1
        return this.player1;
    }

    public Player getPlayer2() {
        // Return player 2
        return this.player2;
    }

    public Board getGameBoard() {
        // Return the game board
        return this.gameBoard;
    }

    public String getCurrentGamePhase() {
        // Return the current game phase
        return this.currentGamePhase;
    }

    public Player getThisPlayerTurn() {
        // Return the player whose turn it is
        return this.thisPlayerTurn;
    }

    public void setThisPlayerTurn(Player player) {
        // Set the player whose turn it is
        this.thisPlayerTurn = player;
    }

    private boolean isGameOver() {
        //TODO: Implement Code for checking if the game is over
        return false;
    }

}
