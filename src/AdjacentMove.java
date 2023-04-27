public class AdjacentMove extends Move {
    public AdjacentMove(Player player, Board board, Piece piece) {
        super(player, board, piece);
    }

    protected boolean isAnAdjacentPosition(int startPosition, int endPosition) {
        // Check if the end position is adjacent to the start position
        if (this.board.isAdjacent(startPosition, endPosition)) {
            return true;
        } else {
            System.out.println("The end position is not adjacent to the start position!");
            return false;
        }
    }

    @Override
    public boolean execute(int startPosition, int endPosition) {
        // Check if the move is valid
        if (isValidStartPosition(startPosition) && isValidEndPosition(endPosition) && isAnAdjacentPosition(startPosition, endPosition)) {
            // Move the piece
            board.setPositionToEmpty(startPosition);
            board.setPositionToPlayer(endPosition, piece);
            board.changePlayerPiecesOnBoardPosition(startPosition, endPosition, piece);

            // TODO: add codes to check if a mill is formed

            // Print console description
            getConsoleDescription(startPosition, endPosition);

            return true;
        } else {
            return false;
        }
    }

    @Override
    public String getConsoleDescription(int startPosition, int endPosition) {
        return player.getName() + " moved a piece from " + startPosition + " to " + endPosition + ".";
    }
}
