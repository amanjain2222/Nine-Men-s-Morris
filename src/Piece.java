public class Piece {
    private final char displayChar;
    private final Player owner;

    public Piece(Player owner) {
        this.owner = owner;
        this.displayChar = owner.getDisplayChar();
    }

    public Player getOwner() {
        // return the owner of the piece
        return owner;
    }

    public char getDisplayChar() {
        // return the display character of the piece
        return displayChar;
    }
}
