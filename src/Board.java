import java.util.ArrayList;

public class Board {
    public static final int TOTAL_POSITION = 24;
    public static final int TOTAL_MILLS_COMBINATION = 16;
    public static final int TOTAL_POSITION_IN_A_MILL = 3;

    private final Position[] boardPosition;
    private final Position[][] millCombinations;
    private int totalPiecesPlaced; // keep track of the total number of pieces placed on the board
    private final Player player1;
    private final Player player2;
    private ArrayList<Integer> player1PiecesOnBoardPositions; // keep track of the Pieces positions on the board for player 1
    private ArrayList<Integer>  player2PiecesOnBoardPositions; // keep track of the Pieces positions on the board for player 2

    public Board(Player player1, Player player2) {
        this.boardPosition = new Position[TOTAL_POSITION];
        this.millCombinations = new Position[TOTAL_MILLS_COMBINATION][TOTAL_POSITION_IN_A_MILL];
        this.totalPiecesPlaced = 0;
        this.player1 = player1;
        this.player2 = player2;
        initBoard();
        initMillCombinations();
    }

    private void initBoard() {
        // initialise the board positions
        for (int i = 0; i < TOTAL_POSITION; i++) {
            boardPosition[i] = new Position(i);
        }

        // add the adjacent indexes for each position
        // outer square
        boardPosition[0].addAdjacentIndexes(1,9);
        boardPosition[1].addAdjacentIndexes(0,2,4);
        boardPosition[2].addAdjacentIndexes(1,14);
        boardPosition[9].addAdjacentIndexes(0,10,21);
        boardPosition[14].addAdjacentIndexes(2,13,23);
        boardPosition[21].addAdjacentIndexes(9,22);
        boardPosition[22].addAdjacentIndexes(19,21,23);
        boardPosition[23].addAdjacentIndexes(14,22);
        // middle square
        boardPosition[3].addAdjacentIndexes(4,10);
        boardPosition[4].addAdjacentIndexes(1,3,5,7);
        boardPosition[5].addAdjacentIndexes(4,13);
        boardPosition[10].addAdjacentIndexes(3,9,11,18);
        boardPosition[13].addAdjacentIndexes(5,12,14,20);
        boardPosition[18].addAdjacentIndexes(10,19);
        boardPosition[19].addAdjacentIndexes(16,18,20,22);
        boardPosition[20].addAdjacentIndexes(13,19);
        // inner square
        boardPosition[6].addAdjacentIndexes(7,11);
        boardPosition[7].addAdjacentIndexes(4,6,8);
        boardPosition[8].addAdjacentIndexes(7,12);
        boardPosition[11].addAdjacentIndexes(6,10,15);
        boardPosition[12].addAdjacentIndexes(8,13,17);
        boardPosition[15].addAdjacentIndexes(11,16);
        boardPosition[16].addAdjacentIndexes(15,17,19);
        boardPosition[17].addAdjacentIndexes(12,16);
    }

    private void initMillCombinations() {
        // initialise the board position indexes for each mill combination
        // millIndex stores all the possible index of the positions in a mill combination
        int[][] millIndex = new int[][]{{0, 1, 2}, {0, 9, 21}, {0, 3, 6}, {1, 4, 7},
                                        {2, 5, 7}, {2, 14, 23}, {3, 4, 5}, {3, 10, 18},
                                        {5, 13, 20}, {6, 7, 8}, {6, 11, 15}, {8, 12, 17},
                                        {9, 10, 11}, {12, 13, 14}, {15, 16, 17}, {18, 19, 20}};
        for (int i = 0; i < TOTAL_MILLS_COMBINATION; i++) {
            for (int j = 0; j < TOTAL_POSITION_IN_A_MILL; j++) {
                // millCombinations stores the positions in a mill combination
                millCombinations[i][j] = boardPosition[millIndex[i][j]];
            }
        }
    }

    public Position[] getMillCombination(int index) {
        // return the mill combination at the index
        return millCombinations[index];
    }

    public int[] getAdjacentPositions(int index) {
        return boardPosition[index].getAdjacentIndexes();
    }

    public boolean isAdjacent(int index1, int index2) {
        // return true if the positions at the indexes are adjacent
        for (int adjIndex : boardPosition[index1].getAdjacentIndexes()) {
            if (adjIndex == index2) {
                return true;
            }
        }
        return false;
    }

    public int showThisPositionIndex(int index) {
        return boardPosition[index].getIndex();
    }

    public Position getPosition(int index) {
        // return the position at the index
        return boardPosition[index];
    }

    public boolean isPositionEmpty(int index) {
        // return true if the position at the index is empty
        return boardPosition[index].isEmpty();
    }

    public void setPositionToEmpty(int index) {
        // set the position at the index to empty
        boardPosition[index].setEmpty();
    }

    public void setPositionToPlayer(int index, Piece piece) {
        // set the position at the index to the player
        boardPosition[index].setPieceOccupying(piece);
    }

    public Piece getPlayerAtPosition(int index) {
        // return the player occupying the position at the index
        return boardPosition[index].getPieceOccupying();
    }

    public int getTotalPiecesPlaced() {
        // return the total number of pieces placed on the board
        return totalPiecesPlaced;
    }

    public ArrayList<Integer> getPlayerPiecesOnBoardPositions(Player player) {
        // return the player's pieces on board positions
        if (player == player1) {
            return player1PiecesOnBoardPositions;
        } else {
            return player2PiecesOnBoardPositions;
        }
    }

    public void changePlayerPiecesOnBoardPosition(int fromIndex, int toIndex, Piece piece) {
        // change the player's pieces on board positions from the fromIndex to the toIndex
        removeFromPlayerPiecesOnBoardPositions(fromIndex, piece);
        addToPlayerPiecesOnBoardPositions(toIndex, piece);
    }
    
    private void addToPlayerPiecesOnBoardPositions(int index, Piece piece) {
        // add the index to the player's pieces on board positions
        totalPiecesPlaced++;
        if (piece.getOwner() == player1) {
            player1PiecesOnBoardPositions.add(index);
        } else {
            player2PiecesOnBoardPositions.add(index);
        }
    }

    private void removeFromPlayerPiecesOnBoardPositions(int index, Piece piece) {
        // remove the index to the player's pieces on board positions
        totalPiecesPlaced--;
        if (piece.getOwner() == player1) {
            player1PiecesOnBoardPositions.remove((Integer) index);
        } else {
            player2PiecesOnBoardPositions.remove((Integer) index);
        }
    }

    public int getPositionIndex(int position){
        return this.getPosition(position).getIndex()
    }

}

