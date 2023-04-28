import java.util.ArrayList;
import java.util.Scanner;

public class DisplayInterface {

    final String GAME_TITLE_MESSAGE = "9 Men's Morris Game";
    final String PLAYER_TURN_INFO_MESSAGE = " Player, make your move.";
    final String PIECES_REMAINING_ON_HAND_MESSAGE = " Player Pieces Remaining On Hand: ";
    final String PIECES_REMAINING_ON_BOARD_MESSAGE = " Player Pieces Remaining On Board: ";
    final String GAME_PHASE_MESSAGE = "Current Game Phase: ";
    final String INVALID_MOVE_MESSAGE = "Invalid move. Please try again.";
    final String PLACEMENT_QUERY_MESSAGE = "Please enter the position on board you want to place the piece to: ";
    final String MOVEMENT_FIRST_QUERY_MESSAGE = "Please enter the position of the piece that you want to move: ";
    final String MOVEMENT_SECOND_QUERY_MESSAGE = "Please enter the position you want to move the piece to: ";

    final String INVALID_GAME_PHASE = "Error Invalid Game Phase";

    Scanner consoleInput = new Scanner(System.in);

    public void updateDisplay(Game game) {
        this.printBoard(game.getGameBoard());
    }

    public InputState queryInputState(Game game) {
        // Print game phase and player turn info
        System.out.println("              " + GAME_PHASE_MESSAGE + game.getCurrentGamePhase()); // display current game phase
        // Print Player Pieces Remaining if in Placement Phase
        if (game.getCurrentGamePhase().equals("PLACEMENT")) {
            System.out.println("              " + game.getThisPlayerTurn().getName() + PIECES_REMAINING_ON_HAND_MESSAGE + game.getThisPlayerTurn().getNumOfPiecesRemaining()+ "\n");
        }
        // Print Player Pieces Remaining if in Movement Phase
        else{
            System.out.println("              " + game.getThisPlayerTurn().getName() + PIECES_REMAINING_ON_BOARD_MESSAGE + game.getThisPlayerTurn().getNumOfPiecesOnBoard()+ "\n");
        }

        // Print Previous Input Result
        if (game.wasPreviousMoveInvalid()) {
            System.out.println(INVALID_MOVE_MESSAGE);
        } else {
            System.out.println(game.getCurrentPlayer().getName() + PLAYER_TURN_INFO_MESSAGE + "( You currently have " + game.getCurrentPlayer().getNumOfPiecesRemaining() + " pieces remaining to place. )");
        }

        // Get Input Based On Current Game Phase
        ArrayList<Integer> inputValues = new ArrayList<>();

        switch(game.getCurrentGamePhase()) {
            case "PLACEMENT":
                // Get Placement Input
                System.out.print(PLACEMENT_QUERY_MESSAGE);
                inputValues.add(consoleInput.nextInt());
                break;
            case "MOVEMENT":
                // Get Movement Input
                System.out.print(MOVEMENT_FIRST_QUERY_MESSAGE);
                inputValues.add(consoleInput.nextInt());
                System.out.print(MOVEMENT_SECOND_QUERY_MESSAGE);
                inputValues.add(consoleInput.nextInt());
                break;
            default:
                // TODO: HANDLE INVALID GAME PHASE
                System.out.println(INVALID_GAME_PHASE);
        }

        // Return Input
        return new InputState(inputValues);
    }

    private void printBoard(Board board) {
        Position[] boardPositions = board.getBoardPositions();

        System.out.println("\n                      " + GAME_TITLE_MESSAGE + "\n");

        System.out.print(
            "  0{" + positionToChar(boardPositions[0]) + "}---------------------- 1{" + positionToChar(boardPositions[1]) +  "}----------------------- 2{" + positionToChar(boardPositions[2]) + "}\n"
            + "    |                          |                           | \n"
            + "    |                          |                           | \n"
            + "    |     3{" + positionToChar(boardPositions[3]) + "}-------------- 4{" + positionToChar(boardPositions[4]) + "}--------------- 5{" + positionToChar(boardPositions[5]) + "}      | \n"
            + "    |       |                  |                   |       |    \n"
            + "    |       |                  |                   |       |    \n"
            + "    |       |     6{" + positionToChar(boardPositions[6]) + "}------ 7{" + positionToChar(boardPositions[7]) + "}------- 8{" + positionToChar(boardPositions[8]) + "}      |       | \n"
            + "    |       |       |                      |       |       | \n"
            + "    |       |       |                      |       |       | \n"
            + "  9{" + positionToChar(boardPositions[9]) + "}-- 10{" + positionToChar(boardPositions[10]) + "}-- 11{" + positionToChar(boardPositions[11]) + "}                  12{" + positionToChar(boardPositions[12]) + "}-- 13{" + positionToChar(boardPositions[13]) + "}-- 14{" + positionToChar(boardPositions[14]) + "}\n"
            + "    |       |       |                      |       |       | \n"
            + "    |       |       |                      |       |       | \n"
            + "    |       |    15{" + positionToChar(boardPositions[15]) + "}----- 16{" + positionToChar(boardPositions[16]) + "}------ 17{" + positionToChar(boardPositions[17]) + "}      |       |\n"
            + "    |       |                  |                   |       |    \n"
            + "    |       |                  |                   |       |    \n"
            + "    |    18{" + positionToChar(boardPositions[18]) + "}------------- 19{" + positionToChar(boardPositions[19]) + "}-------------- 20{" + positionToChar(boardPositions[20]) + "}      | \n"
            + "    |                          |                           | \n"
            + "    |                          |                           | \n"
            + " 21{" + positionToChar(boardPositions[21]) + "}--------------------- 22{" + positionToChar(boardPositions[22]) +  "}---------------------- 23{" + positionToChar(boardPositions[23]) + "}\n\n");
    }

    private char positionToChar (Position position) {
        // Return the character that represents the piece occupying the position
        if (position.isEmpty()) {
            return ' ';  //returns the position number of the position
        }
        else {
            return position.getPieceOccupying().getDisplayChar();
        }
    }
}
