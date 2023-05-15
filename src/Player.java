public class Player {
    private final String name;
    private final char displayChar;
    private int numOfPiecesRemaining;
    private int numOfPiecesOnBoard;

    public Player(String name, char displayChar) {
        this.name = name;
        this.displayChar = displayChar;
        numOfPiecesOnBoard = 0;
        numOfPiecesRemaining = 9;
    }

    public ExecutionCode makeAdjacentMove(Position startPosition, Position targetPosition) {
        return new MoveAdjacent(this, startPosition, targetPosition).execute();
    }

    public ExecutionCode makePlaceMove(Board board, Position targetPosition) {
        // Return any potential invalid status from the move so it can be used to update the GameState.
        return new MovePlace(board,this, targetPosition).execute();
    }

    public boolean removePiece(Position targetPosition){
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
}
