

public class AdjacentMove extends Move {
    public AdjacentMove(Player player, Board board, Piece piece) {
        super(player, board, piece);
    }

    protected boolean isAnAdjacentPosition(int startPosition, int endPosition) {
        // Check if the end position is adjacent to the start position
        if (this.board.isAdjacent(startPosition, endPosition)) {
            return true;
        }
        else {
            System.out.println("The end position is not adjacent to the start position!");
            return false;
        }
    }

    @Override
    public boolean execute(int startPosition, int endPosition) {
        // Check if the move is valid
        if (isValidStartPosition(startPosition) && isValidEndPosition(endPosition) && isAnAdjacentPosition(startPosition, endPosition)) {
            // Place the piece on the board
            board.getPosition(endPosition).setPieceOccupying(piece);
            // Remove the piece from the player's remaining pieces
            player.decreaseNumOfPiecesOnBoard();
            // Check if a mill is formed
            if (isMillFormed()) {
                // Remove a piece from the opponent
                removePieceAfterMillIsFormed();
            }
            return true;
        }
        else {
            return false;
        }
    }
}