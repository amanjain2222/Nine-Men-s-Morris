public class Display {

    private Board board;

    public Display(Board board) {
        this.board = board;
    }

    private void printBoard() {
        String[][] gameBoard = {
                {"{" + showPosition(0) + "}---------------" + "{" + showPosition(1) +  "}----------------" + "{" + showPosition(2) + "}"},
                {" |                 |                  | "},
                {" |                 |                  | "},
                {" |   {" + showPosition(3) + "}----------{" + showPosition(4) + "}-----------{" + showPosition(5) + "}   | "},
                {" |    |            |             |    | "},
                {" |    |            |             |    | "},
                {" |    |   {" + showPosition(6) + "}-----{" + showPosition(7) + "}------{" + showPosition(8) + "}   |    | "},
                {" |    |    |                |    |    | "},
                {" |    |    |                |    |    | "},
                {"{" + showPosition(9) + "}--{" + showPosition(10) + "}--{" + showPosition(11) + "}              {" + showPosition(12) + "}--{" + showPosition(13) + "}--{" + showPosition(14) + "}"},
                {" |    |    |                |    |    | "},
                {" |    |    |                |    |    | "},
                {" |    |   {" + showPosition(15) + "}-----{" + showPosition(16) + "}------{" + showPosition(17) + "}   |    |"},
                {" |    |            |             |    | "},
                {" |    |            |             |    | "},
                {" |   {" + showPosition(18)+ "}----------{" + showPosition(19) + "}-----------{" + showPosition(20) + "}   | "},
                {" |                 |                  | "},
                {" |                 |                  | "},
                {"{" + showPosition(21) + "}---------------{" + showPosition(22) +  "}----------------{" + showPosition(23) + "}"}};


        System.out.println();
        for (String[] row : gameBoard) {
            for (String col : row) {
                System.out.print(col);
            }
            System.out.println();
        }
    }


    private String showPosition (int positionIndex) {
        // Return the character that represents the piece occupying the position
        if (this.board.getPosition(positionIndex).isEmpty()) {
            return Integer.toString(this.board.getPositionIndex(positionIndex))  //returns the positon number of the position
        }
        else {
            return this.board.getPosition(positionIndex).getPieceOccupying().getDisplayChar();
        }
    }

    public static void main(String[] args) {
        Game newGame = new Game();
        Display consoleDisplay = new Display(newGame.getGameBoard());
        consoleDisplay.printBoard();
    }

}