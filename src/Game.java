import java.util.ArrayList;
import java.util.Objects;

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
        while(!isGameOver()) {
            if (Objects.equals(getCurrentGamePhase(), "PLACEMENT")) {
                this.performPlacementPhase();
                this.setCurrentGamePhase();
            } else if (Objects.equals(getCurrentGamePhase(), "MOVEMENT")) {
                this.performMovementPhase();
            }
        }
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

    private String getCurrentGamePhase() {
        // Return the current game phase
        return this.currentGamePhase;
    }

    private void setCurrentGamePhase() {
        // Set the current game phase
        if (this.player1.getNumOfPiecesRemaining() > 0 || this.player2.getNumOfPiecesRemaining() > 0) {
            this.currentGamePhase = "PLACEMENT";
        }
        else {
            this.currentGamePhase = "MOVEMENT";
        }
    }

    public Player getThisPlayerTurn() {
        // Return the player whose turn it is
        return this.thisPlayerTurn;
    }

    public void setThisPlayerTurn(Player player) {
        // Set the player whose turn it is
        this.thisPlayerTurn = player;
    }

    public void switchTurn() {
        // Switch the player whose turn it is
        if (this.thisPlayerTurn == this.player1) {
            this.thisPlayerTurn = this.player2;
        } else {
            this.thisPlayerTurn = this.player1;
        }
    }

    private void performPlacementPhase() {
        //TODO: Implement Logic Code for placing pieces at the start of the game
    }

    private void performMovementPhase() {
        while (Objects.equals(this.getCurrentGamePhase(), "MOVEMENT") && !this.isGameOver()) {
            this.thisPlayerTurn.getAdjacentMove(this.gameBoard);
            this.switchTurn();
            // ToDo: Implement Code for checking whether it is a adjacent move or a flying move
        }
    }

    private boolean isGameOver() {
        //TODO: Implement Code for checking if the game is over
        return false;
    }

}
