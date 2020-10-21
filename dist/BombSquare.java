public class BombSquare extends GameSquare {

    private GameBoard board; // Object reference to the GameBoard this square is part of.
    private boolean hasBomb; // True if this square contains a bomb. False otherwise.
    private int visitedCells[][] = new int[30][30]; // Detects which cells were checked.

    public static final int MINE_PROBABILITY = 10;

    public BombSquare(int x, int y, GameBoard board) {
        super(x, y, "images/blank.png");

        this.board = board;
        this.hasBomb = ((int) (Math.random() * MINE_PROBABILITY)) == 0;

    }

    @Override
    public void leftClicked() {

        if (this.hasBomb) {
            this.setImage("images/bomb.png");
            System.out.println("Game End");
        } else {

            for (int i = 0; i < visitedCells.length; i++) {
                for (int k = 0; k < visitedCells.length; k++) {
                    this.visitedCells[i][k] = 0;
                }
            }

            recursion(this);
        }
    }

    public void recursion(GameSquare square) {
        int bombCount = 0;

        int x = square.getXLocation();
        int y = square.getYLocation();
        this.visitedCells[x][y] = 1;

        GameSquare squares[] = new BombSquare[9];

        squares[0] = this.board.getSquareAt(x, y);
        squares[1] = this.board.getSquareAt(x - 1, y - 1);
        squares[2] = this.board.getSquareAt(x - 1, y);
        squares[3] = this.board.getSquareAt(x - 1, y + 1);
        squares[4] = this.board.getSquareAt(x, y + 1);
        squares[5] = this.board.getSquareAt(x, y - 1);
        squares[6] = this.board.getSquareAt(x + 1, y - 1);
        squares[7] = this.board.getSquareAt(x + 1, y);
        squares[8] = this.board.getSquareAt(x + 1, y + 1);

        for (int i = 0; i < squares.length; i++) {
            if (squares[i] != null && ((BombSquare) squares[i]).getHasBomb()) {
                bombCount++;
            }
        }

        if (bombCount == 0) {
            square.setImage("images/0.png");
            for (int i = 0; i < squares.length; i++) {
                if (squares[i] != null) {
                    if (this.visitedCells[squares[i].getXLocation()][squares[i].getYLocation()] != 1) {
                        recursion(squares[i]);
                    }
                }
            }
        } else {
            square.setImage("images/" + bombCount + ".png");
        }
    }

    @Override
    public void rightClicked() {
        this.setImage("images/flag.png");
    }

    public boolean getHasBomb() {
        return this.hasBomb;
    }
}
