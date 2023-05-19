public class Player {
    private final String name;
    private final char displayChar;
    private int numOfPiecesRemaining;
    private int numOfPiecesOnBoard;
    private int numOfPiecesCaptured;
    private int numOfMillsMade;

    public Player(String name, char displayChar) {
        this.name = name;
        this.displayChar = displayChar;
        numOfPiecesOnBoard = 0;
        numOfPiecesRemaining = 9;
        numOfPiecesCaptured = 0;
        numOfMillsMade = 0;
    }

    public MoveStatus movePiece(Board board, Position startPosition, Position targetPosition) {
        if (numOfPiecesRemaining == 0 && numOfPiecesOnBoard == 3){
            MoveStatus moveStatus = new MoveJump(board, this, startPosition, targetPosition).execute();
            numOfMillsMade += moveStatus == MoveStatus.MILL_FORMED ? 1 : 0;
            return moveStatus;
        }
        MoveStatus moveStatus = new MoveAdjacent(board, this, startPosition, targetPosition).execute();
        numOfMillsMade += moveStatus == MoveStatus.MILL_FORMED ? 1 : 0;
        return moveStatus;
    }

    public MoveStatus makePlaceMove(Board board, Position targetPosition) {
        MoveStatus moveStatus = new MovePlace(board,this, targetPosition).execute();
        numOfMillsMade += moveStatus == MoveStatus.MILL_FORMED ? 1 : 0;
        return moveStatus;
    }

    public MoveStatus removePiece(Board board,Position targetPosition){
        return new RemovePiece(board, this, targetPosition).execute();
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
}
