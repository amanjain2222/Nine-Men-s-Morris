import java.util.ArrayList;
import java.util.Scanner;

public class Display {

    final String PLAYER_TURN_INFO_MESSAGE = " Player, make your move.";
    final String INVALID_MOVE_MESSAGE = "Invalid move. Please try again.";
    final String PLACEMENT_QUERY_MESSAGE = "Please enter the position on board you want to place the piece to: ";
    final String MOVEMENT_FIRST_QUERY_MESSAGE = "Please enter the position of the piece that you want to move: ";
    final String MOVEMENT_SECOND_QUERY_MESSAGE = "Please enter the position you want to move the piece to: ";

    final String INVALID_GAME_PHASE = "Error Invalid Game Phase";

    Scanner consoleInput = new Scanner(System.in);

    public Input updateDisplay(Game game) {
        // Returns integer position input.
        this.printBoard(game.getGameBoard());

        // Print Previous Input Result
        if (game.wasPreviousMoveInvalid()) {
            System.out.println(INVALID_MOVE_MESSAGE);
        } else {
            System.out.println(game.getThisPlayerTurn().getName() + PLAYER_TURN_INFO_MESSAGE);
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
        return new Input(inputValues);
    }

    private void printBoard(Board board) {
        Position[] boardPositions = board.getBoardPositions();

        System.out.print(
            "{" + positionToChar(boardPositions[0]) + "}---------------" + "{" + positionToChar(boardPositions[1]) +  "}----------------" + "{" + positionToChar(boardPositions[2]) + "}\n"
            + " |                 |                  | \n"
            + " |                 |                  | \n"
            + " |   {" + positionToChar(boardPositions[3]) + "}----------{" + positionToChar(boardPositions[4]) + "}-----------{" + positionToChar(boardPositions[5]) + "}   | \n"
            + " |    |            |             |    | \n"
            + " |    |            |             |    | \n"
            + " |    |   {" + positionToChar(boardPositions[6]) + "}-----{" + positionToChar(boardPositions[7]) + "}------{" + positionToChar(boardPositions[8]) + "}   |    | \n"
            + " |    |    |                |    |    | \n"
            + " |    |    |                |    |    | \n"
            + "{" + positionToChar(boardPositions[9]) + "}--{" + positionToChar(boardPositions[10]) + "}--{" + positionToChar(boardPositions[11]) + "}              {" + positionToChar(boardPositions[12]) + "}--{" + positionToChar(boardPositions[13]) + "}--{" + positionToChar(boardPositions[14]) + "}\n"
            + " |    |    |                |    |    | \n"
            + " |    |    |                |    |    | \n"
            + " |    |   {" + positionToChar(boardPositions[15]) + "}-----{" + positionToChar(boardPositions[16]) + "}------{" + positionToChar(boardPositions[17]) + "}   |    |\n"
            + " |    |            |             |    | \n"
            + " |    |            |             |    | \n"
            + " |   {" + positionToChar(boardPositions[18]) + "}----------{" + positionToChar(boardPositions[19]) + "}-----------{" + positionToChar(boardPositions[20]) + "}   | \n"
            + " |                 |                  | \n"
            + " |                 |                  | \n"
            + "{" + positionToChar(boardPositions[21]) + "}---------------{" + positionToChar(boardPositions[22]) +  "}----------------{" + positionToChar(boardPositions[23]) + "}\n");
    }

    private String positionToChar (Position position) {
        // Return the character that represents the piece occupying the position
        if (position.isEmpty()) {
            return Integer.toString(position.getIndex());  //returns the positon number of the position
        }
        else {
            return Character.toString(position.getPieceOccupying().getDisplayChar());
        }
    }
}