import java.util.ArrayList;
import java.util.Scanner;

public class DisplayInterface {

    final String GAME_TITLE_MESSAGE = "9 Men's Morris Game";
    final String PLAYER_TURN_INFO_MESSAGE = " Player, make your move.";
    final String GAME_PHASE_MESSAGE = "Current Game Phase: ";
    final String PLACEMENT_QUERY_MESSAGE = "Please enter the position on board you want to place a piece: ";
    final String CURRENT_PLAYER_MESSAGE = "Current Player Turn: ";
    final String PLAYER_TURN_INFO_AGAIN_MESSAGE = " Player, make another move that is valid.";
    final String MOVEMENT_FIRST_QUERY_MESSAGE = "Please enter the position of the piece that you want to move: ";
    final String MOVEMENT_SECOND_QUERY_MESSAGE = "Please enter the position you want to move the piece to: ";

    final String INVALID_GAME_PHASE = "Error Invalid Game Phase";

    Scanner consoleInput = new Scanner(System.in);

    public void updateDisplay(Game game) {
        this.printBoard(game.getGameBoard());
    }

    public InputState queryInputState(Game game) {
        // Print game phase and player turn info
        System.out.println("\n                        " + GAME_PHASE_MESSAGE + game.getCurrentGamePhase()); // display current game phase
        System.out.println("                        " + CURRENT_PLAYER_MESSAGE + game.getCurrentPlayer().getName() + "\n"); // display current player

        // Print Previous Input Result
        if (game.wasPreviousMoveInvalid()) {
            System.out.println(getErrorMessage(game.getErrorCode()) + "\n");
            System.out.println(game.getCurrentPlayer().getName() + PLAYER_TURN_INFO_AGAIN_MESSAGE);
        } else {
            System.out.println(game.getMoveConsoleDescription() + "\n");
            System.out.println(game.getCurrentPlayer().getName() + PLAYER_TURN_INFO_MESSAGE);
        }

        // Get Input Based On Current Game Phase
        ArrayList<Integer> inputValues = new ArrayList<>();

        switch (game.getCurrentGamePhase()) {
            case "PLACEMENT" -> {
                // Get Placement Input
                System.out.print(PLACEMENT_QUERY_MESSAGE);
                inputValues.add(consoleInput.nextInt());
            }
            case "MOVEMENT" -> {
                // Get Movement Input
                System.out.print(MOVEMENT_FIRST_QUERY_MESSAGE);
                inputValues.add(consoleInput.nextInt());
                System.out.print(MOVEMENT_SECOND_QUERY_MESSAGE);
                inputValues.add(consoleInput.nextInt());
            }
            default ->
                // TODO: HANDLE INVALID GAME PHASE
                    System.out.println(INVALID_GAME_PHASE);
        }

        // Return Input
        return new InputState(inputValues);
    }

    private void printBoard(Board board) {
        Position[] boardPositions = board.getBoardPositions();
        Position[] startPositionsP1 = board.getStartPositionP1();
        Position[] startPositionsP2 = board.getStartPositionP2();

        System.out.println("\n                              " + GAME_TITLE_MESSAGE + "\n");

        System.out.println("                       Ice :  " + startPositionToChar(startPositionsP1[0]) + "  " + startPositionToChar(startPositionsP1[1]) + "  " + startPositionToChar(startPositionsP1[2]) + "  " + startPositionToChar(startPositionsP1[3]) + "  "
                + startPositionToChar(startPositionsP1[4]) + "  " + startPositionToChar(startPositionsP1[5]) + "  " + startPositionToChar(startPositionsP1[6]) + "  " + startPositionToChar(startPositionsP1[7]) + "  " + startPositionToChar(startPositionsP1[8]) +"\n");

        System.out.print(
            "  0{ " + positionToChar(boardPositions[0]) + " }---------------------------- 1{ " + positionToChar(boardPositions[1]) +  " }----------------------------- 2{ " + positionToChar(boardPositions[2]) + " }\n"
            + "     |                                  |                                   | \n"
            + "     |                                  |                                   | \n"
            + "     |       3{ " + positionToChar(boardPositions[3]) + " }----------------- 4{ " + positionToChar(boardPositions[4]) + " }------------------ 5{ " + positionToChar(boardPositions[5]) + " }        | \n"
            + "     |          |                       |                        |          |    \n"
            + "     |          |                       |                        |          |    \n"
            + "     |          |       6{ " + positionToChar(boardPositions[6]) + " }------ 7{ " + positionToChar(boardPositions[7]) + " }------- 8{ " + positionToChar(boardPositions[8]) + " }        |          | \n"
            + "     |          |          |                          |          |          | \n"
            + "     |          |          |                          |          |          | \n"
            + "  9{ " + positionToChar(boardPositions[9]) + " }--- 10{ " + positionToChar(boardPositions[10]) + " }--- 11{ " + positionToChar(boardPositions[11]) + " }                    12{ " + positionToChar(boardPositions[12]) + " }--- 13{ " + positionToChar(boardPositions[13]) + " }--- 14{ " + positionToChar(boardPositions[14]) + " }\n"
            + "     |          |          |                          |          |          | \n"
            + "     |          |          |                          |          |          | \n"
            + "     |          |      15{ " + positionToChar(boardPositions[15]) + " }----- 16{ " + positionToChar(boardPositions[16]) + " }------ 17{ " + positionToChar(boardPositions[17]) + " }        |          |\n"
            + "     |          |                       |                        |          |    \n"
            + "     |          |                       |                        |          |    \n"
            + "     |      18{ " + positionToChar(boardPositions[18]) + " }---------------- 19{ " + positionToChar(boardPositions[19]) + " }----------------- 20{ " + positionToChar(boardPositions[20]) + " }        | \n"
            + "     |                                  |                                   | \n"
            + "     |                                  |                                   | \n"
            + " 21{ " + positionToChar(boardPositions[21]) + " }--------------------------- 22{ " + positionToChar(boardPositions[22]) +  " }---------------------------- 23{ " + positionToChar(boardPositions[23]) + " }\n\n");

        System.out.println("                       Fire :  " + startPositionToChar(startPositionsP2[0]) + "  " + startPositionToChar(startPositionsP2[1]) + "  " + startPositionToChar(startPositionsP2[2]) + "  " + startPositionToChar(startPositionsP2[3]) + "  "
                + startPositionToChar(startPositionsP2[4]) + "  " + startPositionToChar(startPositionsP2[5]) + "  " + startPositionToChar(startPositionsP2[6]) + "  " + startPositionToChar(startPositionsP2[7]) + "  " + startPositionToChar(startPositionsP2[8]) +"\n");



    }

    private char positionToChar (Position position) {
        if (position.isEmpty()) {
            return ' ';
        }
        else {
            return position.getPieceOccupying().getDisplayChar();
        }
    }

    private char startPositionToChar(Position position){
        if (position.isEmpty()) {
            return '_';
        }
        else {
            return position.getPieceOccupying().getDisplayChar();
        }
    }

    private String getErrorMessage(ErrorCode errorCode) {
        return switch (errorCode) {
            case NULL -> "Error : The position you have entered does not exist.";
            case NOT_EMPTY -> "Error : The position you have entered is not empty.";
            case NOT_OWNER -> "Error : The piece you have selected is not yours.";
            case NOT_ADJACENT ->
                    "Error : The position you have entered is not adjacent to the piece you have selected.";
            default -> "Error : An unknown error has occurred. Error Code: " + errorCode;
        };
    }
}
