import java.util.ArrayList;
import java.util.Arrays;

public class Board {
    public static final int TOTAL_POSITION = 24;

    public static final int TOTAL_START_POSITION = 9;
    public static final int TOTAL_MILLS_COMBINATION = 16;
    public static final int TOTAL_POSITION_IN_A_MILL = 3;
    private final Position[] boardPosition;
    private final Position[] startPositionP1;
    private final Position[] startPositionP2;
    private final Player player1;
    private final Player player2;

    private ArrayList<Position[]> Mill_Combinations = new ArrayList<>();

    public Board(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.boardPosition = new Position[TOTAL_POSITION];
        this.startPositionP1 = new Position[TOTAL_START_POSITION];
        this.startPositionP2 = new Position[TOTAL_START_POSITION];
        initBoard();
    }


    public Position[] getBoardPositions() {
        return boardPosition;
    }

    public Position[] getStartPositionP1() {
        return startPositionP1;
    }

    public Position[] getStartPositionP2() {
        return startPositionP2;
    }

    private void initBoard() {
        // initialise the board positions
        for (int i = 0; i < TOTAL_POSITION; i++) {
            boardPosition[i] = new Position(i);
        }

        // initialise the remaining position for player 1
        for (int i = 0; i < TOTAL_START_POSITION; i++) {
            startPositionP1[i] = new Position(i);
            startPositionP1[i].setPieceOccupying(new Piece(player1));
        }

        // initialise the remaining position for player 2
        for (int i = 0; i < TOTAL_START_POSITION; i++) {
            startPositionP2[i] = new Position(i);
            startPositionP2[i].setPieceOccupying(new Piece(player2));
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


        Position[][] millPosition_combinations = new Position[][]{{boardPosition[0], boardPosition[1], boardPosition[2]},
                {boardPosition[0], boardPosition[9], boardPosition[21]}, {boardPosition[0], boardPosition[3], boardPosition[6]},
                {boardPosition[1], boardPosition[4], boardPosition[7]},
                {boardPosition[2], boardPosition[5], boardPosition[7]}, {boardPosition[2], boardPosition[14], boardPosition[23]},
                {boardPosition[3], boardPosition[4], boardPosition[5]}, {boardPosition[3], boardPosition[10], boardPosition[18]},
                {boardPosition[5], boardPosition[13], boardPosition[20]}, {boardPosition[6], boardPosition[7],boardPosition[8]},
                {boardPosition[6], boardPosition[11], boardPosition[15]}, {boardPosition[8], boardPosition[12], boardPosition[17]},
                {boardPosition[9], boardPosition[10], boardPosition[11]}, {boardPosition[12], boardPosition[13], boardPosition[14]},
                {boardPosition[15], boardPosition[16], boardPosition[17]}, {boardPosition[18], boardPosition[19], boardPosition[20]}};

        Mill_Combinations.addAll(Arrays.asList(millPosition_combinations));
    }





    public Position getPosition(int positionIndex) {
        if (positionIndex < 0 || positionIndex > boardPosition.length) return null;
        return boardPosition[positionIndex];
    }

    public boolean isMill(Player player, Position CurrentPosition) {
        for (Position[] combination : Mill_Combinations) {
            Position position1 = combination[0];
            Position position2 = combination[1];
            Position position3 = combination[2];
            if (position1.getPieceOccupying() != null && position2.getPieceOccupying() != null && position3.getPieceOccupying() != null){
                if (position1.getPieceOccupying().getOwner() == player && position2.getPieceOccupying().getOwner() == player && position3.getPieceOccupying().getOwner() == player) {
                    if (CurrentPosition == position1 || CurrentPosition == position2 || CurrentPosition == position3) {
                        return true;
                    }
                }
            }


        }
        return false;
    }



}

