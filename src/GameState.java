// Information about a game's state including it's enumerated phase.
// Acts as a view layer for the game.
public class GameState {
    private GameStatus gameStatus;
    private MoveStatus moveStatus;
    private PlayerData currentPlayerData;
    private PlayerData opponentPlayerData;
    private int gameTurn;
    private char[] board;
    private GameHandlerStatus gameHandlerStatus;

    public GameState(Game game) {
        this.gameStatus = game.getGameStatus();
        this.moveStatus = game.getMoveStatus();
        this.currentPlayerData = new PlayerData(game.getCurrentPlayer());
        this.opponentPlayerData = new PlayerData(game.getOpponentPlayer());
        this.gameTurn = game.getGameTurn();
        this.board = game.getBoard().toCharArray();
    }

    public GameState(GameHandlerStatus gameHandlerStatus) {
        this.gameHandlerStatus = gameHandlerStatus;
    }

    public static class PlayerData {
        private String name;
        private int totalPieces;
        private int piecesLeftToPlace;
        private int millsCreated;

        private PlayerData(Player player) {
            this.name = player.getName();
            this.totalPieces = player.getTotalPieces();
            this.piecesLeftToPlace = player.getPiecesLeftToPlace();
            this.millsCreated = player.getNumOfMillsMade();
        }

        public String getName() {
            return name;
        }

        public int getTotalPieces() {
            return totalPieces;
        }

        public int getPiecesLeftToPlace() {
            return piecesLeftToPlace;
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

    public void setGameHandlerStatus(GameHandlerStatus gameHandlerStatus) {
        this.gameHandlerStatus = gameHandlerStatus;
    }
    
    public GameHandlerStatus getGameHandlerStatus() {
        return gameHandlerStatus;
    }
}
