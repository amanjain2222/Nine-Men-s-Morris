import java.util.ArrayList;

public class Game {
    private final int PLAYER_STARTING_PIECES = 9;

    private final String FIRST_PLAYER_NAME = "Ice";
    private final Character FIRST_PLAYER_CHAR = 'I';
    private final String SECOND_PLAYER_NAME = "Fire";
    private final Character SECOND_PLAYER_CHAR = 'F';
    private final Player[] players = {new Player(FIRST_PLAYER_NAME, FIRST_PLAYER_CHAR), new Player(SECOND_PLAYER_NAME, SECOND_PLAYER_CHAR)};

    private final Board gameBoard;

    private int gameTurn;

    private ArrayList<Position> PreviousMovePositions = new ArrayList<>();

    private String currentGamePhase;
    // Keeps Track of whether Previous Game Move was Valid
    private boolean previousMoveInvalid = false;

    public Game() {
        gameTurn = 0;
        gameBoard = new Board(players[0], players[1]);
        currentGamePhase = "PLACEMENT";
    }

    public void updateGame(InputState input) {
        // Take Player Turn or Determine if Player Move was Invalid
        boolean moveStatus = false;
        if (getCurrentGamePhase().equals("PLACEMENT")) {
            Position targetPosition = gameBoard.getPosition(input.inputValues.get(0));
            moveStatus = players[gameTurn % players.length].makePlaceMove(targetPosition);
            if (moveStatus){
                PreviousMovePositions.add(targetPosition);
            }


        } else if (getCurrentGamePhase().equals("MOVEMENT")) {
            Position startingPosition = gameBoard.getPosition(input.inputValues.get(0));
            Position targetPosition =  gameBoard.getPosition(input.inputValues.get(1));
            moveStatus = players[gameTurn % players.length].makeAdjacentMove(startingPosition, targetPosition);
            if (moveStatus){
                PreviousMovePositions.add(targetPosition);
            }


        }  else if (getCurrentGamePhase().equals("Remove")){
            Position targetPosition =  gameBoard.getPosition(input.inputValues.get(0));
            if (gameBoard.isMill(players[(gameTurn+1) % players.length], targetPosition)){
                moveStatus = false;
            }else{
                moveStatus = players[gameTurn % players.length].removePiece(targetPosition);
            }
0

            if (moveStatus){
                PreviousMovePositions.clear();
            }
        }

        setPreviousMoveInvalid(!moveStatus);
        if (!moveStatus) return;

        // Only if Move Was Valid Update Game Globals

        gameTurn++;
        updateCurrentGamePhase();
    }

    private void updateCurrentGamePhase() {
        // Set the current game phase

        if (PreviousMovePositions.size() > 0 && gameBoard.isMill(players[(gameTurn - 1) % players.length] , PreviousMovePositions.get(PreviousMovePositions.size() - 1))) {
            this.currentGamePhase = "Remove";
            gameTurn--;

        }else if (gameTurn >= players.length * PLAYER_STARTING_PIECES) {
            this.currentGamePhase = "MOVEMENT";

        }else{
            this.currentGamePhase = "PLACEMENT";
        }

    }


    public boolean wasPreviousMoveInvalid() {
        // Returns whether or not the Previous Game Move was Valid
        return previousMoveInvalid;
    }

    private void setPreviousMoveInvalid(boolean wasInvalid) {
        previousMoveInvalid = wasInvalid;
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
