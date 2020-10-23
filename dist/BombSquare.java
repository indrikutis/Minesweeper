import java.util.Arrays;
import java.util.List;

public class BombSquare extends GameSquare {

    private GameBoard board; // Object reference to the GameBoard this square is part of.
    private boolean hasBomb; // State if a square has a bomb or not.
    private boolean visited; // State if a square was visited or not.
    private boolean flag; // State if a square has a flag on or not.

    public static final int MINE_PROBABILITY = 10;

    /**
     * Create a new BombSquare, which can be placed on a GameBoard.
     * 
     * @param x     the x co-ordinate of this square on the game board.
     * @param y     the y co-ordinate of this square on the game board.
     * @param board object reference to the GameBoard this square is part of
     */
    public BombSquare(int x, int y, GameBoard board) {
        super(x, y, "images/blank.png");

        this.board = board;
        this.hasBomb = ((int) (Math.random() * MINE_PROBABILITY)) == 0;
        this.visited = false;
        this.flag = false;
    }

    /**
     * Initiated by the left mouse click. Sets the image of the game square: empty,
     * bomb, or a number of surrounding bombs in surrounding game squares.
     */
    @Override
    public void leftClicked() {
        if (this.hasBomb) {
            this.setImage("images/bomb.png");
            System.out.println("Game End");
        } else {
            displaySquares(this);
        }
    }

    /**
     * 
     * @param square the square on the game board.
     */
    public void displaySquares(GameSquare square) {
        int bombCount = 0;

        int x = square.getXLocation();
        int y = square.getYLocation();

        ((BombSquare) square).setVisited(true);

        GameSquare squares[] = new BombSquare[9];

        // Surrounding game squares.
        squares[0] = this.board.getSquareAt(x, y);
        squares[1] = this.board.getSquareAt(x - 1, y - 1);
        squares[2] = this.board.getSquareAt(x - 1, y);
        squares[3] = this.board.getSquareAt(x - 1, y + 1);
        squares[4] = this.board.getSquareAt(x, y + 1);
        squares[5] = this.board.getSquareAt(x, y - 1);
        squares[6] = this.board.getSquareAt(x + 1, y - 1);
        squares[7] = this.board.getSquareAt(x + 1, y);
        squares[8] = this.board.getSquareAt(x + 1, y + 1);

        // Counts the number of surrounding bombs.
        for (int i = 0; i < squares.length; i++) {
            if (squares[i] != null && ((BombSquare) squares[i]).hasBomb) {
                bombCount++;
            }
        }

        // Checks the surrounding game squares of an empty game square.
        if (bombCount == 0) {
            square.setImage("images/0.png");
            for (int i = 0; i < squares.length; i++) {
                if (squares[i] != null) {
                    if (!((BombSquare) squares[i]).getVisited()) {
                        displaySquares(squares[i]);
                    }
                }
            }
        } else {
            square.setImage("images/" + bombCount + ".png");
        }
    }

    /**
     * Initiated by the right mouse click. Sets the flag on the square by the rules
     * of the game. Flag can only be set on blank squares.
     */
    @Override
    public void rightClicked() {

        boolean visited = this.getVisited();
        boolean flag = this.getFlag();

        if (!visited && !flag) {
            setImage("images/flag.png");
            this.setFlag(true);
        } else if (!visited && flag) {
            setImage("images/blank.png");
            this.setFlag(false);
        }
    }

    /**
     * 
     * @return if the square was visited or not.
     */
    public boolean getVisited() {
        return this.visited;
    }

    /**
     * 
     * @param visited sets the state if the square was visited or not.
     */
    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    /**
     * 
     * @return if the square has a flag on or not.
     */
    public boolean getFlag() {
        return this.flag;
    }

    /**
     * 
     * @param flag sets the state if the square has a flag on or not.
     */
    public void setFlag(boolean flag) {
        this.flag = flag;
    }

}
