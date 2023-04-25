public abstract class Move extends Action {
    protected final Piece piece;
    protected final int startPosition;
    protected final int endPosition;

    public Move(Piece piece, int startPosition, int endPosition) {
        this.piece = piece;
        this.startPosition = startPosition;
        this.endPosition = endPosition;
    }

    protected boolean isValidOriginPosition(Board board, Player player) {
        //TODO: Check if the from position is valid
        if !board.getPosition(endPosition).isEmpty(){
            if board.getPosition(endPosition).getPieceOccupying() == player.getDisplayChar(){
                return true
            }
        }

        return false
    }

//    protected boolean isValidOriginPosition(Board board, Player player1, Player player2) {
//        //TODO?: Check if the from position is valid
//
//
//    }


    // Do we need Player1 and Player2 as parameters here??
    // Do we need both players as inputs in all of these functions??


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

