public abstract class Action {

    public abstract boolean execute(int startPosition, int endPosition);

    public abstract String getConsoleDescription(int startPosition, int endPosition);
}