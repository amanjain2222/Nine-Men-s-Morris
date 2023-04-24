public abstract class Move extends Action {
    protected final Piece piece;
    protected final int startPosition;
    protected final int endPosition;

    public Move(Piece piece, int startPosition, int endPosition) {
        this.piece = piece;
        this.startPosition = startPosition;
        this.endPosition = endPosition;
    }

    protected boolean isValidOriginPosition(Board board, Player player1, Player player2) {
        //TODO: Check if the from position is valid
        return true;

    }

    protected boolean isEmptyDestinationPosition(Board board, Player player1, Player player2) {
        // Check if the to position is empty
        return board.getPosition(endPosition).isEmpty();
    }

    protected boolean isMillFormed(Board board, Player player1, Player player2) {
        // TODO: Check if a mill is formed
        return false;
    }

    protected boolean removePiece(Board board, Player player1, Player player2) {
        // TODO: Remove a piece from the opponent if a mill is formed
        return true;
    }
}