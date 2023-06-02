import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TxtFileHandler implements FileHandler {
    // GameStatus Encoding Hashtables
    private final Dictionary<String, GameStatus> stringToGameStatus = new Hashtable<>();
    private final Dictionary<GameStatus, String> gameStatusToString = new Hashtable<>();

    public TxtFileHandler() {
        // GameStatus Encoding
        stringToGameStatus.put("AWAITING_PLACEMENT", GameStatus.AWAITING_PLACEMENT);
        stringToGameStatus.put("AWAITING_MOVEMENT", GameStatus.AWAITING_MOVEMENT);
        stringToGameStatus.put("AWAITING_REMOVAL", GameStatus.AWAITING_REMOVAL);
        stringToGameStatus.put("GAME_OVER", GameStatus.GAME_OVER);

        gameStatusToString.put(GameStatus.AWAITING_PLACEMENT, "AWAITING_PLACEMENT");
        gameStatusToString.put(GameStatus.AWAITING_MOVEMENT, "AWAITING_MOVEMENT");
        gameStatusToString.put(GameStatus.AWAITING_REMOVAL, "AWAITING_REMOVAL");
        gameStatusToString.put(GameStatus.GAME_OVER, "GAME_OVER");
    }

    @Override
    public GameState[] getFileGameStates(String filepath) {
        List<GameState> gameStates = new ArrayList<>();

        File input = new File("Saved/" + filepath + ".txt");
        try {
            Scanner reader = new Scanner(input);
            while (reader.hasNextLine()) {
                String[] data = reader.nextLine().split(":");
                char[] boardData = Arrays.asList(data[8].split(","))
                        .stream()
                        .collect(Collectors.joining())
                        .toCharArray();
                gameStates.add(new GameState(stringToGameStatus.get(data[0]), MoveStatus.SUCCESS,
                        Integer.parseInt(data[1]), Integer.parseInt(data[2]), Integer.parseInt(data[3]),
                        Integer.parseInt(data[4]), Integer.parseInt(data[5]), Integer.parseInt(data[6]),
                        Integer.parseInt(data[7]), boardData));
            }
            reader.close();
        } catch (FileNotFoundException e) {
            return null;
        }

        GameState[] gameData = new GameState[0];
        return gameStates.toArray(gameData);
    }

    @Override
    public boolean saveFileGameStates(String filepath, GameState[] gameStates) {
        File output = new File("Saved/" + filepath + ".txt");
        try {
            // Override File if it Exists
            if (output.isFile()) {
                output.delete();
            }
            output.createNewFile();

            // Write GameState Data to File
            // Format (Single Line):
            // <gameStatus>:<gameTurn>:
            // <currentPlayerTotalPieces>:<currentPlayerPiecesLeftToPlace>:<currentPlayerMillsCreated>:
            // <opponentPlayerTotalPieces>:<opponentPlayerPiecesLeftToPlace>:<opponentPlayerMillsCreated>:
            // <boardCharArraySpaceSeparatedValues>
            BufferedWriter writer = new BufferedWriter(new FileWriter(output, true));
            for (GameState gameState : gameStates) {
                // <gameStatus>
                writer.write(gameStatusToString.get(gameState.getGameStatus()));
                writer.write(":");
                // <gameTurn>
                writer.write(String.valueOf(gameState.getGameTurn()));
                writer.write(":");
                // <currentPlayerTotalPieces>
                writer.write(String.valueOf(gameState.getCurrentPlayerData().getTotalPieces()));
                writer.write(":");
                // <currentPlayerPiecesLeftToPlace
                writer.write(String.valueOf(gameState.getCurrentPlayerData().getPiecesLeftToPlace()));
                writer.write(":");
                // <currentPlayerMillsCreated>
                writer.write(String.valueOf(gameState.getCurrentPlayerData().getMillsCreated()));
                writer.write(":");
                // <opponentPlayerTotalPieces>
                writer.write(String.valueOf(gameState.getOpponentPlayerData().getTotalPieces()));
                writer.write(":");
                // <opponentPlayerPiecesLeftToPlace>
                writer.write(String.valueOf(gameState.getOpponentPlayerData().getPiecesLeftToPlace()));
                writer.write(":");
                // <opponentPlayerMillsCreated>
                writer.write(String.valueOf(gameState.getOpponentPlayerData().getMillsCreated()));
                writer.write(":");
                // <board>
                for (char character : gameState.getBoard()) {
                    writer.write(character);
                    writer.write(",");
                }
                writer.write(":");

                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }
}