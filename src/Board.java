import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created with IntelliJ IDEA.
 * User: Uncalled For
 * Date: 18/08/12
 * Time: 14:25
 */
public class Board extends JPanel {

    private int x, y, mines;
    private Cell[][] boardCells;


    public Board(int x, int y, int mines) {
        this.x = x;
        this.y = y;
        this.mines = mines;
        this.boardCells = new Cell[x][y];
        this.setLayout(new GridLayout(x, y));
    }

    private Board getThis() {
        return this;
    }

    public void resetBoard() {
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                boardCells[i][j] = new Cell(i, j);
                boardCells[i][j].addMouseListener(cellListener);
                add(boardCells[i][j]);
            }
        }

        // generates "mines" number of mines on the board
        int mineCount = 0;
        while (mineCount != mines) {
            for (int i = 0; i < x; i++) {
                for (int j = 0; j < y; j++) {
                    if (mineCount == mines) {
                        break;
                    }
                    if (Math.random() < 0.2 && !boardCells[i][j].isMine()) {
                        boardCells[i][j].setMine();
                        mineCount++;
                    }
                }

            }

        }
    }

    private int countCloseMines(Cell c) {
        int total = 0;
        int x = c.getx();
        int y = c.gety();
        int[] xposs = {1, -1, 0, 0, 1, 1, -1, -1};
        int[] yposs = {0, 0, 1, -1, 1, -1, 1, -1};

        for (int i = 0; i < 8; i++) {
            int newx = x + xposs[i];
            int newy = y + yposs[i];
            if ((newx >= 0 && newx < 8) && (newy >= 0 && newy < 8)) {
                if (boardCells[newx][newy].isMine()) {
                    total++;
                }
            }
        }
        return total;
    }

    private void findisland(Cell c) {
        int x = c.getx();
        int y = c.gety();

        int[] xposs = {1, -1, 0, 0, 1, 1, -1, -1};
        int[] yposs = {0, 0, 1, -1, 1, -1, 1, -1};


        if (!c.isRevealed() && countCloseMines(c) == 0) {
            c.reveal();

            for (int i = 0; i < 8; i++) {
                int newx = x + xposs[i];
                int newy = y + yposs[i];
                if ((newx >= 0 && newx < 8) && (newy >= 0 && newy < 8)) {
                    findisland(boardCells[newx][newy]);
                }
            }

        } else {
            c.reveal();
            int close = countCloseMines(c);
            if (close != 0) {
                c.setText(Integer.toString(close));
            }
            return;
        }
    }

    private void gameOver() {
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                Cell c = boardCells[i][j];
                if (!c.isRevealed()) {
                    c.reveal();
                    if (!c.isMine()) {
                        int close = countCloseMines(c);
                        if (close != 0) {
                            c.setText(Integer.toString(close));
                        }
                    }
                }
            }
        }


    }

    private boolean hasWon() {
        boolean won = true;
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                Cell c = boardCells[i][j];
                if (!c.isRevealed() && !c.isMine()) {
                    return false;
                }
            }
        }
        return won;
    }

    private MouseListener cellListener = new MouseListener() {
        public void mouseClicked(MouseEvent e) {
            Cell c = (Cell) e.getComponent();
            if (c.isEnabled()) {
                if (e.getButton() == MouseEvent.BUTTON1) { // if left click


                    if (c.isMine()) {
                        gameOver();
                        JOptionPane.showMessageDialog(getThis(), "Better luck next time!", "Game Over!", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }

                    int close = countCloseMines(c);
                    if (close == 0) {
                        findisland(c);
                    } else {
                        c.setText(Integer.toString(close));
                    }

                    c.reveal();
                    if (hasWon()) {
                        gameOver();
                        JOptionPane.showMessageDialog(getThis(), "Winrar is you!", "Congratulations!", JOptionPane.INFORMATION_MESSAGE);
                    }

                } else { // if right click
                    c.flag();
                }
            }
        }

        public void mousePressed(MouseEvent e) {
        }

        public void mouseReleased(MouseEvent e) {
        }

        public void mouseEntered(MouseEvent e) {
        }

        public void mouseExited(MouseEvent e) {
        }
    };
}
