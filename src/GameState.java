// Information about a game's state including it's enumerated phase.
// Acts as a view layer for the game.
public class GameState {
    private GameStatus gameStatus;
    private MoveStatus moveStatus;
    private PlayerData currentPlayerData;
    private PlayerData opponentPlayerData;
    private int gameTurn;
    private char[] board;

    public GameState(Game game) {
        this.gameStatus = game.getGameStatus();
        this.moveStatus = game.getMoveStatus();
        this.currentPlayerData = new PlayerData(game.getCurrentPlayer());
        this.opponentPlayerData = new PlayerData(game.getOpponentPlayer());
        this.gameTurn = game.getGameTurn();
        this.board = game.getGameBoard().toCharArray();
    }

    public static class PlayerData {
        private String name;
        private int piecesRemaining;
        private int piecesOnBoard;
        private int piecesCaptured;
        private int millsCreated;

        private PlayerData(Player player) {
            this.name = player.getName();
            this.piecesRemaining = player.getNumOfPiecesRemaining();
            this.piecesOnBoard = player.getNumOfPiecesOnBoard();
            this.piecesCaptured = player.getNumOfPiecesCaptured();
            this.millsCreated = player.getNumOfMillsMade();
        }

        public String getName() {
            return name;
        }

        public int getPieceRemaining() {
            return piecesRemaining;
        }

        public int getPieceOnBoard() {
            return piecesOnBoard;
        }

        public int getPiecesCaptured() {
            return piecesCaptured;
        }

        public int getMillsCreated() {
            return millsCreated;
        }
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public MoveStatus getMoveStatus() {
        return moveStatus;
    }

    public PlayerData getCurrentPlayerData() {
        return currentPlayerData;
    }

    public PlayerData getOpponentPlayerData() {
        return opponentPlayerData;
    }

    public int getGameTurn() {
        return gameTurn;
    }

    public char[] getBoard() {
        return board;
    }
}
