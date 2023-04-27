public abstract class Move extends Action {
    protected final Piece piece;
    protected final Player player;
    protected final Board board;

    public Move(Player player, Board board, Piece piece) {
        this.piece = piece;
        this.player = player;
        this.board = board;
    }

    protected boolean isValidStartPosition(int startPosition) {
        // check if the Start position exist on the board
        if (startPosition <0 || startPosition > 23) {
            System.out.println("The start position you entered is not a valid position on the board!");
            return false;
        }
        // Check if the Start position is empty
        else if (board.getPosition(startPosition).isEmpty()){
            System.out.println("There is no piece there!");
            return false;
        }
        // Check if the piece belongs to the player
        else if (board.getPosition(startPosition).getPieceOccupying().getOwner() != player){
            System.out.println("That piece does not belong to you!");
            return false;
        }
        else {
            return false;
        }
    }

    protected boolean isValidEndPosition(int endPosition) {
        // check if the End position exist on the board
        if (endPosition <0 || endPosition > 23) {
            System.out.println("The end position you entered is not a valid position on the board!");
            return false;
        }
        // Check if the End position is empty
        else if (!board.getPosition(endPosition).isEmpty()){
            System.out.println("There is already a piece there!");
            return false;
        }
        else {
            return false;
        }
    }

    protected boolean isMillFormed() {
        // TODO: Check if a mill is formed
        return false;
    }

    protected boolean removePieceAfterMillIsFormed() {
        // TODO: Remove a piece from the opponent if a mill is formed
        return true;
    }

    @Override
    public boolean execute(int startPosition, int endPosition) {
        // validate the start position and end position
        if (isValidStartPosition(startPosition) && isValidEndPosition(endPosition)) {
            // move the piece
            board.setPositionToEmpty(startPosition); // set the start position to empty
            board.setPositionToPlayer(endPosition, piece); // set the end position to the player
            board.changePlayerPiecesOnBoardPosition(startPosition, endPosition, piece); // change the player's pieces on board positions
            getConsoleDescription(startPosition, endPosition); // print the console description
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public String getConsoleDescription(int startPosition, int endPosition) {
        return player.getName() + " moved a piece from " + startPosition + " to " + endPosition + ".";
    }
}

