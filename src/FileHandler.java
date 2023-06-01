public interface FileHandler {
    public GameState[] getFileGameStates(String filepath);
    public boolean saveFileGameStates(String filepath, GameState[] gameStates);
    static String[] getSavedFileNames() {
        return new String[0];
    }
}
