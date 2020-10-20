public class BombSquare extends GameSquare {
    private GameBoard board; // Object reference to the GameBoard this square is part of.
    private boolean hasBomb; // True if this squre contains a bomb. False otherwise.

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
            recursion(this.getXLocation(), this.getYLocation());
        }
    }

    @Override
    public void rightClicked() {
        this.setImage("images/flag.png");

    }

    public boolean getHasBomb() {
        return this.hasBomb;
    }

    public boolean getBomb(int x, int y) {
        return ((BombSquare) this.board.getSquareAt(x, y)).getHasBomb();
    }

    public void recursion(int x, int y) {
        int bombCount = 0;

        boolean bombsArray[] = new boolean[9];

        bombsArray[0] = getBomb(x - 1, y - 1);
        bombsArray[1] = getBomb(x - 1, y);
        bombsArray[2] = getBomb(x - 1, y + 1);
        bombsArray[3] = getBomb(x, y + 1);
        bombsArray[4] = getBomb(x, y - 1);
        bombsArray[5] = getBomb(x + 1, y - 1);
        bombsArray[6] = getBomb(x + 1, y);
        bombsArray[7] = getBomb(x + 1, y + 1);
        bombsArray[8] = getBomb(x, y);

        for (int i = 0; i < bombsArray.length; i++) {
            if (bombsArray[i]) {
                bombCount++;
            }
        }

        switch (bombCount) {
            case 0:
                this.setImage("images/0.png");
                break;
            case 1:
                this.setImage("images/1.png");
                break;
            case 2:
                this.setImage("images/2.png");
                break;
            case 3:
                this.setImage("images/3.png");
                break;
            case 4:
                this.setImage("images/4.png");
                break;
            case 5:
                this.setImage("images/5.png");
                break;
            case 6:
                this.setImage("images/6.png");
                break;
            case 7:
                this.setImage("images/7.png");
                break;
            case 8:
                this.setImage("images/8.png");
                break;
            case 9:
                this.setImage("images/9.png");
                break;

        }
    }

}
