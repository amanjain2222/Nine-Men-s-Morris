public class Piece {
    private final Player owner;

    public Piece(Player owner) {
        this.owner = owner;
    }

    public Player getOwner() {
        return owner;
    }

    public char getDisplayChar() {
        return owner.getDisplayChar();
    }
}
