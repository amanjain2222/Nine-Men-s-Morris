import java.util.ArrayList;

public class Game {
    private final Board gameBoard;
    private final Player player1;
    private final Player player2;
    private String currentGamePhase;
    private Player thisPlayerTurn;

    public Game() {
        player1 = new Player("Ice", 'I');
        player2 = new Player("Fire", 'F');
        gameBoard = new Board(player1,player2);
        currentGamePhase = "PLACEMENT";
        thisPlayerTurn = player1;
    }

    public void play() {
        //ToDo: Implement Code
    }

    public Player getPlayer1() {
        // Return player 1
        return player1;
    }

    public Player getPlayer2() {
        // Return player 2
        return player2;
    }

    public Board getGameBoard() {
        // Return the game board
        return gameBoard;
    }

    public String getCurrentGamePhase() {
        // Return the current game phase
        return currentGamePhase;
    }

    public void setCurrentGamePhase(String gamePhase) {
        // Set the current game phase
        currentGamePhase = gamePhase;
    }

    public Player getThisPlayerTurn() {
        // Return the player whose turn it is
        return thisPlayerTurn;
    }

    public void setThisPlayerTurn(Player player) {
        // Set the player whose turn it is
        thisPlayerTurn = player;
    }

    public void switchTurn() {
        // Switch the player whose turn it is
        if (thisPlayerTurn == player1) {
            thisPlayerTurn = player2;
        } else {
            thisPlayerTurn = player1;
        }
    }

    private void performPlacementPhase() {
        //TODO: Implement Logic Code for placing pieces at the start of the game
    }

    private void performMovementPhase() {
        //TODO: Implement Logic Code for moving pieces after pieces is placed
    }

    private void performMovement() {
        //TODO: Implement Code for moving pieces
    }

    private int moveThisPieceFromTo(int fromIndex, int toIndex) {
        //TODO: Implement Code for moving a piece from one position to another
        return 0;
    }

    public int getNumOfPlayerPiecesLeftToMove(Player player) {
        //TODO: Implement Code for getting the number of pieces left to move for a player
        return 0;
    }

    private boolean isGameOver() {
        //TODO: Implement Code for checking if the game is over
        return false;
    }

}
