public class Player {
    public static final int STARTING_PIECES = 9;

    private final String name;
    private final char displayChar;

    private int totalPieces = 9;
    private int piecesLeftToPlace = 9;

    private int numOfMillsMade = 0;

    public Player(String name, char displayChar) {
        this.name = name;
        this.displayChar = displayChar;
    }

    public MoveStatus takeMovementTurn(Position startPosition, Position targetPosition) {
        MoveStatus moveStatus = totalPieces == 3 ? new MoveJump(this, startPosition, targetPosition).execute()
                : new MoveAdjacent(this, startPosition, targetPosition).execute();
        numOfMillsMade += moveStatus == MoveStatus.SUCCESS_MILL_FORMED ? 1 : 0;
        return moveStatus;
    }

    public MoveStatus takePlaceTurn(Position targetPosition) {
        MoveStatus moveStatus = new MovePlace(this, targetPosition).execute();
        piecesLeftToPlace -= !moveStatus.IS_INVALID ? 1 : 0;
        numOfMillsMade += moveStatus == MoveStatus.SUCCESS_MILL_FORMED ? 1 : 0;
        return moveStatus;
    }

    public MoveStatus takeRemoveTurn(Position targetPosition) {
        return new MoveRemove(this, targetPosition).execute();
    }

    public String getName() {
        return name;
    }

    public char getDisplayChar() {
        return displayChar;
    }

    public int getTotalPieces() {
        return totalPieces;
    }

    public void decreaseTotalPieces() {
        totalPieces--;
    }

    public int getPiecesLeftToPlace() {
        return piecesLeftToPlace;
    }

    public int getNumOfMillsMade() {
        return numOfMillsMade;
    }

    public void loadPlayerData(GameState.PlayerData playerData) {
        totalPieces = playerData.getTotalPieces();
        piecesLeftToPlace = playerData.getPiecesLeftToPlace();
        numOfMillsMade = playerData.getMillsCreated();
    }

    public void loadPieceData(Position[] positions, char[] charArray) {
        for (int i = 0; i < charArray.length; i++) {
            if (charArray[i] == getDisplayChar()) {
                positions[i].setPieceOccupying(new Piece(this));
            }
        }
    }
}
