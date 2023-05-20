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
        if (totalPieces == 3){
            MoveStatus moveStatus = new MoveJump(this, startPosition, targetPosition).execute();
            numOfMillsMade += moveStatus == MoveStatus.MILL_FORMED ? 1 : 0;
            return moveStatus;
        }
        MoveStatus moveStatus = new MoveAdjacent(this, startPosition, targetPosition).execute();
        numOfMillsMade += moveStatus == MoveStatus.MILL_FORMED ? 1 : 0;
        return moveStatus;
    }

    public MoveStatus takePlaceTurn(Position targetPosition) {
        MoveStatus moveStatus = new MovePlace(this, targetPosition).execute();
        piecesLeftToPlace -= !moveStatus.IS_INVALID ? 1 : 0;
        numOfMillsMade += moveStatus == MoveStatus.MILL_FORMED ? 1 : 0;
        return moveStatus;
    }

    public MoveStatus takeRemoveTurn(Position targetPosition){
        return new RemovePiece(this, targetPosition).execute();
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
}
