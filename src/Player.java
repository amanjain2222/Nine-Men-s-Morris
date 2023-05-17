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

    public ExecutionCode makeAdjacentMove(Board board, Position startPosition, Position targetPosition) {
        return new MoveAdjacent(board, this, startPosition, targetPosition).execute();
    }

    public ExecutionCode makePlaceMove(Board board, Position targetPosition) {
        // Return any potential invalid status from the move so it can be used to update the GameState.
        return new MovePlace(board,this, targetPosition).execute();
    }

    public ExecutionCode makeJumpMove(Board board, Position startPosition, Position targetPosition) {
        return new MoveJump(board, this, startPosition, targetPosition).execute();
    }

    public ExecutionCode performRemovePieceAction(Game game, Position targetPosition) {
        // Return any potential invalid status from the move, so it can be used to update the GameState.
        //TODO: Implement Remove Piece action
        return null;
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
