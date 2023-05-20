import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Board {
    public static final int TOTAL_POSITION = 24;
    public static final int TOTAL_START_POSITION = 9;
    private final Position[] boardPosition;

    public Board(Player player1, Player player2) {
        this.boardPosition = new Position[TOTAL_POSITION];
        initBoard();
    }

    private void initBoard() {
        // initialise the board positions
        for (int i = 0; i < TOTAL_POSITION; i++) {
            boardPosition[i] = new Position(i);
        }

        // add the adjacent indexes for each position
        // outer square
        boardPosition[0].setAdjacentPositions().setRight(boardPosition[1]).setBottom(boardPosition[9]).build();
        boardPosition[1].setAdjacentPositions().setLeft(boardPosition[0]).setRight(boardPosition[2])
                .setBottom(boardPosition[4]).build();
        boardPosition[2].setAdjacentPositions().setLeft(boardPosition[1]).setBottom(boardPosition[14]).build();
        boardPosition[9].setAdjacentPositions().setTop(boardPosition[0]).setRight(boardPosition[10])
                .setBottom(boardPosition[21]).build();
        boardPosition[14].setAdjacentPositions().setTop(boardPosition[2]).setLeft(boardPosition[13])
                .setBottom(boardPosition[23]).build();
        boardPosition[21].setAdjacentPositions().setTop(boardPosition[9]).setRight(boardPosition[22]).build();
        boardPosition[22].setAdjacentPositions().setTop(boardPosition[19]).setLeft(boardPosition[21])
                .setRight(boardPosition[23]).build();
        boardPosition[23].setAdjacentPositions().setTop(boardPosition[14]).setLeft(boardPosition[22]).build();
        // middle square
        boardPosition[3].setAdjacentPositions().setRight(boardPosition[4]).setBottom(boardPosition[10]).build();
        boardPosition[4].setAdjacentPositions().setTop(boardPosition[1]).setLeft(boardPosition[3])
                .setRight(boardPosition[5]).setBottom(boardPosition[7]).build();
        boardPosition[5].setAdjacentPositions().setLeft(boardPosition[4]).setBottom(boardPosition[13]).build();
        boardPosition[10].setAdjacentPositions().setTop(boardPosition[3]).setLeft(boardPosition[9])
                .setRight(boardPosition[11]).setBottom(boardPosition[18]).build();
        boardPosition[13].setAdjacentPositions().setTop(boardPosition[5]).setLeft(boardPosition[12])
                .setRight(boardPosition[14]).setBottom(boardPosition[20]).build();
        boardPosition[18].setAdjacentPositions().setTop(boardPosition[10]).setRight(boardPosition[19]).build();
        boardPosition[19].setAdjacentPositions().setTop(boardPosition[16]).setRight(boardPosition[20])
                .setLeft(boardPosition[18]).setBottom(boardPosition[22]).build();
        boardPosition[20].setAdjacentPositions().setTop(boardPosition[13]).setLeft(boardPosition[19]).build();
        // inner square
        boardPosition[6].setAdjacentPositions().setRight(boardPosition[7]).setBottom(boardPosition[11]).build();
        boardPosition[7].setAdjacentPositions().setTop(boardPosition[4]).setLeft(boardPosition[6])
                .setRight(boardPosition[8]).build();
        boardPosition[8].setAdjacentPositions().setLeft(boardPosition[7]).setBottom(boardPosition[12]).build();
        boardPosition[11].setAdjacentPositions().setTop(boardPosition[6]).setLeft(boardPosition[10])
                .setBottom(boardPosition[15]).build();
        boardPosition[12].setAdjacentPositions().setTop(boardPosition[8]).setRight(boardPosition[13])
                .setBottom(boardPosition[17]).build();
        boardPosition[15].setAdjacentPositions().setTop(boardPosition[11]).setRight(boardPosition[16]).build();
        boardPosition[16].setAdjacentPositions().setLeft(boardPosition[15]).setRight(boardPosition[17])
                .setBottom(boardPosition[19]).build();
        boardPosition[17].setAdjacentPositions().setTop(boardPosition[12]).setLeft(boardPosition[16]).build();
    }

    public Position getPosition(int positionIndex) {
        if (positionIndex < 0 || positionIndex >= TOTAL_POSITION)
            return null;
        return boardPosition[positionIndex];
    }

    public char[] toCharArray() {
        List<Character> charArray = new ArrayList<>();
        for (int i = 0; i < boardPosition.length; i++) {
            charArray.add(boardPosition[i].getPieceOccupying() == null ? ' '
                    : boardPosition[i].getPieceOccupying().getOwner().getDisplayChar());
        }
        return charArray.stream().map(Object::toString).collect(Collectors.joining()).toCharArray();
    }
}
