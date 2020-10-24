import java.util.Arrays;
import java.util.List;

public class BombSquare extends GameSquare {

    private GameBoard board; // Object reference to the GameBoard this square is part of.
    private boolean hasBomb; // State whether square has a bomb or not.
    private boolean visited; // State whether square was visited or not.
    private boolean flag; // State whether square has a flag on or not.
    private boolean gameEnd; // State whether game has ended.

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
        this.gameEnd = false;
    }

    /**
     * Initiated by the left mouse click. Sets the image of the game square: empty,
     * bomb, or a number of bombs in surrounding game squares.
     */
    @Override
    public void leftClicked() {

        if (!this.gameEnd) {

            if (this.hasBomb) {
                System.out.println("Game End");
                gameEnd();

                // Reveals all bombs.
                int i = 0, j = 0;
                while (this.board.getSquareAt(i, j) != null) {
                    while (this.board.getSquareAt(i, j) != null) {

                        BombSquare bombSquare = ((BombSquare) this.board.getSquareAt(i, j));

                        if (!bombSquare.getVisited()) {
                            if (bombSquare.getHasBomb()) {
                                bombSquare.setImage("images/bomb.png");
                            } else {
                                bombSquare.setImage("images/blank.png");
                            }
                        }
                        j++;
                    }
                    i++;
                    j = 0;
                }
            } else {
                displaySquares(this);
            }
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

        // Surrounding squares.
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

        // Counts the number of surrounding bombs.
        for (int i = 0; i < squares.length; i++) {
            if (squares[i] != null && ((BombSquare) squares[i]).hasBomb) {
                bombCount++;
            }
        }

        // Checks the surrounding game squares.
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

        if (!this.gameEnd) {

            boolean visited = this.getVisited();
            boolean flag = this.getFlag();

            if (!visited && !flag) {
                this.setImage("images/flag.png");
                this.setFlag(true);
            } else if (!visited && flag) {
                this.setImage("images/blank.png");
                this.setFlag(false);
            }
        }

    }

    /**
     * 
     * @return if the square was visited.
     */
    public boolean getVisited() {
        return this.visited;
    }

    /**
     * 
     * @param visited sets the state if the square was visited.
     */
    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    /**
     * 
     * @return if the square has a flag on.
     */
    public boolean getFlag() {
        return this.flag;
    }

    /**
     * 
     * @param flag sets the state if the square has a flag on.
     */
    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    /**
     * 
     * @return if the square has a bomb.
     */
    public boolean getHasBomb() {
        return this.hasBomb;
    }

    /**
     * 
     * @param gameEnd sets the state of the game on the square.
     */
    public void setGameEnd(boolean gameEnd) {
        this.gameEnd = gameEnd;
    }

    /**
     * 
     * @return the sate of the game.
     */
    public boolean getGameEnd() {
        return this.gameEnd;
    }

    /**
     * Sets the game end to all squares on the board.
     */
    public void gameEnd() {

        int i = 0, j = 0;
        while (this.board.getSquareAt(i, j) != null) {
            while (this.board.getSquareAt(i, j) != null) {

                BombSquare bombSquare = ((BombSquare) this.board.getSquareAt(i, j));

                bombSquare.setGameEnd(true);
                j++;
            }
            i++;
            j = 0;
        }
    }
}
