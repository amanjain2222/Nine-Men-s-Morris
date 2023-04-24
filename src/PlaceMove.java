public class PlaceMove extends Move {

    public PlaceMove(Piece piece, int fromIndex, int toIndex) {
        super(piece, fromIndex, toIndex);
    }

    @Override
    public boolean execute(Board board, Player player1, Player player2) {
        //Todo: Implement Code for PlaceMove
        return false;
    }

    @Override
    public boolean isValid(Board board, Player player1, Player player2) {
        //Todo: Implement Code
        return false;
    }

    @Override
    public String getConsoleDescription() {
        //Todo: Implement Code
        return null;
    }

    @Override
    public Action getNextAction() {
        //Todo: Implement Code
        return null;
    }
}