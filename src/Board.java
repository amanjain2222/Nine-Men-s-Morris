import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Board {
        private static final int TOTAL_POSITION = 24;
        private final Position[] positions;

        public Board() {
                this.positions = new Position[TOTAL_POSITION];
                initBoard();
        }

        public Position getPosition(int positionIndex) {
                if (positionIndex < 0 || positionIndex >= TOTAL_POSITION)
                        return null;
                return positions[positionIndex];
        }

        public Position[] getPositions() {
                return positions;
        }

        public char[] toCharArray() {
                List<Character> charArray = new ArrayList<>();
                for (int i = 0; i < positions.length; i++) {
                        charArray.add(positions[i].getPieceOccupying() == null ? ' '
                                        : positions[i].getCurrentPieceChar());
                }
                return charArray.stream().map(Object::toString).collect(Collectors.joining()).toCharArray();
        }

        public boolean isNoValidPieceMoves(Character pieceChar) {
                return Stream.of(positions)
                                .filter(i -> (i.getCurrentPieceChar() == pieceChar))
                                .filter(Position::hasEmptyAdjacent)
                                .count() == 0;
        }

        public boolean findUnprotectedPieces(Character pieceChar) {
                return Stream.of(positions)
                                .filter(i -> (i.getCurrentPieceChar() == pieceChar))
                                .filter(i -> (!i.isMill()))
                                .count() != 0;
        }

        private void initBoard() {
                // initialise the board positions
                for (int i = 0; i < TOTAL_POSITION; i++) {
                        positions[i] = new Position();
                }

                // add the adjacent indexes for each position
                // outer square
                positions[0].setAdjacentPositions()
                                .setRight(positions[1])
                                .setBottom(positions[9]).build();
                positions[1].setAdjacentPositions()
                                .setLeft(positions[0])
                                .setRight(positions[2])
                                .setBottom(positions[4]).build();
                positions[2].setAdjacentPositions()
                                .setLeft(positions[1])
                                .setBottom(positions[14]).build();
                positions[9].setAdjacentPositions()
                                .setTop(positions[0])
                                .setRight(positions[10])
                                .setBottom(positions[21]).build();
                positions[14].setAdjacentPositions()
                                .setTop(positions[2])
                                .setLeft(positions[13])
                                .setBottom(positions[23]).build();
                positions[21].setAdjacentPositions()
                                .setTop(positions[9])
                                .setRight(positions[22]).build();
                positions[22].setAdjacentPositions()
                                .setTop(positions[19])
                                .setLeft(positions[21])
                                .setRight(positions[23]).build();
                positions[23].setAdjacentPositions()
                                .setTop(positions[14])
                                .setLeft(positions[22]).build();
                // middle square
                positions[3].setAdjacentPositions()
                                .setRight(positions[4])
                                .setBottom(positions[10]).build();
                positions[4].setAdjacentPositions()
                                .setTop(positions[1])
                                .setLeft(positions[3])
                                .setRight(positions[5])
                                .setBottom(positions[7]).build();
                positions[5].setAdjacentPositions()
                                .setLeft(positions[4])
                                .setBottom(positions[13]).build();
                positions[10].setAdjacentPositions()
                                .setTop(positions[3])
                                .setLeft(positions[9])
                                .setRight(positions[11])
                                .setBottom(positions[18]).build();
                positions[13].setAdjacentPositions()
                                .setTop(positions[5])
                                .setLeft(positions[12])
                                .setRight(positions[14])
                                .setBottom(positions[20]).build();
                positions[18].setAdjacentPositions()
                                .setTop(positions[10])
                                .setRight(positions[19]).build();
                positions[19].setAdjacentPositions()
                                .setTop(positions[16])
                                .setRight(positions[20])
                                .setLeft(positions[18])
                                .setBottom(positions[22]).build();
                positions[20].setAdjacentPositions()
                                .setTop(positions[13])
                                .setLeft(positions[19]).build();
                // inner square
                positions[6].setAdjacentPositions()
                                .setRight(positions[7])
                                .setBottom(positions[11]).build();
                positions[7].setAdjacentPositions()
                                .setTop(positions[4])
                                .setLeft(positions[6])
                                .setRight(positions[8]).build();
                positions[8].setAdjacentPositions()
                                .setLeft(positions[7])
                                .setBottom(positions[12]).build();
                positions[11].setAdjacentPositions()
                                .setTop(positions[6])
                                .setLeft(positions[10])
                                .setBottom(positions[15]).build();
                positions[12].setAdjacentPositions()
                                .setTop(positions[8])
                                .setRight(positions[13])
                                .setBottom(positions[17]).build();
                positions[15].setAdjacentPositions()
                                .setTop(positions[11])
                                .setRight(positions[16]).build();
                positions[16].setAdjacentPositions()
                                .setLeft(positions[15])
                                .setRight(positions[17])
                                .setBottom(positions[19]).build();
                positions[17].setAdjacentPositions()
                                .setTop(positions[12])
                                .setLeft(positions[16]).build();
        }
}
