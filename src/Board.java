import java.util.ArrayList;

public class Board {
    public static final int TOTAL_POSITION = 24;
    public static final int TOTAL_MILLS_COMBINATION = 16;
    public static final int TOTAL_POSITION_IN_A_MILL = 3;

    private final Position[] boardPosition;

    public Board(Player player1, Player player2) {
        this.boardPosition = new Position[TOTAL_POSITION];
        initBoard();
    }

    public Position[] getBoardPositions() {
        return boardPosition;
    }

    private void initBoard() {
        // initialise the board positions
        for (int i = 0; i < TOTAL_POSITION; i++) {
            boardPosition[i] = new Position(i);
        }

        // add the adjacent indexes for each position
        // outer square
        boardPosition[0].addAdjacentPositions(boardPosition[1], boardPosition[9]);
        boardPosition[1].addAdjacentPositions(boardPosition[0], boardPosition[2], boardPosition[4]);
        boardPosition[2].addAdjacentPositions(boardPosition[1], boardPosition[14]);
        boardPosition[9].addAdjacentPositions(boardPosition[0], boardPosition[10], boardPosition[21]);
        boardPosition[14].addAdjacentPositions(boardPosition[2], boardPosition[13], boardPosition[23]);
        boardPosition[21].addAdjacentPositions(boardPosition[9], boardPosition[22]);
        boardPosition[22].addAdjacentPositions(boardPosition[19], boardPosition[21], boardPosition[23]);
        boardPosition[23].addAdjacentPositions(boardPosition[14], boardPosition[22]);
        // middle square
        boardPosition[3].addAdjacentPositions(boardPosition[4], boardPosition[10]);
        boardPosition[4].addAdjacentPositions(boardPosition[1], boardPosition[3], boardPosition[5], boardPosition[7]);
        boardPosition[5].addAdjacentPositions(boardPosition[4], boardPosition[13]);
        boardPosition[10].addAdjacentPositions(boardPosition[3], boardPosition[9], boardPosition[11], boardPosition[18]);
        boardPosition[13].addAdjacentPositions(boardPosition[5], boardPosition[12], boardPosition[14], boardPosition[20]);
        boardPosition[18].addAdjacentPositions(boardPosition[10], boardPosition[19]);
        boardPosition[19].addAdjacentPositions(boardPosition[16], boardPosition[18], boardPosition[20], boardPosition[22]);
        boardPosition[20].addAdjacentPositions(boardPosition[13],boardPosition[19]);
        // inner square
        boardPosition[6].addAdjacentPositions(boardPosition[7], boardPosition[11]);
        boardPosition[7].addAdjacentPositions(boardPosition[4], boardPosition[6], boardPosition[8]);
        boardPosition[8].addAdjacentPositions(boardPosition[7], boardPosition[12]);
        boardPosition[11].addAdjacentPositions(boardPosition[6], boardPosition[10], boardPosition[15]);
        boardPosition[12].addAdjacentPositions(boardPosition[8], boardPosition[13], boardPosition[17]);
        boardPosition[15].addAdjacentPositions(boardPosition[11], boardPosition[16]);
        boardPosition[16].addAdjacentPositions(boardPosition[15], boardPosition[17], boardPosition[19]);
        boardPosition[17].addAdjacentPositions(boardPosition[12], boardPosition[16]);
    }

    public Position getPosition(int positionIndex) {
        if (positionIndex < 0 || positionIndex > boardPosition.length) return null;
        return boardPosition[positionIndex];
    }
}

