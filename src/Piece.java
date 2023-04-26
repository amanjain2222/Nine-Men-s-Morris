public class Piece {
    private final char displayChar;
    private final Player owner;
    private int position;
    private boolean placedOnBoard;
    private boolean removedFromBoard;

    public Piece(char displayCharacter, Player owner) {
        this.displayChar = displayCharacter;
        this.owner = owner;
        this.placedOnBoard = false;
        this.removedFromBoard = false;
        this.position = -1;
    }

    public Player getOwner() {
        // return the owner of the piece
        return owner;
    }

    public char getDisplayChar() {
        // return the display character of the piece
        return displayChar;
    }

    public int getPosition() {
        // return the position of the piece
        return position;
    }

    public void setPosition(int position) {
        // set the position of the piece
        this.position = position;
    }

    public boolean isPlacedOnBoard() {
        // check if the piece is placed on the board
        return placedOnBoard;
    }

    public void setPlacedOnBoard(int position) {
        // set the piece to be placed on the board and update the position
        this.position = position;
        this.placedOnBoard = true;
    }

    public boolean isRemovedFromBoard() {
        // check if the piece is removed from the board
        return removedFromBoard;
    }

    public void setRemovedFromBoard() {
        // set the piece to be removed from the board and change the position
        this.position = -1;
        this.removedFromBoard = true;
    }
}
