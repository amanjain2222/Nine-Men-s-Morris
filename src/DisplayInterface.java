import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class DisplayInterface {

    private static final String REMOVE_PIECE_MESSAGE = " Player, Please select a piece to remove: ";
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

    public InputState queryInputState(Game game) {
        this.printMoveDescription(game);
        String input;

        // Get Input Based On Current Game Phase
        ArrayList<Integer> inputValues = new ArrayList<>();
        String gamePhase = game.getCurrentGamePhase();

        switch (gamePhase) {
            case "PLACEMENT" -> {
                // Get Placement Input
                System.out.print(PLACEMENT_QUERY_MESSAGE);
                input = consoleInput.nextLine();
                checkForRestartOrExitInput(input, game);
                inputValues.add(transcriptInputValue(input));
            }
            case "MOVEMENT" -> {
                // Get Start Input
                System.out.print(MOVEMENT_FIRST_QUERY_MESSAGE);
                input = consoleInput.nextLine();
                checkForRestartOrExitInput(input, game);
                inputValues.add(transcriptInputValue(input));
                // Get Target Input
                System.out.print(MOVEMENT_SECOND_QUERY_MESSAGE);
                input = consoleInput.nextLine();
                checkForRestartOrExitInput(input, game);
                inputValues.add(transcriptInputValue(input));
            }
            case "REMOVAL" -> {
                // Get Removal Input
                System.out.print(REMOVE_QUERY_MESSAGE);
                input = consoleInput.nextLine();
                checkForRestartOrExitInput(input, game);
                inputValues.add(transcriptInputValue(input));
            }
            default ->
                //TODO: HANDLE INVALID GAME PHASE
                    System.out.println(INVALID_GAME_PHASE);
        }

        // Return Input
        return new InputState(inputValues);
    }

    public void updateDisplay(Game game) {
        if(game.isGameRestarted()){
            this.printStartScreen(game);
            game.resetGameRestarted();
            updateDisplay(game);
        }else if (!game.getIsGameOver()) {
            this.printBoard(game.getGameBoard());
            this.printGamePhaseAndPlayer(game);
            this.printOtherOptions();
            System.out.println();
        } else if (game.getIsGameOver()){
            this.printEndGameResult(game);
            this.handleEndGameInputQuery(game);
        }
    }

    public void printStartScreen(Game game) {
        System.out.printf("%nWelcome to %s!%n", GAME_TITLE_MESSAGE);
        System.out.printf("%nOnce the game starts, players will take turns until one wins.%nTo win, reduce their pieces to 2.%n", GAME_TITLE_MESSAGE);
        System.out.println("\nOther Options: ");
        System.out.println("- Exit Game   : Type 'E' or '-2'.");
        System.out.println("- Restart Game: Type 'R' or '-3'.");
        handleStartGameInputQuery(game);
    }

    private void handleStartGameInputQuery(Game game) {
        System.out.print("\nWould you like to start the game? (Y): ");
        String input = consoleInput.nextLine();
        if (input.equalsIgnoreCase("Y")) {
            System.out.println("\nStarting Game... \n.\n.\n.");
        } else if (input.equalsIgnoreCase("E") || input.equalsIgnoreCase("-2")) {
            exitGame();
        } else if (input.equalsIgnoreCase("R") || input.equalsIgnoreCase("-3")) {
            restartGame(game);
        } else {
            System.out.println("Invalid input. Please try again.");
            handleStartGameInputQuery(game);
        }
    }

    private void checkForRestartOrExitInput(String input, Game game) {
        if (input.equals("E") || input.equals("-2")) {
            exitGame();
        } else if (input.equals("R") || input.equals("-3")) {
            restartGame(game);
        }
    }

    private int transcriptInputValue(String inputValue) {
       if (inputValue.toLowerCase(Locale.ROOT).equals("U")) return -1;
       return Integer.parseInt(inputValue);
    }

    private static void exitGame() {
        System.out.println("\nGame has ended.\nThank you for playing!");
        System.out.println("Exiting game...\n.\n.\n.");
        System.exit(0);
    }

    private void restartGame(Game game) {
        System.out.println("\nRestarting game...\n.\n.\n.");
        game.restartGame();
        updateDisplay(game);
    }
    private void printOtherOptions() {
        System.out.println("\nOther Options: ");
        System.out.println("- Undo Move   : Type 'U' or '-1'");
        System.out.println("- Exit Game   : Type 'E' or '-2'");
        System.out.println("- Restart Game: Type 'R' or '-3'");
    }

    private void printMoveDescription(Game game) {
        // Print Previous Input Result
        if (game.wasPreviousMoveInvalid()) {
            System.out.println(getErrorMessage(game.getExecutionCode()) + "\n");
            System.out.println(game.getCurrentPlayer().getName() + PLAYER_TURN_INFO_AGAIN_MESSAGE);
        } else if(game.isPreviousMoveUndo()) {
            // TODO: Double check code implementation for handling Undo
            System.out.println(getMoveDescription(game) + "\n");
            System.out.println(game.getCurrentPlayer().getName() + PLAYER_TURN_INFO_MESSAGE);
        } else if (game.isPreviousRemoveValid()) {
            System.out.println(getMoveDescription(game) + "\n");
            System.out.println(game.getCurrentPlayer().getName() + PLAYER_TURN_INFO_MESSAGE);
        } else if(!game.isPreviousRemoveValid()) {
            System.out.println(getMoveDescription(game) + "\n");
            System.out.println(game.getCurrentPlayer().getName() + REMOVE_PIECE_MESSAGE);
        }
    }

    private void printGamePhaseAndPlayer(Game game) {
        // Print game phase and player turn info
        System.out.println("\n                        " + GAME_PHASE_MESSAGE + game.getCurrentGamePhase());
        System.out.println("                        " + CURRENT_PLAYER_MESSAGE + game.getCurrentPlayer().getName());
    }

    private void printBoard(Board board) {
        Position[] boardPositions = board.getBoardPositions();
        Position[] startPositionsP1 = board.getStartPositionsP1();
        Position[] startPositionsP2 = board.getStartPositionsP2();

        // Print Title
        System.out.println("\n                              " + GAME_TITLE_MESSAGE + "\n");

        // Print Player 2 Start Positions
        System.out.println("                       Ice :  " + startPositionToChar(startPositionsP1[0]) + "  " + startPositionToChar(startPositionsP1[1]) + "  " + startPositionToChar(startPositionsP1[2]) + "  " + startPositionToChar(startPositionsP1[3]) + "  "
                + startPositionToChar(startPositionsP1[4]) + "  " + startPositionToChar(startPositionsP1[5]) + "  " + startPositionToChar(startPositionsP1[6]) + "  " + startPositionToChar(startPositionsP1[7]) + "  " + startPositionToChar(startPositionsP1[8]) + "\n");

        // Print Board
        System.out.print(
                "  0{ " + boardPositionToChar(boardPositions[0]) + " }---------------------------- 1{ " + boardPositionToChar(boardPositions[1]) + " }----------------------------- 2{ " + boardPositionToChar(boardPositions[2]) + " }\n"
                        + "     |                                  |                                   | \n"
                        + "     |                                  |                                   | \n"
                        + "     |       3{ " + boardPositionToChar(boardPositions[3]) + " }----------------- 4{ " + boardPositionToChar(boardPositions[4]) + " }------------------ 5{ " + boardPositionToChar(boardPositions[5]) + " }        | \n"
                        + "     |          |                       |                        |          |    \n"
                        + "     |          |                       |                        |          |    \n"
                        + "     |          |       6{ " + boardPositionToChar(boardPositions[6]) + " }------ 7{ " + boardPositionToChar(boardPositions[7]) + " }------- 8{ " + boardPositionToChar(boardPositions[8]) + " }        |          | \n"
                        + "     |          |          |                          |          |          | \n"
                        + "     |          |          |                          |          |          | \n"
                        + "  9{ " + boardPositionToChar(boardPositions[9]) + " }--- 10{ " + boardPositionToChar(boardPositions[10]) + " }--- 11{ " + boardPositionToChar(boardPositions[11]) + " }                    12{ " + boardPositionToChar(boardPositions[12]) + " }--- 13{ " + boardPositionToChar(boardPositions[13]) + " }--- 14{ " + boardPositionToChar(boardPositions[14]) + " }\n"
                        + "     |          |          |                          |          |          | \n"
                        + "     |          |          |                          |          |          | \n"
                        + "     |          |      15{ " + boardPositionToChar(boardPositions[15]) + " }----- 16{ " + boardPositionToChar(boardPositions[16]) + " }------ 17{ " + boardPositionToChar(boardPositions[17]) + " }        |          |\n"
                        + "     |          |                       |                        |          |    \n"
                        + "     |          |                       |                        |          |    \n"
                        + "     |      18{ " + boardPositionToChar(boardPositions[18]) + " }---------------- 19{ " + boardPositionToChar(boardPositions[19]) + " }----------------- 20{ " + boardPositionToChar(boardPositions[20]) + " }        | \n"
                        + "     |                                  |                                   | \n"
                        + "     |                                  |                                   | \n"
                        + " 21{ " + boardPositionToChar(boardPositions[21]) + " }--------------------------- 22{ " + boardPositionToChar(boardPositions[22]) + " }---------------------------- 23{ " + boardPositionToChar(boardPositions[23]) + " }\n\n");

        // Print Player 1 Start Positions
        System.out.println("                       Fire :  " + startPositionToChar(startPositionsP2[0]) + "  " + startPositionToChar(startPositionsP2[1]) + "  " + startPositionToChar(startPositionsP2[2]) + "  " + startPositionToChar(startPositionsP2[3]) + "  "
                + startPositionToChar(startPositionsP2[4]) + "  " + startPositionToChar(startPositionsP2[5]) + "  " + startPositionToChar(startPositionsP2[6]) + "  " + startPositionToChar(startPositionsP2[7]) + "  " + startPositionToChar(startPositionsP2[8]) + "\n");
    }

    private char boardPositionToChar(Position position) {
        if (position.isEmpty()) {
            return ' ';
        } else {
            return position.getPieceOccupying().getDisplayChar();
        }
    }

    private char startPositionToChar(Position position) {
        if (position.isEmpty()) {
            return '_';
        } else {
            return position.getPieceOccupying().getDisplayChar();
        }
    }

    private String getErrorMessage(ExecutionCode executionCode) {
        return switch (executionCode) {
            case NULL -> "Error : The position you have entered does not exist.";
            case NOT_EMPTY -> "Error : The position you have entered is not empty.";
            case NOT_OWNER -> "Error : The piece you have selected is not yours.";
            case NOT_ADJACENT ->
                    "Error : The position you have entered is not adjacent to the piece you have selected.";
            default -> "Error : An unknown error has occurred. Error Code: " + executionCode;
        };
    }

    private String getMoveDescription(Game game) {
        String moveDescription = "";
        if (game.getGameTurn() == 0) return "Game has Started.";
        // TODO: This is a hardcode to fix null pointer exception when game phase change from Placement to Movement but still need to print one last placement move description description
        if (game.getGameTurn() == 18) return game.getOpponentPlayer().getName() + " Player placed a piece at Position "
                + game.getInputTargetPosition().getPositionNumber() + ".";;
        switch (game.getCurrentGamePhase()) {
            case "PLACEMENT" ->
                    moveDescription = game.getOpponentPlayer().getName() + " Player placed a piece at Position "
                            + game.getInputTargetPosition().getPositionNumber() + ".";
            case "MOVEMENT" ->
                    moveDescription = game.getOpponentPlayer().getName()  + " Player moved a piece from Position "
                            + game.getInputStartPosition().getPositionNumber() + " to Position " + game.getInputTargetPosition().getPositionNumber() + ".";
            case "REMOVAL" ->
                    moveDescription = game.getCurrentPlayer().getName() + " Player removed a piece of Player " + game.getOpponentPlayer() + " at Position "
                            + game.getInputTargetPosition().getPositionNumber() + ".";
        }
        return moveDescription;
    }

    private void printEndGameResult(Game game) {
        System.out.println("\nGame Over!");
        System.out.println(game.getOpponentPlayer().getName() + " Player has won the game!\n");
        System.out.println("                                          Game Statistics\n");
        System.out.println("                                          Total Turns: " + game.getGameTurn() + "\n");
        System.out.printf("                                %s                          %s\n", game.getCurrentPlayer().getName(), game.getOpponentPlayer().getName());
        System.out.printf("Total Pieces Remaining:         %d                            %d\n", game.getCurrentPlayer().getNumOfPiecesOnBoard(), game.getOpponentPlayer().getNumOfPiecesOnBoard());
        System.out.printf("Total Pieces Captured:          %d                            %d\n", game.getCurrentPlayer().getNumOfPiecesCaptured(), game.getOpponentPlayer().getNumOfPiecesCaptured());
        System.out.printf("Total Mills Made:               %d                            %d\n", game.getCurrentPlayer().getNumOfMillsMade(), game.getOpponentPlayer().getNumOfMillsMade());
    }

    private void handleEndGameInputQuery(Game game) {
        System.out.print("\nWould you like to restart the game? (Y/N): ");
        String input = consoleInput.nextLine();
        if (input.equalsIgnoreCase("Y") || input.equalsIgnoreCase("r")) {
            restartGame(game);
        } else if (input.equalsIgnoreCase("N") || input.equalsIgnoreCase("e")) {
            exitGame();
        } else {
            System.out.println("Invalid input. Please try again.");
            handleEndGameInputQuery(game);
        }
    }
}
