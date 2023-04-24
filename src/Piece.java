public class Piece {
    private final char displayChar;
    private final Player owner;
    private Position position;
    private boolean placedOnBoard;
    private boolean removedFromBoard;

    public Piece(char displayCharacter, Player owner) {
        this.displayChar = displayCharacter;
        this.owner = owner;
        this.placedOnBoard = false;
        this.removedFromBoard = false;
    }

    public Player getOwner() {
        return owner;
    }

    public char getDisplayChar() {
        return displayChar;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public boolean isPlacedOnBoard() {
        return placedOnBoard;
    }

    public void setPlacedOnBoard() {
        this.placedOnBoard = true;
    }

    public boolean isRemovedFromBoard() {
        return removedFromBoard;
    }

    public void setRemovedFromBoard() {
        this.removedFromBoard = true;
    }
}
