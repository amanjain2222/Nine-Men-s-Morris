public class Display {

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
