// Information about a game's state including it's enumerated phase.
public class GameState {
    private GameStatus gameStatus;
    private MoveStatus moveStatus;
    private PlayerData currentPlayerData;
    private PlayerData opponentPlayerData;
    private int gameTurn;
    private char[] board;

    private GameState(
            GameStatus gameStatus,
            MoveStatus moveStatus,
            PlayerData currentPlayerData,
            PlayerData opponentPlayerData,
            int gameTurn,
            char[] board) {
        this.gameStatus = gameStatus;
        this.moveStatus = moveStatus;
        this.currentPlayerData = currentPlayerData;
        this.opponentPlayerData = opponentPlayerData;
        this.gameTurn = gameTurn;
        this.board = board;
    }

    public static enum GameStatus {
        AWAITING_PLACEMENT("PLACEMENT"),
        AWAITING_MOVEMENT("MOVEMENT"),
        AWAITING_REMOVAL("REMOVAL"),
        AWAITING_JUMPING("JUMPING"),
        
        GAME_OVER("GAME OVER");

        public final String INPUT_DESCRIPTOR;

        private GameStatus(String inputDescriptor) {
            this.INPUT_DESCRIPTOR = inputDescriptor;
        }
    }

    public static class PlayerData {
        private String name;
        private int piecesRemaining;
        private int piecesOnBoard;
        private int piecesCaptured;
        private int millsCreated;

        private void setName(String name) {
            this.name = name;
        }

        private void setPiecesRemaining(int piecesRemaining) {
            this.piecesRemaining = piecesRemaining;
        }

        private void setPiecesOnBoard(int piecesOnBoard) {
            this.piecesOnBoard = piecesOnBoard;
        }

        private void setPiecesCaptured(int piecesCaptured) {
            this.piecesCaptured = piecesCaptured;
        }

        private void setMillsCreated(int millsCreated) {
            this.millsCreated = millsCreated;
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

    public static GameStateBuilder getGameStateBuilder() {
        return new GameStateBuilder();
    }

    public static class GameStateBuilder {

        private GameStatus gameStatus;
        private MoveStatus moveStatus;
        private PlayerData currentPlayerData;
        private PlayerData opponentPlayerData;
        private int gameTurn;
        private char[] board;

        private GameStateBuilder() {
            currentPlayerData = new PlayerData();
            opponentPlayerData = new PlayerData();
        }

        public GameStateBuilder setGameStatus(GameStatus gameStatus) {
            this.gameStatus = gameStatus;
            return this;
        }

        public GameStateBuilder setMoveStatus(MoveStatus moveStatus) {
            this.moveStatus = moveStatus;
            return this;
        }

        public GameStateBuilder setCurrentPlayerName(String currentPlayerName) {
            this.currentPlayerData.setName(currentPlayerName);
            return this;
        }

        public GameStateBuilder setCurrentPlayerPiecesRemaining(
                int currentPlayerPiecesRemaining) {
            this.currentPlayerData.setPiecesRemaining(currentPlayerPiecesRemaining);
            return this;
        }

        public GameStateBuilder setCurrentPlayerPiecesOnBoard(int currentPlayerPiecesOnBoard) {
            this.currentPlayerData.setPiecesOnBoard(currentPlayerPiecesOnBoard);
            return this;
        }

        public GameStateBuilder setCurrentPlayerPiecesCaptured(int currentPlayerPiecesCaptured) {
            this.currentPlayerData.setPiecesCaptured(currentPlayerPiecesCaptured);
            return this;
        }

        public GameStateBuilder setCurrentPlayerMillsCreated(int currentPlayerMillsCreated) {
            this.currentPlayerData.setMillsCreated(currentPlayerMillsCreated);
            return this;
        }

        public GameStateBuilder setOpponentPlayerName(String opponentPlayerName) {
            this.opponentPlayerData.setName(opponentPlayerName);
            return this;
        }

        public GameStateBuilder setOpponentPlayerPiecesRemaining(int opponentPiecesRemaining) {
            this.opponentPlayerData.setPiecesRemaining(opponentPiecesRemaining);
            return this;
        }

        public GameStateBuilder setOpponentPlayerPiecesOnBoard(int opponentPlayerPiecesOnBoard) {
            this.opponentPlayerData.setPiecesOnBoard(opponentPlayerPiecesOnBoard);
            return this;
        }

        public GameStateBuilder setOpponentPlayerPiecesCaptured(int opponentPlayerPiecesCaptured) {
            this.opponentPlayerData.setPiecesCaptured(opponentPlayerPiecesCaptured);
            return this;
        }

        public GameStateBuilder setOpponentPlayerMillsCreated(int opponentPlayerMillsCreated) {
            this.opponentPlayerData.setMillsCreated(opponentPlayerMillsCreated);
            return this;
        } 

        public GameStateBuilder setGameTurn(int gameTurn) {
            this.gameTurn = gameTurn;
            return this;
        }

        public GameStateBuilder setBoard(char[] board) {
            this.board = board;
            return this;
        }

        public GameState build() {
            return new GameState(
                    gameStatus,
                    moveStatus,
                    currentPlayerData,
                    opponentPlayerData,
                    gameTurn,
                    board);
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
