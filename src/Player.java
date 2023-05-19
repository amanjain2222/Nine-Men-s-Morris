public class Player {
    private final String name;
    private final char displayChar;
    private int numOfPiecesRemaining;
    private int numOfPiecesOnBoard;
    private int numOfPiecesCaptured;
    private int numOfMillsMade;
    private boolean canJump;

    public Player(String name, char displayChar) {
        this.name = name;
        this.displayChar = displayChar;
        numOfPiecesOnBoard = 0;
        numOfPiecesRemaining = 9;
        numOfPiecesCaptured = 0;
        numOfMillsMade = 0;
        canJump = false;
    }

    public MoveStatus makeAdjacentMove(Position startPosition, Position targetPosition) {
        MoveStatus moveStatus = new MoveAdjacent(this, startPosition, targetPosition).execute();
        numOfMillsMade += moveStatus == MoveStatus.MILL_FORMED ? 1 : 0;
        return moveStatus;
    }

    public MoveStatus makePlaceMove(Position targetPosition) {
        MoveStatus moveStatus = new MovePlace(this, targetPosition).execute();
        numOfMillsMade += moveStatus == MoveStatus.MILL_FORMED ? 1 : 0;
        return moveStatus;
    }

    public MoveStatus makeJumpMove(Position startPosition, Position targetPosition) {
        MoveStatus moveStatus = new MoveJump(this, startPosition, targetPosition).execute();
        numOfMillsMade += moveStatus == MoveStatus.MILL_FORMED ? 1 : 0;
        return moveStatus;
    }

    public MoveStatus removePiece(Position targetPosition){
        return new RemovePiece(this, targetPosition).execute();
    }

    public String getName() {
        return name;
    }

    public char getDisplayChar() {
        return displayChar;
    }

    public int getNumOfPiecesRemaining() {
        return numOfPiecesRemaining;
    }

    public void increaseNumOfPiecesRemaining() {
        numOfPiecesRemaining++;
    }

    public void decreaseNumOfPiecesRemaining() {
        numOfPiecesRemaining--;
    }

    public int getNumOfPiecesOnBoard() {
        return numOfPiecesOnBoard;
    }

    public void increaseNumOfPiecesOnBoard() {
        numOfPiecesOnBoard++;
    }

    public void decreaseNumOfPiecesOnBoard() {
        numOfPiecesOnBoard--;
    }

    public int getNumOfPiecesCaptured() {
        return numOfPiecesCaptured;
    }

    public void increaseNumOfPiecesCaptured() {
        numOfPiecesCaptured++;
    }

    public int getNumOfMillsMade() {
        return numOfMillsMade;
    }

    public void increaseNumOfMillsMade() {
        numOfMillsMade++;
    }

    public boolean getCanJump() {
        return canJump;
    }

    public void setCanJump(boolean canJump) {
        this.canJump = canJump;
    }
}
