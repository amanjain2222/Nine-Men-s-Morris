public class Piece {
    private final char displayChar;
    private final Player owner;

    public Piece(char displayCharacter, Player owner) {
        this.displayChar = displayCharacter;
        this.owner = owner;
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
