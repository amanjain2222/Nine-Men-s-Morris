import java.util.stream.Stream;

public class Position {
    // Adjacent Positions Stored as Nested Class
    private AdjacentPositions adjacentPositions;
    private Piece pieceOccupying;

    public Character getCurrentPieceChar() {
        return pieceOccupying != null ? pieceOccupying.getDisplayChar() : ' ';
    }

    public Piece getPieceOccupying() {
        return pieceOccupying;
    }

    public void setPieceOccupying(Piece piece) {
        pieceOccupying = piece;
    }

    public Piece popPieceOccupying() {
        Piece piece = pieceOccupying;
        pieceOccupying = null;
        return piece;
    }

    public boolean isEmpty() {
        return (pieceOccupying.equals(null));
    }

    public void setEmpty() {
        pieceOccupying = null;
    }

    public boolean isMill() {
        Character pieceChar = getCurrentPieceChar();
        boolean isMill = false;
        // Check if part of vertical mill.
        isMill |= (pieceChar.equals(adjacentPositions.topMostChar())
                && !pieceChar.equals(adjacentPositions.bottomMostChar())
                || pieceChar.equals(adjacentPositions.bottomChar()))
                && (pieceChar.equals(adjacentPositions.topChar())
                        || pieceChar.equals(adjacentPositions.bottomMostChar()));
        // Check if part of horizontal mill.
        isMill |= (pieceChar.equals(adjacentPositions.leftMostChar())
                && !pieceChar.equals(adjacentPositions.rightMostChar())
                || pieceChar.equals(adjacentPositions.rightChar()))
                && (pieceChar.equals(adjacentPositions.leftChar())
                        || pieceChar.equals(adjacentPositions.rightMostChar()));
        return isMill;
    }

    public boolean isAdjacentToThisPosition(Position queryPosition) {
        return adjacentPositions.contains(queryPosition);
    }

    public boolean hasEmptyAdjacent() {
        return Stream
                .of(adjacentPositions.top, adjacentPositions.bottom, adjacentPositions.left, adjacentPositions.right)
                .filter(i -> (i != null))
                .anyMatch(i -> (i.getPieceOccupying().equals(null)));
    }

    public AdjacentPositions.AdjacentPositionsBuilder setAdjacentPositions() {
        return AdjacentPositions.getAdjacentPositionsBuilder(this);
    }

    public static class AdjacentPositions {
        private final Position top;
        private final Position bottom;
        private final Position left;
        private final Position right;

        private AdjacentPositions(Position top, Position bottom, Position left, Position right) {
            this.top = top;
            this.bottom = bottom;
            this.left = left;
            this.right = right;
        }

        private Character getPosChar(Position pos) {
            if (pos.equals(null)) {
                return ' ';
            }
            return pos.getPieceOccupying() != null ? pos.getCurrentPieceChar() : ' ';
        }

        private Character topChar() {
            return getPosChar(top);
        }

        private Character bottomChar() {
            return getPosChar(bottom);
        }

        private Character leftChar() {
            return getPosChar(left);
        }

        private Character rightChar() {
            return getPosChar(right);
        }

        private Character topMostChar() {
            return top != null ? top.adjacentPositions.topChar() : ' ';
        }

        private Character bottomMostChar() {
            return bottom != null ? bottom.adjacentPositions.bottomChar() : ' ';
        }

        private Character leftMostChar() {
            return left != null ? left.adjacentPositions.leftChar() : ' ';
        }

        private Character rightMostChar() {
            return right != null ? right.adjacentPositions.rightChar() : ' ';
        }

        private boolean contains(Position pos) {
            if (pos.equals(top) || pos.equals(bottom) || pos.equals(left) || pos.equals(right))
                return true;
            return false;
        }

        private static AdjacentPositionsBuilder getAdjacentPositionsBuilder(Position parent) {
            return new AdjacentPositionsBuilder(parent);
        }

        public static class AdjacentPositionsBuilder {
            private Position top;
            private Position bottom;
            private Position left;
            private Position right;

            private Position parent;

            private AdjacentPositionsBuilder(Position parent) {
                this.parent = parent;
            }

            public AdjacentPositionsBuilder setTop(Position top) {
                this.top = top;
                return this;
            }

            public AdjacentPositionsBuilder setBottom(Position bottom) {
                this.bottom = bottom;
                return this;
            }

            public AdjacentPositionsBuilder setLeft(Position left) {
                this.left = left;
                return this;
            }

            public AdjacentPositionsBuilder setRight(Position right) {
                this.right = right;
                return this;
            }

            public void build() {
                parent.adjacentPositions = new AdjacentPositions(top, bottom, left, right);
            }
        }
    }
}
