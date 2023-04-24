public abstract class Action {
    public abstract boolean execute(Board board, Player player1, Player player2);
    public abstract boolean isValid(Board board, Player player1, Player player2);
    public abstract String getConsoleDescription();
    public abstract Action getNextAction();
}