public class PlaceMove extends Move{
    public PlaceMove(Player player, Board board, Piece piece) {
        super(player, board, piece);
    }

    @Override
    public boolean execute(int startPosition, int endPosition) {
        // Check if the move is valid
        if (isValidStartPosition(startPosition) && isValidEndPosition(endPosition)) {
            // Place the piece on the board
            Piece piece = this.player.popPiecesRemaining();
            this.board.getPosition(endPosition).setPieceOccupying(piece);
            board.addPlayerPieceToBoard(piece, startPosition);
            // Check if a mill is formed
            if (isMillFormed()) {
                // Remove a piece from the opponent
                removePieceAfterMillIsFormed(); // TODO: go back and implement this method
            }
            return true;
        }
        else {
            return false;
        }
        // TODO: implement code for checking if a mill is formed after moving the piece
    }

    @Override
    public String getConsoleDescription(int startPosition, int endPosition) {
        // Get the description of the move
        return this.player.getName() + " placed a piece at " + endPosition + ".";
    }

    @Override
    protected boolean isValidStartPosition(int startPosition) {
        // check if the Start position exist on the board
        if (startPosition == -1) {
            return true;
        }
        // Check if the player has any pieces remaining
        else if (this.player.getNumOfPiecesRemaining() <0) {
            System.out.println("You have no more pieces to place!");
            return false;
        }
        // Check if the piece belongs to the player
        else if (this.board.getPlayerAtPosition(startPosition).getOwner() != this.player){
            System.out.println("That piece does not belong to you!");
            return false;
        }
        else {
            System.out.println("The position of the piece is not from remaining pieces!");
            return false;
        }
    }
}
