public class Position {
    // Adjacent Positions Stored as Nested Class
    private AdjacentPositions adjacentPositions;
    private Piece pieceOccupying;
    private int positionNumber;

    public Position(int positionNumber) {
        pieceOccupying = null;
        this.positionNumber = positionNumber;

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
        return (pieceOccupying == null);
    }

    public void setEmpty() {
        pieceOccupying = null;
    }

    public boolean isAdjacentToThisPosition(Position queryPosition) {
        return adjacentPositions.contains(queryPosition);
    }

    public int getPositionNumber() {
        return positionNumber;
    }

    public boolean isMill() {
        Character pieceChar = getPieceOccupying().getDisplayChar();
        boolean isMill = false;
        // Check if part of vertical mill.
        isMill |= pieceChar == adjacentPositions.topMostChar()
                && !(pieceChar == adjacentPositions.bottomMostChar())
                || pieceChar == adjacentPositions.bottomChar()
                        && (pieceChar == adjacentPositions.topChar()
                                || pieceChar == adjacentPositions.bottomMostChar());
        // Check if part of horizontal mill.
        isMill |= pieceChar == adjacentPositions.leftMostChar()
                && !(pieceChar == adjacentPositions.rightMostChar())
                || pieceChar == adjacentPositions.rightChar()
                        && (pieceChar == adjacentPositions.leftChar()
                                || pieceChar == adjacentPositions.rightMostChar());
        return isMill;
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
            if (pos == null) {
                return ' ';
            }
            return pos.getPieceOccupying() != null ? pos.getPieceOccupying().getDisplayChar() : ' ';
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
