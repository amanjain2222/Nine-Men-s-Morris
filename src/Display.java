public class Display {

    public Display(Board board) {
        this.board = board;
        printBoard();

    }

    public void printBoard() {
        String[][] gameBoard = {
                {board.getPosition(0) + "---------------" + board.getPosition(1) +  "----------------" + board.getPosition(2)},
                {" |                 |                  | "},
                {" |                 |                  | "},
                {" |   " + board.getPosition(3)+ "----------" + board.getPosition(4) + "----------_" + board.getPosition(5) + "   | "},
                {" |    |            |             |    | "},
                {" |    |            |             |    | "},
                {" |    |   " + board.getPosition(6) + "-----" board.getPosition(7) + "------" board.getPosition(8) + "   |    | "},
                {" |    |    |                |    |    | "},
                {" |    |    |                |    |    | "},
                {board.getPosition(9) + "--" + board.getPosition(10) + "--" + board.getPosition(11) + "              " + board.getPosition(12) + "--" + board.getPosition(13) + "--" board.getPosition(14},
                {" |    |    |                |    |    | "},
                {" |    |    |                |    |    | "},
                {" |    |   " + board.getPosition(15) + "-----" + board.getPosition(16) + "----_-" + board.getPosition(17) + "   |    |"},
                {" |    |            |             |    | "},
                {" |    |            |             |    | "},
                {" |   " + board.getPosition(18)+ "----------" + board.getPosition(19) + "----------_" + board.getPosition(20) + "   | "},
                {" |                 |                  | "},
                {" |                 |                  | "},
                {board.getPosition(21) + "---------------" + board.getPosition(22) +  "----------------" + board.getPosition(23)}};


        System.out.println();
        for (String[] row : gameBoard) {
            for (String col : row) {
                System.out.print(col);
            }
            System.out.println();
        }
    }

    public int showPositon (int position) {
        return position.getPieceOccupying().getPieceType();
    }

    public static void main(String[] args) {
        Board board = new Board();
        new Display(board);

    }
}

/*
ppublic class Display {

    public Display() {
        printBoard();
    }

    public void printBoard() {
        String[][] gameBoard = {
                {"{ }---------------{ }----------------{ }"},
                {" |                 |                  | "},
                {" |                 |                  | "},
                {" |   { }----------{ }----------_{ }   | "},
                {" |    |            |             |    | "},
                {" |    |            |             |    | "},
                {" |    |   { }-----{ }------{ }   |    | "},
                {" |    |    |                |    |    | "},
                {" |    |    |                |    |    | "},
                {"{ }--{ }--{ }              { }--{ }--{ }"},
                {" |    |    |                |    |    | "},
                {" |    |    |                |    |    | "},
                {" |    |   { }-----{ }----_-{ }   |    |"},
                {" |    |            |             |    | "},
                {" |    |            |             |    | "},
                {" |   { }----------{ }----------_{ }   | "},
                {" |                 |                  | "},
                {" |                 |                  | "},
                {"{ }---------------{ }----------------{ }"}};


        System.out.println();
        for (String[] row : gameBoard) {
            for (String col : row) {
                System.out.print(col);
            }
            System.out.println();
        }
    }

    public int showPositon (int position) {
        return position.getPieceOccupying().getPieceType();
    }

    public static void main(String[] args) {
        new Display();

    }
}


 */