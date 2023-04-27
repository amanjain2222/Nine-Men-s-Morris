public class PlaceMove extends Move{
    public PlaceMove(Player player, Board board, Piece piece) {
        super(player, board, piece);
    }

    @Override
    public boolean execute(int startPosition, int endPosition) {
        // Check if the move is valid
        if (isValidStartPosition(startPosition) && isValidEndPosition(endPosition)) {
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

    @Override
    public String getConsoleDescription(int startPosition, int endPosition) {
        // Get the description of the move
        return player.getName() + " placed a piece at " + endPosition + ".";
    }

    @Override
    protected boolean isValidStartPosition(int startPosition) {
        // check if the Start position exist on the board
        if (startPosition != -1) {
            System.out.println("The position of the piece is not from remaining pieces!");
            return false;
        }
        // Check if the player has any pieces remaining
        else if (player.getNumOfPiecesRemaining() <0) {
            System.out.println("You have no more pieces to place!");
            return false;
        }
        // Check if the piece belongs to the player
        else if (board.getPlayerAtPosition(startPosition).getOwner() != player){
            System.out.println("That piece does not belong to you!");
            return false;
        }
        else {
            return false;
        }
    }
}