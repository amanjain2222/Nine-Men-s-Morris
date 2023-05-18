import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class DisplayInterface {
    private static final int STARTING_PIECE_TOTAL = 9;
    private static final char[] PLAYER_CHARS = {'I', 'F'};
    private static final String REMOVE_QUERY_MESSAGE = " Player, Please enter the position of the Opponent's piece you want to remove: ";
    private static final String GAME_TITLE_MESSAGE = "9 Men's Morris Game";
    private static final String PLAYER_TURN_INFO_MESSAGE = " Player, make your move.";
    private static final String GAME_PHASE_MESSAGE = "Current Game Phase: ";
    private static final String PLACEMENT_QUERY_MESSAGE = "Please enter the position on board you want to place a piece: ";
    private static final String CURRENT_PLAYER_MESSAGE = "Current Player Turn: ";
    private static final String PLAYER_TURN_INFO_AGAIN_MESSAGE = " Player, make another move that is valid.";
    private static final String MOVEMENT_FIRST_QUERY_MESSAGE = "Please enter the position of the piece that you want to move: ";
    private static final String MOVEMENT_SECOND_QUERY_MESSAGE = "Please enter the position you want to move the piece to: ";
    final String INVALID_GAME_PHASE = "Error Invalid Game Phase";
    Scanner consoleInput = new Scanner(System.in);

    GameState gameState = null;

    public void updateDisplay(GameState gameState) {
        this.gameState = gameState;
        // Game hasn't started yet.
        if(gameState == null) {
            printStartScreen();
            return;
        }
        // Game is over.
        if (gameState.getNextInput() == GameState.NextInput.GAME_OVER) {
            printEndGameResult();
            return;
        }
        // Game is midway through.
        this.printBoard(gameState.getBoard());
        this.printGamePhaseAndPlayer();
        this.printOtherOptions();
        System.out.println();
    }

    public InputState queryInputState() {
        // No game currently exists, get menu input.
        if (gameState == null) {
            return handleStartGameInputQuery();
        }
        
        // Game is over so handle accordingly.
        if (gameState.getNextInput() == GameState.NextInput.GAME_OVER) {
            return handleEndGameInputQuery();
        }

        this.printMoveDescription();
        String input;

        // Get Input Based On Current Game Phase
        ArrayList<Integer> inputValues = new ArrayList<>();
        InputState alternateInput = null;

        switch (gameState.getNextInput()) {
            case AWAITING_PLACEMENT -> {
                // Get Placement Input
                System.out.print(PLACEMENT_QUERY_MESSAGE);
                input = consoleInput.nextLine();
                alternateInput = checkForRestartOrExitInput(input);
                inputValues.add(transcriptInputValue(input));
            }
            case AWAITING_MOVEMENT -> {
                // Get Start Input
                System.out.print(MOVEMENT_FIRST_QUERY_MESSAGE);
                input = consoleInput.nextLine();
                alternateInput = checkForRestartOrExitInput(input);
                if (alternateInput != null) {
                    return alternateInput;
                }
                inputValues.add(transcriptInputValue(input));
                // Get Target Input
                System.out.print(MOVEMENT_SECOND_QUERY_MESSAGE);
                input = consoleInput.nextLine();
                alternateInput = checkForRestartOrExitInput(input);
                inputValues.add(transcriptInputValue(input));
            }
            case AWAITING_REMOVAL -> {
                // Get Removal Input
                System.out.print(REMOVE_QUERY_MESSAGE);
                input = consoleInput.nextLine();
                alternateInput = checkForRestartOrExitInput(input);
                inputValues.add(transcriptInputValue(input));
            }
            default ->
                System.out.println(INVALID_GAME_PHASE);
        }

        // Return Input
        return alternateInput != null ? alternateInput : new InputState(InputState.InputType.GAME_TURN, inputValues);
    }

    public void printStartScreen() {
        System.out.printf("%nWelcome to %s!%n", GAME_TITLE_MESSAGE);
        System.out.printf("%nOnce the game starts, players will take turns until one wins.%nTo win, reduce their pieces to 2.%n", GAME_TITLE_MESSAGE);
        System.out.println("\nOther Options: ");
        System.out.println("- Exit Game   : Type 'E' or '-2'.");
        System.out.println("- Restart Game: Type 'R' or '-3'.");
    }

    private InputState handleStartGameInputQuery() {
        System.out.print("\nWould you like to start the game? (Y): ");
        String input = consoleInput.nextLine();
        if (input.equalsIgnoreCase("Y")) {
            System.out.println("\nStarting Game... \n.\n.\n.");
            return new InputState(InputState.InputType.GAME_START);
        } else if (input.equalsIgnoreCase("E") || input.equalsIgnoreCase("-2")) {
            return exitGame();
        } else if (input.equalsIgnoreCase("R") || input.equalsIgnoreCase("-3")) {
            return new InputState(InputState.InputType.GAME_START);
        } else {
            System.out.println("Invalid input. Please try again.");
            return handleStartGameInputQuery();
        }
    }

    private InputState checkForRestartOrExitInput(String input) {
        if (input.equals("E") || input.equals("-2")) {
            return exitGame();
        } else if (input.equals("R") || input.equals("-3")) {
            return new InputState(InputState.InputType.GAME_START);
        }
        return null;
    }

    private int transcriptInputValue(String inputValue) {
       if (inputValue.toLowerCase(Locale.ROOT).equals("U")) return -1;
       return Integer.parseInt(inputValue);
    }

    private static InputState exitGame() {
        System.out.println("\nGame has ended.\nThank you for playing!");
        System.out.println("Exiting game...\n.\n.\n.");
        return new InputState(InputState.InputType.GAME_END);
    }

    private void printOtherOptions() {
        System.out.println("\nOther Options: ");
        System.out.println("- Undo Move   : Type 'U' or '-1'");
        System.out.println("- Exit Game   : Type 'E' or '-2'");
        System.out.println("- Restart Game: Type 'R' or '-3'");
    }

    private void printMoveDescription() {
        // Print Previous Input Result
        if (gameState.getExecutionCode().IS_INVALID) {
            System.out.println(getErrorMessage(gameState.getExecutionCode()) + "\n");
            System.out.println(gameState.getCurrentPlayerData().getName() + PLAYER_TURN_INFO_AGAIN_MESSAGE);
        } else {
            System.out.println(getMoveDescription() + "\n");
            System.out.println(gameState.getCurrentPlayerData().getName() + PLAYER_TURN_INFO_MESSAGE);
        }
    }

    private void printGamePhaseAndPlayer() {
        // Print game phase and player turn info
        System.out.println("\n                        " + GAME_PHASE_MESSAGE + gameState.getNextInput().INPUT_DESCRIPTOR);
        System.out.println("                        " + CURRENT_PLAYER_MESSAGE + gameState.getCurrentPlayerData().getName());
    }

    private void printBoard(char[] board) {
        // Construct start position values.
        String firstPlayerStartPositions = "";
        String secondPlayerStartPositions = "";
        for (int i = 0; i < STARTING_PIECE_TOTAL; i++) {
            if (i < (gameState.getGameTurn() % 2 == 0 ? gameState.getCurrentPlayerData().getPieceRemaining() : gameState.getOpponentPlayerData().getPieceRemaining())) {
                firstPlayerStartPositions += "  " + PLAYER_CHARS[0];
            } else {
                firstPlayerStartPositions += "  _";
            }
            if (i < (gameState.getGameTurn() % 2 == 1 ? gameState.getCurrentPlayerData().getPieceRemaining() : gameState.getOpponentPlayerData().getPieceRemaining())) {
                secondPlayerStartPositions += "  " + PLAYER_CHARS[1];
            } else {
                secondPlayerStartPositions += "  _";
            }
        }

        // Print Title + Start Positions + Board
        System.out.println("\n                              " + GAME_TITLE_MESSAGE + "\n");
        System.out.println("                       Ice :" + firstPlayerStartPositions + "\n");
        System.out.print(
                "  0{ " + board[0] + " }---------------------------- 1{ " + board[1] + " }----------------------------- 2{ " + board[2] + " }\n"
                        + "     |                                  |                                   | \n"
                        + "     |                                  |                                   | \n"
                        + "     |       3{ " + board[3] + " }----------------- 4{ " + board[4] + " }------------------ 5{ " + board[5] + " }        | \n"
                        + "     |          |                       |                        |          |    \n"
                        + "     |          |                       |                        |          |    \n"
                        + "     |          |       6{ " + board[6] + " }------ 7{ " + board[7] + " }------- 8{ " + board[8] + " }        |          | \n"
                        + "     |          |          |                          |          |          | \n"
                        + "     |          |          |                          |          |          | \n"
                        + "  9{ " + board[9] + " }--- 10{ " + board[10] + " }--- 11{ " + board[11] + " }                    12{ " + board[12] + " }--- 13{ " + board[13] + " }--- 14{ " + board[14] + " }\n"
                        + "     |          |          |                          |          |          | \n"
                        + "     |          |          |                          |          |          | \n"
                        + "     |          |      15{ " + board[15] + " }----- 16{ " + board[16] + " }------ 17{ " + board[17] + " }        |          |\n"
                        + "     |          |                       |                        |          |    \n"
                        + "     |          |                       |                        |          |    \n"
                        + "     |      18{ " + board[18] + " }---------------- 19{ " + board[19] + " }----------------- 20{ " + board[20] + " }        | \n"
                        + "     |                                  |                                   | \n"
                        + "     |                                  |                                   | \n"
                        + " 21{ " + board[21] + " }--------------------------- 22{ " + board[22] + " }---------------------------- 23{ " + board[23] + " }\n\n");
        System.out.println("                       Fire :" + secondPlayerStartPositions + "\n");
    }

    private String getErrorMessage(ExecutionCode executionCode) {
        return switch (executionCode) {
            case INVALID_NULL -> "Error : The position you have entered does not exist.";
            case INVALID_NOT_EMPTY -> "Error : The position you have entered is not empty.";
            case INVALID_NOT_OWNER -> "Error : The piece you have selected is not yours.";
            case INVALID_NOT_ADJACENT ->
                    "Error : The position you have entered is not adjacent to the piece you have selected.";
            default -> "Error : An unknown error has occurred. Error Code: " + executionCode;
        };
    }

    private String getMoveDescription() {
        String moveDescription = "";
        if (gameState.getGameTurn() == 0) return "Game has Started.";
        // TODO: This is a hardcode to fix null pointer exception when game phase change from Placement to Movement but still need to print one last placement move description description
        if (gameState.getGameTurn() == 18) return gameState.getOpponentPlayerData().getName() + " Player placed a piece.";
        switch (gameState.getNextInput()) {
            case AWAITING_PLACEMENT ->
                    moveDescription = gameState.getOpponentPlayerData().getName() + " Player placed a piece at Position.";
            case AWAITING_MOVEMENT ->
                    moveDescription = gameState.getOpponentPlayerData().getName()  + " Player moved a piece.";
            case AWAITING_REMOVAL ->
                    moveDescription = gameState.getCurrentPlayerData().getName() + " Player removed a piece of Player " + gameState.getOpponentPlayerData().getName() + ".";
            default -> 
                moveDescription = "Unknown Move.";
        }
        return moveDescription;
    }

    private void printEndGameResult() {
        System.out.println("\nGame Over!");
        System.out.println(gameState.getOpponentPlayerData().getName() + " Player has won the game!\n");
        System.out.println("                                          Game Statistics\n");
        System.out.println("                                          Total Turns: " + gameState.getGameTurn() + "\n");
        System.out.printf("                                %s                          %s\n", gameState.getCurrentPlayerData().getName(), gameState.getOpponentPlayerData().getName());
        System.out.printf("Total Pieces Remaining:         %d                            %d\n", gameState.getCurrentPlayerData().getPieceOnBoard(), gameState.getOpponentPlayerData().getPieceOnBoard());
        System.out.printf("Total Pieces Captured:          %d                            %d\n", gameState.getCurrentPlayerData().getPiecesCaptured(), gameState.getOpponentPlayerData().getPiecesCaptured());
        System.out.printf("Total Mills Made:               %d                            %d\n", gameState.getCurrentPlayerData().getMillsCreated(), gameState.getOpponentPlayerData().getMillsCreated());
    }

    private InputState handleEndGameInputQuery() {
        System.out.print("\nWould you like to restart the game? (Y/N): ");
        String input = consoleInput.nextLine();
        if (input.equalsIgnoreCase("Y") || input.equalsIgnoreCase("r")) {
            return new InputState(InputState.InputType.GAME_START);
        } else if (input.equalsIgnoreCase("N") || input.equalsIgnoreCase("e")) {
            return exitGame();
        } else {
            System.out.println("Invalid input. Please try again.");
            return handleEndGameInputQuery();
        }
    }
}
