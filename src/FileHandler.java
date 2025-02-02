import java.io.File;
import java.util.ArrayList;
import java.util.List;

public interface FileHandler {
    public GameState[] getFileGameStates(String filepath);
    public boolean saveFileGameStates(String filepath, GameState[] gameStates);
    public static String[] getSavedFileNames() {
        File folder = new File("Saved/");
        File[] listOfFiles = folder.listFiles();
        if (listOfFiles == null) {
            return new String[0];
        }
        List<String> fileNames = new ArrayList<>();
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                String filename = listOfFiles[i].getName();
                // Remove extension from filename so only the name is displayed
                int dotIndex = filename.lastIndexOf(".");
                if (dotIndex > 0) {
                    filename = filename.substring(0, dotIndex);
                }
                fileNames.add(filename);
            }
        }
        return fileNames.toArray(new String[0]);
    }
    public static boolean checkFileExists(String filepath) {
        File file = new File("Saved/" + filepath + ".txt");
        return file.isFile();
    }
}
